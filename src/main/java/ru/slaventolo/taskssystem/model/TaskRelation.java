package ru.slaventolo.taskssystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class TaskRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID parentTask;

    private UUID childTask;

    public TaskRelation() {}

    public TaskRelation(UUID parentTask, UUID childTask) {
        this.parentTask = parentTask;
        this.childTask = childTask;
    }

    public UUID getId() {
        return id;
    }

    public UUID getParentTask() {
        return parentTask;
    }

    public void setParentTask(UUID parentTask) {
        this.parentTask = parentTask;
    }

    public UUID getChildTask() {
        return childTask;
    }

    public void setChildTask(UUID childTask) {
        this.childTask = childTask;
    }
}
