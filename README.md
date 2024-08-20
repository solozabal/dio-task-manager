# DIO Task Manager

## Overview

DIO Task Manager é uma aplicação de gerenciamento de tarefas desenvolvida com Spring Boot. A aplicação utiliza um banco de dados H2 em memória para armazenar as tarefas.

## Estrutura do Projeto

- **Model**: Contém as classes de entidade.
- **Repository**: Contém as interfaces de repositório que estendem `JpaRepository`.
- **Service**: Contém as classes de serviço que implementam a lógica de negócios.
- **Controller**: Contém os controladores REST para gerenciar as requisições HTTP.
- **Config**: Contém as classes de configuração, como segurança.
- **Application**: Classe principal que inicializa a aplicação Spring Boot.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- H2 Database

## Configuração do Banco de Dados

A aplicação está configurada para usar um banco de dados H2 em memória. As configurações podem ser encontradas no arquivo `application.properties`.

```properties
# Configurações do banco de dados H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# Dialeto do Hibernate para o H2
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Habilitar o console H2 para visualização do banco de dados
spring.h2.console.enabled=true

## Diagrama UML

classDiagram
    direction TB

    class TaskManagerSystemApplication {
    }

    class TaskController {
        - TaskService taskService
        + getAllTasks(): List~Task~
    }

    class TaskService {
        - TaskRepository taskRepository
        + findAllTasks(): List~Task~
    }

    class TaskRepository {
        + findAll(): List~Task~
    }

    class Task {
        Long id
        String name
        Category category
        Project project
        User user
        + getId(): Long
        + getName(): String
        + getCategory(): Category
        + getProject(): Project
        + getUser(): User
        + setId(id: Long): void
        + setName(name: String): void
        + setCategory(category: Category): void
        + setProject(project: Project): void
        + setUser(user: User): void
    }

    class Category {
        Long id
        String name
        + getId(): Long
        + getName(): String
        + setId(id: Long): void
        + setName(name: String): void
    }

    class Project {
        Long id
        String name
        + getId(): Long
        + getName(): String
        + setId(id: Long): void
        + setName(name: String): void
    }

    class User {
        Long id
        String name
        + getId(): Long
        + getName(): String
        + setId(id: Long): void
        + setName(name: String): void
    }

    TaskController --> TaskService
    TaskService --> TaskRepository
    TaskRepository --> Task
    Task --> Category
    Task --> Project
    Task --> User
