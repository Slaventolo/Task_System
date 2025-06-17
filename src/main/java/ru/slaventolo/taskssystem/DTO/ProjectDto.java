package ru.slaventolo.taskssystem.DTO;

import ru.slaventolo.taskssystem.model.Project;

import java.util.UUID;

public class ProjectDto {
    private UUID id;
    private String name;

    /**
     * Превращение полей из запроса в поля сущности Project
     */
    public Project toEntity() {
        return new Project(this.getName());
    }

    /**
     * Возврат полей сущности Project в поля для ответа
     */
    public static ProjectDto fromEntity(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        return dto;
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