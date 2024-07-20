# dio-task-manager
Simple task management system with RESTful API, built with Java, Spring Boot, and design patterns. Manage tasks with priority, due date, and importance.

graph LR
    classDef task fill:#f9f,stroke:#333,stroke-width:4px;
    classDef user fill:#ccf,stroke:#333,stroke-width:4px;
    classDef api fill:#ffc,stroke:#333,stroke-width:4px;

    user1[User] -->|Create Task|> task1[Task]
    user1 -->|Assign Task|> task2[Task]
    user1 -->|View Tasks|> taskList[Task List]

    task1 -->|Has|> priority1[Priority]
    task1 -->|Has|> dueDate1[Due Date]
    task1 -->|Has|> importance1[Importance]

    task2 -->|Has|> priority2[Priority]
    task2 -->|Has|> dueDate2[Due Date]
    task2 -->|Has|> importance2[Importance]

    api1[RESTful API] -->|CRUD Operations|> taskList
    api1 -->|Task Management|> task1
    api1 -->|Task Management|> task2

    class task1,task2 task;
    class user1 user;
    class api1 api;
