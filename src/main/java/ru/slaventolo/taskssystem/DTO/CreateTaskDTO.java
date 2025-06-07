package ru.slaventolo.taskssystem.DTO;

import ru.slaventolo.taskssystem.model.Task;


public class CreateTaskDTO {

    private int taskNumber;
    private String title, projectName, taskType, status, description,  assignee;

    public Task toEntity() {
        return new Task(this.getTaskNumber(),
                        this.getTitle(),
                        this.getProjectName(),
                        this.getTaskType(),
                        this.getStatus(),
                        this.getDescription(),
                        this.getAssignee());
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

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
