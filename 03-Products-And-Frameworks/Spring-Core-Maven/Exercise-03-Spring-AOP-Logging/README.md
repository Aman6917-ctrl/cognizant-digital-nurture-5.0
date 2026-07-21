# Exercise 03 — Spring AOP Logging

## Objective

Apply **Spring AOP** with an `@Around` aspect that logs method execution time for `BookService`, enabling proxy-based cross-cutting concerns via `aop:aspectj-autoproxy` and an annotation-configured `@Aspect` bean.

## Technologies

- Java 21
- Maven 3.9+
- Spring Framework 6.1.14 (`spring-context`, `spring-aop`)
- AspectJ Weaver 1.9.22.1

## Folder Structure

```
Exercise-03-Spring-AOP-Logging/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/spring/library/
    │   ├── AopLoggingDemoApplication.java
    │   ├── aspect/LoggingAspect.java
    │   ├── model/Book.java
    │   ├── repository/
    │   └── service/BookService.java
    └── resources/applicationContext.xml
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-03-Spring-AOP-Logging
mvn clean compile
mvn exec:java
```

## Expected Output

```
Spring AOP logging enabled (Around advice on BookService).
[AOP] BookService.displayCatalog() completed in ... ms
=== Library Catalog ===
 ...
[AOP] BookService.registerBook(..) completed in ... ms
[AOP] BookService.displayCatalog() completed in ... ms
=== Library Catalog ===
 ...
```

## Key Concepts

- **Aspect-oriented programming** — separate logging from business logic
- **`@Around` advice** — wrap join points and measure elapsed time
- **`aop:aspectj-autoproxy`** — create JDK/CGLIB proxies for advised beans
- **Annotation-based aspects** — `@Aspect` + `@Component` with component-scan

## Screenshots

<!-- Add screenshot of `mvn exec:java` console output here -->
