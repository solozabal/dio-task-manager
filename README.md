# dio-task-manager
Simple task management system with RESTful API, built with Java, Spring Boot, and design patterns. Manage tasks with priority, due date, and importance.

classDiagram
    direction LR

    class TaskManagerSystemApplication {
    }

    class TaskController {
        - TaskFacade taskFacade
        + getAllTasks(strategy: String): List~Task~
        + createTask(task: Task): Task
        + updateTask(id: Long, task: Task): Task
        + deleteTask(id: Long): void
    }

    class TaskFacade {
        - TaskRepository taskRepository
        + getAllTasks(strategy: TaskPrioritizationStrategy): List~Task~
        + createTask(task: Task): Task
        + updateTask(task: Task): Task
        + deleteTask(id: Long): void
    }

    class Task {
        Long id
        String name
        String description
        LocalDateTime dueDate
        int importance
        LocalDateTime creationDate
        + setId(id: Long): void
        + getCreationDate(): LocalDateTime
        + getImportance(): int
    }

    class TaskRepository {
    }

    class TaskManager {
        - static TaskManager instance
        - TaskRepository taskRepository
        - TaskPrioritizationStrategy strategy
        ~ TaskManager() 
        + static getInstance(): TaskManager
        + setStrategy(strategy: TaskPrioritizationStrategy): void
        + getAllTask(): List~Task~
        + createTask(task: Task): Task
        + updateTask(task: Task): Task
        + deleteTask(id: Long): void
    }

    class TaskPrioritizationStrategy {
        + prioritize(tasks: List~Task~): List~Task~
    }

    class PrioritizeByDate {
        + prioritize(tasks: List~Task~): List~Task~
    }

    class PrioritizeByImportance {
        + prioritize(tasks: List~Task~): List~Task~
    }

    TaskController --> TaskFacade : uses
    TaskFacade --> TaskRepository : uses
    TaskFacade --> Task : manages
    TaskRepository --> Task : persists
    TaskManager --> TaskRepository : uses
    TaskManager --> TaskPrioritizationStrategy : uses
    TaskManager --> Task : manages
    TaskPrioritizationStrategy <|-- PrioritizeByDate : implements
    TaskPrioritizationStrategy <|-- PrioritizeByImportance : implements
