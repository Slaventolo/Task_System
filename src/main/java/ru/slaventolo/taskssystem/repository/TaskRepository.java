package ru.slaventolo.taskssystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.slaventolo.taskssystem.model.Task;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    @Query("SELECT MAX(t.taskNumber) FROM Task t WHERE t.projectId = :projectId")
    int findMaxTaskNumberWithinProject(@Param("projectId") UUID projectId);
}
