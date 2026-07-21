# Exercise 08 — Projections

## Objective

Expose employee data via interface, DTO class, and constructor-based JPQL projections.

## Concepts

- Closed interface projections
- DTO class projections
- `SELECT new ...` constructor expressions

## Folder Structure

`projection/`, `dto/EmployeeDto.java`, repository query methods, REST controller.

## Technologies

Java 21, Spring Boot 3.3.5, Spring Data JPA

## Architecture

Read-only endpoints return slim projection types instead of full entities.

## Database Config

H2 `jdbc:h2:mem:ems_ex8`

## REST APIs

| GET | `/api/employees/summaries` |
| GET | `/api/employees/dtos` |
| GET | `/api/employees/constructor-dtos` |

## Sample Requests

```bash
curl http://localhost:8080/api/employees/summaries
```

## Expected Output

JSON arrays with only `name` and `email` (or DTO fields).

## How to Run

```bash
cd Exercise-08-Projections
mvn test
```

## Learning Outcomes

- Reduce over-fetching with projections
- Pick the right projection style for each use case
