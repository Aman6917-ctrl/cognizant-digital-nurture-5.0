# Hands-On 17 — Country Validation

## Objective

Validate incoming `Country` payloads with Bean Validation and return **HTTP 400** for invalid requests.

## Concepts Covered

- `@Valid` on controller parameters
- `@NotNull`, `@NotBlank`, `@Size(min=2,max=2)`
- Hibernate Validator
- POST `/countries` with validation gate

## Folder Structure

```
Hands-On-17-Country-Validation/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/springlearn/
    ├── model/Country.java (constraints)
    ├── dao/CountryDao.java
    ├── service/CountryService.java
    └── web/CountryController.java (@Valid)
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring Validation, Hibernate Validator, Jackson, MockMvc, SLF4J

## REST Methods

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/countries` | Create country; 400 if validation fails |

## Validation

| Field | Rules |
|-------|--------|
| `code` | `@NotBlank`, `@Size(2,2)` |
| `name` | `@NotNull`, `@NotBlank` |

## Global Exception Handling

Spring MVC default `MethodArgumentNotValidException` → 400.

## JSON Mapping

Jackson maps JSON to validated `Country` bean.

## MockMvc Testing

- POST success
- POST validation failure (400)

## Sample Requests

```bash
curl -X POST http://localhost:8080/countries \
  -H "Content-Type: application/json" \
  -d '{"code":"GB","name":"United Kingdom"}'
```

Invalid:

```bash
curl -X POST http://localhost:8080/countries \
  -H "Content-Type: application/json" \
  -d '{"code":"USA","name":""}'
```

## Sample Responses

Success (201):

```json
{"code":"GB","name":"United Kingdom"}
```

Validation failure: HTTP 400.

## How to Run

```bash
cd Hands-On-17-Country-Validation
mvn clean test
mvn spring-boot:run
```

## Learning Outcomes

- Declarative validation on REST DTOs
- Reject bad input before service/DAO layers
- Test validation with MockMvc
