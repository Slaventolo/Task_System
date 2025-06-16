package ru.slaventolo.taskssystem.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter
public class CompleteByConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return zonedDateTime == null ? null :
                Timestamp.from(zonedDateTime.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp == null ? null :
                ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }
}
