# Exercise 5: Mocking Multiple Return Values

## Scenario

This exercise demonstrates testing a service that receives different return values from a mocked method.

## Implementation

- Created a Repository interface.
- Created a Service class that depends on Repository.
- Configured consecutive return values on a mock repository.
- Verified both processed results and two repository interactions.

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

- Learned to configure consecutive return values with thenReturn.
- Tested repeated calls to a mocked dependency.
- Used times to verify the number of interactions.
