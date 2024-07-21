package com.dio.strategy;

import java.util.Collections;
import java.util.List;
import com.dio.model.Task;

public class TaskPrioritizer implements TaskPrioritizationStrategy {

    public enum Strategy {
        BY_DATE,
        BY_IMPORTANCE
    }

    private Strategy strategy;

    public TaskPrioritizer(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<Task> prioritize(List<Task> tasks) {
        switch (strategy) {
            case BY_DATE -> Collections.sort(tasks, (t1, t2) -> t1.getCreationDate().compareTo(t2.getCreationDate()));
            case BY_IMPORTANCE -> Collections.sort(tasks, (t1, t2) -> t2.getImportance() - t1.getImportance());
        }
        return tasks;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
