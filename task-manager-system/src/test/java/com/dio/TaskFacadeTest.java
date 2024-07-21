package com.dio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.dio.facade.TaskFacade;
import com.dio.model.Task;
import com.dio.repository.TaskRepository;
import com.dio.strategy.TaskPrioritizationStrategy;

@RunWith(MockitoJUnitRunner.class)
public class TaskFacadeTest {

    @InjectMocks
    private TaskFacade taskFacade;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskPrioritizationStrategy taskPrioritizationStrategy;

    private List<Task> tasks;

    @Before
    public void setup() {
        tasks = Arrays.asList(new Task(null, "Task 1", "Description 1", null, 0, null), new Task(null, "Task 2", "Description 2", null, 0, null));
    }

    @Test
    public void testGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> result = taskFacade.getAllTasks(taskPrioritizationStrategy);
        assertNotNull(result);
        assertEquals(tasks, result);
        verify(taskPrioritizationStrategy).prioritize(tasks);
    }

    @Test
    public void testCreateTask() {
        Task task = new Task(null, "New Task", "New Description", null, 0, null);
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskFacade.createTask(task);
        assertNotNull(result);
        assertEquals(task, result);
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task(null, "Updated Task", "Updated Description", null, 0, null);
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskFacade.updateTask(task);
        assertNotNull(result);
        assertEquals(task, result);
    }

    @Test
    public void testDeleteTask() {
        Long id = 1L;
        taskFacade.deleteTask(id);
        verify(taskRepository).deleteById(id);
    }
}