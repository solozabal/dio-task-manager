
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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}