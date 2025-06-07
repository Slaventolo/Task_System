package ru.slaventolo.taskssystem.service;

import org.springframework.stereotype.Service;
import ru.slaventolo.taskssystem.model.Project;
import ru.slaventolo.taskssystem.repository.ProjectRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProjectById(UUID id) {
        return projectRepository.getReferenceById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(UUID id) {
        projectRepository.deleteById(id);
    }

    // TODO
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }
}
