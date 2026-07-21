# SLF4J

Hands-on exercises for structured logging with SLF4J and Logback.

## Exercises

| Exercise | Topic |
|----------|--------|
| [Exercise 1](Exercise-1-Logging-Error-Messages-And-Warning-Levels/) | `Logger`, `LoggerFactory`, `error`, and `warn` |
| [Exercise 2](Exercise-2-Parameterized-Logging/) | Parameterized messages with `{}` placeholders |
| [Exercise 3](Exercise-3-Using-Different-Appenders/) | Console and file appenders via `logback.xml` |

## Dependencies

- `org.slf4j:slf4j-api:1.7.30`
- `ch.qos.logback:logback-classic:1.2.3`
- Java 21

## How to run

From each inner Maven project directory:

```bash
mvn clean compile
mvn exec:java
```

## Exercise 1

Log one ERROR and one WARN message using SLF4J.

## Exercise 2

Use parameterized logging (`logger.info("User {} logged in", username)`) without string concatenation.

## Exercise 3

Configure Logback with a console appender and a file appender (`app.log`), root level DEBUG, and emit TRACE through ERROR from Java.
