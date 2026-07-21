# Hands-On 5 — Bean Scopes

## Objective

Compare **singleton** and **prototype** scopes by requesting the same bean id twice and inspecting instance identity and construction count.

## Concepts Covered

- Default singleton scope
- `scope="prototype"`
- Reference equality (`==`) vs. separate instances
- Static construction counter for observability

## Folder Structure

```
Hands-On-05-Bean-Scopes/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/cognizant/springlearn/
    │   ├── BeanScopeDemoApplication.java
    │   ├── ScopeDemoBean.java
    │   └── BeanScopeDemoRunner.java
    └── resources/
        ├── application.properties
        └── scope-beans.xml
```

## Technologies Used

Java 21, Maven, Spring Boot 3.3.5, Spring Context, SLF4J

## Spring Boot

Hosts the demo; scope examples live in XML.

## Spring Core

Two beans of the same class: `singletonBean` (default) and `prototypeBean` (`scope="prototype"`).

## XML Configuration

`scope-beans.xml` with Spring beans XSD.

## IoC Container

`ClassPathXmlApplicationContext` loads scope definitions; runner fetches each bean twice.

## Bean Scope

| Bean id           | Scope      | Behavior |
|-------------------|------------|----------|
| `singletonBean`   | singleton  | One shared instance per context; second `getBean` returns the same reference (`true` for `==`). |
| `prototypeBean`   | prototype  | New instance on every `getBean`; references differ (`false` for `==`). |

Construction count: singleton creates **one** instance; prototype creates **two** when fetched twice — total **three** `ScopeDemoBean` constructions in the demo.

## Logging

Each construction logs `instanceId` and running total; runner logs equality results and final count at DEBUG.

## How to Run

```bash
cd Hands-On-05-Bean-Scopes
mvn spring-boot:run
mvn test
```

## Expected Output

- `Singleton same reference: true`
- `Prototype same reference: false`
- `Total ScopeDemoBean constructions: 3`

## Learning Outcomes

- Choose singleton for shared stateless/stateful services (carefully)
- Use prototype when each injection must be a distinct object
- Verify scope behavior empirically, not only from documentation
