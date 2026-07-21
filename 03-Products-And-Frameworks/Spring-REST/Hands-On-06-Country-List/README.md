# Hands-On 6 — Country List (Collection Wiring)

## Objective

Declare multiple country beans and assemble them into a `List` via XML `<list>` and `<ref>` for constructor injection.

## Concepts Covered

- Multiple named beans
- `<constructor-arg>` with nested `<list>`
- Bean references with `<ref bean="..."/>`
- Iterating wired collections at runtime

## Folder Structure

```
Hands-On-06-Country-List/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/springlearn/
    │   ├── CountryListDemoApplication.java
    │   ├── Country.java
    │   └── CountryListDemoRunner.java
    └── resources/
        ├── application.properties
        └── country-list.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Context, SLF4J

## Spring Boot

Application entry point; list assembly is pure XML.

## Spring Core

Beans `countryIn`, `countryUs`, `countryDe`, `countryJp` wired into `countryList` (`ArrayList`).

## XML Configuration

Spring beans XSD; list of refs passed as constructor argument to `java.util.ArrayList`.

## IoC Container

`ClassPathXmlApplicationContext("country-list.xml")` provides `countryList` bean.

## Bean Scope

All country beans and the list are singletons (single shared list instance).

## Logging

Runner logs list size; each `Country.displayCountry()` logs code and name at DEBUG.

## How to Run

```bash
cd Hands-On-06-Country-List
mvn spring-boot:run
mvn test
```

## Expected Output

- DEBUG: `Loaded 4 countries from XML`
- Four DEBUG lines for IN, US, DE, JP

## Learning Outcomes

- Compose complex object graphs in XML without Java configuration
- Reuse bean definitions via refs inside collections
- Load and iterate Spring-managed collections
