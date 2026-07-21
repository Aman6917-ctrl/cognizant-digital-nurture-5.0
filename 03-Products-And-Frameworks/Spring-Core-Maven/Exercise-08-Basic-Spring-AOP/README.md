# Exercise 08 — Basic Spring AOP

## Objective

Implement **multiple advice types** (`@Before`, `@AfterReturning`, `@AfterThrowing`) on `BookService` join points, enable `aop:aspectj-autoproxy`, and demonstrate exception advice with a method that throws.

## Technologies

- Java 21
- Maven 3.9+
- Spring Framework 6.1.14 (`spring-context`, `spring-aop`)
- AspectJ Weaver 1.9.22.1

## Folder Structure

```
Exercise-08-Basic-Spring-AOP/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/spring/library/
    │   ├── BasicAopDemoApplication.java
    │   ├── aspect/LoggingAspect.java
    │   ├── model/Book.java
    │   ├── repository/
    │   └── service/BookService.java
    └── resources/applicationContext.xml
```

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven/Exercise-08-Basic-Spring-AOP
mvn clean compile
mvn exec:java
```

## Expected Output

```
Basic Spring AOP demo (@Before, @AfterReturning, @AfterThrowing).
[AOP @Before] Entering BookService.displayCatalog()
=== Library Catalog ===
 ...
[AOP @AfterReturning] displayCatalog returned: null
[AOP @Before] Entering BookService.requireBook(..)
[AOP @AfterThrowing] requireBook threw IllegalArgumentException: Book not found for id 999
Application handled: Book not found for id 999
```

## Key Concepts

- **Pointcuts** — reusable `@Pointcut` expressions targeting service methods
- **Before advice** — log entry without changing behavior
- **After returning** — observe successful results
- **After throwing** — handle failures such as `requireBook` not finding a record

## Screenshots

<!-- Add screenshot of `mvn exec:java` console output here -->
