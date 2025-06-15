package ru.slaventolo.taskssystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slaventolo.taskssystem.DTO.ProjectDto;
import ru.slaventolo.taskssystem.repository.ProjectRepository;
import ru.slaventolo.taskssystem.service.ProjectService;

import java.util.UUID;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectRepository projectRepository, ProjectService projectService) {
        this.projectService = projectService;
    }


    /**
     * Получение проекта по id
     */
    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable UUID id) {
        var project = projectService.getProjectById(id);
        return ResponseEntity.ok().body(project);
    }


    /**
     * Создание проекта
     */
    @PostMapping("/create_project")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto createProjectDTO) {
        ProjectDto savedProject = projectService.createProject(createProjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }


    /**
     * Удаление проекта по id
     */
    @DeleteMapping("project/{id}")
    public void deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
    }
}
