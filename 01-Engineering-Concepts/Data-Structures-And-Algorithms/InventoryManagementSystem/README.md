# Inventory Management System

## Objective

Implement an Inventory Management System in Java using HashMap to efficiently store, update, and delete products.

---

## Problem Statement

Develop an inventory management system for a warehouse where products can be added, updated, deleted, and displayed efficiently.

---

## Data Structure Used

- HashMap<Integer, Product>

HashMap provides fast access to products using Product ID as the key.

---

## Project Structure

```text
InventoryManagementSystem
├── README.md
└── src
    ├── Product.java
    ├── Inventory.java
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
Product Added Successfully.
Product Added Successfully.

ID: 101, Name: Laptop, Quantity: 10, Price: ₹55000.0
ID: 102, Name: Mouse, Quantity: 50, Price: ₹500.0

Product Updated Successfully.

ID: 101, Name: Laptop, Quantity: 8, Price: ₹53000.0
ID: 102, Name: Mouse, Quantity: 50, Price: ₹500.0

Product Deleted Successfully.

ID: 101, Name: Laptop, Quantity: 8, Price: ₹53000.0
```

---

## Time Complexity

| Operation | Complexity |
|-----------|------------|
| Add Product | O(1) |
| Search Product | O(1) |
| Update Product | O(1) |
| Delete Product | O(1) |

---

## Learning Outcomes

- Learned HashMap
- Learned CRUD Operations
- Understood Time Complexity
- Improved Data Management

