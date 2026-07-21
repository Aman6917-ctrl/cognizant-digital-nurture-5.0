# Hands-On 10 — Get Country By Code

## Objective

Look up a single country by path variable using a service layer and return HTTP 404 when no match exists (without a custom exception).

## Concepts Covered

- `@PathVariable` and RESTful resource paths
- `@Service` with injected XML `countryList`
- Case-insensitive lookup with `Optional`
- `ResponseEntity.notFound()` for missing resources

## Folder Structure

```
Hands-On-10-Get-Country-By-Code/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/springlearn/
    │   ├── GetCountryByCodeApplication.java
    │   ├── Country.java
    │   ├── service/CountryService.java
    │   └── web/CountryController.java
    └── resources/
        ├── application.properties
        └── country-list.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, SLF4J

## Spring Boot

Layered REST app with XML-backed data and annotation-driven services.

## Spring Core

`countryList` bean from XML; `CountryService` is component-scanned.

## XML Configuration

Same four-country list as Hands-On 6 / 9.

## IoC Container

Service receives qualified list injection from the context.

## Bean Scope

Singleton service and list beans.

## Logging

Service and controller log lookup attempts and not-found cases at INFO.

## How to Run

```bash
cd Hands-On-10-Get-Country-By-Code
mvn spring-boot:run
curl http://localhost:8080/countries/IN
curl -i http://localhost:8080/countries/ZZ
mvn test
```

## Expected Output

- `GET /countries/IN` → 200 and India JSON
- `GET /countries/ZZ` → 404 with empty body

## Learning Outcomes

- Separate lookup logic into a `@Service`
- Map optional results to correct HTTP status codes
- Perform case-insensitive matching on path input
