# Exercise 06 — Annotation Configuration

## Objective

Replace XML bean definitions with **Java configuration**: `@Configuration`, `@ComponentScan`, stereotype annotations (`@Service`, `@Repository`), and `@Autowired` wiring.

## Technologies

- Java 21
- Maven 3.9+
- Spring Framework 6.1.14 (`spring-context`)

## Folder Structure

```
Exercise-06-Annotation-Configuration/
├── pom.xml
├── README.md
└── src/main/
    └── java/com/cognizant/spring/library/
        ├── AnnotationConfigurationDemoApplication.java
        ├── config/AppConfig.java
        ├── model/Book.java
        ├── repository/
        └── service/BookService.java
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-06-Annotation-Configuration
mvn clean compile
mvn exec:java
```

## Expected Output

```
Java @Configuration loaded (no XML bean definitions).
BookService wired via @Autowired constructor/setter injection.
=== Library Catalog ===
 ...
```

## Key Concepts

- **`@Configuration`** — Java-based Spring configuration class
- **`@ComponentScan`** — detect `@Service` and `@Repository` beans
- **`@Autowired`** — inject dependencies by type
- **`AnnotationConfigApplicationContext`** — bootstrap without XML

## Screenshots

<!-- Add screenshot of `mvn exec:java` console output here -->
