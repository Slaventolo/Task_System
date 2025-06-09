package ru.slaventolo.taskssystem.DTO;

import org.springframework.lang.NonNull;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.model.TaskType;

import java.util.UUID;

public class CreateTaskDTO {

    private int taskNumber;

    @NonNull
    private UUID projectId;

    private String title, taskType, status, description,  assignee;

    public Task toEntity() {
        return new Task(this.getTaskNumber(),
                        this.getTitle(),
                        this.getProjectId(),
                        TaskType.fromDbValue(this.taskType),
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

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
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
