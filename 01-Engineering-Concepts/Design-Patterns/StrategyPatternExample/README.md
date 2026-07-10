# Strategy Pattern Example

## Objective

Implement the Strategy Design Pattern in Java to demonstrate selecting different payment methods at runtime.

---

## What is Strategy Pattern?

Strategy Pattern is a Behavioral Design Pattern that allows selecting different algorithms or behaviors at runtime.

---

## Project Structure

```text
StrategyPatternExample
├── README.md
└── src
    ├── PaymentStrategy.java
    ├── CreditCardPayment.java
    ├── PayPalPayment.java
    ├── PaymentContext.java
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
Paid ₹1500.0 using Credit Card.
Paid ₹2500.0 using PayPal.
```

---

## Learning Outcomes

- Learned Strategy Design Pattern
- Understood Strategy Interface
- Learned Runtime Behavior Selection
- Understood Context Class
- Learned Loose Coupling