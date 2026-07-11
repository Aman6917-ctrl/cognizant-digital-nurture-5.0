# Exercise 3: Test Execution Order

## Scenario

This exercise demonstrates how to control the execution order of test methods using JUnit 5.

## Implementation

- Created an OrderedTests class.
- Used @TestMethodOrder annotation.
- Used MethodOrderer.OrderAnnotation.
- Used @Order to define test execution priority.

## Execution Order

1. First Test
2. Second Test
3. Third Test

## Technologies Used

- Java
- JUnit 5
- Maven

## Test Result

Tests run: 3  
Failures: 0  
Errors: 0  
Skipped: 0  

BUILD SUCCESS

## Learning Outcomes

- Learned test execution ordering in JUnit 5.
- Used @TestMethodOrder.
- Used @Order annotation.
- Understood that lower order values execute first.
