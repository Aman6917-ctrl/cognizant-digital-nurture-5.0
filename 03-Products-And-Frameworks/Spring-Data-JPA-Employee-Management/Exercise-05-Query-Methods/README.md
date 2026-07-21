# Exercise 05 — Query Methods

## Objective

Combine derived queries, `@Query` JPQL, and `@NamedQuery` with search REST endpoints.

## Concepts

- JPQL vs named queries
- Repository `@Query` annotations
- Search APIs

## Folder Structure

Same layered layout as Exercise 04 plus named queries on `Employee`.

## Technologies

Java 21, Spring Boot 3.3.5, Spring Data JPA

## Architecture

CRUD + dedicated search endpoints backed by multiple query styles.

## Database Config

H2 `jdbc:h2:mem:ems_ex5`

## REST APIs

| GET | `/api/employees/search?q=` |
| GET | `/api/employees/search/by-domain?domain=` |
| GET | `/api/employees/by-department/{id}` |

## Sample Requests

```bash
curl "http://localhost:8080/api/employees/search?q=ali"
curl "http://localhost:8080/api/employees/search/by-domain?domain=cognizant.com"
```

## Expected Output

JSON arrays of matching employees.

## How to Run

```bash
cd Exercise-05-Query-Methods
mvn test
```

## Learning Outcomes

- Choose between derived, JPQL, and named queries
- Expose search operations via REST
