# Hands-On 5 — Native Queries

## Objective

Execute **vendor SQL** through Spring Data JPA `nativeQuery = true` and discuss portability trade-offs.

## Concepts Covered

- Native SELECT mapping to entities
- Native scalar `COUNT`
- Optional native UPDATE
- Portability vs SQL features

## Folder Structure

```
Hands-On-05-Native-Queries/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/hql/nativequery/
    ├── NativeQueryApplication.java
    ├── demo/NativeQueryDemoRunner.java
    ├── model/Department.java, Employee.java
    └── repository/EmployeeRepository.java
```

## Technologies Used

Java 21, Spring Boot 3.3.5, Spring Data JPA, MySQL 8, H2 (tests, MySQL compatibility mode)

## Database Setup

Database `hql_native`. Table name `employee` matches entity `@Table`.

## Configuration

Standard validate + SQL init pattern.

## HQL / JPQL

Used for comparison in learning materials; this module focuses on native SQL.

## Native Query

```java
@Query(value = "SELECT * FROM employee", nativeQuery = true)
List<Employee> findAllNative();

@Query(value = "SELECT COUNT(*) FROM employee WHERE permanent = true", nativeQuery = true)
long countPermanentNative();
```

### Advantages

- Full access to database-specific SQL (window functions, hints, stored procedures).
- Easier migration of legacy SQL.
- Can be faster for tuned reporting queries.

### Disadvantages

- **Not portable** across databases without rewrite.
- Result mapping to entities breaks when column names/types diverge from mappings.
- Bypasses some JPA caching and polymorphic semantics.

## Criteria API

Not used.

## How to Run

```bash
cd Hands-On-05-Native-Queries
mvn clean compile
mvn spring-boot:run
mvn test
```

## Expected Output

All employees from native SELECT and permanent count from native COUNT.

## Learning Outcomes

- Decide when native SQL is justified.
- Map native results to JPA entities responsibly.
