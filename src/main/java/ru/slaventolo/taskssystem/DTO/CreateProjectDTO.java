package ru.slaventolo.taskssystem.DTO;

import ru.slaventolo.taskssystem.model.Project;

import java.util.UUID;

public class CreateProjectDTO {

    private UUID id;
    private String name;

    public Project toEntity() {
        return new Project(this.getName());
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
