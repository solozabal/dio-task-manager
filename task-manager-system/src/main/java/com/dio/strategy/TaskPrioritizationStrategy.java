package com.dio.strategy;

import java.util.List;
import com.dio.model.Task;

public interface TaskPrioritizationStrategy {
    List<Task> prioritize(List<Task> tasks);
}