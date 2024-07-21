package com.dio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.model.Task;
import com.dio.repository.TaskRepository;
import com.dio.strategy.TaskPrioritizer;

@Service
public class TaskManager {

    private final TaskRepository taskRepository;
    private TaskPrioritizer taskPrioritizer;

    @Autowired
    public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void setTaskPrioritizer(TaskPrioritizer taskPrioritizer) {
        this.taskPrioritizer = taskPrioritizer;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (taskPrioritizer != null) {
            taskPrioritizer.prioritize(tasks);
        }
        return tasks;
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
