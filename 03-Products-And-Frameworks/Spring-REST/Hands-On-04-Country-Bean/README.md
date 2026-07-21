# Hands-On 4 — Country Bean (Setter Injection)

## Objective

Model a domain object as a Spring bean with XML setter injection and observe lifecycle logging on property access.

## Concepts Covered

- POJO as a bean
- `<property name="..." value="..."/>` setter injection
- `ClassPathXmlApplicationContext` bean lookup
- SLF4J in constructors, getters, setters, and display methods

## Folder Structure

```
Hands-On-04-Country-Bean/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/springlearn/
    │   ├── CountryBeanDemoApplication.java
    │   ├── Country.java
    │   └── CountryBeanDemoRunner.java
    └── resources/
        ├── application.properties
        └── country.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Context, SLF4J

## Spring Boot

Boot application wraps the exercise; country bean is defined in XML.

## Spring Core

`country.xml` injects `code=IN` and `name=India` into `Country`.

## XML Configuration

Spring beans XSD; single `country` bean with two property elements.

## IoC Container

Runner opens `ClassPathXmlApplicationContext("country.xml")` and calls `getBean("country", Country.class)`.

## Bean Scope

Default singleton.

## Logging

Constructor and every getter/setter at DEBUG; `displayCountry()` at INFO.

## How to Run

```bash
cd Hands-On-04-Country-Bean
mvn spring-boot:run
mvn test
```

## Expected Output

- DEBUG logs from setter injection during context refresh
- INFO: `Country: code=IN, name=India`

## Learning Outcomes

- Relate XML properties to JavaBean conventions
- See when the container invokes setters vs. application code
- Combine Boot with classic XML wiring
