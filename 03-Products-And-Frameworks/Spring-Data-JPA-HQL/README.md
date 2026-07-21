# Spring Data JPA — HQL, JPQL, Native SQL & Criteria API

Hands-on exercises **1–6** (Java 21, Spring Boot 3.3.5, Hibernate 6, MySQL 8).

| Hands-On | Project | Focus |
|----------|---------|--------|
| 1 | [Hands-On-01-HQL-JPQL-Introduction](Hands-On-01-HQL-JPQL-Introduction/) | JPQL `@Query`, HQL via `EntityManager`, UPDATE/DELETE |
| 2 | [Hands-On-02-HQL-Permanent-Employees](Hands-On-02-HQL-Permanent-Employees/) | JPQL filters, `JOIN FETCH`, N+1 vs eager graph |
| 3 | [Hands-On-03-Quiz-HQL](Hands-On-03-Quiz-HQL/) | Multi-join `JOIN FETCH` chain, quiz report |
| 4 | [Hands-On-04-HQL-Aggregate-Functions](Hands-On-04-HQL-Aggregate-Functions/) | `AVG`, `COUNT`, `MAX`, `MIN`, `SUM`, `GROUP BY` |
| 5 | [Hands-On-05-Native-Queries](Hands-On-05-Native-Queries/) | `nativeQuery = true`, SQL portability |
| 6 | [Hands-On-06-Criteria-API](Hands-On-06-Criteria-API/) | Dynamic predicates with Criteria API |

```bash
docker run -d --name nurture-mysql -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=hql_intro -p 3306:3306 mysql:8.0
```

Validate all modules:

```bash
cd 03-Products-And-Frameworks/Spring-Data-JPA-HQL
for d in Hands-On-*; do (cd "$d" && mvn -q clean compile) || exit 1; done
```

Tests use H2 (`spring.profiles.active=test`); demos use `@Profile("!test")`.
