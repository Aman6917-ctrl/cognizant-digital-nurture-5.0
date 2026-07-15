# Exercise 3: Mocking File I/O

## Scenario

This exercise demonstrates testing a service that reads from and writes to files.

## Implementation

- Created custom FileReader and FileWriter interfaces.
- Created a FileService class that depends on both interfaces.
- Stubbed file reading and a void file-writing method.
- Verified the processed content and file operations.

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

- Learned to make file operations mockable with custom interfaces.
- Used doNothing for a void method.
- Verified the value passed to a mock method.
