# Hands-On 15 — Department REST Service

## Objective

Expose departments from the shared **`employee.xml`** catalog via `GET /departments`, using the same layered architecture and transaction boundaries as the employee service.

## Concepts Covered

- Reusing XML configuration across modules
- `DepartmentDao` with static `DEPARTMENT_LIST`
- Controller → Service → DAO
- `@Transactional` service methods
- MockMvc JSON array validation

## Folder Structure

```
Hands-On-15-Department-REST-Service/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/springlearn/
    │   ├── DepartmentRestApplication.java
    │   ├── model/
    │   ├── dao/DepartmentDao.java
    │   ├── service/DepartmentService.java
    │   └── web/DepartmentController.java
    └── resources/
        ├── application.properties
        └── employee.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring JDBC, H2, MockMvc, SLF4J

## Controller Layer

`DepartmentController` — `GET /departments`.

## Service Layer

`DepartmentService` — `@Transactional(readOnly = true)` `getAllDepartments()`.

## DAO Layer

`DepartmentDao` — loads `departmentList` from `employee.xml` into static `DEPARTMENT_LIST`.

## XML Configuration

Same `employee.xml` as Hands-On 14 (departments, skills, employees, lists).

## REST APIs

| Method | URL | Response |
|--------|-----|----------|
| GET | `/departments` | JSON array of 3 departments |

## Sample Requests

```bash
curl http://localhost:8080/departments
```

## Sample Responses

```json
[
  { "id": 1, "name": "Engineering" },
  { "id": 2, "name": "Human Resources" },
  { "id": 3, "name": "Operations" }
]
```

## Transaction Management

`@EnableTransactionManagement` + JDBC transaction manager (H2) for `@Transactional` on `DepartmentService`.

## How to Run

```bash
cd Hands-On-15-Department-REST-Service
mvn spring-boot:run
mvn test
```

## Learning Outcomes

- Publish a second REST resource from the same XML master data
- Keep DAO responsibilities focused on XML hydration and in-memory access
