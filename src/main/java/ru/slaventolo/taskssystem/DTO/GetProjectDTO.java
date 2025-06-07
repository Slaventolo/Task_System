package ru.slaventolo.taskssystem.DTO;

import ru.slaventolo.taskssystem.model.Project;
import ru.slaventolo.taskssystem.model.Task;

import java.util.UUID;

public class GetProjectDTO {

    private UUID id;
    private String name;

    public static GetProjectDTO fromEntity(Project project) {
        GetProjectDTO dto = new GetProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        return dto;
    }

    public UUID getId () {
        return id;
    }

    public void setId (UUID id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
