# Exercise 2: Parameterized Logging

## Objective

Demonstrate SLF4J parameterized logging with `{}` placeholders for `info`, `debug`, `warn`, and `error` without string concatenation.

## Folder structure

```
Exercise-2-Parameterized-Logging/
├── README.md
└── Exercise-2-Parameterized-Logging/
    ├── pom.xml
    └── src/main/java/com/cognizant/slf4j/
        └── ParameterizedLoggingApp.java
```

## How to run

```bash
cd Exercise-2-Parameterized-Logging
mvn clean compile
mvn exec:java
```

## Expected output

Console output includes lines such as:

```
... INFO  com.cognizant.slf4j.ParameterizedLoggingApp - User alice logged in
... DEBUG com.cognizant.slf4j.ParameterizedLoggingApp - Age 29
... WARN  com.cognizant.slf4j.ParameterizedLoggingApp - Session expiring in 120 seconds for user alice
... ERROR com.cognizant.slf4j.ParameterizedLoggingApp - Failed to save profile for user alice
```

(Default Logback root level is DEBUG, so INFO, WARN, ERROR, and DEBUG appear; TRACE is not used in this exercise.)

Maven ends with `BUILD SUCCESS`.

## Technologies used

- Java 21
- SLF4J 1.7.30
- Logback 1.2.3
- Maven
