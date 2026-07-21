# PL/SQL Banking Assignment — Execution Order

Run all scripts as the **same Oracle schema user** in Oracle SQL Developer (Run Script / F5).

## Required order

| Step | Script | Purpose |
|------|--------|---------|
| 1 | `01-Schema/schema.sql` | Create tables, indexes, constraints |
| 2 | `01-Schema/sample_data.sql` | Load customers, accounts, transactions, loans, employees |
| 3 | `02-Control-Structures/Exercise1.sql` | Control structures (3 scenarios) |
| 4 | `03-Error-Handling/Exercise2.sql` | Error-handling procedures + demos |
| 5 | `04-Stored-Procedures/Exercise3.sql` | Banking procedures + demos |
| 6 | `05-Functions/Exercise4.sql` | Functions + demo block |
| 7 | `06-Triggers/Exercise5.sql` | Triggers + validation demos |
| 8 | `07-Cursors/Exercise6.sql` | Cursor procedures + demos |
| 9 | `08-Packages/Exercise7.sql` | Package specs/bodies + demos |

## SQL Developer steps

1. Enable **View → Dbms Output** and click the green **+** to attach output to your connection.
2. Open `schema.sql` → Run Script.
3. Continue through the list above in order.

## Reset database

To start fresh:

```text
@01-Schema/schema.sql
@01-Schema/sample_data.sql
```

Then re-run exercises 1–7.

## Compile verification (`SHOW ERRORS`)

After each exercise that creates stored objects, scripts include `SHOW ERRORS`. You can also run these manually:

### Procedures (Exercise 2)

```sql
SHOW ERRORS PROCEDURE SafeTransferFunds;
SHOW ERRORS PROCEDURE UpdateSalary;
SHOW ERRORS PROCEDURE AddNewCustomer;
```

### Procedures (Exercise 3)

```sql
SHOW ERRORS PROCEDURE ProcessMonthlyInterest;
SHOW ERRORS PROCEDURE UpdateEmployeeBonus;
SHOW ERRORS PROCEDURE TransferFunds;
```

### Procedures (Exercise 6)

```sql
SHOW ERRORS PROCEDURE GenerateMonthlyStatements;
SHOW ERRORS PROCEDURE ApplyAnnualFee;
SHOW ERRORS PROCEDURE UpdateLoanInterestRates;
```

### Functions (Exercise 4)

```sql
SHOW ERRORS FUNCTION CalculateAge;
SHOW ERRORS FUNCTION CalculateMonthlyInstallment;
SHOW ERRORS FUNCTION HasSufficientBalance;
```

### Triggers (Exercise 5)

```sql
SHOW ERRORS TRIGGER trg_customers_last_modified;
SHOW ERRORS TRIGGER trg_log_transaction;
SHOW ERRORS TRIGGER trg_check_transaction_rules;
```

### Packages (Exercise 7)

```sql
SHOW ERRORS PACKAGE CustomerManagement;
SHOW ERRORS PACKAGE BODY CustomerManagement;
SHOW ERRORS PACKAGE EmployeeManagement;
SHOW ERRORS PACKAGE BODY EmployeeManagement;
SHOW ERRORS PACKAGE AccountOperations;
SHOW ERRORS PACKAGE BODY AccountOperations;
```

### Object status query

```sql
SELECT object_name, object_type, status
FROM user_objects
WHERE object_type IN ('PROCEDURE','FUNCTION','TRIGGER','PACKAGE','PACKAGE BODY')
ORDER BY object_type, object_name;
```

All objects should show `STATUS = VALID` after successful runs.

## Notes

- Exercise 1 adds column `IsVIP` to `Customers` if missing.
- Exercise 5 triggers affect subsequent inserts/updates on `Customers` and `Transactions`.
- For a clean demo of Exercise 2 transfer demos without trigger interaction, run Exercise 2 **before** Exercise 5, or reset schema/data and follow the full order once.
