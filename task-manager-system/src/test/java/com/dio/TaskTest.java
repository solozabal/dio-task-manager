package com.dio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.dio.model.Task;

public class TaskTest 
{
   
    @Test
    public void testTaskCreation()
    {
            Task task = new Task(null, "My Task", "This is a task", null, 0, null);
            assertNotNull(task);
            assertEquals("My Task", task.getName());
            assertEquals("This is a task", task.getDescription());
    }
}