# Exercise 3: Resilience4j Circuit Breaker, TimeLimiter, Fallback

## Objective

Protect gateway routes with Resilience4j circuit breaker and time limiter; forward to a fallback endpoint when the payment service fails.

## Architecture

Gateway circuit breaker wraps `lb://payment-service` calls.

## Folder structure

```
Exercise-3-Resilience4j-Circuit-Breaker/…/discovery-server, payment-service, api-gateway
```

## Dependencies

- spring-cloud-starter-circuitbreaker-reactor-resilience4j

## How to run

Start discovery (8763), payment-service (8093), gateway (8086). Set `payment.fail=true` to trigger fallback.

## Example request

`GET http://localhost:8086/api/payments/payments/charge?amount=250`

## Example response

Success: `{"status":"SUCCESS","amount":250}` — Fallback: `{"status":"FALLBACK","message":"Payment service temporarily unavailable"}`

## Expected output

Circuit opens after failures; fallback controller responds with FALLBACK status.

## Technologies used

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Maven
