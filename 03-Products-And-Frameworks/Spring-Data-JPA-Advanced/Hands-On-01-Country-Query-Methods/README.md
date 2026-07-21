# Hands-On 1 — Country Query Methods

## Objective

Practice **Spring Data JPA derived query methods** on a `Country` entity and execute them from a Boot application.

## Concepts Covered

- Derived query method naming (`Containing`, `OrderBy`, `StartingWith`)
- `JpaRepository` integration
- `@Transactional` read-only service layer
- SQL logging with Hibernate 6

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Data JPA, Hibernate 6, MySQL 8, SLF4J

## Folder Structure

```
Hands-On-01-Country-Query-Methods/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/ormlearn/
    ├── OrmLearnApplication.java
    ├── demo/CountryQueryMethodsDemoRunner.java
    ├── model/Country.java
    ├── repository/CountryRepository.java
    └── service/CountryService.java
```

## Database Setup

MySQL database `orm_learn_query` (auto-created). Docker example in module `README.md`.

## Configuration

`ddl-auto=validate`, `schema.sql` + `data.sql`, DEBUG SQL logging.

## How to Run

```bash
cd Hands-On-01-Country-Query-Methods
mvn clean compile
mvn spring-boot:run
mvn test   # H2, no MySQL required
```

## Expected Output

Printed result sets for:

- `findByNameContaining("an")` → India, United **An**…
- `findByNameContainingOrderByNameAsc("a")` → sorted matches
- `findByNameStartingWith("U")` → United States, United Arab Emirates

## Learning Outcomes

- Map method names to SQL predicates without `@Query`
- Keep query logic in repositories and orchestration in services
