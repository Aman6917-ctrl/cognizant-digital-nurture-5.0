# Hands-On 4 — HQL Aggregate Functions

## Objective

Use JPQL **aggregate functions** and **GROUP BY** with `@Param` to summarize employee payroll data.

## Concepts Covered

- `AVG`, `COUNT`, `MAX`, `MIN`, `SUM`
- Grouped queries returning `List<Object[]>`
- Scalar query results as `BigDecimal`

## Folder Structure

```
Hands-On-04-HQL-Aggregate-Functions/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/hql/aggregate/
    ├── AggregateApplication.java
    ├── demo/AggregateDemoRunner.java
    ├── model/Department.java, Employee.java
    └── repository/EmployeeRepository.java
```

## Technologies Used

Java 21, Spring Boot 3.3.5, Spring Data JPA, MySQL 8, H2 (tests)

## Database Setup

Database `hql_aggregate`.

## Configuration

Standard validate + SQL init; SQL logging enabled.

## HQL / JPQL

Repository methods: `averageSalaryByDepartmentName`, `countEmployeesByDepartment`, `maxSalary`, `minSalary`, `sumSalaryForPermanentEmployees`.

## Native Query

Not used.

## Criteria API

Not used.

## How to Run

```bash
cd Hands-On-04-HQL-Aggregate-Functions
mvn clean compile
mvn spring-boot:run
mvn test
```

## Expected Output

Printed averages, per-department counts, min/max for Engineering, and sum of permanent salaries.

## Learning Outcomes

- Map aggregate JPQL to Spring Data return types.
- Interpret multi-column `GROUP BY` results.
