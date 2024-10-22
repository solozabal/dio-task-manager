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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task Controller", description = "APIs para gerenciamento de tarefas")
public class TaskController {

    private final TaskManager taskManager;

    //@Autowired
    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @GetMapping
    @Operation(summary = "Obter todas as tarefas")
    public List<Task> getAllTasks() {
        return taskManager.getAllTasks();
    }

    @PostMapping
    @Operation(summary = "Criar uma nova tarefa")
    public Task createTask(@RequestBody Task task) {
        return taskManager.createTask(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma tarefa existente")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskManager.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma tarefa")
    public void deleteTask(@PathVariable Long id) {
        taskManager.deleteTask(id);
    }
}