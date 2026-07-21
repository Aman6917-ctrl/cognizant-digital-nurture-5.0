# Exercise 1: Logging Error Messages and Warning Levels

## Objective

Create a small Maven application that uses SLF4J to log one ERROR message and one WARN message via `Logger` and `LoggerFactory`.

## Folder structure

```
Exercise-1-Logging-Error-Messages-And-Warning-Levels/
├── README.md
└── Exercise-1-Logging-Error-Messages-And-Warning-Levels/
    ├── pom.xml
    └── src/main/java/com/cognizant/slf4j/
        └── ErrorWarningLoggingApp.java
```

## How to run

```bash
cd Exercise-1-Logging-Error-Messages-And-Warning-Levels
mvn clean compile
mvn exec:java
```

## Expected output

Console output includes Logback lines similar to:

```
... ERROR com.cognizant.slf4j.ErrorWarningLoggingApp - Payment failed: insufficient funds
... WARN  com.cognizant.slf4j.ErrorWarningLoggingApp - Retrying payment in 5 seconds
```

Maven ends with `BUILD SUCCESS`.

## Technologies used

- Java 21
- SLF4J 1.7.30
- Logback 1.2.3
- Maven
