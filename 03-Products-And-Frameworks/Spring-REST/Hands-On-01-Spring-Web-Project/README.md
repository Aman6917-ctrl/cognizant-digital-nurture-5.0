# Hands-On 1 — Spring Boot Web Project

## Objective

Bootstrap a Spring Boot **3.3.x** web application with embedded Tomcat and REST health/info endpoints.

## Concepts Covered

- `@SpringBootApplication` and `SpringApplication.run()`
- Embedded Tomcat
- Spring Web MVC (`@RestController`)
- Maven dependency management
- SLF4J startup logging

## Folder Structure

```
Hands-On-01-Spring-Web-Project/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/springlearn/
    ├── SpringWebApplication.java
    └── web/ApiInfoController.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, DevTools, SLF4J

## Spring Boot

Auto-configuration, executable JAR, embedded server, production-ready defaults.

## Spring Core

Component scanning for `@RestController` beans in the Boot application context.

## XML Configuration

Not used in this exercise (annotation-driven Boot).

## IoC Container

ApplicationContext created by Spring Boot.

## Bean Scope

Default singleton controllers/services.

## Logging

`LoggerFactory` in main and controller.

## How to Run

```bash
cd Hands-On-01-Spring-Web-Project
mvn spring-boot:run
curl http://localhost:8080/api/health
curl http://localhost:8080/api/info
mvn test
```

## Expected Output

- Logs: application start on embedded Tomcat
- `GET /api/health` → `{"status":"UP"}`
- `GET /api/info` → module metadata JSON

## Learning Outcomes

- Understand why Spring Boot reduces XML and boilerplate
- Expose simple REST endpoints for health checks
