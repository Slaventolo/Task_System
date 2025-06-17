package ru.slaventolo.taskssystem.Dto;

import org.springframework.lang.NonNull;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.model.TaskStatus;
import ru.slaventolo.taskssystem.model.TaskType;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

public class TaskDto {
    private UUID id;

    private int taskNumber;

    @NonNull
    private UUID projectId;

    private String title, taskType, status, description,  assignee;

    private String timeSpent;
    private String completeBy;

    /**
     * Превращение полей из запроса в поля сущности Task при создании
     */
    public Task toEntitySaveCase() {
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
     * Превращение полей из запроса в поля сущности Task при update'е
     */
    public Task toEntityUpdateCase() {
        return new Task(this.getTitle(),
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
     * Строка приходит в виде "2019-02-22T11:37:58.074816+02:00[Africa/Cairo]"
     * или "2019-02-22T11:37:58.074816+02:00"
     */
    public ZonedDateTime parseZoneDateTime(String input) {
        if (input == null || input == "") {
            return null;
        }
        return ZonedDateTime.parse(input);
    }

    /**
     * Возврат полей сущности Task в поля для ответа
     */
    public static TaskDto fromEntity(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTaskNumber(task.getTaskNumber());
        dto.setTitle(task.getTitle());
        dto.setProjectId(task.getProjectId());
        dto.taskType = task.getTaskType().getDbValue();
        dto.status = task.getStatus().getDbValue();
        dto.setDescription(task.getDescription());
        dto.setAssignee(task.getAssignee());
        dto.setTimeSpent(TaskDto.formatDuration(task));
        dto.setCompleteBy(TaskDto.formatZoneDateTime(task));
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
