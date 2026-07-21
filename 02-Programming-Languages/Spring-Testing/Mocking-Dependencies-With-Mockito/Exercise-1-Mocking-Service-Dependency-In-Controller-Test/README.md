# Exercise 1: Mocking Service Dependency in a Controller Test

## Scenario

Test a controller by mocking its service dependency.

## Implementation

- Used MockitoExtension with a mocked UserService.
- Injected the mock into UserController.
- Verified the HTTP response and service interaction.

## Test Command

`mvn clean test`
