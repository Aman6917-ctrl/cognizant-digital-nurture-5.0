# Exercise 1: Routing, Filtering, and Custom GlobalFilter

## Objective

Configure Spring Cloud Gateway routes to a Eureka-registered catalog service, add route filters, and implement a custom `GlobalFilter` for request/response logging.

## Architecture

Client → API Gateway → Eureka lookup → Catalog Service

## Folder structure

```
Exercise-1-Routing-Filtering-And-GlobalFilter/
├── README.md
└── Exercise-1-Routing-Filtering-And-GlobalFilter/
    ├── pom.xml
    ├── discovery-server/
    ├── catalog-service/
    └── api-gateway/
```

## Dependencies

- spring-cloud-starter-gateway
- spring-cloud-starter-netflix-eureka-client/server

## How to run

1. Start `discovery-server` (8761)
2. Start `catalog-service` (8081)
3. Start `api-gateway` (8080)

From inner folder: `mvn clean compile`

## Example request

`GET http://localhost:8080/api/catalog/catalog/items`

## Example response

`{"service":"catalog-service","items":["book","pen"]}`

## Expected output

Gateway logs each request/response; catalog JSON returned through `/api/catalog/**` route.

## Technologies used

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Maven
