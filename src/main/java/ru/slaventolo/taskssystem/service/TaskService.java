package ru.slaventolo.taskssystem.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjectService projectService;

    public TaskService(TaskRepository taskRepository, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
    }


    public Task saveTask(Task task) {
        if (projectService.getProjectById(task.getProjectId()) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project not found");
        }


        Integer maxNumber;
        try {
            maxNumber = this.taskRepository.findMaxTaskNumberWithinProject(task.getProjectId());
        } catch (Exception e) {
            maxNumber = null;
        }

        int nextNumber = (maxNumber == null || maxNumber == 0) ? 1 : maxNumber + 1;
        task.setTaskNumber(nextNumber);

        return taskRepository.save(task);
    }


    public Task getTaskById(UUID id) {
        return taskRepository.findById(id).orElse(null);
    }


    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }


    public Task updateTask(UUID id, Task task) {
        Task existingTask = getTaskById(id);
        if (existingTask == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        } else {
            existingTask.setTitle(task.getTitle());
            existingTask.setTaskType(task.getTaskType());
            existingTask.setStatus(task.getStatus());
            existingTask.setDescription(task.getDescription());
            existingTask.setAssignee(task.getAssignee());
            existingTask.setTimeSpent(task.getTimeSpent());
            existingTask.setCompleteBy(task.getCompleteBy());
        }
        return taskRepository.save(existingTask);
    }


    // TODO
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}
