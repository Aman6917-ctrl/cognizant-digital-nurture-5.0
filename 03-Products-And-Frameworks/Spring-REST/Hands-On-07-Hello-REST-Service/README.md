# Hands-On 7 — Hello REST Service

## Objective

Expose a minimal REST endpoint that returns plain text and practice SLF4J logging at the start and end of a controller method.

## Concepts Covered

- `@RestController` and `@GetMapping`
- Returning `String` from REST handlers (text/plain)
- SLF4J `Logger` in web layer
- Spring Boot web starter and embedded Tomcat

## Folder Structure

```
Hands-On-07-Hello-REST-Service/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/springlearn/
    │   ├── HelloRestApplication.java
    │   └── web/HelloController.java
    └── test/java/com/cognizant/springlearn/
        └── HelloRestApplicationTests.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, SLF4J

## Spring Boot

Executable web application with auto-configured MVC and Jackson (unused for this plain-text endpoint).

## Spring Core

Component scanning registers `HelloController` as a singleton bean.

## XML Configuration

Not used.

## IoC Container

`ApplicationContext` created by Spring Boot at startup.

## Bean Scope

Default singleton for `@RestController`.

## Logging

Controller logs `START hello()` and `END hello()` at INFO.

## How to Run

```bash
cd Hands-On-07-Hello-REST-Service
mvn spring-boot:run
curl http://localhost:8080/hello
mvn test
```

## Expected Output

- `GET /hello` → `Hello World!!`
- Logs include START/END around the handler

## Learning Outcomes

- Map HTTP GET to a Java method with Spring MVC
- Return simple text responses from REST controllers
- Use SLF4J instead of console printing in web code
