package com.dio.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.model.Task;
import com.dio.repository.TaskRepository;
import com.dio.strategy.TaskPrioritizationStrategy;

@Service
public class TaskFacade {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskFacade(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(TaskPrioritizationStrategy strategy) {
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
