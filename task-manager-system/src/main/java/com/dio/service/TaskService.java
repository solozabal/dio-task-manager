
/**
 * This class represents the TaskService, which is responsible for managing tasks.
 * It provides methods for retrieving, creating, updating, and deleting tasks.
 */

 
package com.dio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dio.model.Task;
import com.dio.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        return taskRepository.findById(id).map(existingTask -> {
            task.setId(id);
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task " + id + " não existe!"));
    }

    public void deleteTask(Long id) {
        taskRepository.findById(id).ifPresentOrElse(taskRepository::delete, () -> {
            throw new RuntimeException("Task " + id + " não existe!");
        });
    }
}