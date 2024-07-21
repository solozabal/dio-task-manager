package com.dio;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.dio.facade.TaskFacade;
import com.dio.model.Task;
import com.dio.service.TaskManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskFacade taskFacade;

    @MockBean
    private TaskManager taskManager;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllTasks() throws Exception {
        List<Task> tasks = Arrays.asList(
            new Task(null, "Task 1", "Description 1", null, 0, null),
            new Task(null, "Task 2", "Description 2", null, 0, null)
        );
        when(taskManager.getAllTasks()).thenReturn(tasks);

        MvcResult result = mockMvc.perform(get("/tasks?strategy=date"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(tasks), json);
    }

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task(null, "New Task", "New Description", null, 0, null);
        when(taskFacade.createTask(any(Task.class))).thenReturn(task);

        MvcResult result = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(task), json);
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task task = new Task(1L, "Updated Task", "Updated Description", null, 0, null);
        when(taskFacade.updateTask(any(Task.class))).thenReturn(task);

        MvcResult result = mockMvc.perform(put("/tasks/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(task), json);
    }
}
