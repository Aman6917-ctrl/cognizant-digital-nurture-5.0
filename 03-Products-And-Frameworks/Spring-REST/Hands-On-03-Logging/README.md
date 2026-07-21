# Hands-On 3 — Logging

## Objective

Configure Spring Boot logging levels and console pattern, and demonstrate SLF4J usage across a service invoked at startup.

## Concepts Covered

- `logging.level.*` in `application.properties`
- Custom `logging.pattern.console`
- Log levels: INFO, DEBUG, WARN, ERROR
- `@Profile("!test")` to skip startup demos in tests

## Folder Structure

```
Hands-On-03-Logging/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/springlearn/
    │   ├── LoggingDemoApplication.java
    │   ├── LoggingDemoService.java
    │   └── LoggingDemoRunner.java
    └── resources/application.properties
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Logback (via Boot), SLF4J

## Spring Boot

Starter logging defaults overridden for package and framework levels.

## Spring Core

`LoggingDemoService` is a `@Service` bean; runner triggers demo methods on startup.

## XML Configuration

Not used.

## IoC Container

Spring Boot `ApplicationContext` injects `LoggingDemoService` into `LoggingDemoRunner`.

## Bean Scope

Singleton service and runner beans.

## Logging

Required properties:

- `logging.level.org.springframework=info`
- `logging.level.com.cognizant.springlearn=debug`
- Custom `logging.pattern.console` with timestamp, level, thread, logger, message

Service methods log START/END, DEBUG values, WARN, and ERROR with stack trace.

## How to Run

```bash
cd Hands-On-03-Logging
mvn spring-boot:run
mvn test
```

## Expected Output

- Colored/custom-formatted console lines for each demo method
- WARN for deprecated API scenario; ERROR with stack for simulated failure
- Tests pass with `test` profile (no runner output)

## Learning Outcomes

- Tune logging per package without changing code
- Structure operational logs with START/END boundaries
- Keep integration tests quiet using profiles
