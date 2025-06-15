package ru.slaventolo.taskssystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.slaventolo.taskssystem.DTO.TaskDto;
import ru.slaventolo.taskssystem.model.TaskEntity;

@Component
public class TaskConverter {

    @Autowired
    private ProjectConverter projectConverter;

    public TaskDto toFromEntity(TaskEntity taskEntity) {
        var taskDto = new TaskDto();
        taskDto.setId(taskEntity.getId());
        taskDto.setTaskNumber(taskEntity.getTaskNumber());
        taskDto.setProjectId(taskEntity.getProjectId());
        // Остальные поля...
        return taskDto;

    }

    public TaskEntity entityFromTo(TaskDto taskDto) {
        var taskEntity = new TaskEntity();
        taskEntity.setId(taskDto.getId());
        taskEntity.setTaskNumber(taskDto.getTaskNumber());
        taskEntity.setProjectId(taskDto.getProjectId());
        // Остальные поля...
        return taskEntity;
    }
}
