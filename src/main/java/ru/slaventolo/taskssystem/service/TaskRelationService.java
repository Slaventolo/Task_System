package ru.slaventolo.taskssystem.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.slaventolo.taskssystem.model.TaskRelation;
import ru.slaventolo.taskssystem.repository.TaskRelationRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskRelationService {
    private TaskRelationRepository taskRelationRepository;

    public TaskRelationService(TaskRelationRepository taskRelationRepository) {
        this.taskRelationRepository = taskRelationRepository;
    }

    public TaskRelation saveTaskRelation(TaskRelation taskRelation) {
        return taskRelationRepository.save(taskRelation);
    }

    public TaskRelation getTaskRelationByParentId(UUID parentTaskId) {
        try {
            return taskRelationRepository.findRelatedTasks(parentTaskId);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteTaskRelationByParentId(UUID parentTaskId) {
        TaskRelation taskRelationList = taskRelationRepository.findRelatedTasks(parentTaskId);
        taskRelationRepository.delete(taskRelationList);
    }
}
