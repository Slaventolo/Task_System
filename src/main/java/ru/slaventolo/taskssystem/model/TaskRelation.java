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

    private UUID parentTaskId;

    private UUID childTaskId;

    public TaskRelation() {}

    public TaskRelation(UUID parentTask, UUID childTask) {
        this.parentTaskId = parentTask;
        this.childTaskId = childTask;
    }

    public UUID getId() {
        return id;
    }

    public UUID getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(UUID parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public UUID getChildTaskId() {
        return childTaskId;
    }

    public void setChildTaskId(UUID childTaskId) {
        this.childTaskId = childTaskId;
    }
}
