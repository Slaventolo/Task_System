package ru.slaventolo.taskssystem.model;

public enum TaskType {
    ERROR("error"),
    TASK("task"),
    REQUIREMENT("requirement");

    private final String dbValue;

    TaskType(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static TaskType fromDbValue(String dbValue) {
        for (TaskType type : values()) {
            if (type.dbValue.equalsIgnoreCase(dbValue)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown TaskType: " + dbValue);
    }
}
