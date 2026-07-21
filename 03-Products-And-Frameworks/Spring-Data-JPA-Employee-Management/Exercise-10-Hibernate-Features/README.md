# Exercise 10 — Hibernate Batch Features

## Objective

Configure Hibernate JDBC batching and demonstrate bulk inserts with `@DynamicUpdate`.

## Concepts

- `hibernate.jdbc.batch_size`
- `order_inserts` / `order_updates`
- `jdbc.batch_versioned_data`
- `@DynamicUpdate` for partial SQL updates

## Folder Structure

`service/BatchInsertDemoService.java`, `config/BatchDemoRunner.java`

## Technologies

Java 21, Spring Boot 3.3.5, Hibernate 6

## Architecture

On startup, `BatchInsertDemoService` persists many employees in one transaction to exercise batch settings.

## Database Config

H2 `jdbc:h2:mem:ems_ex10` with batch properties in `application.properties`.

## REST APIs

| GET | `/api/batch/demo-status` |

## Sample Requests

```bash
curl http://localhost:8080/api/batch/demo-status
```

## Expected Output

JSON indicating how many employees were batch-inserted.

## How to Run

```bash
cd Exercise-10-Hibernate-Features
mvn test
```

## Learning Outcomes

- Know when to enable JDBC batching (bulk loads, reduced round trips)
- Understand ordering requirements for batching
- Use `@DynamicUpdate` to limit UPDATE column sets
