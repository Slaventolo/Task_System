package ru.slaventolo.taskssystem.converter;

import org.springframework.stereotype.Component;
import ru.slaventolo.taskssystem.DTO.ProjectDto;
import ru.slaventolo.taskssystem.model.ProjectEntity;

@Component
public class ProjectConverter {

    public ProjectDto toFromEntity (ProjectEntity project) {
        var projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        return projectDto;
    }

    public ProjectEntity entityFromTo(ProjectDto projectDto) {
        var projectEntity = new ProjectEntity();
        projectEntity.setId(projectDto.getId());
        projectEntity.setName(projectDto.getName());
        return projectEntity;
    }
}
