# Exercise 03 — Repositories

## Objective

Use Spring Data JPA derived query methods on `EmployeeRepository` and `DepartmentRepository`.

## Concepts

- Repository interfaces extending `JpaRepository`
- Derived query method naming conventions
- `@DataJpaTest` slice tests

## Folder Structure

Standard Maven layout under `com.cognizant.ems.repositories`.

## Technologies

Java 21, Spring Boot 3.3.5, Spring Data JPA, H2

## Architecture

Entities + repositories only; tests validate derived queries against H2.

## Database Config

H2 `jdbc:h2:mem:ems_ex3`

## REST APIs

None.

## Sample Requests

N/A

## Expected Output

All `@DataJpaTest` tests pass.

## How to Run

```bash
cd Exercise-03-Repositories
mvn test
```

## Learning Outcomes

- Declare derived finder methods
- Write focused repository tests with `@DataJpaTest`
