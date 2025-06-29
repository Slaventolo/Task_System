package ru.slaventolo.taskssystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slaventolo.taskssystem.Dto.TaskDto;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.model.TaskRelation;
import ru.slaventolo.taskssystem.service.TaskRelationService;
import ru.slaventolo.taskssystem.service.TaskService;

import java.util.ArrayList;
import java.util.List;
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
        List<TaskRelation> taskRelations = taskRelationService.getTaskRelationsByParentId(id);
        return ResponseEntity.ok().body(TaskDto.fromEntity(task, taskRelations));
    }


    /**
     * Создание задачи
     */
    @PostMapping("/create_task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskDto.toEntitySaveCase();
        Task savedTask = taskService.saveTask(task);

        if (taskDto.getTaskRelations() != null) {
            UUID taskParentId = savedTask.getId();
            List<String> taskChildIds = taskDto.getTaskRelations();

            List<TaskRelation> taskRelations = new ArrayList<>();
            for (String taskChildId : taskChildIds) {
                TaskRelation taskRelation = taskDto.toEntitySaveRelationCase(taskParentId, UUID.fromString(taskChildId));
                taskRelations.add(taskRelation);
            }
            List<TaskRelation> savedTaskRelations = taskRelationService.saveTaskRelation(taskRelations);

            return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask, savedTaskRelations));
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
        taskRelationService.deleteTaskRelationsByParentId(id);
    }


    /**
     * Редактирование задачи по id
     */
    @PatchMapping("/update_task/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID id, @RequestBody TaskDto taskDto) {
        Task task = taskDto.toEntityUpdateCase();
        Task savedTask = taskService.updateTask(id, task);

        taskRelationService.deleteTaskRelationsByParentId(id);

        if (taskDto.getTaskRelations() != null) {
            UUID taskParentId = savedTask.getId();
            List<String> taskChildIds = taskDto.getTaskRelations();

            List<TaskRelation> taskRelations = new ArrayList<>();
            for (String taskChildId : taskChildIds) {
                TaskRelation taskRelation = taskDto.toEntitySaveRelationCase(taskParentId, UUID.fromString(taskChildId));
                taskRelations.add(taskRelation);
            }
            List<TaskRelation> savedTaskRelations = taskRelationService.saveTaskRelation(taskRelations);

            return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask, savedTaskRelations));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask, null));
        }
    }
}
