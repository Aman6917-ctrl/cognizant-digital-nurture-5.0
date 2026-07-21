# Hands-On 3–6 — JPA Relationships

Single Maven module covering entity mapping, `@ManyToOne`, `@OneToMany` (lazy vs fetch join), and `@ManyToMany`.

## Objective

Model a small org chart with departments, employees, and skills; persist and load associations; observe lazy loading and safe eager fetching.

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Data JPA, Hibernate 6, MySQL 8, SLF4J

## Folder Structure

```
Hands-On-03-Relationships/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/relationships/
    ├── RelationshipsApplication.java
    ├── demo/          # CommandLineRunner demos (@Order 1–5)
    ├── model/         # Department, Employee, Skill
    ├── repository/
    └── service/       # CRUD services + RelationshipFetchService
```

## Database Setup

MySQL database `jpa_advanced` (shared with other advanced modules):

```bash
docker run -d --name nurture-mysql -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=jpa_advanced -p 3306:3306 mysql:8.0
```

Environment variables: `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_DATABASE`, `MYSQL_USER`, `MYSQL_PASSWORD`.

## Configuration

- `ddl-auto=validate`
- `schema.sql` + `data.sql` + `payroll.sql` via `spring.sql.init.data-locations=classpath:data.sql,classpath:payroll.sql`
- `show-sql=true`, formatted SQL, DEBUG Hibernate logging

## How to Run

```bash
cd Hands-On-03-Relationships
mvn clean compile
mvn spring-boot:run
mvn test   # H2, profile `test`, demos disabled
```

---

## Hands-On 3 — Entity Mapping

**Runner:** `HandsOn03EntityMappingDemo` (`@Order(1)`)

- Load seeded `Department`, `Employee`, and `Skill` by id
- Save a new department, employee (with department), and skill

**Entities:** `Department`, `Employee`, `Skill` in package `com.cognizant.relationships.model`.

---

## Hands-On 4 — @ManyToOne

**Runner:** `HandsOn04ManyToOneDemo` (`@Order(2)`)

| Method | Behavior |
|--------|----------|
| `testGetEmployee()` | Load employee; access lazy `department` inside transaction (SQL in logs) |
| `testAddEmployee()` | Insert employee with `department_id` FK |
| `testUpdateEmployee()` | Change salary and flush update |

---

## Hands-On 5 — @OneToMany (Lazy vs Eager)

**Lazy runner:** `HandsOn05OneToManyLazyDemo` (`@Order(3)`)

- `RelationshipFetchService.loadDepartmentOutsideTransaction` returns a detached `Department`
- Calling `getEmployees()` triggers `LazyInitializationException` (logged and caught)

**Eager runner:** `HandsOn05OneToManyEagerDemo` (`@Order(4)`)

- Uses `DepartmentRepository.findByIdWithEmployees` with  
  `SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.id = :id`
- Employees are initialized inside the transaction and safe to read after load

**Alternative (not used here):** change `@OneToMany(fetch = FetchType.EAGER)` on `Department.employees`. That eagerly loads employees on every department load; JOIN FETCH is preferred for targeted reads.

---

## Hands-On 6 — @ManyToMany

**Runner:** `HandsOn06ManyToManyDemo` (`@Order(5)`)

- Lazy fail: load employee outside tx, access `skills` → `LazyInitializationException`
- Success: `EmployeeRepository.findByIdWithSkills` with JOIN FETCH
- Add `Communication` skill to employee 1 and persist via `EmployeeService.save`

Join table: `employee_skill` (`employee_id`, `skill_id`).

---

## RelationshipFetchService

Supports demos that must end the persistence context before accessing collections:

- `loadDepartmentOutsideTransaction` / `loadDepartmentWithEmployees`
- `loadEmployeeOutsideTransaction` / `loadEmployeeWithSkills`
- `tryAccessEmployeesOutsideTransaction` / `tryAccessSkillsOutsideTransaction`

Uses `@Transactional(propagation = REQUIRES_NEW)` so each load runs in its own short-lived transaction.

## Learning Outcomes

- Map bidirectional associations with owning vs inverse sides
- Default to `LAZY` and fetch associations explicitly when needed
- Use JOIN FETCH (or DTO projections) instead of blanket `EAGER`
- Keep write operations in `@Transactional` service methods
