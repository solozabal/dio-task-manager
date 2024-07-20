package com.dio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dio.model.Task;
import com.dio.repository.TaskRepository;
import com.dio.strategy.TaskPrioritizationStrategy;

public class TaskManager {
    private static TaskManager instance;

    @Autowired
    private TaskRepository taskRepository;

    private TaskPrioritizationStrategy strategy;

    private TaskManager(){

    }

    public static synchronized TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void setStrategy(TaskPrioritizationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Task> getAllTask() {
        List<Task> tasks = taskRepository.findAll();
        if (strategy != null) {
            strategy.prioritize(tasks);
        }
        return tasks;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}