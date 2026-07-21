# Hands-On 16 — Country POST REST Service

## Objective

Implement `POST /countries` with Jackson JSON-to-Java mapping and layered **Controller → Service → DAO** design.

## Concepts Covered

- `@PostMapping` and `@RequestBody`
- HTTP 201 Created responses
- Jackson deserialization
- XML-seeded in-memory country catalog
- SLF4J START/END logging

## Folder Structure

```
Hands-On-16-Country-POST/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/springlearn/
    │   ├── CountryPostApplication.java
    │   ├── model/Country.java
    │   ├── dao/CountryDao.java
    │   ├── service/CountryService.java
    │   └── web/CountryController.java
    ├── main/resources/{application.properties, country-list.xml}
    └── test/java/.../CountryControllerMockMvcTest.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring MVC, Jackson, JUnit 5, MockMvc, SLF4J

## REST Methods

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/countries` | Create country from JSON body |

## Validation

Not applied in this exercise (see Hands-On 17).

## Global Exception Handling

Default Spring Boot error handling.

## JSON Mapping

Request body maps to `Country` (`code`, `name`) via Jackson.

## MockMvc Testing

- POST success — verifies 201 and JSON echo

## Sample Requests

```bash
curl -X POST http://localhost:8080/countries \
  -H "Content-Type: application/json" \
  -d '{"code":"FR","name":"France"}'
```

## Sample Responses

```json
{"code":"FR","name":"France"}
```

## How to Run

```bash
cd Hands-On-16-Country-POST
mvn spring-boot:run
```

## Learning Outcomes

- Bind JSON payloads to Java beans
- Return REST resources from POST endpoints
- Trace requests through controller, service, and DAO layers
