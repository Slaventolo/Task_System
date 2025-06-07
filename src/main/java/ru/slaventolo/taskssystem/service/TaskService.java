package ru.slaventolo.taskssystem.service;

import org.springframework.stereotype.Service;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(UUID id) {
        return taskRepository.getReferenceById(id);
    }

    // TODO
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // TODO
    public Task updateTask(UUID id, Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
