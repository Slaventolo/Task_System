package ru.slaventolo.taskssystem.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.slaventolo.taskssystem.model.TaskRelation;
import ru.slaventolo.taskssystem.repository.TaskRelationRepository;
import ru.slaventolo.taskssystem.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskRelationService {
    private TaskRelationRepository taskRelationRepository;
    private TaskRepository taskRepository;

    public TaskRelationService(TaskRelationRepository taskRelationRepository, TaskRepository taskRepository) {
        this.taskRelationRepository = taskRelationRepository;
        this.taskRepository = taskRepository;
    }

    public List<TaskRelation> saveTaskRelation(List<TaskRelation> taskRelation) {
        // Проверка, существует ли childTask
        taskRelation.forEach(relation -> {
            if (!taskRepository.existsById(relation.getChildTaskId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Child Task Not Found");
            };
        });

        // Проверка, существует ли связь уже, но сейчас избыточно,
        // так как в update сначала удаляются все связи и переписываются заново
        taskRelation.forEach(relation -> {
            if (taskRelationRepository.existsByParentAndChildTaskIds(relation.getParentTaskId(), relation.getChildTaskId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Relation already exists");
            }
        });

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
