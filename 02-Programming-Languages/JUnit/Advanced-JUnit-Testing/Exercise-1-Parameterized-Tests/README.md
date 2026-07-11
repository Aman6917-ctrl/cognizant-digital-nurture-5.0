# Exercise 1: Parameterized Tests

## Scenario

Test a method that checks whether a number is even using JUnit parameterized tests.

## Implementation

- Created an `EvenChecker` class with the `isEven(int number)` method.
- Used JUnit 5.
- Used `@ParameterizedTest` to execute the same test multiple times.
- Used `@ValueSource` to provide multiple integer inputs.

## Test Inputs

- 2
- 4
- 6
- 8
- 10

## Test Result

Tests run: 5  
Failures: 0  
Errors: 0  
Skipped: 0  

BUILD SUCCESS

## Technologies Used

- Java
- JUnit 5
- Maven
