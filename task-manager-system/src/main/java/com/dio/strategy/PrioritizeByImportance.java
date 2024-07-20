package com.dio.strategy;

import java.util.List;
import com.dio.model.Task;

public class PrioritizeByImportance implements TaskPrioritizationStrategy {
    @Override
    public List<Task> prioritize(List<Task> tasks) {
        tasks.sort((t1, t2) -> t2.getImportance() - t1.getImportance());
        return tasks;
    }
}