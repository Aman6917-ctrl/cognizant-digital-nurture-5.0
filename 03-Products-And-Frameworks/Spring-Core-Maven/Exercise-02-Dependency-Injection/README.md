# Exercise 02 — Dependency Injection

## Objective

Demonstrate **setter-based dependency injection** in Spring: configure `BookService` with a `BookRepository` reference in `applicationContext.xml`, load the container, and prove the service uses the injected collaborator from `main`.

## Technologies

- Java 21
- Maven 3.9+
- Spring Framework 6.1.14 (`spring-context`)

## Folder Structure

```
Exercise-02-Dependency-Injection/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/spring/library/
    │   ├── DependencyInjectionDemoApplication.java
    │   ├── model/Book.java
    │   ├── repository/
    │   └── service/BookService.java
    └── resources/applicationContext.xml
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-02-Dependency-Injection
mvn clean compile
mvn exec:java
```

## Expected Output

```
Spring context loaded successfully.
Dependency injection: BookService received repository bean InMemoryBookRepository
Initial catalog size: 2
=== Library Catalog ===
 - Book{id=1, title='Spring in Action', author='Craig Walls'}
 - Book{id=2, title='Effective Java', author='Joshua Bloch'}
=== Library Catalog ===
 ...
```

## Key Concepts

- **Dependency injection (DI)** — objects receive collaborators instead of creating them
- **Setter injection** — `<property name="bookRepository" ref="bookRepository"/>`
- **Bean wiring** — `ref` attribute links beans by id
- **Inversion of control** — Spring constructs and injects dependencies

## Screenshots

<!-- Add screenshot of `mvn exec:java` console output here -->
