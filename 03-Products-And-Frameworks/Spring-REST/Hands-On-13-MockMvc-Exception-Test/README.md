# Hands-On 13 — MockMvc Exception Test

## Objective

Use `MockMvc` to verify that invalid country codes produce HTTP 404 responses whose body or message includes **Country not found**.

## Concepts Covered

- Testing `@ResponseStatus` exceptions through the MVC stack
- `MockMvc` status and content assertions
- `CountryNotFoundException` from Hands-On 11 pattern
- Negative-path REST testing

## Folder Structure

```
Hands-On-13-MockMvc-Exception-Test/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/springlearn/
    │   ├── MockMvcExceptionTestApplication.java
    │   ├── Country.java
    │   ├── CountryNotFoundException.java
    │   ├── service/CountryService.java
    │   └── web/CountryController.java
    ├── main/resources/
    │   ├── application.properties
    │   └── country-list.xml
    └── test/java/com/cognizant/springlearn/
        ├── MockMvcExceptionTestApplicationTests.java
        └── web/CountryControllerExceptionTest.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring Boot Test, MockMvc, Hamcrest, SLF4J

## Spring Boot

Full-stack test loads exception mapping and error response formatting.

## Spring Core

XML-backed `countryList` with four countries.

## XML Configuration

`country-list.xml` (IN, US, DE, JP).

## IoC Container

Same configuration for runtime and test contexts.

## Bean Scope

Singleton service, controller, and list beans.

## Logging

Service logs failed lookups at INFO before throwing.

## How to Run

```bash
cd Hands-On-13-MockMvc-Exception-Test
mvn test
mvn spring-boot:run
curl -i http://localhost:8080/countries/ZZ
```

## Expected Output

- MockMvc tests: 404 and response text containing `Country not found`
- Manual curl shows NOT FOUND status and error reason in body

## Learning Outcomes

- Assert error responses, not only happy paths
- Validate exception-to-HTTP mapping with MockMvc
- Build confidence in API failure behavior before deployment
