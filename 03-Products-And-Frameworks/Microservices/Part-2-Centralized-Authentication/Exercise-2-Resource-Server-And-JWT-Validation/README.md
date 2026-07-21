# Exercise 2: Resource Server and JWT Validation

## Objective

Validate JWT bearer tokens with Spring Security 6 resource server configuration and protect `/api/secure/**`.

## Architecture

Authorization server concepts illustrated with symmetric-key JWT validation (Boot 3 resource server).

## Folder structure

```
Exercise-2-Resource-Server-And-JWT-Validation/Exercise-2-Resource-Server-And-JWT-Validation/
```

## Dependencies

- spring-boot-starter-oauth2-resource-server

## How to run

`mvn clean compile`. Run app on 9002. Use a JWT from Exercise 3 or any HS256 token signed with the configured secret.

## Example request

`GET http://localhost:9002/api/secure/profile` with `Authorization: Bearer <token>`

## Example response

`{"subject":"demo-user","scope":"read"}`

## Expected output

`/api/public/status` is open; `/api/secure/profile` requires valid JWT.

## Technologies used

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Maven
