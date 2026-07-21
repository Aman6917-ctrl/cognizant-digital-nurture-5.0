# Hands-On 1 — Spring Data JPA Quick Example (`orm-learn`)

## Objective

Bootstrap a Spring Boot application with **Spring Data JPA**, connect to **MySQL 8**, load sample data, and list countries using a transactional service layer.

## Concepts Covered

- Spring Boot auto-configuration
- `JpaRepository` and derived queries
- `@Transactional` read-only service methods
- DDL validation (`ddl-auto=validate`)
- SQL initialization (`schema.sql`, `data.sql`)
- Hibernate SQL logging

## Technologies Used

- Java 21, Maven, Spring Boot 3.3.5
- Spring Data JPA, Hibernate 6, MySQL 8
- SLF4J (Logback)

## Folder Structure

```
Hands-On-01-Quick-Example/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/ormlearn/
    │   ├── OrmLearnApplication.java
    │   ├── demo/QuickExampleRunner.java
    │   ├── model/Country.java
    │   ├── repository/CountryRepository.java
    │   └── service/CountryService.java
    └── resources/
        ├── application.properties
        ├── schema.sql
        └── data.sql
```

## Database Setup

1. Start MySQL 8 and create database `orm_learn` (or use Docker from module README).
2. Set credentials via env vars if needed: `MYSQL_USER`, `MYSQL_PASSWORD`.

## Configuration

`application.properties` configures MySQL JDBC URL, `ddl-auto=validate`, Hibernate dialect, and DEBUG SQL logging.

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Data-JPA/Hands-On-01-Quick-Example
mvn clean compile
mvn spring-boot:run
```

**Tests (H2, no MySQL required):**

```bash
mvn test
```

## Expected Output

Console prints countries from `data.sql`, for example:

```
Country{countryId=1, countryCode='IN', countryName='India'}
Country{countryId=2, countryCode='US', countryName='United States'}
Country{countryId=3, countryCode='GB', countryName='United Kingdom'}
```

Logs show Hibernate SQL and repository interaction counts.

## Screenshots Placeholder

<!-- Screenshot: spring-boot:run console with country list and SQL logs -->

## Learning Outcomes

- Wire Spring Data JPA repositories without boilerplate DAO code
- Validate schema against entities safely in production-like setups
- Use `@Transactional` at the service boundary for consistent reads
