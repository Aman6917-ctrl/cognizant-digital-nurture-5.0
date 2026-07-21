# Microservices

Hands-on microservices learning aligned with Cognizant Digital Nurture 5.0.

## Parts

| Part | Topic |
|------|--------|
| [Part 1 – Edge Services and API Gateway](Part-1-Edge-Services-And-API-Gateway/) | Gateway routing, filters, load balancing, Resilience4j |
| [Part 2 – Centralized Authentication](Part-2-Centralized-Authentication/) | OAuth2/OIDC login, resource server JWT, custom JWT filter |
| [Part 3 – Microservices Platform](Part-3-Microservices-Platform/) | Eureka, Config, Gateway, ten domain services |

## Stack

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3 (Leyton)
- Spring Security 6

## Validation

From any inner Maven module:

```bash
mvn clean compile
```

Part 1 and Part 3 aggregators compile all modules:

```bash
cd <exercise-inner-folder>
mvn clean compile
```
