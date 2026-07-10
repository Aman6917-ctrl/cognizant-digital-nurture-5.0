# Proxy Pattern Example

## Objective

Implement the Proxy Design Pattern in Java to demonstrate lazy initialization and caching while loading images.

---

## What is Proxy Pattern?

Proxy Pattern is a Structural Design Pattern that provides a placeholder for another object and controls access to it.

---

## Project Structure

```text
ProxyPatternExample
├── README.md
└── src
    ├── Image.java
    ├── RealImage.java
    ├── ProxyImage.java
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
Loading image from remote server: nature.jpg
Displaying image: nature.jpg

Displaying image: nature.jpg
```

---

## Learning Outcomes

- Learned Proxy Design Pattern
- Understood Lazy Initialization
- Learned Caching using Proxy
- Understood Access Control
- Learned Object Reuse