# Exercise 3: JWT Token Provider and JwtFilter

## Objective

Generate JWTs with `JwtTokenProvider`, authenticate requests using `JwtFilter`, and secure endpoints with `SecurityFilterChain`.

## Architecture

Custom JWT pipeline (handbook pattern) using jjwt 0.12.x and Spring Security 6 filter chain.

## Folder structure

```
Exercise-3-JWT-Token-Provider-And-Filter/Exercise-3-JWT-Token-Provider-And-Filter/
```

## Dependencies

- jjwt-api 0.12.6
- spring-boot-starter-security

## How to run

Run on 9003. POST `/api/auth/token` then GET `/api/secure/data` with Bearer token.

## Example request

`POST http://localhost:9003/api/auth/token` body `{"username":"demo-user"}`

## Example response

`{"accessToken":"...","tokenType":"Bearer"}`

## Expected output

Token generation succeeds; protected endpoint returns user name when Bearer token is valid.

## Technologies used

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Maven
