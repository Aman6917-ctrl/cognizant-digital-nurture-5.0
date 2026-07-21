# Exercise 06 — Pagination and Sorting

## Objective

Return paginated, sorted employee lists using `Page`, `Pageable`, and `Sort`.

## Concepts

- Spring Data pagination
- `@PageableDefault`
- Page metadata in JSON responses

## Folder Structure

Layered CRUD with pageable list endpoint.

## Technologies

Java 21, Spring Boot 3.3.5, Spring Data JPA

## Architecture

`GET /api/employees` accepts `page`, `size`, and `sort` query parameters.

## Database Config

H2 `jdbc:h2:mem:ems_ex6`

## REST APIs

| GET | `/api/employees?page=0&size=10&sort=name,asc` |

## Sample Requests

```bash
curl "http://localhost:8080/api/employees?page=0&size=2&sort=email,desc"
```

## Expected Output

Spring Data `Page` JSON with `content`, `totalElements`, `totalPages`.

## How to Run

```bash
cd Exercise-06-Pagination-Sorting
mvn test
```

## Learning Outcomes

- Apply pagination and sorting without writing SQL
- Test pageable repository methods
