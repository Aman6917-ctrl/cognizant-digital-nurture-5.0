# Part 3: Microservices Platform

Full platform with Eureka discovery, Spring Cloud Config (native), API Gateway, and ten domain microservices.

## Services

| Service | Port |
|---------|------|
| discovery-server | 8765 |
| config-server | 8888 |
| api-gateway | 8088 |
| account-service | 9101 |
| loan-service | 9102 |
| user-service | 9103 |
| order-service | 9104 (OpenFeign → inventory) |
| product-service | 9105 (caching) |
| inventory-service | 9106 |
| payment-service | 9107 (OpenFeign → billing) |
| customer-service | 9108 |
| billing-service | 9109 |
| greet-service | 9110 |

## Compile all modules

```bash
cd Microservices-Platform/Microservices-Platform
mvn clean compile
```

## Example gateway request

`GET http://localhost:8088/platform/greet/greet`
