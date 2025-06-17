package ru.slaventolo.taskssystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slaventolo.taskssystem.DTO.TaskDto;
import ru.slaventolo.taskssystem.DTO.UpdateTaskDTO;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.service.TaskService;

import java.util.UUID;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Получение задачи по id
     */
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok().body(TaskDto.fromEntity(task));
    }


    /**
     * Создание задачи
     */
    @PostMapping("/create_task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskDto.toEntity();
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask));
    }


    /**
     * Удаление задачи по id
     */
    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }


    /**
     * Редактирование задачи по id   TODO
     */
    @PatchMapping("/update_task/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody UpdateTaskDTO updateTaskDTO, @PathVariable UUID id) {
        return null;
    }
}
