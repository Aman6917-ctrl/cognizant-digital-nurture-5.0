# Hands-On 2 — HQL Permanent Employees & Fetch Join

## Objective

Compare JPQL that loads **permanent** employees with and without **`LEFT JOIN FETCH`** on a **LAZY** `department`, observing N+1 SQL vs a single joined query.

## Concepts Covered

- Boolean filters in JPQL
- Lazy `@ManyToOne`
- N+1 select problem
- `JOIN FETCH` eager graph loading

## Folder Structure

```
Hands-On-02-HQL-Permanent-Employees/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/hql/permanent/
    ├── PermanentEmployeeApplication.java
    ├── demo/PermanentEmployeeDemoRunner.java
    ├── model/Department.java, Employee.java
    ├── repository/EmployeeRepository.java
    └── service/PermanentEmployeeService.java
```

## Technologies Used

Java 21, Spring Boot 3.3.5, Spring Data JPA, MySQL 8, H2 (tests)

## Database Setup

Database `hql_permanent`. Seed data includes permanent and temporary employees.

## Configuration

Same validate + SQL init pattern as other modules; SQL logging at DEBUG.

## HQL / JPQL

```java
@Query("SELECT e FROM Employee e WHERE e.permanent = true")
List<Employee> findPermanentEmployees();

@Query("SELECT e FROM Employee e LEFT JOIN FETCH e.department WHERE e.permanent = true")
List<Employee> findPermanentEmployeesWithDepartmentFetch();
```

## Native Query

Not covered here.

## Criteria API

Not covered here.

## How to Run

```bash
cd Hands-On-02-HQL-Permanent-Employees
mvn clean compile
mvn spring-boot:run
mvn test
```

## Expected Output

Two sections: first prints departments with extra lazy-load SQL in logs; second loads departments in one fetch-join query.

## Learning Outcomes

- Predict when lazy associations cause N+1 queries.
- Fix read paths with `JOIN FETCH` while keeping mappings LAZY by default.
