package ru.slaventolo.taskssystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.slaventolo.taskssystem.DTO.CreateProjectDTO;
import ru.slaventolo.taskssystem.DTO.GetProjectDTO;
import ru.slaventolo.taskssystem.model.Project;
import ru.slaventolo.taskssystem.repository.ProjectRepository;
import ru.slaventolo.taskssystem.service.ProjectService;

import java.util.UUID;

@RestController
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    public ProjectController(ProjectRepository projectRepository, ProjectService projectService) {
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }


    /**
     * Получение проекта по id
     */
    @GetMapping("/project/{id}")
    public ResponseEntity<GetProjectDTO> getProject(@PathVariable UUID id) {
        Project project = projectRepository.getReferenceById(id);
        return ResponseEntity.ok().body(GetProjectDTO.fromEntity(project));
    }


    /**
     * Создание проекта
     */
    @PostMapping("/create_project")
    public ResponseEntity<GetProjectDTO> createProject(@RequestBody CreateProjectDTO createProjectDTO) {
        Project project = createProjectDTO.toEntity();
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetProjectDTO.fromEntity(savedProject));
    }


    /**
     * Удаление проекта по id
     */
    @DeleteMapping("project/{id}")
    public void deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
    }
}
