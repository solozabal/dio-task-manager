package com.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication(scanBasePackages = "com.dio")
@EntityScan("com.dio.model")
@EnableJpaRepositories(basePackages = "com.dio.repository")
public class TaskManagerSystemApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerSystemApplication.class, args);
    }
}