# Exercise 2: Spring Cloud LoadBalancer and RandomLoadBalancer

## Objective

Route through `lb://quote-service` with a custom `RandomLoadBalancer` configuration.

## Architecture

Gateway uses client-side load balancing across registered `quote-service` instances.

## Folder structure

```
Exercise-2-Spring-Cloud-LoadBalancer/…/discovery-server, quote-service, api-gateway
```

## Dependencies

- spring-cloud-starter-loadbalancer
- RandomLoadBalancer bean

## How to run

Run discovery on 8762, two quote-service instances (8091 and `--spring.profiles.active=instance-b` on 8092), then gateway on 8085.

## Example request

`GET http://localhost:8085/api/quotes/quotes/random` (repeat to see random instance ids)

## Example response

`{"instance":"quote-a","quote":"Learning microservices with Spring Cloud"}`

## Expected output

Repeated calls may return different `instance` values when multiple replicas register.

## Technologies used

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Maven
