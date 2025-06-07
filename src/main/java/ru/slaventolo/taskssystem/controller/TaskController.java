package ru.slaventolo.taskssystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.slaventolo.taskssystem.DTO.CreateTaskDTO;
import ru.slaventolo.taskssystem.DTO.GetTaskDTO;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.service.TaskService;

import java.util.UUID;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

/*    @RequestMapping(value = {"/task", "/task/"}, method = RequestMethod.GET)
    public ResponseEntity<Task> task(Model model) {
        Task task = new Task(UUID.fromString("3fdf6d75-903a-49ad-a101-1ed2cc30cfa8"), 1, "Заголовок",
                "Газпром", "Nonerror", "in progress", "Описание", "Ivan");
        return ResponseEntity.ok().body(task);
    }*/

    /**
     * Получение задачи по id
     */
    @GetMapping("/task/{id}")
    public ResponseEntity<GetTaskDTO> getTask(@PathVariable UUID id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok().body(GetTaskDTO.fromEntity(task));
    }


    /**
     * Создание задачи
     */
    @PostMapping("/create_task")
    public ResponseEntity<GetTaskDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO) {
        Task task = createTaskDTO.toEntity();
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetTaskDTO.fromEntity(savedTask));
    }


    /**
     * Удаление задачи по id
     */
    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}
