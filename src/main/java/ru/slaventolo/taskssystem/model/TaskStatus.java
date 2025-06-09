package ru.slaventolo.taskssystem.model;

public enum TaskStatus {
    REGISTERED("registered"),
    IN_PROGRESS("in_progress"),
    UNDER_REVIEW("under_review"),
    FIXING_BUGS("fixing_bugs"),
    CLOSED("closed");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getDbValue() {
        return status;
    }

    public static TaskStatus fromDbValue(String dbValue) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.status.equalsIgnoreCase(dbValue)) {
                return taskStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + dbValue);
    }
}
