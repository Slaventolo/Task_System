package ru.slaventolo.taskssystem.intergationTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CreateTaskTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Test
    public void createTask_ShouldCreateTask() throws Exception {

        /**
         * Создаем проект, к которому прикрепится задача
         */
        jdbcTemplate.execute("""
        INSERT INTO project (id, name) 
        VALUES ('5fdf6d75-903a-49ad-a101-1ed2cc30cfa8', 'Test Project')
        """);

        /**
         * Создание Task + проверки полей ответа
         */
        String requestJson = """
                {
                    "title": "Test Task",
                    "projectId": "5fdf6d75-903a-49ad-a101-1ed2cc30cfa8",
                    "taskType": "error",
                    "status": "registered",
                    "description": "This is test task",
                    "assignee": "test assignee",
                    "timeSpent": "1h 30m",
                    "completeBy": "2019-02-22T11:37:58.074816+02:00"
                }
                """;


        String responseJson = mockMvc.perform(post("/create_task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();


        assertTrue(responseJson.contains("\"title\":\"Test Task\""));
        assertTrue(responseJson.contains("\"projectId\":\"5fdf6d75-903a-49ad-a101-1ed2cc30cfa8\""));
        assertTrue(responseJson.contains("\"taskType\":\"error\""));
        assertTrue(responseJson.contains("\"status\":\"registered\""));
        assertTrue(responseJson.contains("\"description\":\"This is test task\""));
        assertTrue(responseJson.contains("\"assignee\":\"test assignee\""));
        assertTrue(responseJson.contains("\"timeSpent\":\"1h 30m\""));
        assertTrue(responseJson.contains("\"completeBy\":\"22.02.2019 11:37:58 UTC+02:00\""));
        assertTrue(responseJson.contains("\"taskRelation\":null"));


        /**
         * Проверка, что Task с таким id теперь действительно существует в БД
         */
        JsonNode jsonNode = objectMapper.readTree(responseJson);
        UUID taskId = UUID.fromString(jsonNode.get("id").asText());
        Boolean taskExists = jdbcTemplate.queryForObject(
                "SELECT EXISTS (SELECT 1 FROM task WHERE id = ?)",
                Boolean.class,
                taskId
        );
        assertEquals(Boolean.TRUE, taskExists);

        /**
         * Удаление ранее созданных данных
         */
        jdbcTemplate.update("DELETE FROM task WHERE id = ?", taskId);
        jdbcTemplate.update("DELETE FROM project WHERE id = '5fdf6d75-903a-49ad-a101-1ed2cc30cfa8'");

    }


}
