# Observer Pattern Example

## Objective

Implement the Observer Design Pattern in Java to notify multiple clients whenever a stock price changes.

---

## What is Observer Pattern?

Observer Pattern is a Behavioral Design Pattern where one object (Subject) notifies multiple dependent objects (Observers) whenever its state changes.

---

## Project Structure

```text
ObserverPatternExample
├── README.md
└── src
    ├── Stock.java
    ├── Observer.java
    ├── StockMarket.java
    ├── MobileApp.java
    ├── WebApp.java
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
Mobile App: TCS price updated to ₹4250.5
Web App: TCS price updated to ₹4250.5
```

---

## Learning Outcomes

- Learned Observer Design Pattern
- Understood Subject and Observer relationship
- Learned Register and Deregister operations
- Understood Notification mechanism
- Learned One-to-Many communication