# Exercise 07 — Constructor and Setter Injection

## Objective

Compare **constructor injection** (`BookService` requires `BookRepository`) and **setter injection** (`CatalogReporter` receives `BookService`) using XML configuration and a console demo.

## Technologies

- Java 21
- Maven 3.9+
- Spring Framework 6.1.14 (`spring-context`)

## Folder Structure

```
Exercise-07-Constructor-Setter-Injection/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/spring/library/
    │   ├── InjectionStylesDemoApplication.java
    │   ├── model/Book.java
    │   ├── repository/
    │   └── service/
    └── resources/applicationContext.xml
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-07-Constructor-Setter-Injection
mvn clean compile
mvn exec:java
```

## Expected Output

```
Constructor injection: BookService -> constructor-injected InMemoryBookRepository
Setter injection: CatalogReporter -> setter-injected BookService
CatalogReporter: library contains 2 book(s).
=== Library Catalog ===
 ...
```

## Key Concepts

- **Constructor injection** — mandatory dependencies supplied at creation (`<constructor-arg>`)
- **Setter injection** — optional or mutable collaborators via `<property>`
- **Immutability** — constructor injection supports `final` fields
- **Composition** — `CatalogReporter` delegates to `BookService`

## Screenshots

<!-- Add screenshot of `mvn exec:java` console output here -->
