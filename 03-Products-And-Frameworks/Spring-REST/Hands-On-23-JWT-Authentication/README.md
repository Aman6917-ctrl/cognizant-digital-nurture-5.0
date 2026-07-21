# Hands-On 23 — JWT Authentication

## Objective

Issue JWT access tokens from **GET /authenticate** after validating HTTP Basic credentials.

## Concepts Covered

- `AuthenticationManager` + `DaoAuthenticationProvider`
- Manual Basic header parsing
- jjwt **HS256** tokens
- Secret `secretkey` (SHA-256 derived signing key)
- 20-minute expiry

## Folder Structure

```
Hands-On-23-JWT-Authentication/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/springlearn/
    ├── config/SecurityConfig.java
    ├── security/JwtTokenService.java
    └── web/AuthenticationController.java
```

## Spring Security

- `/authenticate` permitted without JWT
- HTTP Basic auto-login disabled so the controller reads `Authorization` explicitly
- `AuthenticationProvider` validates `admin`/`user` with password `pwd`

## Authentication

1. Client sends `Authorization: Basic base64(user:pwd)`
2. Controller authenticates via `AuthenticationManager`
3. `JwtTokenService` builds signed JWT

## Authorization

Other endpoints open in this exercise (focus on token issuance).

## JWT Flow

```
Basic credentials → AuthenticationManager → JwtTokenService → { "token": "..." }
```

## SecurityFilterChain

CSRF off; form/basic login disabled; `DaoAuthenticationProvider` registered.

## JWT Structure

HS256 JWT with `sub` = username, `iat`, `exp` (+20 minutes). Signing key derived from configured secret `secretkey`.

## Sample Requests

```bash
curl -H "Authorization: Basic $(echo -n 'user:pwd' | base64)" \
  http://localhost:8080/authenticate
```

## Sample Responses

```json
{ "token": "eyJhbGciOiJIUzI1NiJ9..." }
```

## How to Run

```bash
cd Hands-On-23-JWT-Authentication
mvn clean test
mvn spring-boot:run
```

## Learning Outcomes

- Bridge Basic login to JWT issuance
- Use jjwt 0.12.x with Spring Boot 3
- Test token generation with MockMvc
