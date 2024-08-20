
/**
 * This interface represents a repository for managing tasks.
 * It extends the JpaRepository interface, providing CRUD operations for the Task entity.
 * The TaskRepository interface is annotated with @Repository to indicate that it is a Spring Data repository.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.dio.model.Task
 */

 package com.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}