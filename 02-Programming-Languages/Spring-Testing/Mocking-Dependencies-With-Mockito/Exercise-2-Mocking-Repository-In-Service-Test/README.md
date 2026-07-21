# Exercise 2: Mocking Repository in a Service Test

## Scenario

Test a service by mocking its repository dependency.

## Implementation

- Used MockitoExtension and a mocked UserRepository.
- Injected the repository mock into UserService.
- Verified the returned user and repository interaction.

## Test Command

`mvn clean test`
