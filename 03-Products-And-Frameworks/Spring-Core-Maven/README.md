# Spring Core & Maven — Cognizant Digital Nurture

Hands-On exercises **1–9**: independent Maven projects covering XML configuration, dependency injection, AOP, annotation config, Maven dependency management, and Spring Boot REST + JPA.

| Exercise | Topic |
|----------|--------|
| [Exercise-01-Basic-Spring-Application](Exercise-01-Basic-Spring-Application/) | XML beans, `BookService`, `LibraryManagementApplication` |
| [Exercise-02-Dependency-Injection](Exercise-02-Dependency-Injection/) | Setter injection via XML |
| [Exercise-03-Spring-AOP-Logging](Exercise-03-Spring-AOP-Logging/) | `@Around` execution-time logging |
| [Exercise-04-Maven-Configuration](Exercise-04-Maven-Configuration/) | Context + AOP + WebMVC on classpath, Java 21 compiler |
| [Exercise-05-IoC-Container](Exercise-05-IoC-Container/) | IoC container and bean lookup |
| [Exercise-06-Annotation-Configuration](Exercise-06-Annotation-Configuration/) | `@ComponentScan`, `@Service`, `@Repository` |
| [Exercise-07-Constructor-Setter-Injection](Exercise-07-Constructor-Setter-Injection/) | Constructor vs setter XML wiring |
| [Exercise-08-Basic-Spring-AOP](Exercise-08-Basic-Spring-AOP/) | `@Before`, `@AfterReturning`, `@AfterThrowing` |
| [Exercise-09-Spring-Boot-Library-Management](Exercise-09-Spring-Boot-Library-Management/) | Boot 3, H2, JPA, CRUD REST API |

**Stack:** Java 21, Spring Framework 6.1.14 (exercises 1–8), Spring Boot 3.3.5 (exercise 9).

**Validate all compiles:**

```bash
cd 03-Products-And-Frameworks/Spring-Core-Maven
for d in Exercise-*; do (cd "$d" && mvn clean compile) || exit 1; done
```

Each exercise README documents `mvn exec:java` (1–8) or `mvn spring-boot:run` (9).
