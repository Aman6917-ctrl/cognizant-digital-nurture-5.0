# Spring Data JPA — Employee Management

Hands-on module with **10 independent** Spring Boot **3.3.5** / **Java 21** Maven projects. Each exercise builds employee and department domain concepts while introducing Spring Data JPA features incrementally.

## Exercises

| # | Folder | Focus |
|---|--------|--------|
| 01 | [Exercise-01-Project-Setup](./Exercise-01-Project-Setup) | Maven + starters + health/info |
| 02 | [Exercise-02-Entities](./Exercise-02-Entities) | JPA entities & relationships |
| 03 | [Exercise-03-Repositories](./Exercise-03-Repositories) | Derived query methods |
| 04 | [Exercise-04-CRUD](./Exercise-04-CRUD) | REST CRUD + exception handling |
| 05 | [Exercise-05-Query-Methods](./Exercise-05-Query-Methods) | JPQL, `@NamedQuery`, search APIs |
| 06 | [Exercise-06-Pagination-Sorting](./Exercise-06-Pagination-Sorting) | `Pageable` / `Sort` |
| 07 | [Exercise-07-Auditing](./Exercise-07-Auditing) | JPA auditing |
| 08 | [Exercise-08-Projections](./Exercise-08-Projections) | Interface / DTO / constructor projections |
| 09 | [Exercise-09-Multiple-Datasources](./Exercise-09-Multiple-Datasources) | Primary + secondary H2 |
| 10 | [Exercise-10-Hibernate-Features](./Exercise-10-Hibernate-Features) | JDBC batching & `@DynamicUpdate` |

## Common stack

- `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`
- H2 (runtime), Lombok (optional), `spring-boot-starter-test`

## Package base

`com.cognizant.ems.<exercise-subpackage>`

## Prerequisites

- JDK 21
- Apache Maven 3.9+

Ensure Maven uses JDK 21 (for example `export JAVA_HOME=$(/usr/libexec/java_home -v 21)` on macOS).

## Build all exercises

```bash
for d in Exercise-*/; do (cd "$d" && mvn -q clean compile); done
```

Run tests per exercise with `mvn test` inside each folder.
