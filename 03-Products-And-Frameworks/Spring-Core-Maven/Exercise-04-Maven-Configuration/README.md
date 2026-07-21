# Exercise 04 — Maven Configuration

## Objective

Configure a Maven project with **Spring Context, AOP, and WebMVC** dependencies, **Java 21** via `maven-compiler-plugin` 3.13, compile a minimal library app plus an optional `@RestController`, and load the IoC container from `main`.

## Technologies

- Java 21
- Maven 3.9+
- Spring Framework 6.1.14 (`spring-context`, `spring-aop`, `spring-webmvc`)
- maven-compiler-plugin 3.13.0

## Folder Structure

```
Exercise-04-Maven-Configuration/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/spring/library/
    │   ├── MavenConfigurationDemoApplication.java
    │   ├── model/Book.java
    │   ├── repository/
    │   ├── service/BookService.java
    │   └── web/BookRestController.java
    └── resources/applicationContext.xml
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-04-Maven-Configuration
mvn clean compile
mvn exec:java
```

## Expected Output

```
Maven-managed Spring modules loaded (context, aop, webmvc on classpath).
Active beans: bookRepository, bookService
=== Library Catalog ===
 - Book{id=1, title='Spring in Action', author='Craig Walls'}
 - Book{id=2, title='Effective Java', author='Joshua Bloch'}
```

## Key Concepts

- **Maven dependency management** — coordinate Spring modules in `pom.xml`
- **Compiler plugin** — `<release>21</release>` for modern Java bytecode
- **Layered classpath** — WebMVC types compile without starting a servlet container
- **XML bean configuration** — same IoC pattern as earlier exercises

## Screenshots

<!-- Add screenshot of `mvn exec:java` console output here -->
