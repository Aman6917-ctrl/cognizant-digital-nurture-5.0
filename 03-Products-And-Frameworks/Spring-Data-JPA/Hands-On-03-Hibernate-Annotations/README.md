# Hands-On 3 — Hibernate Annotation Mapping

## Objective

Map the `Country` entity with **Hibernate/JPA annotations** (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`) and run the same **Session API** CRUD flow as Hands-On 2.

## Concepts Covered

- Annotation-based O/R mapping
- Registering annotated classes in `hibernate.cfg.xml` via `<mapping class="..."/>`
- Session lifecycle and HQL queries

## Technologies Used

- Java 21, Maven, Hibernate ORM 6.4.x, MySQL 8
- SLF4J + Logback

## Folder Structure

```
Hands-On-03-Hibernate-Annotations/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/hibernateannotations/
    │   ├── HibernateAnnotationCrudApplication.java
    │   ├── config/HibernateConfigHelper.java
    │   └── model/Country.java
    └── resources/
        ├── hibernate.cfg.xml
        └── logback.xml
```

## Database Setup

Same MySQL env vars as Hands-On 2: `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_DATABASE` (default `country_db`), `MYSQL_USER`, `MYSQL_PASSWORD`.

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Data-JPA/Hands-On-03-Hibernate-Annotations
mvn clean compile
mvn exec:java
```

## Expected Output

Console logs for save, get, list, update, delete of a `FR` / France row, plus Hibernate SQL.

## Screenshots Placeholder

<!-- Screenshot: annotation CRUD console output -->

## Learning Outcomes

- Replace XML mappings with annotations while keeping Hibernate Session API
- Align column names (`country_id`, `country_code`, `country_name`) with relational schema
