package com.dio.controller;

import org.springframework.web.bind.annotation.*;

import com.dio.model.Task;
import com.dio.service.TaskManager;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskManager taskManager;

    //@Autowired
    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskManager.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskManager.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskManager.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskManager.deleteTask(id);
    }
}