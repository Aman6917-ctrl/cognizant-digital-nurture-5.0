# Decorator Pattern Example

## Objective

Implement the Decorator Design Pattern in Java to add notification channels dynamically.

---

## What is Decorator Pattern?

Decorator Pattern is a Structural Design Pattern used to add new functionality to an object without modifying its existing code.

---

## Project Structure

```text
DecoratorPatternExample
├── README.md
└── src
    ├── Notifier.java
    ├── EmailNotifier.java
    ├── NotifierDecorator.java
    ├── SMSNotifierDecorator.java
    ├── SlackNotifierDecorator.java
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
Sending Email: Your order has been shipped!
Sending SMS: Your order has been shipped!
Sending Slack Message: Your order has been shipped!
```

---

## Learning Outcomes

- Learned Decorator Design Pattern
- Understood Component Interface
- Learned Abstract Decorator
- Learned Dynamic Feature Addition
- Understood Runtime Object Decoration