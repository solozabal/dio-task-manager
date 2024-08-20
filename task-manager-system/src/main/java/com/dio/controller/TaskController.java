/**
 * This file represents the TaskController class.
 * It is responsible for handling the tasks related operations.
 * The TaskController class is located in the package com.dio.controller.
 */
package com.dio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.model.Task;
import com.dio.service.TaskManager;

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