package com.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dio.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}