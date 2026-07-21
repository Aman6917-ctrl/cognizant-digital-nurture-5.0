# Hands-On 6 — Criteria API

## Objective

Build **dynamic AND predicates** (Amazon-style filters) using the JPA **Criteria API** instead of concatenating JPQL strings.

## Concepts Covered

- `CriteriaBuilder`, `CriteriaQuery`, `Root`, `Predicate`, `TypedQuery`
- Optional filter fields via `EmployeeSearchCriteria` record
- Joins for department and skill filters

## Folder Structure

```
Hands-On-06-Criteria-API/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/hql/criteria/
    ├── CriteriaApplication.java
    ├── demo/CriteriaDemoRunner.java
    ├── model/Department.java, Employee.java, Skill.java
    ├── search/EmployeeSearchCriteria.java
    └── service/EmployeeSearchService.java
```

## Technologies Used

Java 21, Spring Boot 3.3.5, Spring Data JPA, MySQL 8, H2 (tests)

## Database Setup

Database `hql_criteria` with employees, departments, and skills.

## Configuration

Standard validate + SQL init pattern.

## HQL / JPQL

Alternative to Criteria for static queries; Criteria shines when filters are optional.

## Native Query

Not used.

## Criteria API

`EmployeeSearchService.search` composes predicates for salary range, department name, permanent flag, name substring, and skill name.

## How to Run

```bash
cd Hands-On-06-Criteria-API
mvn clean compile
mvn spring-boot:run
mvn test
```

## Expected Output

Four search scenarios with match counts (Engineering permanent, salary band, name contains `a`, Spring Boot skill).

## Learning Outcomes

- Type-safe dynamic queries without string parsing.
- Combine joins and predicates for faceted search patterns.
