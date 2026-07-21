# Hands-On 2 — Stock Query Methods

## Objective

Practice **Spring Data JPA derived query methods** on a `Stock` entity: date ranges, numeric comparisons, `Top`/`OrderBy` limits, and symbol-scoped sorting.

## Concepts Covered

- Derived query methods (`Between`, `GreaterThan`, `Top3`, `OrderBy`)
- `BigDecimal` price fields and `LocalDate` trade dates
- `@Transactional(readOnly = true)` service layer with SLF4J logging
- Schema and seed data via `spring.sql.init.*`

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Data JPA, Hibernate 6, MySQL 8, SLF4J

## Folder Structure

```
Hands-On-02-Stock-Query-Methods/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/stock/
    ├── StockQueryApplication.java
    ├── demo/StockQueryDemoRunner.java
    ├── model/Stock.java
    ├── repository/StockRepository.java
    └── service/StockService.java
```

## Database Setup

Uses MySQL database `jpa_advanced` (or set `MYSQL_DATABASE`). Example:

```bash
docker run -d --name nurture-mysql -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=jpa_advanced -p 3306:3306 mysql:8.0
```

Environment variables: `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_DATABASE`, `MYSQL_USER`, `MYSQL_PASSWORD`.

## Configuration

- `ddl-auto=validate`
- `spring.sql.init.schema-locations=classpath:schema.sql`
- `spring.sql.init.data-locations=classpath:stock-data.sql`
- Hibernate SQL DEBUG logging

## Query Methods

| Method | Purpose |
|--------|---------|
| `findBySymbolAndTradeDateBetween("FB", …)` | Facebook trades in September 2019 |
| `findBySymbolAndClosePriceGreaterThan("GOOG", 1250)` | Google closes above 1250 |
| `findTop3ByOrderByVolumeDesc()` | Three highest-volume rows (any symbol) |
| `findTop3BySymbolOrderByClosePriceAsc("NFLX")` | Three lowest NFLX close prices |

## How to Run

```bash
cd Hands-On-02-Stock-Query-Methods
mvn clean compile
mvn spring-boot:run
mvn test   # H2 in-memory, no MySQL required
```

## Expected Output

The demo runner prints four result sets:

1. All FB rows with `trade_date` in September 2019
2. GOOG rows with `close_price` > 1250
3. Top 3 stocks by volume (AAPL, TSLA, AMZN from seed data)
4. Three NFLX rows with lowest close price

## Learning Outcomes

- Express common reporting filters with method names instead of JPQL
- Combine `Top`, `OrderBy`, and property paths in repository interfaces
- Keep read queries in a transactional service for consistent session boundaries
