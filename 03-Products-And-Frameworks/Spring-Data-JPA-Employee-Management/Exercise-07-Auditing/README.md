# Exercise 07 — JPA Auditing

## Objective

Track creation and modification metadata on `Employee` using Spring Data JPA auditing.

## Concepts

- `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy`
- `@EnableJpaAuditing` and `AuditorAware`

## Folder Structure

`config/JpaAuditingConfig.java`, `entity/AuditableEntity.java`, repositories, REST CRUD.

## Technologies

Java 21, Spring Boot 3.3.5, Spring Data JPA Auditing

## Architecture

Auditable mapped superclass; fixed auditor user `training-user`.

## Database Config

H2 `jdbc:h2:mem:ems_ex7`

## REST APIs

Standard `/api/employees` CRUD; responses include audit fields.

## Sample Requests

```bash
curl -X POST http://localhost:8080/api/employees -H "Content-Type: application/json" -d '{"name":"Dan","email":"dan@cognizant.com","departmentId":1}'
```

## Expected Output

Employee JSON includes `createdAt`, `updatedAt`, `createdBy`, `updatedBy`.

## How to Run

```bash
cd Exercise-07-Auditing
mvn test
```

## Learning Outcomes

- Enable JPA auditing in Spring Boot
- Provide auditor identity via `AuditorAware`
