# Oracle PL/SQL Banking Assignment

## Introduction

This module is a complete **Oracle Database 21c** banking assignment implemented in PL/SQL. It models customers, accounts, transactions, loans, and employees, then applies core PL/SQL features—control structures, exception handling, stored programs, triggers, cursors, and packages—against realistic banking scenarios.

All scripts are designed for **Oracle SQL Developer** (SQL*Plus-compatible syntax).

## Objectives

- Design a normalized banking schema with integrity constraints
- Implement procedural logic with loops, conditions, and explicit cursors
- Handle errors with meaningful exceptions, `COMMIT`, and `ROLLBACK`
- Build reusable procedures, functions, triggers, and packages
- Audit transactions and enforce business rules at the database layer

## Folder Structure

```
PL-SQL/
├── README.md
├── 01-Schema/
│   ├── schema.sql
│   └── sample_data.sql
├── 02-Control-Structures/
│   └── Exercise1.sql
├── 03-Error-Handling/
│   └── Exercise2.sql
├── 04-Stored-Procedures/
│   └── Exercise3.sql
├── 05-Functions/
│   └── Exercise4.sql
├── 06-Triggers/
│   └── Exercise5.sql
├── 07-Cursors/
│   └── Exercise6.sql
├── 08-Packages/
│   └── Exercise7.sql
└── Validation/
    └── execution_order.md
```

## Oracle Version

- **Oracle Database 21c** (compatible with 19c+ for standard PL/SQL features used here)

## Prerequisites

- Oracle SQL Developer or SQL*Plus
- Database user with `CREATE TABLE`, `CREATE PROCEDURE`, `CREATE TRIGGER`, and `CREATE PACKAGE` privileges
- `SET SERVEROUTPUT ON` enabled (scripts set this where needed)

## Database Schema

| Table | Purpose |
|-------|---------|
| `Customers` | Customer profile and `LastModified` (trigger-maintained); `IsVIP` added in Exercise 1 |
| `Accounts` | SAVINGS / CHECKING / CURRENT accounts linked to customers |
| `Transactions` | Deposits, withdrawals, transfers |
| `Loans` | Loan amounts, rates, due dates |
| `Employees` | Bank staff and salaries |
| `AuditLog` | Audit trail for transaction inserts (trigger) |

See `01-Schema/schema.sql` for keys and checks.

## Exercises Covered

| Exercise | Topic | Artifacts |
|----------|--------|-----------|
| 1 | Control structures | Anonymous blocks (age-based loan rates, VIP flag, due reminders) |
| 2 | Error handling | `SafeTransferFunds`, `UpdateSalary`, `AddNewCustomer` |
| 3 | Stored procedures | `ProcessMonthlyInterest`, `UpdateEmployeeBonus`, `TransferFunds` |
| 4 | Functions | `CalculateAge`, `CalculateMonthlyInstallment`, `HasSufficientBalance` |
| 5 | Triggers | Last modified, audit log, transaction rules |
| 6 | Cursors | `GenerateMonthlyStatements`, `ApplyAnnualFee`, `UpdateLoanInterestRates` |
| 7 | Packages | `CustomerManagement`, `EmployeeManagement`, `AccountOperations` |

## Execution Order

Run scripts in this order (details in `Validation/execution_order.md`):

1. `01-Schema/schema.sql`
2. `01-Schema/sample_data.sql`
3. `02-Control-Structures/Exercise1.sql` through `08-Packages/Exercise7.sql`

Re-running `schema.sql` drops and recreates tables (destructive reset).

## How to Execute

In Oracle SQL Developer:

1. Connect as your schema user.
2. Open a script file.
3. Run Script (F5) or Execute as Script.
4. View **DBMS Output** for messages.

Example:

```text
@01-Schema/schema.sql
@01-Schema/sample_data.sql
@02-Control-Structures/Exercise1.sql
```

## Expected Output

- **Exercise 1:** Customer age/loan messages, VIP flags, loan due reminders
- **Exercise 2–3:** Success/failure messages for transfers, salary updates, interest, bonuses
- **Exercise 4:** Sample age, EMI, and balance check results
- **Exercise 5:** Audit rows in `AuditLog`; invalid withdrawal rejected
- **Exercise 6:** Monthly statement report, fee deductions, loan rate updates
- **Exercise 7:** Package demo output for customer, employee, and account operations

After Exercise 5, new transactions are logged to `AuditLog` automatically.

## Learning Outcomes

- Model banking data with constraints and relationships
- Write production-style PL/SQL with logging and transaction control
- Separate business logic into procedures, functions, and packages
- Enforce rules with triggers and validate behavior end-to-end in SQL Developer
