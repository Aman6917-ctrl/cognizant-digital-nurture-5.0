# Exercise 1: OAuth2 Login and OIDC

## Objective

Configure OAuth2/OIDC login with Spring Security 6 and expose an authenticated `/user` endpoint.

## Architecture

Browser OAuth2 login → OIDC user principal → `/user` JSON.

## Folder structure

```
Exercise-1-OAuth2-Login-And-OIDC/Exercise-1-OAuth2-Login-And-OIDC/
```

## Dependencies

- spring-boot-starter-oauth2-client
- SecurityFilterChain

## How to run

`mvn clean compile` then run app. Set `GOOGLE_CLIENT_ID` and `GOOGLE_CLIENT_SECRET` for live login.

## Example request

`GET http://localhost:9001/user` (after browser OAuth2 login)

## Example response

`{"authenticated":true,"email":"user@example.com","name":"Example User"}`

## Expected output

Unauthenticated `/user` returns `authenticated: false`; after login returns OIDC claims.

## Technologies used

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Maven
