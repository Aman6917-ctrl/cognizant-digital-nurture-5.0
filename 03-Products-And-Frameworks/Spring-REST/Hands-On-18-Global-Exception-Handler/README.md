# Hands-On 18 — Global Exception Handler

## Objective

Centralize API error responses with `@ControllerAdvice` and `GlobalExceptionHandler` extending `ResponseEntityExceptionHandler`.

## Concepts Covered

- `@ControllerAdvice`
- Override `handleMethodArgumentNotValid()`
- Override `handleHttpMessageNotReadable()`
- Structured JSON errors: `timestamp`, `status`, `message`, `errors`
- Invalid JSON and numeric type mismatches

## Folder Structure

```
Hands-On-18-Global-Exception-Handler/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/springlearn/web/
    ├── GlobalExceptionHandler.java
    ├── ApiErrorResponse.java
    └── CountryController.java
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring Validation, Jackson, MockMvc, SLF4J

## REST Methods

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/countries` | Create country; errors return unified JSON body |

## Validation

`Country`: `code` (`@NotBlank`, `@Size(2,2)`), `name` (`@NotNull`, `@NotBlank`), optional `displayOrder` (`@Min`, `@Max`).

## Global Exception Handling

| Scenario | Handler |
|----------|---------|
| Validation errors | `handleMethodArgumentNotValid` |
| Malformed JSON / wrong types | `handleHttpMessageNotReadable` |

Example error body:

```json
{
  "timestamp": "2026-07-22T02:00:00Z",
  "status": 400,
  "message": "Validation failed for request body",
  "errors": { "code": "Country code must be exactly 2 characters" }
}
```

## JSON Mapping

Jackson binds request JSON; unreadable bodies trigger global handler.

## MockMvc Testing

- POST success
- Validation errors (structured JSON)
- Invalid JSON
- Incorrect numeric field (`displayOrder`)

## Sample Requests

```bash
curl -X POST http://localhost:8080/countries \
  -H "Content-Type: application/json" \
  -d '{"code":"CA","name":"Canada","displayOrder":10}'
```

## How to Run

```bash
cd Hands-On-18-Global-Exception-Handler
mvn clean test
```

## Learning Outcomes

- Consistent API error contracts
- Differentiate validation vs parse errors
- Log failures in the exception handler
