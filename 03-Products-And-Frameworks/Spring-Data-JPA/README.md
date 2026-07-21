# Spring Data JPA — Cognizant Digital Nurture

Hands-on exercises **1–9** for ORM, Hibernate, JPA, and Spring Data JPA with **Java 21**, **Spring Boot 3.3.x**, **Hibernate 6**, and **MySQL 8**.

| Hands-On | Project folder | Description |
|----------|----------------|-------------|
| 1 | [Hands-On-01-Quick-Example](Hands-On-01-Quick-Example/) | Spring Data JPA quick start (`orm-learn`) |
| 2 | [Hands-On-02-Hibernate-XML](Hands-On-02-Hibernate-XML/) | Hibernate XML mapping & Session API |
| 3 | [Hands-On-03-Hibernate-Annotations](Hands-On-03-Hibernate-Annotations/) | Hibernate annotation mapping |
| 4 | [Hands-On-04-JPA-vs-Hibernate-vs-SpringDataJPA](Hands-On-04-JPA-vs-Hibernate-vs-SpringDataJPA/) | Side-by-side Hibernate vs Spring Data JPA |
| 5–9 | [Hands-On-05-Country-Management](Hands-On-05-Country-Management/) | Country CRUD service (continued across HO 5–9) |

## MySQL setup (all Boot projects)

```bash
docker run -d --name nurture-mysql -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=country_db -p 3306:3306 mysql:8.0
```

Default JDBC URL: `jdbc:mysql://localhost:3306/country_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true`

Override with environment variables: `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_DATABASE`, `MYSQL_USER`, `MYSQL_PASSWORD`.

## Validate compiles

```bash
cd 03-Products-And-Frameworks/Spring-Data-JPA
for d in Hands-On-*; do (cd "$d" && mvn clean compile) || exit 1; done
```
