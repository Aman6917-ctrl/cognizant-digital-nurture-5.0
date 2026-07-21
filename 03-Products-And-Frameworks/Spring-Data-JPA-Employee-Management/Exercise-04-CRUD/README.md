# Exercise 04 — CRUD REST API

## Objective

Implement full CRUD for employees and departments with service layer and global exception handling.

## Concepts

- Layered architecture (web → service → repository)
- DTO validation and `GlobalExceptionHandler`
- MockMvc / `@WebMvcTest`

## Folder Structure

`web`, `service`, `repository`, `entity`, `dto`, `exception` packages.

## Technologies

Java 21, Spring Boot 3.3.5, Spring Web, Data JPA, Validation, H2

## Architecture

REST controllers delegate to transactional services backed by JPA repositories.

## Database Config

H2 `jdbc:h2:mem:ems_ex4`

## REST APIs

| Method | Path |
|--------|------|
| GET/POST | `/api/employees`, `/api/departments` |
| GET/PUT/DELETE | `/api/employees/{id}`, `/api/departments/{id}` |

## Sample Requests

```bash
curl -X POST http://localhost:8080/api/departments -H "Content-Type: application/json" -d '{"name":"Engineering"}'
curl -X POST http://localhost:8080/api/employees -H "Content-Type: application/json" -d '{"name":"Alice","email":"alice@cognizant.com","departmentId":1}'
```

## Expected Output

`201 Created` with JSON body for POST; `404` with error message when resource missing.

## How to Run

```bash
cd Exercise-04-CRUD
mvn spring-boot:run
```

## Learning Outcomes

- Build CRUD REST APIs with validation
- Centralize error handling
- Test controllers with MockMvc
