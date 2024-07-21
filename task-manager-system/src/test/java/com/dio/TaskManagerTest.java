package com.dio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dio.model.Task;
import com.dio.repository.TaskRepository;
import com.dio.service.TaskManager;
import com.dio.strategy.TaskPrioritizer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskManagerTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskPrioritizer taskPrioritizer;

    @InjectMocks
    private TaskManager taskManager;

    @Test
    void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        // Configura o TaskManager para usar o mock do TaskPrioritizer
        taskManager.setTaskPrioritizer(taskPrioritizer);

        // Quando prioritize é chamado, retorna a lista de tarefas
        when(taskPrioritizer.prioritize(tasks)).thenReturn(tasks);

        // Chama o método que deve usar o prioritizer
        List<Task> result = taskManager.getAllTasks();

        // Verifica o resultado
        assertIterableEquals(tasks, result);

        // Verifica se o método prioritize foi chamado
        verify(taskPrioritizer).prioritize(tasks);
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskManager.createTask(task);

        assertEquals(task, result);
    }

    @Test
    void testUpdateTask() {
        Long id = 1L;
        Task task = new Task();
        when(taskRepository.findById(id)).thenReturn(java.util.Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskManager.updateTask(id, task);

        assertEquals(task, result);
    }

    @Test
    void testDeleteTask() {
        Long id = 1L;
        Task task = new Task();
        when(taskRepository.findById(id)).thenReturn(java.util.Optional.of(task));

        taskManager.deleteTask(id);

        verify(taskRepository).delete(task);
    }
}
