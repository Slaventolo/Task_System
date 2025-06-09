package ru.slaventolo.taskssystem.model;

import jakarta.persistence.*;
import ru.slaventolo.taskssystem.converter.TaskStatusConverter;
import ru.slaventolo.taskssystem.converter.TaskTypeConverter;

import java.util.UUID;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "task_number", nullable = false, updatable = false, unique = true)
    public int taskNumber;

    private UUID projectId;

    private String title, description,  assignee;

    @Convert(converter = TaskStatusConverter.class)
    private TaskStatus status;

    @Convert(converter = TaskTypeConverter.class)
    private TaskType taskType;

    /**
     * Констурктор для создания без передачи id
     */
    public Task(int taskNumber,
                String title,
                UUID projectId,
                TaskType taskType,
                TaskStatus status,
                String description,
                String assignee) {
        this.taskNumber = taskNumber;
        this.title = title;
        this.projectId = projectId;
        this.taskType = taskType;
        this.status = status;
        this.description = description;
        this.assignee = assignee;
    }

    /**
     * Пустой конструктор для Spring
     */
    public Task() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
