# Hands-On 11 — Country Exception

## Objective

Signal missing countries with a domain-specific runtime exception mapped to HTTP 404 via `@ResponseStatus`.

## Concepts Covered

- `CountryNotFoundException` with `@ResponseStatus`
- Service throws instead of returning `Optional`
- Spring MVC exception-to-status translation
- Same lookup endpoint as Hands-On 10

## Folder Structure

```
Hands-On-11-Country-Exception/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/springlearn/
    │   ├── CountryExceptionApplication.java
    │   ├── Country.java
    │   ├── CountryNotFoundException.java
    │   ├── service/CountryService.java
    │   └── web/CountryController.java
    └── resources/
        ├── application.properties
        └── country-list.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, SLF4J

## Spring Boot

REST API with declarative error responses for missing entities.

## Spring Core

XML `countryList` plus scanned `@Service` / `@RestController` beans.

## XML Configuration

Four-country list (IN, US, DE, JP).

## IoC Container

Standard Boot application context with imported XML.

## Bean Scope

Singleton service and list.

## Logging

Service logs lookups and not-found paths at INFO before throwing.

## How to Run

```bash
cd Hands-On-11-Country-Exception
mvn spring-boot:run
curl http://localhost:8080/countries/US
curl -i http://localhost:8080/countries/ZZ
mvn test
```

## Expected Output

- Valid code → 200 JSON country
- Invalid code → 404 with reason **Country not found** in error payload

## Learning Outcomes

- Prefer exceptions for exceptional control flow in services
- Use `@ResponseStatus` for simple HTTP error mapping
- Contrast with Hands-On 10 `ResponseEntity` approach
