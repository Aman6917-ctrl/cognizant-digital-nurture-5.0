# Hands-On 20 — Employee DELETE REST Service

## Objective

Implement `DELETE /employees/{id}` to remove employees from the XML-backed list and expose `GET /employees` for verification.

## Concepts Covered

- `@DeleteMapping` and `@PathVariable`
- HTTP 204 No Content on success
- `EmployeeNotFoundException` when ID missing
- `@Transactional` delete in service layer

## Folder Structure

```
Hands-On-20-Employee-DELETE-Service/
├── pom.xml
├── README.md
├── src/main/resources/employee.xml
└── src/main/java/com/cognizant/springlearn/
    ├── exception/EmployeeNotFoundException.java
    ├── dao/EmployeeDao.java (deleteEmployee, getAllEmployees)
    ├── service/EmployeeService.java
    └── web/EmployeeController.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Jackson, JDBC/H2, MockMvc, SLF4J

## REST Methods

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/employees` | List remaining employees |
| DELETE | `/employees/{id}` | Remove employee by id |

## Validation

Path variable `id` must reference an existing employee.

## Global Exception Handling

`EmployeeNotFoundException` → 404.

## JSON Mapping

GET returns employee arrays with `@JsonFormat` dates (`dd/MM/yyyy`).

## MockMvc Testing

- DELETE success (204) and reduced list size
- DELETE not found (404)
- GET initial catalog size

## Sample Requests

```bash
curl http://localhost:8080/employees
curl -X DELETE http://localhost:8080/employees/102
```

## Sample Responses

DELETE success: **204 No Content**.

GET after delete: JSON array with one fewer employee.

## How to Run

```bash
cd Hands-On-20-Employee-DELETE-Service
mvn clean test
mvn spring-boot:run
```

## Learning Outcomes

- RESTful delete semantics
- Safe removal from shared in-memory store
- Test ordered delete scenarios with MockMvc
