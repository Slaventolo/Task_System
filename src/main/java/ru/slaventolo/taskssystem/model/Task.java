package ru.slaventolo.taskssystem.model;

import jakarta.persistence.*;
import ru.slaventolo.taskssystem.converter.CompleteByConverter;
import ru.slaventolo.taskssystem.converter.TaskStatusConverter;
import ru.slaventolo.taskssystem.converter.TaskTypeConverter;
import ru.slaventolo.taskssystem.converter.TimeSpentConverter;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "task_number", nullable = false, updatable = false)
    public int taskNumber;

    private UUID projectId;

    private String title, description,  assignee;

    @Convert(converter = TaskStatusConverter.class)
    private TaskStatus status;

    @Convert(converter = TaskTypeConverter.class)
    private TaskType taskType;

    @Column(name = "timeSpent")
    @Convert(converter = TimeSpentConverter.class)
    Duration timeSpent;

    @Column(name = "completeBy")
    @Convert(converter = CompleteByConverter.class)
    ZonedDateTime completeBy;


    /**
     * Конструктор для создания без передачи id
     */
    public Task(int taskNumber,
                String title,
                UUID projectId,
                TaskType taskType,
                TaskStatus status,
                String description,
                String assignee,
                Duration timeSpent,
                ZonedDateTime completeBy) {
        this.taskNumber = taskNumber;
        this.title = title;
        this.projectId = projectId;
        this.taskType = taskType;
        this.status = status;
        this.description = description;
        this.assignee = assignee;
        this.timeSpent = timeSpent;
        this.completeBy = completeBy;
    }

    /**
     * Конструктор для update'a без передачи id, taskNumber и projectId
     */
    public Task(String title,
                TaskType taskType,
                TaskStatus status,
                String description,
                String assignee,
                Duration timeSpent,
                ZonedDateTime completeBy) {
        this.title = title;
        this.taskType = taskType;
        this.status = status;
        this.description = description;
        this.assignee = assignee;
        this.timeSpent = timeSpent;
        this.completeBy = completeBy;
    }

    /**
     * Пустой конструктор для Spring
     */
    public Task() {}


    /**
     * Геттеры и сеттеры
     */
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

    public Duration getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Duration timeSpent) {
        this.timeSpent = timeSpent;
    }

    public ZonedDateTime getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(ZonedDateTime completeBy) {
        this.completeBy = completeBy;
    }
}
