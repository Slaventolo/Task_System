package ru.slaventolo.taskssystem.unitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.slaventolo.taskssystem.Dto.TaskDto;
import ru.slaventolo.taskssystem.model.Task;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TaskDtoTests {

    @Test
    public void test_ToEntitySaveCase() {
        TaskDto requestDto = new TaskDto();
        requestDto.setTitle("Test Task");
        requestDto.setProjectId(UUID.fromString("b746b35a-149a-47cb-a27c-01c941f29867"));
        requestDto.setTaskType("task");
        requestDto.setStatus("in_progress");
        requestDto.setDescription("Test Description");
        requestDto.setAssignee("Some Assignee");
        requestDto.setTimeSpent("1h 30m");
        requestDto.setCompleteBy("2019-02-22T11:37:58.074816+02:00");

        Task task = requestDto.toEntitySaveCase();

        Assertions.assertEquals("Test Task", task.getTitle());
        Assertions.assertEquals("b746b35a-149a-47cb-a27c-01c941f29867", task.getProjectId().toString());
        Assertions.assertEquals("task", task.getTaskType().toString().toLowerCase());
        Assertions.assertEquals("in_progress", task.getStatus().toString().toLowerCase());
        Assertions.assertEquals("Test Description", task.getDescription());
        Assertions.assertEquals("Some Assignee", task.getAssignee());
        Assertions.assertEquals("1h 30m", TaskDto.formatDuration(task));
        Assertions.assertEquals("22.02.2019 11:37:58 UTC+02:00", TaskDto.formatZoneDateTime(task));
    }
}
