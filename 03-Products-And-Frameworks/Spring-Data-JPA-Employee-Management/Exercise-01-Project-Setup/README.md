# Exercise 01 — Project Setup

## Objective

Bootstrap a minimal Spring Boot 3.3.5 Maven application with the Employee Management dependency stack and verify the project runs with a simple health/info endpoint.

## Concepts

- Spring Boot auto-configuration
- Maven project structure and `spring-boot-starter-parent`
- Embedded web server and REST controllers

## Folder Structure

```
Exercise-01-Project-Setup/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/ems/setup/
    │   ├── EmployeeManagementApplication.java
    │   └── InfoController.java
    ├── main/resources/application.properties
    └── test/java/com/cognizant/ems/setup/
        └── EmployeeManagementApplicationTests.java
```

## Technologies

- Java 21, Maven, Spring Boot 3.3.5
- spring-boot-starter-web, spring-boot-starter-data-jpa, spring-boot-starter-validation
- H2 (runtime), Lombok (optional), spring-boot-starter-test

## Architecture

Single Spring Boot application with one REST controller exposing project metadata. JPA is on the classpath for later exercises but not used yet.

## Database Config

H2 in-memory `jdbc:h2:mem:ems_ex1` with console at `/h2-console`. MySQL settings are commented in `application.properties`.

## REST APIs

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/health` | Liveness check |
| GET | `/api/info` | Project name and version |

## Sample Requests

```bash
curl -s http://localhost:8080/api/health
curl -s http://localhost:8080/api/info
```

## Expected Output

```json
{"status":"UP"}
```

```json
{"project":"Exercise-01-Project-Setup","description":"Spring Data JPA Employee Management — Setup"}
```

## How to Run

```bash
cd Exercise-01-Project-Setup
mvn spring-boot:run
```

## Learning Outcomes

- Create a Spring Boot Maven project with standard starters
- Understand default `application.properties` and H2 setup
- Expose a first REST endpoint and run integration smoke tests
