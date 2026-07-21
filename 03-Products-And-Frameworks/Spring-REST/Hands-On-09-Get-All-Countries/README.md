# Hands-On 9 — Get All Countries

## Objective

Return a Spring XML-wired `List<Country>` as a JSON array from a REST endpoint.

## Concepts Covered

- `@ImportResource("classpath:country-list.xml")`
- Injecting `List<Country>` with `@Qualifier("countryList")`
- Collection JSON serialization
- REST list endpoints

## Folder Structure

```
Hands-On-09-Get-All-Countries/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/springlearn/
    │   ├── GetAllCountriesApplication.java
    │   ├── Country.java
    │   └── web/CountriesController.java
    └── resources/
        ├── application.properties
        └── country-list.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring Context, SLF4J

## Spring Boot

Boot web application importing XML list wiring from Hands-On 6.

## Spring Core

Four country beans assembled into `countryList` via `<list>` / `<ref>`.

## XML Configuration

`country-list.xml` mirrors Hands-On 6 (IN, US, DE, JP).

## IoC Container

Merged Boot and XML bean definitions.

## Bean Scope

Singleton list and country beans.

## Logging

Controller logs country count at INFO.

## How to Run

```bash
cd Hands-On-09-Get-All-Countries
mvn spring-boot:run
curl http://localhost:8080/countries
mvn test
```

## Expected Output

- `GET /countries` → JSON array of four countries

## Learning Outcomes

- Expose collection beans over HTTP as JSON arrays
- Reuse XML list wiring in REST services
- Qualify list injection when multiple lists could exist
