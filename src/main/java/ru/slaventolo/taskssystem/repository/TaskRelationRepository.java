package ru.slaventolo.taskssystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.slaventolo.taskssystem.model.Task;
import ru.slaventolo.taskssystem.model.TaskRelation;

import java.util.List;
import java.util.UUID;

public interface TaskRelationRepository extends JpaRepository<TaskRelation, UUID> {
    @Query("SELECT t FROM TaskRelation t WHERE t.parentTask = :parentId")
    List<TaskRelation> findRelatedTasks(@Param("parentId") UUID parentId);
}
