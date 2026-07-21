# Hands-On 24 — JWT Authorization Filter

## Objective

Protect REST APIs with a custom **JwtAuthorizationFilter** registered on the `SecurityFilterChain`. Only **GET /authenticate** is public.

## Concepts Covered

- `JwtAuthorizationFilter` (`OncePerRequestFilter`)
- Bearer token validation
- Stateless sessions
- Role rules on `/employees` (ADMIN)
- Custom `401` entry point

## Folder Structure

```
Hands-On-24-JWT-Authorization-Filter/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/springlearn/
    ├── config/SecurityConfig.java
    ├── security/JwtAuthorizationFilter.java
    ├── security/JwtTokenService.java
    └── web/AuthenticationController.java (+ Country/Employee/Department controllers)
```

## Spring Security

Filter order: `JwtAuthorizationFilter` → `UsernamePasswordAuthenticationFilter`.

## Authentication

1. **GET /authenticate** with Basic credentials → JWT
2. Subsequent calls use `Authorization: Bearer <token>`

## Authorization

| Endpoint | Access |
|----------|--------|
| GET `/authenticate` | Public |
| GET `/countries` | Authenticated JWT |
| GET `/departments` | Authenticated JWT |
| GET `/employees` | JWT + ROLE_ADMIN |

## JWT Flow

```
/authenticate (Basic) → token
/countries (Bearer) → JwtAuthorizationFilter validates → Controller
```

## SecurityFilterChain

Stateless session; CSRF disabled; JWT filter before username/password filter; authentication entry point returns 401.

## JWT Structure

Same as Hands-On 23 (HS256, `sub`, 20-minute default expiry).

## Sample Requests

```bash
TOKEN=$(curl -s -H "Authorization: Basic $(echo -n 'user:pwd' | base64)" \
  http://localhost:8080/authenticate | jq -r .token)

curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/countries
```

## Sample Responses

Protected resources return JSON when authorized; missing/invalid/expired JWT → **401**; USER token on `/employees` → **403**.

## How to Run

```bash
cd Hands-On-24-JWT-Authorization-Filter
mvn clean test
mvn spring-boot:run
```

## Learning Outcomes

- Implement Bearer JWT authorization in a servlet filter
- Integrate filters with Spring Security 6
- Test unauthorized, forbidden, invalid, and expired tokens
