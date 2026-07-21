# Hands-On 21 — Spring Security Basic Authentication

## Objective

Secure REST APIs with Spring Security 6 **SecurityFilterChain**, HTTP Basic authentication, and CSRF disabled.

## Concepts Covered

- `SecurityFilterChain` (no `WebSecurityConfigurerAdapter`)
- HTTP Basic authentication
- CSRF disabled for stateless REST
- HTTP 401 for unauthenticated requests

## Folder Structure

```
Hands-On-21-Spring-Security-Basic/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/springlearn/
    ├── SecurityBasicApplication.java
    ├── config/SecurityConfig.java
    ├── model/Country.java
    ├── service/CountryService.java
    └── web/CountryController.java
```

## Spring Security

| Component | Role |
|-----------|------|
| `SecurityFilterChain` | Requires authentication for all requests |
| Default user | `spring.security.user.*` in `application.properties` |

## Authentication

HTTP Basic (`Authorization: Basic …`). Invalid or missing credentials → **401 Unauthorized**.

## Authorization

Any authenticated user may call protected endpoints.

## JWT Flow

Not used in this exercise.

## SecurityFilterChain

```java
http.csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
    .httpBasic(Customizer.withDefaults());
```

## JWT Structure

N/A

## Sample Requests

```bash
curl -u learner:secret http://localhost:8080/countries
```

Unauthenticated:

```bash
curl http://localhost:8080/countries
```

## Sample Responses

JSON country list when authenticated; **401** when not.

## How to Run

```bash
cd Hands-On-21-Spring-Security-Basic
mvn clean test
mvn spring-boot:run
```

## Learning Outcomes

- Configure Spring Security 6 without deprecated APIs
- Protect REST resources with HTTP Basic
- Verify security behavior with MockMvc and `httpBasic()`
