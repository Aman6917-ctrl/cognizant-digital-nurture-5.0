# Hands-On 3 — Quiz HQL & JOIN FETCH

## Objective

Model a small quiz domain and load a full **attempt report** with one JPQL query using a **JOIN FETCH** chain (user, questions, options, answers).

## Concepts Covered

- Normalized quiz schema (`quiz_option` table)
- `@OneToMany` / `@OneToOne` attempt flow
- `DISTINCT` with fetch joins
- Handbook-style report formatting

## Folder Structure

```
Hands-On-03-Quiz-HQL/
├── pom.xml
├── README.md
└── src/main/java/com/cognizant/hql/quiz/
    ├── QuizHqlApplication.java
    ├── demo/QuizDemoRunner.java
    ├── model/User, Question, QuizOption, Attempt, AttemptQuestion, AttemptAnswer
    ├── repository/AttemptRepository.java
    └── service/AttemptService.java
```

## Technologies Used

Java 21, Spring Boot 3.3.5, Spring Data JPA, MySQL 8, H2 (tests)

## Database Setup

Database `hql_quiz`. Scripts: `schema.sql`, `data.sql`, `quiz.sql` (one complete attempt with score 2/3).

## Configuration

`data-locations=classpath:data.sql,classpath:quiz.sql`

## HQL / JPQL

`AttemptRepository.findFullyLoadedById` uses multi-level `JOIN FETCH` for user, attempt questions, question options, and selected answers.

## Native Query

Not used.

## Criteria API

Not used.

## How to Run

```bash
cd Hands-On-03-Quiz-HQL
mvn clean compile
mvn spring-boot:run
mvn test
```

## Expected Output

Formatted report:

```
========================================
       QUIZ ATTEMPT REPORT
========================================
User        : learner.demo
Attempt Id  : 1
Score       : 2
...
```

## Learning Outcomes

- Design fetch graphs for read-heavy aggregate views.
- Avoid lazy-init errors when rendering nested collections outside a session.
