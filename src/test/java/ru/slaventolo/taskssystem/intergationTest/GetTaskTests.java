package ru.slaventolo.taskssystem.intergationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GetTaskTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void getTask_ShouldReturnTask() throws Exception {

        UUID taskId = UUID.fromString("ee034e54-0395-4a1f-a5d7-f42f2f9f2b29");
        UUID projectId = UUID.fromString("a81216db-0ba7-4ad9-a1fe-027fdb34a5de");

        /**
         * Создаем проект, к которому прикрепится задача
         */
        jdbcTemplate.update("""
        INSERT INTO project (id, name) 
        VALUES (?, 'Test Project')
        """, projectId);

        /**
         * Создаём задачу
         */
        jdbcTemplate.update("""
            INSERT INTO task (id, task_number, title, project_id, task_type, status, 
                            description, assignee, time_spent, complete_by) 
            VALUES (?, 1, 'Заголовок', ?, 'error', 'in_progress', 'Описание', 'Ivan', 600, '2019-02-22 12:37:58.074')
            """, taskId, projectId);

        String responseJson = mockMvc.perform(get("/task/{id}", taskId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(responseJson.contains("\"title\":\"Заголовок\""));
        assertTrue(responseJson.contains("\"projectId\":\"a81216db-0ba7-4ad9-a1fe-027fdb34a5de\""));
        assertTrue(responseJson.contains("\"taskType\":\"error\""));
        assertTrue(responseJson.contains("\"status\":\"in_progress\""));
        assertTrue(responseJson.contains("\"description\":\"Описание\""));
        assertTrue(responseJson.contains("\"assignee\":\"Ivan\""));
        assertTrue(responseJson.contains("\"timeSpent\":\"10h\""));
        assertTrue(responseJson.contains("\"taskRelation\":[]"));
        // TODO подумать над assert для completeBy, там так влоб не получается

        // Если убираем @Transactional
        //jdbcTemplate.update("DELETE FROM task WHERE id = ?", taskId);
        //jdbcTemplate.update("DELETE FROM project WHERE id = ?", projectId);
    }
}
