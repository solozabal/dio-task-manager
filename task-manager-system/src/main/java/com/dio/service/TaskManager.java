package com.dio.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dio.model.Task;
import com.dio.model.Project;
import com.dio.repository.TaskRepository;
import com.dio.repository.ProjectRepository;

@Service
public class TaskManager {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        prioritizeTasks(tasks);
        return tasks;
    }

    @Transactional
    public Task createTask(Task task) {
        validateTask(task);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
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
        // Validação do título
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser vazio");
        }
        if (task.getTitle().length() > 100) {
            throw new IllegalArgumentException("O título da tarefa não pode ter mais de 100 caracteres");
        }

        // Validação da descrição
        if (task.getDescription() != null && task.getDescription().length() > 500) {
            throw new IllegalArgumentException("A descrição da tarefa não pode ter mais de 500 caracteres");
        }

        // Validação da data de vencimento
        if (task.getDueDate() != null && task.getDueDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de vencimento não pode ser no passado");
        }

        // Validação da prioridade
        if (task.getPriority() < 1 || task.getPriority() > 5) {
            throw new IllegalArgumentException("A prioridade da tarefa deve estar entre 1 e 5");
        }

        // Validação da categoria (exemplo de categorias válidas)
        List<String> validCategories = Arrays.asList("Trabalho", "Pessoal", "Estudo");
        if (task.getCategory() != null && !validCategories.contains(task.getCategory().toString())) {
            throw new IllegalArgumentException("Categoria inválida");
        }

        // Validação do projeto (exemplo de validação de projeto)
        if (task.getProject() != null && !isValidProject(task.getProject())) {
            throw new IllegalArgumentException("Projeto inválido");
        }
    }

    private boolean isValidProject(Project project) {
        // Verificar se o projeto existe no banco de dados
        return projectRepository.existsById(project.getId());
    }
}

// Exceção personalizada para tarefas não encontradas
class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}