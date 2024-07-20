package com.dio.strategy;

import java.util.List;
import com.dio.model.Task;

public class PrioritizeByDate implements TaskPrioritizationStrategy {
    @Override
    public List<Task> prioritize(List<Task> tasks) {
        tasks.sort((t1, t2) -> t1.getCreationDate().compareTo(t2.getCreationDate()));
        return tasks;
    }
}