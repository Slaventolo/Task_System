package ru.slaventolo.taskssystem.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.slaventolo.taskssystem.model.TaskStatus;

@Converter
public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {
    @Override
    public String convertToDatabaseColumn(TaskStatus taskStatus) {
        if (taskStatus == null) {
            return null;
        }
        return taskStatus.getDbValue();
    }

    @Override
    public TaskStatus convertToEntityAttribute(String s) {
        return TaskStatus.fromDbValue(s);
    }
}
