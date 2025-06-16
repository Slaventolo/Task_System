package ru.slaventolo.taskssystem.DTO;

import org.springframework.lang.NonNull;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.model.TaskStatus;
import ru.slaventolo.taskssystem.model.TaskType;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CreateTaskDTO {

    private int taskNumber;

    @NonNull
    private UUID projectId;

    private String title, taskType, status, description,  assignee;

    private String timeSpent;
    private String completeBy;


    public Task toEntity() {
        return new Task(this.getTaskNumber(),
                        this.getTitle(),
                        this.getProjectId(),
                        TaskType.fromDbValue(this.taskType),
                        TaskStatus.fromDbValue(this.status),
                        this.getDescription(),
                        this.getAssignee(),
                        this.parseTimeSpent(this.timeSpent),
                        this.parseZoneDateTime(this.completeBy)
        );
    }

    /**
     * Парсинг входящей строки со временем в Duration
     */
    public Duration parseTimeSpent(String input) {
        if (input.contains(" ")) {
            String[] parts = input.split(" ");
            int hours = Integer.parseInt(parts[0].substring(0, parts[0].indexOf("h")));
            int minutes = Integer.parseInt(parts[1].substring(0, parts[1].indexOf("m")));
            System.out.println(hours + "h " + minutes + "m");
            System.out.println(Duration.ofHours(hours).plusMinutes(minutes).toString());
            return Duration.ofHours(hours).plusMinutes(minutes);
        } else if (input.contains("h")) {
            int hours = Integer.parseInt(input.substring(0, input.length() - 1));
            return Duration.ofHours(hours);
        } else if (input.contains("m")) {
            int minutes = Integer.parseInt(input.substring(0, input.length() - 1));
            return Duration.ofMinutes(minutes);
        }
        return Duration.ZERO;
    }

    /**
     * Парсинг входящей строки в виде timestamp в значение ZonedDateTime
     */
    public ZonedDateTime parseZoneDateTime(String input) {
        if (input == null || input == "") {
            return null;
        }
        return ZonedDateTime.parse(input);
    }


    /**
     * Геттеры и сеттеры
     */
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
