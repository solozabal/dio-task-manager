package com.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dio.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}