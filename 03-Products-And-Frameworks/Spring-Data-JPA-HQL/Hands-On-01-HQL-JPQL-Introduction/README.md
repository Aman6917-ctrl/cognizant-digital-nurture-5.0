# Hands-On 1 — HQL & JPQL Introduction

## Objective

Learn **JPQL** with Spring Data `@Query`, compare it to **HQL** executed through `EntityManager`, and practice **SELECT**, **UPDATE**, and **DELETE** DML in object-oriented query language.

## Concepts Covered

- JPQL entity queries vs SQL table queries
- HQL as Hibernate’s superset of JPQL
- `@Query`, `@Param`, `@Modifying`
- Read-only vs write transactions
- Optional `@ManyToMany` skills on `Employee`

## Folder Structure

```
Hands-On-01-HQL-JPQL-Introduction/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/hql/intro/
    ├── HqlIntroApplication.java
    ├── demo/IntroDemoRunner.java
    ├── model/Department.java, Employee.java, Skill.java
    ├── repository/EmployeeRepository.java
    └── service/IntroQueryService.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Data JPA, Hibernate 6, MySQL 8, H2 (tests)

## Database Setup

Default database: `hql_intro`. Schema and seed data load from `schema.sql` and `data.sql`.

```bash
docker run -d --name nurture-mysql -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=hql_intro -p 3306:3306 mysql:8.0
```

## Configuration

- `spring.jpa.hibernate.ddl-auto=validate`
- `spring.sql.init.mode=always` with `defer-datasource-initialization=true`
- `spring.jpa.show-sql=true` and Hibernate SQL DEBUG logging

## HQL vs JPQL

| Aspect | JPQL | HQL |
|--------|------|-----|
| Standard | JPA specification (portable across providers) | Hibernate-specific (extends JPQL) |
| Syntax | Entity names & attributes | Same core grammar; extra functions/features in Hibernate |
| API | `@Query`, Criteria API, `EntityManager.createQuery(String, Class)` | `EntityManager.createQuery` (Hibernate session) |
| Portability | Highest among object queries | Tied to Hibernate |

**Practical rule:** In Spring Boot with Hibernate, strings in `@Query` are JPQL unless you use Hibernate-specific functions. Strings passed to `EntityManager` are often called HQL in documentation; for portable code, stay within JPQL.

### Advantages of object queries (HQL/JPQL)

- Work on **entity graphs**, not column lists—refactoring field names updates queries in one place.
- **Polymorphic** queries (`FROM Employee e` includes subclasses when mapped).
- **Database-independent** (no vendor SQL dialect in the query text).
- Integrates with JPA **caching**, **flush ordering**, and **lifecycle**.

### DML support

| Operation | JPQL example | Notes |
|-----------|--------------|-------|
| SELECT | `SELECT e FROM Employee e WHERE e.salary >= :min` | Returns entities or DTOs |
| UPDATE | `UPDATE Employee e SET e.salary = e.salary * :f WHERE ...` | Requires `@Modifying` + transaction |
| DELETE | `DELETE FROM Employee e WHERE e.permanent = false` | Bulk delete; does not cascade by default |
| INSERT | Not in JPQL | Use `persist()` or native SQL |

**Code samples (this project):**

```java
@Query("SELECT e FROM Employee e WHERE e.salary >= :minSalary ORDER BY e.name")
List<Employee> findHighEarners(@Param("minSalary") BigDecimal minSalary);

@Modifying
@Query("UPDATE Employee e SET e.salary = e.salary * :factor WHERE e.department.name = :deptName")
int applyRaiseInDepartment(@Param("deptName") String deptName, @Param("factor") BigDecimal factor);

// HQL-style string via EntityManager (JPQL-compatible in this demo):
entityManager.createQuery("FROM Employee e WHERE e.permanent = true", Employee.class);
```

## JPQL

Repository methods demonstrate filtered selects and bulk update/delete. Service layer wraps them in `@Transactional` boundaries.

## Native Query

Not used in this module (see Hands-On 5).

## Criteria API

Not used in this module (see Hands-On 6).

## How to Run

```bash
cd Hands-On-01-HQL-JPQL-Introduction
mvn clean compile
mvn spring-boot:run    # requires MySQL
mvn test               # H2, context load only
```

## Expected Output

Console listing of high earners, Engineering staff, permanent employees, then counts of updated and deleted rows from JPQL DML.

## Learning Outcomes

- Choose JPQL for portable object queries in Spring Data repositories.
- Execute equivalent reads through `EntityManager` and relate that to “HQL” terminology.
- Apply `@Modifying` UPDATE/DELETE safely inside transactions.
