# Command Pattern Example

## Objective

Implement the Command Design Pattern in Java to control a light using a remote control.

---

## What is Command Pattern?

Command Pattern is a Behavioral Design Pattern that converts a request into an object, allowing commands to be executed, queued, or changed independently.

---

## Project Structure

```text
CommandPatternExample
├── README.md
└── src
    ├── Command.java
    ├── Light.java
    ├── LightOnCommand.java
    ├── LightOffCommand.java
    ├── RemoteControl.java
    └── Main.java
```

---

## How to Compile

```bash
cd src
javac *.java
```

---

## How to Run

```bash
java Main
```

---

## Expected Output

```text
Light is ON
Light is OFF
```

---

## Learning Outcomes

- Learned Command Design Pattern
- Understood Command Interface
- Learned Invoker and Receiver
- Understood Command Execution
- Learned Loose Coupling