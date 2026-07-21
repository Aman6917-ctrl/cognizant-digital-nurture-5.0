# Exercise 7: Handling Void Methods with Exceptions

## Scenario

This exercise demonstrates testing a void method configured to throw an exception.

## Implementation

- Created an ExternalApi interface with a void sendData method.
- Configured the mock to throw an API Error using doThrow.
- Used assertThrows to verify the exception.
- Verified that the mocked method was called.

## Technologies Used

- Java
- JUnit 5
- Mockito
- Maven

## Learning Outcomes

- Learned how to configure exceptions for void methods.
- Used assertThrows to test expected failures.
