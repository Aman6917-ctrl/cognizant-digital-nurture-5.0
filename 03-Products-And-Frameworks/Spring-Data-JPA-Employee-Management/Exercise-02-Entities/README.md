# Exercise 02 — Entities

## Objective

Model `Department` and `Employee` JPA entities with validation, Lombok, and bidirectional `@OneToMany` / `@ManyToOne` mapping.

## Concepts

- JPA entity mapping, associations, and cascade
- Jakarta Bean Validation on entities
- Optional data initialization at startup

## Folder Structure

```
Exercise-02-Entities/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/ems/entities/
    ├── EmployeeManagementApplication.java
    ├── entity/Department.java, Employee.java
    └── config/SampleDataLoader.java
```

## Technologies

Java 21, Spring Boot 3.3.5, Spring Data JPA, Validation, H2, Lombok

## Architecture

Entities persist via JPA; `SampleDataLoader` seeds one department and employee on startup.

## Database Config

H2 `jdbc:h2:mem:ems_ex2`, `ddl-auto=update`, H2 console enabled.

## REST APIs

None required; data is loaded via `CommandLineRunner`.

## Sample Requests

N/A — verify via H2 console or logs after startup.

## Expected Output

Application starts; SQL insert logs for sample department and employee.

## How to Run

```bash
cd Exercise-02-Entities
mvn spring-boot:run
```

## Learning Outcomes

- Define entity relationships and validation constraints
- Use Lombok to reduce boilerplate
- Confirm schema generation with H2
