
/**
 * The main class for the Task Manager System application.
 * This class is responsible for starting the application.
 * It initializes the Spring Boot application and sets up the necessary configurations.
 * The application scans the specified base packages for components, repositories, and entities.
 * 
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.boot.autoconfigure.domain.EntityScan
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.data.jpa.repository.config.EnableJpaRepositories
 */

 package com.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dio.repository")
@EntityScan(basePackages = "com.dio.model")
@ComponentScan(basePackages = "com.dio")
public class TaskManagerSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerSystemApplication.class, args);
    }
}