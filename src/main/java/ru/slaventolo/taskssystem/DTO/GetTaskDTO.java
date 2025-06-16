package ru.slaventolo.taskssystem.DTO;

import org.springframework.lang.NonNull;
import ru.slaventolo.taskssystem.model.Task;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

public class GetTaskDTO {
    private UUID id;

    private int taskNumber;

    @NonNull
    private UUID projectId;

    private String title, taskType, status, description,  assignee;

    private String timeSpent;
    private String completeBy;

    public static GetTaskDTO fromEntity(Task task) {
        GetTaskDTO dto = new GetTaskDTO();
        dto.setId(task.getId());
        dto.setTaskNumber(task.getTaskNumber());
        dto.setTitle(task.getTitle());
        dto.setProjectId(task.getProjectId());
        dto.taskType = task.getTaskType().getDbValue();
        dto.status = task.getStatus().getDbValue();
        dto.setDescription(task.getDescription());
        dto.setAssignee(task.getAssignee());
        dto.setTimeSpent(GetTaskDTO.formatDuration(task));
        dto.setCompleteBy(GetTaskDTO.formatZoneDateTime(task));
        return dto;
    }

    /**
     * Возврат значения timeSpent в виде строки вида "10h 30m"
     */
    public static String formatDuration(Task task) {
        Duration duration = task.getTimeSpent();
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return minutes > 0 ? hours + "h " + minutes + "m" : hours + "h";
    }

    /**
     * Возврат значения completeBy в виде строки "02.01.2015 UTC+02:00"
     */
    public static String formatZoneDateTime(Task task) {
        String dbZonedDateTime = task.getCompleteBy().toString();
        String date = dbZonedDateTime.substring(8, 10)+ "." +
                dbZonedDateTime.substring(5, 7) + "." +
                dbZonedDateTime.substring(0, 4);
        String time = dbZonedDateTime.substring(11, 19);
        String zone = dbZonedDateTime.substring(26, 32);
        return date + " " + time + " UTC" + zone;
    }


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

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(String completeBy) {
        this.completeBy = completeBy;
    }
}
