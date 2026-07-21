# Hands-On 8 — Country REST Service

## Objective

Combine Spring Core XML bean definition with a REST controller that serializes a `Country` POJO to JSON.

## Concepts Covered

- `@ImportResource` loading `country.xml`
- Injecting XML-defined beans into `@RestController`
- JSON response via Jackson and getter-based serialization
- `@RequestMapping` on controller class

## Folder Structure

```
Hands-On-08-Country-REST-Service/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/cognizant/springlearn/
    │   ├── CountryRestApplication.java
    │   ├── Country.java
    │   └── web/CountryController.java
    └── resources/
        ├── application.properties
        └── country.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Web, Spring Context, SLF4J

## Spring Boot

Web stack with XML beans merged into the Boot application context.

## Spring Core

`country` bean (IN / India) defined in XML with property injection.

## XML Configuration

`country.xml` declares a single `Country` bean.

## IoC Container

Boot `ApplicationContext` plus imported XML definitions.

## Bean Scope

Singleton `country` bean shared by the controller.

## Logging

Controller logs country code and name at INFO when handling requests.

## How to Run

```bash
cd Hands-On-08-Country-REST-Service
mvn spring-boot:run
curl http://localhost:8080/country
mvn test
```

## Expected Output

- `GET /country` → `{"code":"IN","name":"India"}`

## Learning Outcomes

- Expose XML-configured domain objects through REST
- Understand JSON mapping for simple Java beans
- Wire legacy-style XML beans into Boot applications
