package ru.slaventolo.taskssystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksListController {

    @GetMapping("/tasks")
    public String showTasks() {
        return "tasksList";
    }
}
