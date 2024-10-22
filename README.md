# Descrição Técnica do Projeto

O Task Manager System é uma aplicação de gerenciamento de tarefas desenvolvida utilizando o framework Spring Boot. A aplicação permite criar, atualizar, listar e excluir tarefas, além de associá-las a categorias e projetos. A interface de usuário para a API é fornecida pelo Swagger UI.

## Conceitos e Técnicas Utilizadas

### Java

- **Java 17**: A aplicação foi desenvolvida utilizando a versão 17 do Java, aproveitando as melhorias e novos recursos introduzidos nesta versão.

### Spring Boot

- **Spring Boot**: Utilizado para simplificar a configuração e o desenvolvimento da aplicação. O Spring Boot permite criar aplicações standalone, prontas para produção, com configuração mínima.

### Spring Data JPA

- **JPA (Java Persistence API)**: Utilizado para mapeamento objeto-relacional (ORM). As entidades JPA representam as tabelas do banco de dados e são manipuladas através de repositórios.
- **Spring Data JPA**: Facilita a implementação de repositórios baseados em JPA, fornecendo uma abstração de persistência de dados.

### Spring Security

- **Autenticação e Autorização**: O Spring Security foi configurado para gerenciar a segurança da aplicação, incluindo autenticação e autorização. A configuração permite acesso público a determinados endpoints e protege outros endpoints.
- **BCryptPasswordEncoder**: Utilizado para criptografar senhas de usuários, garantindo a segurança dos dados armazenados.

### Spring Web

- **RESTful Services**: A aplicação expõe uma API RESTful para gerenciamento de tarefas. Os controladores REST são responsáveis por manipular as requisições HTTP e retornar as respostas apropriadas.
- **Spring MVC**: Utilizado para criar controladores que lidam com requisições HTTP e retornam respostas JSON.

### Banco de Dados H2

- **H2 Database**: Um banco de dados em memória utilizado para desenvolvimento e testes. O H2 é leve e fácil de configurar, ideal para ambientes de desenvolvimento.

### Swagger/OpenAPI

- **Documentação da API**: O Swagger foi configurado para gerar documentação interativa da API. A interface do Swagger UI permite testar os endpoints da API diretamente no navegador.
A documentação interativa da API pode ser acessada em:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Lombok

- **Redução de Código Boilerplate**: Utilizado para gerar automaticamente getters, setters, construtores, e outros métodos comuns, reduzindo a quantidade de código boilerplate.

## Estrutura do Projeto

### Pacotes

- **com.dio.config**: Contém as classes de configuração, incluindo segurança e Swagger.
- **com.dio.controller**: Contém os controladores REST que expõem os endpoints da API.
- **com.dio.model**: Contém as entidades JPA que representam as tabelas do banco de dados.
- **com.dio.repository**: Contém os repositórios JPA para acesso aos dados.
- **com.dio.service**: Contém as classes de serviço que implementam a lógica de negócios.

### Classes Principais

- **TaskManagerSystemApplication**: Classe principal que inicializa a aplicação Spring Boot.
- **SecurityConfig**: Configuração de segurança do Spring Security.
- **SwaggerConfig**: Configuração do Swagger para documentação da API.
- **TaskController**: Controlador REST para gerenciar tarefas.
- **TaskService**: Serviço que contém a lógica de negócios para gerenciar tarefas.
- **User**: Entidade JPA que representa um usuário no sistema.
- **Task**: Entidade JPA que representa uma tarefa no sistema.

## Endpoints da API

### Tarefas

- **GET /tasks**: Retorna todas as tarefas.
- **POST /tasks**: Cria uma nova tarefa.
- **PUT /tasks/{id}**: Atualiza uma tarefa existente.
- **DELETE /tasks/{id}**: Exclui uma tarefa.

## Conclusão

O Task Manager System é um exemplo de aplicação moderna desenvolvida com Java e Spring Boot, utilizando uma variedade de conceitos e técnicas para criar uma aplicação robusta e escalável. A aplicação demonstra o uso de JPA para persistência de dados, Spring Security para autenticação e autorização, e Swagger para documentação da API, entre outros conceitos importantes.