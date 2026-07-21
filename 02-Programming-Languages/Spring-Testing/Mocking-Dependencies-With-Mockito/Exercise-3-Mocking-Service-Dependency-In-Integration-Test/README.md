# Exercise 3: Mocking Service Dependency in an Integration Test

## Scenario

Test a real controller with MockMvc and a mocked Spring service.

## Implementation

- Started the Spring test context with MockMvc.
- Replaced UserService with @MockBean.
- Verified the controller response and mock interaction.

## Test Command

`mvn clean test`
