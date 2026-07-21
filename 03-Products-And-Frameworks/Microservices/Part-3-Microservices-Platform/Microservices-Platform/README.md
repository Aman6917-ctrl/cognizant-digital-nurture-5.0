# Microservices Platform

## Objective

Integrated microservices lab demonstrating discovery, config, gateway filters, load balancing, circuit breaker, rate limiting, caching, Feign, and JWT-ready gateway dependencies.

## Architecture

Client → API Gateway → Eureka → target microservice (Feign across order/inventory and payment/billing).

## Folder structure

```
Microservices-Platform/Microservices-Platform/ (multi-module Maven project)
```

## Dependencies

Spring Boot 3.3.5, Spring Cloud 2023.0.3, Eureka, Config Server, Gateway, OpenFeign, Resilience4j, Cache

## How to run

Start discovery-server, config-server, domain services, then api-gateway.

## Example request

`GET http://localhost:8088/platform/product/products/101`

## Example response

`{"service":"Product Service","id":"101"}`

## Expected output

Gateway logs requests; services register in Eureka; optional config from config-server.

## Technologies used

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Maven
