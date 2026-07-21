# Exercise 05 — IoC Container

## Objective

Explore the **Spring IoC container**: define beans in `applicationContext.xml`, start `ClassPathXmlApplicationContext`, and retrieve collaborators through the `ApplicationContext` API.

## Technologies

- Java 21
- Maven 3.9+
- Spring Framework 6.1.14 (`spring-context`)

## Folder Structure

```
Exercise-05-IoC-Container/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/spring/library/
    │   ├── IoCContainerDemoApplication.java
    │   ├── model/Book.java
    │   ├── repository/
    │   └── service/BookService.java
    └── resources/applicationContext.xml
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-05-IoC-Container
mvn clean compile
mvn exec:java
```

## Expected Output

```
IoC container type: ClassPathXmlApplicationContext
Registered bean names: bookRepository, bookService
Retrieved bookRepository -> InMemoryBookRepository
Retrieved bookService -> BookService
=== Library Catalog ===
 ...
```

## Key Concepts

- **Inversion of Control** — container owns object lifecycle
- **`ApplicationContext`** — superset of `BeanFactory` with resource/event support
- **Bean lookup** — `getBean(String name, Class<T> type)`
- **Setter injection** — XML `<property>` wiring between beans

## Screenshots

<!-- Add screenshot of `mvn exec:java` console output here -->
