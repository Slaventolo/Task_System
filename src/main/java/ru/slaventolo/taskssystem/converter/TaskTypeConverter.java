package ru.slaventolo.taskssystem.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.slaventolo.taskssystem.model.TaskType;

@Converter
public class TaskTypeConverter implements AttributeConverter<TaskType, String> {

    @Override
    public String convertToDatabaseColumn(TaskType taskType) {
        if (taskType == null) {
            return null;
        }
        return taskType.getDbValue();
    }

    @Override
    public TaskType convertToEntityAttribute(String dbValue) {
        if (dbValue == null) {
            return null;
        }
        return TaskType.fromDbValue(dbValue);
    }
}
