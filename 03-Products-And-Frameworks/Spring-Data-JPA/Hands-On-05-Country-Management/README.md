# Hands-On 5–9 — Country Management (Spring Data JPA)

Single Spring Boot project covering **repository query methods**, **service-layer CRUD**, **Optional** lookups, **custom exceptions**, and demo runners for each hands-on.

## Technologies Used

- Java 21, Maven, Spring Boot 3.3.5, Spring Data JPA, MySQL 8, SLF4J (Logback)

## Folder Structure

```
Hands-On-05-Country-Management/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/countrymgmt/
    ├── CountryManagementApplication.java
    ├── demo/HandsOn05…HandsOn09*.java
    ├── exception/CountryNotFoundException.java
    ├── model/Country.java
    ├── repository/CountryRepository.java
    └── service/CountryService.java
```

## Database Setup

MySQL database `country_db` (Docker example in [module README](../README.md)). Columns: `country_id`, `country_code`, `country_name`.

## How to Run (all demos)

```bash
cd 03-Products-And-Frameworks/Spring-Data-JPA/Hands-On-05-Country-Management
mvn clean compile
mvn spring-boot:run
```

**Tests (H2, no MySQL):**

```bash
mvn test
```

---

## Hands-On 5 — List & query methods

**Objective:** Use `JpaRepository`, `getAllCountries()`, and derived queries `findByCountryCode`, `findByCountryNameContainingIgnoreCase`.

**Run:** `mvn spring-boot:run` — first demo block in logs (`@Order(5)`).

**Expected output:** All seed countries printed; partial search `"united"` returns United States and United Kingdom.

---

## Hands-On 6 — Find by code & exception

**Objective:** `findCountryByCode` returns a country or throws `CountryNotFoundException`.

**Run:** Same app start — `@Order(6)` block.

**Expected output:** `Found: Country{… countryCode='IN' …}` and `Expected not found: Country not found for code: XX`.

---

## Hands-On 7 — Add country

**Objective:** `addCountry` persists a new row and verifies with `findCountryByCode`.

**Run:** `@Order(7)` block.

**Expected output:** `Added: Country{… countryCode='SG', countryName='Singapore' …}` and matching verify line.

---

## Hands-On 8 — Update country

**Objective:** `updateCountry(code, newName)` changes `country_name` for `CA`.

**Run:** `@Order(8)` block.

**Expected output:** `Updated: Country{… countryCode='CA', countryName='Canada (Updated Demo)' …}`.

---

## Hands-On 9 — Delete country

**Objective:** `deleteCountry` removes the row added in HO7 (`SG`).

**Run:** `@Order(9)` block.

**Expected output:** Delete confirmation and `Lookup after delete present? false`.

---

## Screenshots Placeholder

<!-- Screenshot: full console run showing HO 5–9 sections -->

## Learning Outcomes

- Keep persistence in repositories and business rules in services
- Use Optional internally and explicit exceptions at API boundaries
- Order startup demos with `@Order` for teaching flows
