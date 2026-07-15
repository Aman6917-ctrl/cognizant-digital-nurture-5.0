# Exercise 1: Mocking Databases and Repositories

## Scenario

This exercise demonstrates testing a service that interacts with a database repository.

## Implementation

- Created a Repository interface.
- Created a Service class that depends on Repository.
- Created a mock repository using Mockito.
- Stubbed the getData method using when and thenReturn.
- Verified the returned value and repository interaction.

## Technologies Used

- Java
- JUnit 5
- Mockito
- Maven

## Test Result

Tests run: 1  
Failures: 0  
Errors: 0  
Skipped: 0  

BUILD SUCCESS

## Learning Outcomes

- Learned to mock a repository dependency.
- Used constructor dependency injection in a service.
- Verified an interaction with a mock object.
