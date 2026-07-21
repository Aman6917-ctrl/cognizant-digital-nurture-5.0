# Exercise 09 — Spring Boot Library Management

## Objective

Build a **Spring Boot 3.3.5** library management API with JPA, H2, and REST CRUD endpoints for books.

## Technologies

- Java 21
- Maven 3.9+
- Spring Boot 3.3.5
- Spring Web, Spring Data JPA
- H2 in-memory database

## Folder Structure

```
Exercise-09-Spring-Boot-Library-Management/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/spring/library/
    │   ├── LibraryManagementApplication.java
    │   ├── model/Book.java
    │   ├── repository/BookJpaRepository.java
    │   ├── service/
    │   └── web/BookController.java
    └── resources/application.properties
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-09-Spring-Boot-Library-Management
mvn clean package
mvn spring-boot:run
```

H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:library`)

Sample requests:

```bash
curl -s -X POST http://localhost:8080/api/books \
  -H 'Content-Type: application/json' \
  -d '{"title":"Spring Boot in Action","author":"Craig Walls"}'
curl -s http://localhost:8080/api/books
```

## Expected Output

Server starts on port 8080 with JPA creating the `books` table. REST calls return JSON arrays and entities; missing ids return HTTP 404 with a message body.

## Key Concepts

- **`@SpringBootApplication`** — auto-configuration and component scanning
- **Spring Data JPA** — `JpaRepository` for persistence
- **REST controllers** — CRUD mapping with HTTP semantics
- **Embedded H2** — rapid development with console access

## Screenshots

<!-- Add screenshot of Spring Boot startup and REST client output here -->
