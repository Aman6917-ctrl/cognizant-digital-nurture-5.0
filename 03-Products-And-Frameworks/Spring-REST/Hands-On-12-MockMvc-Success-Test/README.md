# Hands-On 12 — MockMvc Success Test

## Objective

Write integration tests with `MockMvc` to verify successful REST responses and JSON payloads without starting a browser or external HTTP client.

## Concepts Covered

- `@SpringBootTest` + `@AutoConfigureMockMvc`
- `jsonPath` assertions on response body
- Testing `/country` and `/countries/{code}` success paths
- Optional `@SpringBootTest` service unit-style tests

## Folder Structure

```
Hands-On-12-MockMvc-Success-Test/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/springlearn/
    │   ├── MockMvcSuccessTestApplication.java
    │   ├── Country.java
    │   ├── service/CountryService.java
    │   └── web/CountryController.java
    ├── main/resources/
    │   ├── application.properties
    │   └── country.xml
    └── test/java/com/cognizant/springlearn/
        ├── MockMvcSuccessTestApplicationTests.java
        ├── web/CountryControllerTest.java
        └── service/CountryServiceTest.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring Boot Test, MockMvc, AssertJ, SLF4J

## Spring Boot

Full application context loaded for slice-free MVC tests.

## Spring Core

Single `country` XML bean (IN / India).

## XML Configuration

`country.xml` defines the India bean consumed by service and controller.

## IoC Container

Test and main share the same Boot context configuration.

## Bean Scope

Singleton beans for service, controller, and country.

## Logging

Production code uses SLF4J at INFO; tests rely on assertions.

## How to Run

```bash
cd Hands-On-12-MockMvc-Success-Test
mvn test
mvn spring-boot:run
curl http://localhost:8080/country
curl http://localhost:8080/countries/IN
```

## Expected Output

- Tests pass with status 200 and `code`/`name` JSON paths
- Live curls return India JSON

## Learning Outcomes

- Test REST controllers without a running server port
- Assert JSON structure with `jsonPath`
- Combine MockMvc tests with service-level context tests
