# Hands-On 19 — Employee PUT REST Service

## Objective

Implement `PUT /employees` to update XML-backed employees with validation, transactions, and `EmployeeNotFoundException` (404).

## Concepts Covered

- `@PutMapping`, `@RequestBody`, `@Valid`
- `@Transactional` service updates
- Nested validation (`Department`, `Skill`)
- `@JsonFormat(pattern="dd/MM/yyyy")` for dates
- `@Min` on salary

## Folder Structure

```
Hands-On-19-Employee-PUT-Service/
├── pom.xml
├── README.md
├── src/main/resources/employee.xml
└── src/main/java/com/cognizant/springlearn/
    ├── exception/EmployeeNotFoundException.java
    ├── model/{Employee,Department,Skill}.java
    ├── dao/EmployeeDao.java (updateEmployee)
    ├── service/EmployeeService.java
    └── web/EmployeeController.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring Validation, Jackson, JDBC/H2 (transactions), MockMvc, SLF4J

## REST Methods

| Method | URL | Description |
|--------|-----|-------------|
| PUT | `/employees` | Update employee by `id` in body |

## Validation

**Employee:** `id`, `name`, `salary` (≥0), `permanent`, `dateOfBirth`, `department`, non-empty `skills`.

**Department:** `id`, `name`.

**Skill:** `id`, `name`.

## Global Exception Handling

`EmployeeNotFoundException` → 404 via `@ResponseStatus`.

## JSON Mapping

Dates use `dd/MM/yyyy` in JSON (e.g. `"15/03/1990"`).

## MockMvc Testing

- PUT success
- PUT employee not found (404)
- PUT validation failure (400)

## Sample Requests

```bash
curl -X PUT http://localhost:8080/employees \
  -H "Content-Type: application/json" \
  -d '{
    "id": 101,
    "name": "Aisha Khan Updated",
    "salary": 95000,
    "permanent": true,
    "dateOfBirth": "15/03/1990",
    "department": { "id": 1, "name": "Engineering" },
    "skills": [ { "id": 1, "name": "Java" } ]
  }'
```

## Sample Responses

Updated employee JSON with new `name` and `salary`.

## How to Run

```bash
cd Hands-On-19-Employee-PUT-Service
mvn clean test
mvn spring-boot:run
```

## Learning Outcomes

- Idempotent-style updates over in-memory catalog
- Validate nested graphs in REST payloads
- Map domain not-found cases to HTTP 404
