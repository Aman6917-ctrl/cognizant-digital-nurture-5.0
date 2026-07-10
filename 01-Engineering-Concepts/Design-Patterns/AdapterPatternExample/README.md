# Adapter Pattern Example

## Objective

Implement the Adapter Design Pattern in Java to integrate different payment gateways using a common interface.

---

## What is Adapter Pattern?

Adapter Pattern is a Structural Design Pattern that allows incompatible interfaces to work together.

---

## Project Structure

```text
AdapterPatternExample
├── README.md
└── src
    ├── PaymentProcessor.java
    ├── PayPalGateway.java
    ├── StripeGateway.java
    ├── PayPalAdapter.java
    ├── StripeAdapter.java
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
Payment of ₹1500.0 processed using PayPal.
Payment of ₹2500.0 processed using Stripe.
```

---

## Learning Outcomes

- Learned Adapter Design Pattern
- Understood Interface Implementation
- Learned Adapter Classes
- Understood Third-party Integration
- Learned Loose Coupling
