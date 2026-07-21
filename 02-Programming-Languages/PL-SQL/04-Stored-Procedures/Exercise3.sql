-- ============================================================================
-- Exercise 3 - Stored Procedures
-- Prerequisites: schema.sql and sample_data.sql
-- ============================================================================

SET SERVEROUTPUT ON SIZE UNLIMITED;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
    v_interest_rate CONSTANT NUMBER := 0.01;
    v_updated_count NUMBER := 0;
BEGIN
    UPDATE Accounts
    SET Balance = ROUND(Balance * (1 + v_interest_rate), 2)
    WHERE AccountType = 'SAVINGS'
      AND Status = 'ACTIVE';

    v_updated_count := SQL%ROWCOUNT;
    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        'ProcessMonthlyInterest: applied 1% interest to ' || v_updated_count || ' savings account(s).'
    );
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ProcessMonthlyInterest failed: ' || SQLERRM);
        RAISE;
END ProcessMonthlyInterest;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department       IN Employees.Department%TYPE,
    p_bonus_percent    IN NUMBER
) IS
    v_rows NUMBER;
BEGIN
    IF p_bonus_percent IS NULL OR p_bonus_percent < 0 THEN
        RAISE_APPLICATION_ERROR(-20030, 'Bonus percentage must be zero or positive.');
    END IF;

    UPDATE Employees
    SET Salary = ROUND(Salary * (1 + (p_bonus_percent / 100)), 2)
    WHERE UPPER(Department) = UPPER(p_department);

    v_rows := SQL%ROWCOUNT;

    IF v_rows = 0 THEN
        RAISE_APPLICATION_ERROR(-20031, 'No employees found in department: ' || p_department);
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(
        'UpdateEmployeeBonus: updated ' || v_rows || ' employee(s) in ' || p_department
        || ' by ' || p_bonus_percent || '%.'
    );
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('UpdateEmployeeBonus failed: ' || SQLERRM);
        RAISE;
END UpdateEmployeeBonus;
/

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN Accounts.AccountID%TYPE,
    p_to_account   IN Accounts.AccountID%TYPE,
    p_amount       IN NUMBER
) IS
    v_balance Accounts.Balance%TYPE;
    v_txn_id  Transactions.TransactionID%TYPE;
BEGIN
    IF p_amount IS NULL OR p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20040, 'Amount must be greater than zero.');
    END IF;

    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_from_account
      AND Status = 'ACTIVE'
    FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20041, 'Insufficient balance for transfer.');
    END IF;

    SELECT NVL(MAX(TransactionID), 3000) + 1 INTO v_txn_id FROM Transactions;

    INSERT INTO Transactions (TransactionID, AccountID, TransactionType, Amount, Description)
    VALUES (v_txn_id, p_from_account, 'TRANSFER_OUT', p_amount, 'TransferFunds outbound');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionType, Amount, Description)
    VALUES (v_txn_id + 1, p_to_account, 'TRANSFER_IN', p_amount, 'TransferFunds inbound');

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('TransferFunds success: ' || p_amount || ' from ' || p_from_account || ' to ' || p_to_account);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20042, 'One or both accounts not found or inactive.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('TransferFunds failed: ' || SQLERRM);
        RAISE;
END TransferFunds;
/

PROMPT --- Demonstrating Exercise 3 procedures ---

BEGIN
    ProcessMonthlyInterest;
END;
/

BEGIN
    UpdateEmployeeBonus('IT', 3);
END;
/

BEGIN
    TransferFunds(2008, 2005, 250);
END;
/

PROMPT Exercise3.sql finished.
SHOW ERRORS PROCEDURE ProcessMonthlyInterest;
SHOW ERRORS PROCEDURE UpdateEmployeeBonus;
SHOW ERRORS PROCEDURE TransferFunds;
