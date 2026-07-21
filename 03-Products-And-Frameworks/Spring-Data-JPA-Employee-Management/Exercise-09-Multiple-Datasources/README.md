# Exercise 09 — Multiple Datasources

## Objective

Configure primary and secondary H2 datasources with separate entity managers and repositories.

## Concepts

- `@ConfigurationProperties` for datasource binding
- Multiple `EntityManagerFactory` beans
- `@Primary` vs secondary persistence units

## Folder Structure

`config/primary`, `config/secondary`, entity packages per database.

## Technologies

Java 21, Spring Boot 3.3.5, Spring Data JPA

## Architecture

Primary PU hosts `Employee`/`Department`; secondary PU hosts `AuditEvent`.

## Database Config

`ems_ex9_primary` and `ems_ex9_secondary` in-memory URLs.

## REST APIs

| GET | `/api/employees` |
| GET | `/api/audit-events` |

## Sample Requests

```bash
curl http://localhost:8080/api/audit-events
```

## Expected Output

Lists from each database independently.

## How to Run

```bash
cd Exercise-09-Multiple-Datasources
mvn test
```

## Learning Outcomes

- Wire multiple datasources explicitly
- Understand when Boot auto-config is disabled
