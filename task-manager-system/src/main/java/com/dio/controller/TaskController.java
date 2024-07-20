package com.dio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dio.strategy.PrioritizeByDate;
import com.dio.strategy.PrioritizeByImportance;

import com.dio.facade.TaskFacade;
import com.dio.model.Task;
import com.dio.strategy.TaskPrioritizationStrategy;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskFacade taskFacade;

    @GetMapping
public List<Task> getAllTasks(@RequestParam String strategy) {
    TaskPrioritizationStrategy taskPrioritizationStrategy = switch (strategy) {
        case "date" -> new PrioritizeByDate();
        case "importance" -> new PrioritizeByImportance();
        default -> new PrioritizeByDate();
    };
    return taskFacade.getAllTasks(taskPrioritizationStrategy);
}

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskFacade.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id); // assuming Task class has setId method
        return taskFacade.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskFacade.deleteTask(id);
    }
}