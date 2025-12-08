package com.dio.service;

import com.dio.model.*;
import com.dio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskManager {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        prioritizeTasks(tasks);
        return tasks;
    }

    @Transactional
    public Task createTask(Task task) {
        if (task.getCategory() != null && task.getCategory().getId() != null) {
            Category dbCategory = categoryRepository.findById(task.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
            task.setCategory(dbCategory);
        }
        if (task.getProject() != null && task.getProject().getId() != null) {
            Project dbProject = projectRepository.findById(task.getProject().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
            task.setProject(dbProject);
        }
        if (task.getUser() != null && task.getUser().getId() != null) {
            User dbUser = userRepository.findById(task.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            task.setUser(dbUser);
        }
        validateTask(task);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
        if (updatedTask.getCategory() != null && updatedTask.getCategory().getId() != null) {
            Category dbCategory = categoryRepository.findById(updatedTask.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
            updatedTask.setCategory(dbCategory);
        }
        if (updatedTask.getProject() != null && updatedTask.getProject().getId() != null) {
            Project dbProject = projectRepository.findById(updatedTask.getProject().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
            updatedTask.setProject(dbProject);
        }
        if (updatedTask.getUser() != null && updatedTask.getUser().getId() != null) {
            User dbUser = userRepository.findById(updatedTask.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            updatedTask.setUser(dbUser);
        }
        validateTask(updatedTask);
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDueDate(updatedTask.getDueDate());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setCompleted(updatedTask.isCompleted());
            existingTask.setCategory(updatedTask.getCategory());
            existingTask.setProject(updatedTask.getProject());
            existingTask.setUser(updatedTask.getUser());
            return taskRepository.save(existingTask);
        } else {
            throw new TaskNotFoundException("Task " + id + " não existe!");
        }
    }

    @Transactional
    public void deleteTask(Long id) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if (existingTaskOpt.isPresent()) {
            taskRepository.delete(existingTaskOpt.get());
        } else {
            throw new TaskNotFoundException("Task " + id + " não existe!");
        }
    }

    private void prioritizeTasks(List<Task> tasks) {
        tasks.sort((task1, task2) -> Integer.compare(task2.getPriority(), task1.getPriority()));
    }

    private void validateTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser vazio");
        }
        if (task.getTitle().length() > 100) {
            throw new IllegalArgumentException("O título da tarefa não pode ter mais de 100 caracteres");
        }
        if (task.getDescription() != null && task.getDescription().length() > 500) {
            throw new IllegalArgumentException("A descrição da tarefa não pode ter mais de 500 caracteres");
        }
        if (task.getDueDate() != null && task.getDueDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de vencimento não pode ser no passado");
        }
        if (task.getPriority() < 1 || task.getPriority() > 5) {
            throw new IllegalArgumentException("A prioridade da tarefa deve estar entre 1 e 5");
        }
        // Remove validação de nome de categoria restrito
        // Isso permite qualquer nome criado via /categories
    }
}

class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}