# Factory Method Pattern Example

## Objective

Implement the Factory Method Design Pattern in Java to create different types of documents without exposing the object creation logic.

---

## What is Factory Method Pattern?

Factory Method is a **Creational Design Pattern** that provides an interface for creating objects while allowing subclasses to decide which object to create.

---

## Project Structure

```text
FactoryMethodPatternExample
├── README.md
└── src
    ├── Document.java
    ├── WordDocument.java
    ├── PdfDocument.java
    ├── ExcelDocument.java
    ├── DocumentFactory.java
    ├── WordDocumentFactory.java
    ├── PdfDocumentFactory.java
    ├── ExcelDocumentFactory.java
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
Opening Word Document
Opening PDF Document
Opening Excel Document
```

---

## Learning Outcomes

* Learned Factory Method Design Pattern
* Understood interfaces
* Understood abstract classes
* Learned object creation using factories
* Learned loose coupling


