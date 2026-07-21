# Hands-On 14 — Employee REST Service

## Objective

Expose employees loaded from Spring XML as a JSON REST API using a **Controller → Service → DAO** stack with **@Transactional** read-only service methods.

## Concepts Covered

- Spring Core XML (`employee.xml`)
- `ClassPathXmlApplicationContext` in DAO constructor
- Layered architecture
- Spring MVC REST
- Transaction management (`@EnableTransactionManagement`)
- SLF4J structured logging (START/END)
- MockMvc integration tests

## Folder Structure

```
Hands-On-14-Employee-REST-Service/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/springlearn/
    │   ├── EmployeeRestApplication.java
    │   ├── model/Employee.java, Department.java, Skill.java
    │   ├── dao/EmployeeDao.java
    │   ├── service/EmployeeService.java
    │   └── web/EmployeeController.java
    └── resources/
        ├── application.properties
        └── employee.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring JDBC (transaction manager), H2, JUnit 5, MockMvc, SLF4J

## Controller Layer

`EmployeeController` — `GET /employees` returns `List<Employee>` as JSON.

## Service Layer

`EmployeeService` — `@Service`, `@Transactional(readOnly = true)` on `getAllEmployees()`.

## DAO Layer

`EmployeeDao` — static `EMPLOYEE_LIST`, constructor loads `employee.xml`, `getAllEmployees()`.

## XML Configuration

`employee.xml` defines 3 departments, 5 skills, 5 employees, `employeeList`, and `departmentList` beans using `<bean>`, `<property>`, `<list>`, `<ref>`.

## REST APIs

| Method | URL | Response |
|--------|-----|----------|
| GET | `/employees` | JSON array of 5 employees |

## Sample Requests

```bash
curl http://localhost:8080/employees
```

## Sample Responses

```json
[
  {
    "id": 101,
    "name": "Aisha Khan",
    "email": "aisha.khan@example.com",
    "department": { "id": 1, "name": "Engineering" },
    "skills": [ { "id": 1, "name": "Java" }, { "id": 2, "name": "Spring" } ]
  }
]
```

## Transaction Management

`@EnableTransactionManagement` on the application class; H2 datasource enables Spring’s `PlatformTransactionManager` for `@Transactional` boundaries on the service layer.

## How to Run

```bash
cd Hands-On-14-Employee-REST-Service
mvn spring-boot:run
mvn test
```

## Learning Outcomes

- Wire XML-defined domain graphs into REST APIs without JPA
- Separate concerns across controller, service, and DAO layers
- Apply read-only transactions at the service boundary
