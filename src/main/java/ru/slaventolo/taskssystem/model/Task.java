package ru.slaventolo.taskssystem.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "task_number", nullable = false, updatable = false, unique = true)
    public int taskNumber;

    private String title, projectName, taskType, status, description,  assignee;

    /**
     * конструктор для тестирования
     */
    public Task( UUID id,
                 int taskNumber,
                 String title,
                 String projectName,
                 String taskType,
                 String status,
                 String description,
                 String assignee
            ) {
        this.assignee = assignee;
        this.description = description;
        this.status = status;
        this.taskType = taskType;
        this.projectName = projectName;
        this.title = title;
        this.taskNumber = taskNumber;
        this.id = id;
    }

    /**
     * констурктор для создания без передачи id и taskNumber
     */
    public Task(String title,
                String projectName,
                String taskType,
                String status,
                String description,
                String assignee) {
        this.title = title;
        this.projectName = projectName;
        this.taskType = taskType;
        this.status = status;
        this.description = description;
        this.assignee = assignee;
    }

    /**
     * констурктор для создания без передачи id
     */
    public Task(int taskNumber,
                String title,
                String projectName,
                String taskType,
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

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
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
