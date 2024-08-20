
/**
 * Represents a task in the task manager system.
 * 
 * This class is an entity that is mapped to a database table.
 * It contains information about a task, such as its title, description, due date, priority, and completion status.
 * 
 * The task is associated with a category and a project through many-to-one relationships.
 * 
 * @see Category
 * @see Project
 */
package com.dio.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private int priority;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}