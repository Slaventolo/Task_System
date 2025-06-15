package ru.slaventolo.taskssystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.slaventolo.taskssystem.DTO.ProjectDto;
import ru.slaventolo.taskssystem.converter.ProjectConverter;
import ru.slaventolo.taskssystem.model.ProjectEntity;
import ru.slaventolo.taskssystem.repository.ProjectRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectConverter projectConverter;


    public ProjectDto getProjectById(UUID id) {
        var projectEntity = projectRepository.getReferenceById(id);
        return projectConverter.toFromEntity(projectEntity);
    }

    public ProjectDto createProject(ProjectDto projectDto) {
        var projectEntity = projectConverter.entityFromTo(projectDto);
        return projectConverter.toFromEntity(projectRepository.save(projectEntity));
    }

    public void deleteProject(UUID id) {
        projectRepository.deleteById(id);
    }

    // TODO
    public List<ProjectEntity> getProjects() {
        return projectRepository.findAll();
    }
}
