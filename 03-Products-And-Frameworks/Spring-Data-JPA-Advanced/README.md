# Spring Data JPA — Query Methods & Relationships

Advanced hands-on exercises **1–6** (Java 21, Spring Boot 3.3.x, Hibernate 6, MySQL 8).

| Hands-On | Project |
|----------|---------|
| 1 | [Hands-On-01-Country-Query-Methods](Hands-On-01-Country-Query-Methods/) |
| 2 | [Hands-On-02-Stock-Query-Methods](Hands-On-02-Stock-Query-Methods/) |
| 3–6 | [Hands-On-03-Relationships](Hands-On-03-Relationships/) — mapping, `@ManyToOne`, `@OneToMany`, `@ManyToMany` |

```bash
docker run -d --name nurture-mysql -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=jpa_advanced -p 3306:3306 mysql:8.0
```

Validate: `for d in Hands-On-*; do (cd "$d" && mvn clean compile) || exit 1; done`
