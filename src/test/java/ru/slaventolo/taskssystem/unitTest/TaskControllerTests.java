package ru.slaventolo.taskssystem.unitTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.slaventolo.taskssystem.Dto.TaskDto;
import ru.slaventolo.taskssystem.controller.TaskController;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.model.TaskStatus;
import ru.slaventolo.taskssystem.model.TaskType;
import ru.slaventolo.taskssystem.service.TaskService;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTests {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void createTask_ShouldReturnCreatedTask() {

        TaskDto requestDto = new TaskDto();
        requestDto.setTitle("Test Task");
        requestDto.setProjectId(UUID.fromString("b746b35a-149a-47cb-a27c-01c941f29867"));
        requestDto.setTaskType("task");
        requestDto.setStatus("in_progress");
        requestDto.setDescription("Test Description");
        requestDto.setAssignee("Some Assignee");
        requestDto.setTimeSpent("1h 30m");
        requestDto.setCompleteBy("2019-02-22T11:37:58.074816+02:00");

        Task savedTask = new Task();
        savedTask.setId(UUID.randomUUID());
        savedTask.setTaskType(TaskType.TASK);
        savedTask.setStatus(TaskStatus.IN_PROGRESS);
        savedTask.setTimeSpent(Duration.ofHours(1).plusMinutes(30));
        savedTask.setCompleteBy(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));

        when(taskService.saveTask(any(Task.class))).thenReturn(savedTask);

        ResponseEntity<TaskDto> response = taskController.createTask(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(taskService, times(1)).saveTask(any(Task.class));
    }
}
