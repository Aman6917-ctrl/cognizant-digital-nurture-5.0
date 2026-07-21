# Hands-On 22 — Users and Roles

## Objective

Configure in-memory users with **BCrypt** passwords and role-based URL authorization.

## Concepts Covered

- `UserDetailsService` + `InMemoryUserDetailsManager`
- `PasswordEncoder` / `BCryptPasswordEncoder`
- `hasRole("USER")`, `hasRole("ADMIN")`
- HTTP 401 vs 403

## Folder Structure

```
Hands-On-22-Users-and-Roles/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/springlearn/config/SecurityConfig.java
```

## Spring Security

| User | Password | Role |
|------|----------|------|
| admin | pwd | ADMIN |
| user | pwd | USER |

Passwords stored BCrypt-encoded via `PasswordEncoder`.

## Authentication

HTTP Basic with in-memory users.

## Authorization

| Path | Rule |
|------|------|
| `/countries` | `hasRole("USER")` |
| `/employees` | `hasRole("ADMIN")` |

## JWT Flow

N/A

## SecurityFilterChain

Role rules applied before controller dispatch; CSRF disabled; HTTP Basic enabled.

## JWT Structure

N/A

## Sample Requests

```bash
curl -u user:pwd http://localhost:8080/countries
curl -u admin:pwd http://localhost:8080/employees
```

## Sample Responses

**200** when role matches; **403 Forbidden** when role does not (e.g. admin on `/countries`).

## How to Run

```bash
cd Hands-On-22-Users-and-Roles
mvn clean test
```

## Learning Outcomes

- Map users to roles in memory
- Encode passwords with BCrypt
- Enforce fine-grained URL authorization
