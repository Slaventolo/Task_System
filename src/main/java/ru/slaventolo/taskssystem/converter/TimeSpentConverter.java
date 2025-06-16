package ru.slaventolo.taskssystem.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

@Converter
public class TimeSpentConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration == null ? null : duration.toMinutes();
    }

    @Override
    public Duration convertToEntityAttribute(Long minutes) {
        return minutes == null ? null : Duration.ofMinutes(minutes);
    }
}
