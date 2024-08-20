
/**
 * This interface represents the repository for the Project entity.
 * It extends the JpaRepository interface, providing CRUD operations for the Project entity.
 * The ProjectRepository interface is responsible for interacting with the underlying database
 * and performing operations such as saving, updating, deleting, and querying Project entities.
 * 
 * @param <Project> The type of the entity being managed by this repository, which is Project.
 * @param <Long> The type of the primary key of the entity being managed by this repository, which is Long.
 */
package com.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}