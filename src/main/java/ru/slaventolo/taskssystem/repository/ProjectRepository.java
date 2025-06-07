package ru.slaventolo.taskssystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.slaventolo.taskssystem.model.Project;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
