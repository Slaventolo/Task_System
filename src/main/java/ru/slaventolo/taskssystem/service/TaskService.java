package ru.slaventolo.taskssystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.slaventolo.taskssystem.DTO.TaskDto;
import ru.slaventolo.taskssystem.converter.TaskConverter;
import ru.slaventolo.taskssystem.model.TaskEntity;
import ru.slaventolo.taskssystem.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskConverter taskConverter;

    public TaskDto saveTask(TaskDto task) {
        var taskForSave = taskConverter.entityFromTo(task);
        return taskConverter.toFromEntity(taskRepository.save(taskForSave));
    }

    public TaskDto getTaskById(UUID id) {
        var taskEntity = taskRepository.getReferenceById(id);
        return taskConverter.toFromEntity(taskEntity);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    // TODO
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    // TODO
    public TaskEntity updateTask(UUID id, TaskEntity task) {
        return taskRepository.save(task);
    }


}
