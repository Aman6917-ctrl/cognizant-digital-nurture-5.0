# Exercise 01 — Basic Spring Application

## Objective

Create a minimal Spring application using **XML bean configuration**, wire `BookRepository` into `BookService`, load the IoC container, and run library operations from a `main` method.

## Technologies

- Java 21
- Maven 3.9+
- Spring Framework 6.1.14 (`spring-context`)

## Folder Structure

```
Exercise-01-Basic-Spring-Application/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/spring/library/
    │   ├── LibraryManagementApplication.java
    │   ├── model/Book.java
    │   ├── repository/
    │   └── service/BookService.java
    └── resources/applicationContext.xml
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-01-Basic-Spring-Application
mvn clean compile
mvn exec:java
```

## Expected Output

```
Spring context loaded successfully.
=== Library Catalog ===
 - Book{id=1, title='Spring in Action', author='Craig Walls'}
 - Book{id=2, title='Effective Java', author='Joshua Bloch'}
Registered: Book{id=3, title='Clean Code', author='Robert C. Martin'}
=== Library Catalog ===
 ...
```

## Key Concepts

- **IoC container** — `ClassPathXmlApplicationContext`
- **Bean definitions** — `<bean>` elements in `applicationContext.xml`
- **Setter injection** — `<property name="bookRepository" ref="bookRepository"/>`
- **Separation of concerns** — service vs repository layers

## Screenshots

<!-- Add screenshot of `mvn exec:java` console output here -->
