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

    public List<TaskRelation> saveTaskRelation(List<TaskRelation> taskRelation) {
        return taskRelationRepository.saveAll(taskRelation);
    }

    public List<TaskRelation> getTaskRelationsByParentId(UUID parentTaskId) {
        try {
            return taskRelationRepository.findRelatedTasks(parentTaskId);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteTaskRelationsByParentId(UUID parentTaskId) {
        List<TaskRelation> taskRelationList = taskRelationRepository.findRelatedTasks(parentTaskId);
        taskRelationRepository.deleteAll(taskRelationList);
    }
}
