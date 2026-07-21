# Hands-On 2 — Hibernate XML Mapping & Session API

## Objective

Use **standalone Hibernate 6** (no Spring Boot) with **XML mapping** (`.hbm.xml`), `SessionFactory`, `Session`, and `Transaction` to perform full CRUD and observe **transaction rollback**.

## Concepts Covered

- `hibernate.cfg.xml` and `Country.hbm.xml`
- POJO entity without JPA annotations
- `Configuration` → `SessionFactory`
- Hibernate Query Language (HQL)
- Commit vs rollback

## Technologies Used

- Java 21, Maven
- Hibernate ORM 6.4.x
- MySQL 8 (`mysql-connector-j`)
- SLF4J + Logback

## Folder Structure

```
Hands-On-02-Hibernate-XML/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/hibernatexml/
    │   ├── HibernateXmlCrudApplication.java
    │   ├── config/HibernateConfigHelper.java
    │   └── model/Country.java
    └── resources/
        ├── hibernate.cfg.xml
        ├── Country.hbm.xml
        └── logback.xml
```

## Database Setup

Start MySQL 8 (see module [README](../README.md) for Docker). Default database: `country_db`.

Environment variables (same pattern as Hands-On 1):

| Variable | Default |
|----------|---------|
| `MYSQL_HOST` | `localhost` |
| `MYSQL_PORT` | `3306` |
| `MYSQL_DATABASE` | `country_db` |
| `MYSQL_USER` | `root` |
| `MYSQL_PASSWORD` | `root` |

`HibernateConfigHelper` applies these overrides after loading `hibernate.cfg.xml`.

## Configuration

- **Table:** `country` with columns `country_id`, `country_code`, `country_name`
- **`hibernate.hbm2ddl.auto=update`** creates/updates schema for local demos

## How to Run

```bash
cd 03-Products-And-Frameworks/Spring-Data-JPA/Hands-On-02-Hibernate-XML
mvn clean compile
mvn exec:java
```

## Expected Output

- Logs for **SAVE**, **GET**, **LIST**, **UPDATE**, **DELETE**
- Hibernate SQL in DEBUG
- Rollback section: failed transaction (duplicate `country_code`), **row count unchanged** after rollback

## Screenshots Placeholder

<!-- Screenshot: exec:java output with CRUD and rollback messages -->

## Learning Outcomes

- Map entities with XML instead of annotations
- Manage persistence manually with Session and Transaction
- Understand why rollback prevents partial commits
