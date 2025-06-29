package ru.slaventolo.taskssystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slaventolo.taskssystem.Dto.TaskDto;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.model.TaskRelation;
import ru.slaventolo.taskssystem.service.TaskRelationService;
import ru.slaventolo.taskssystem.service.TaskService;

import java.util.UUID;

@RestController
public class TaskController {

    private final TaskService taskService;
    private final TaskRelationService taskRelationService;

    public TaskController(TaskService taskService, TaskRelationService taskRelationService) {
        this.taskService = taskService;
        this.taskRelationService = taskRelationService;
    }

    /**
     * Получение задачи по id
     */
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID id) {
        Task task = taskService.getTaskById(id);
        TaskRelation taskRelation = taskRelationService.getTaskRelationByParentId(id);
        return ResponseEntity.ok().body(TaskDto.fromEntity(task, taskRelation));
    }


    /**
     * Создание задачи
     */
    @PostMapping("/create_task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskDto.toEntitySaveCase();
        Task savedTask = taskService.saveTask(task);

        if (taskDto.getTaskRelation() != null) {
            UUID taskParentId = savedTask.getId();
            TaskRelation taskRelation = taskDto.toEntitySaveRelationCase(taskParentId);
            TaskRelation savedTaskRelation = taskRelationService.saveTaskRelation(taskRelation);
            return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask, savedTaskRelation));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask, null));
        }
    }


    /**
     * Удаление задачи по id
     */
    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        taskRelationService.deleteTaskRelationByParentId(id);
    }


    /**
     * Редактирование задачи по id
     */
    @PatchMapping("/update_task/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID id, @RequestBody TaskDto taskDto) {
        Task task = taskDto.toEntityUpdateCase();
        Task savedTask = taskService.updateTask(id, task);

        taskRelationService.deleteTaskRelationByParentId(id);

        if (taskDto.getTaskRelation() != null) {
            UUID taskParentId = savedTask.getId();
            TaskRelation taskRelation = taskDto.toEntitySaveRelationCase(taskParentId);
            TaskRelation savedTaskRelation = taskRelationService.saveTaskRelation(taskRelation);
            return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask, savedTaskRelation));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask, null));
        }
    }
}
