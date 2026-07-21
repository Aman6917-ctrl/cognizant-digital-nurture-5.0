# Hands-On 2 — DateFormat Bean (XML)

## Objective

Define a `SimpleDateFormat` bean in Spring XML and parse a date string using a standalone `ClassPathXmlApplicationContext`.

## Concepts Covered

- XML bean definition with constructor injection
- `ClassPathXmlApplicationContext`
- Third-party / JDK class as a Spring bean
- SLF4J logging and `ParseException` handling

## Folder Structure

```
Hands-On-02-DateFormat-Bean/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/springlearn/
    │   ├── DateFormatDemoApplication.java
    │   └── DateFormatDemoRunner.java
    └── resources/
        ├── application.properties
        └── date-format.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Context, SLF4J

## Spring Boot

Boot hosts the app and test slice; the date format bean is loaded from XML in the runner, not via Boot auto-config.

## Spring Core

Explicit IoC: `date-format.xml` declares `dateFormat` with pattern `dd/MM/yyyy`.

## XML Configuration

`date-format.xml` uses the Spring beans XSD and `<constructor-arg value="dd/MM/yyyy"/>`.

## IoC Container

A short-lived `ClassPathXmlApplicationContext` is created in `DateFormatDemoRunner` to resolve the bean.

## Bean Scope

Default singleton for the `dateFormat` bean within the XML context.

## Logging

`LoggerFactory` only; parsed date at DEBUG, parse failures at ERROR.

## How to Run

```bash
cd Hands-On-02-DateFormat-Bean
mvn spring-boot:run
mvn test
```

## Expected Output

- DEBUG log: `Parsed date:` with `Mon Dec 31 ... 2018` (locale-dependent string)
- Tests: context loads with `test` profile (runner disabled)

## Learning Outcomes

- Wire JDK types as beans in XML
- Use Spring Core context alongside Spring Boot
- Practice safe date parsing with structured logging
