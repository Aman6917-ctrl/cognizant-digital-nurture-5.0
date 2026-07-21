# Exercise 3: Using Different Appenders

## Objective

Configure Logback with a console appender and a file appender, set the root logger to DEBUG, and emit TRACE through ERROR from Java. Logs are written to the console and to `app.log` in the project working directory.

## Folder structure

```
Exercise-3-Using-Different-Appenders/
├── README.md
└── Exercise-3-Using-Different-Appenders/
    ├── pom.xml
    ├── app.log                    (created after mvn exec:java)
    └── src/main/
        ├── java/com/cognizant/slf4j/
        │   └── AppenderLoggingApp.java
        └── resources/
            └── logback.xml
```

## How to run

```bash
cd Exercise-3-Using-Different-Appenders
mvn clean compile
mvn exec:java
```

## Expected output

**Console:** DEBUG, INFO, WARN, and ERROR lines from `AppenderLoggingApp`. TRACE is invoked in code but is below the DEBUG root threshold, so it does not appear in output.

**File:** The same visible levels are appended to `app.log` in the inner project directory.

Example line:

```
... ERROR com.cognizant.slf4j.AppenderLoggingApp - Critical failure in batch job
```

Maven ends with `BUILD SUCCESS`.

## Technologies used

- Java 21
- SLF4J 1.7.30
- Logback 1.2.3
- Maven
