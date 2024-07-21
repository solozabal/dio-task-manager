package com.dio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dio.facade.TaskFacade;
import com.dio.model.Task;
import com.dio.service.TaskManager;
import com.dio.strategy.TaskPrioritizer;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskFacade taskFacade;
    private final TaskManager taskManager;

    @Autowired
    public TaskController(TaskFacade taskFacade, TaskManager taskManager) {
        this.taskFacade = taskFacade;
        this.taskManager = taskManager;
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestParam String strategy) {
        TaskPrioritizer.Strategy prioritizationStrategy = switch (strategy.toLowerCase()) {
            case "date" -> TaskPrioritizer.Strategy.BY_DATE;
            case "importance" -> TaskPrioritizer.Strategy.BY_IMPORTANCE;
            default -> TaskPrioritizer.Strategy.BY_DATE;
        };
        TaskPrioritizer taskPrioritizer = new TaskPrioritizer(prioritizationStrategy);
        taskManager.setTaskPrioritizer(taskPrioritizer);
        return taskManager.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskFacade.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return taskFacade.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskFacade.deleteTask(id);
    }
}
