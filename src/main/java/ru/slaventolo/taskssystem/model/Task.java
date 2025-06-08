package ru.slaventolo.taskssystem.model;

import jakarta.persistence.*;
import ru.slaventolo.taskssystem.converter.TaskTypeConverter;

import java.util.UUID;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "task_number", nullable = false, updatable = false, unique = true)
    public int taskNumber;

    private String title, projectName, status, description,  assignee;

    @Convert(converter = TaskTypeConverter.class)
    private TaskType taskType;

    /**
     * констурктор для создания без передачи id
     */
    public Task(int taskNumber,
                String title,
                String projectName,
                TaskType taskType,
                String status,
                String description,
                String assignee) {
        this.taskNumber = taskNumber;
        this.title = title;
        this.projectName = projectName;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectprojectName(String project_name) {
        this.projectName = project_name;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
