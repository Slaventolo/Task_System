package ru.slaventolo.taskssystem.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

@Converter
public class TimeSpentConverter implements AttributeConverter<Duration, Time> {

    @Override
    public Time convertToDatabaseColumn(Duration duration) {
        return duration == null ? null :
                Time.valueOf(LocalTime.MIN.plus(duration));
    }

    @Override
    public Duration convertToEntityAttribute(Time time) {
        return time == null ? null :
                Duration.between(LocalTime.MIN, time.toLocalTime());
    }
}
