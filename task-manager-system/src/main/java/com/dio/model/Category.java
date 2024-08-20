/**
 * Represents a category in the task manager system.
 * 
 * This class is an entity that is mapped to a database table using JPA annotations.
 * It contains the following properties:
 * - id: The unique identifier of the category.
 * - name: The name of the category.
 * 
 * This class also uses the Lombok library to generate getter and setter methods for its properties.
 */
package com.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category", schema = "taskmanager")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks = new ArrayList<>();
}