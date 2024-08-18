package com.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dio.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}