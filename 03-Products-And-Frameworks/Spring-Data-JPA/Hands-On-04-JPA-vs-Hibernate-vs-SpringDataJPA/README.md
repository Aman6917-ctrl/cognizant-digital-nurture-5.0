# Hands-On 4 — JPA vs Hibernate vs Spring Data JPA

## Objective

In one Spring Boot app, run the same **Country** CRUD twice: once with the **Hibernate Session API** (`EntityManager.unwrap(Session.class)`) and once with **Spring Data JPA** (`JpaRepository`), then compare approaches.

## Concepts Covered

- JPA as specification; Hibernate as provider
- Repository abstraction vs manual Session/HQL
- Declarative `@Transactional` in Spring
- Shared entity mapping and schema validation

## Technologies Used

- Java 21, Spring Boot 3.3.5, Spring Data JPA, Hibernate 6, MySQL 8
- H2 for unit tests

## Folder Structure

```
Hands-On-04-JPA-vs-Hibernate-vs-SpringDataJPA/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/ormcompare/
    ├── OrmCompareApplication.java
    ├── demo/ComparisonDemoRunner.java
    ├── hibernate/HibernateCountryDao.java
    └── springdata/
        ├── model/Country.java
        ├── repository/CountryRepository.java
        └── service/SpringDataCountryService.java
```

## Comparison Table

| Aspect | JPA (API) | Hibernate (Session API) | Spring Data JPA |
|--------|-----------|-------------------------|-----------------|
| **What it is** | Standard persistence API (`EntityManager`) | Native ORM with `Session`, HQL, caching hooks | Spring abstraction over `JpaRepository` |
| **Advantages** | Portable across providers; vendor-neutral | Full ORM control; HQL; fine-grained session behavior | Minimal boilerplate; derived queries; pagination |
| **Disadvantages** | Still need provider and config | Tied to Hibernate; more manual code | Less control over SQL; magic method names |
| **Architecture** | Entity + `EntityManager` in service/DAO | Entity + `Session` / HQL in DAO | Entity + interface repository + service |
| **Repository abstraction** | You implement DAOs | You implement DAOs with Session | Framework generates implementation |
| **Transaction management** | `@Transactional` or JTA | `@Transactional` + Session join current transaction | `@Transactional` on service; repo participates |

## Database Setup

MySQL `country_db` with env overrides (`MYSQL_*`). `schema.sql` / `data.sql` seed sample rows.

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Data-JPA/Hands-On-04-JPA-vs-Hibernate-vs-SpringDataJPA
mvn clean compile
mvn spring-boot:run
```

**Tests (H2):**

```bash
mvn test
```

## Expected Output

Two labeled sections in logs — **Hibernate Session API path** and **Spring Data JPA path** — each listing countries, save/update/delete for a temporary row (`DE` / `JP`).

## Screenshots Placeholder

<!-- Screenshot: side-by-side log sections for Hibernate vs Spring Data -->

## Learning Outcomes

- See that Spring Data JPA still uses Hibernate under the hood
- Choose Session API when you need explicit ORM control; choose Spring Data for speed and consistency
