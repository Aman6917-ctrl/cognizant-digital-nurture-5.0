-- ============================================================================
-- Exercise 2 - Error Handling (Stored Procedures)
-- Prerequisites: schema.sql and sample_data.sql
-- ============================================================================

SET SERVEROUTPUT ON SIZE UNLIMITED;

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_source_account   IN Accounts.AccountID%TYPE,
    p_dest_account     IN Accounts.AccountID%TYPE,
    p_amount           IN NUMBER
) IS
    v_source_balance   Accounts.Balance%TYPE;
    v_dest_exists      NUMBER;
    v_source_exists    NUMBER;
    v_next_txn_id      Transactions.TransactionID%TYPE;
BEGIN
    IF p_amount IS NULL OR p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be greater than zero.');
    END IF;

    IF p_source_account = p_dest_account THEN
        RAISE_APPLICATION_ERROR(-20002, 'Source and destination accounts must differ.');
    END IF;

    SELECT COUNT(*) INTO v_source_exists FROM Accounts WHERE AccountID = p_source_account AND Status = 'ACTIVE';
    SELECT COUNT(*) INTO v_dest_exists FROM Accounts WHERE AccountID = p_dest_account AND Status = 'ACTIVE';

    IF v_source_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Source account does not exist or is not active.');
    END IF;

    IF v_dest_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Destination account does not exist or is not active.');
    END IF;

    SELECT Balance INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_source_account
    FOR UPDATE;

    IF v_source_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20005, 'Insufficient balance in source account.');
    END IF;

    SELECT NVL(MAX(TransactionID), 3000) + 1 INTO v_next_txn_id FROM Transactions;

    INSERT INTO Transactions (TransactionID, AccountID, TransactionType, Amount, Description)
    VALUES (v_next_txn_id, p_source_account, 'TRANSFER_OUT', p_amount, 'SafeTransferFunds outbound');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionType, Amount, Description)
    VALUES (v_next_txn_id + 1, p_dest_account, 'TRANSFER_IN', p_amount, 'SafeTransferFunds inbound');

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_source_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_dest_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(
        'SafeTransferFunds success: ' || p_amount || ' from ' || p_source_account || ' to ' || p_dest_account
    );
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('SafeTransferFunds failed: ' || SQLERRM);
        RAISE;
END SafeTransferFunds;
/

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id       IN Employees.EmployeeID%TYPE,
    p_increase_percent  IN NUMBER
) IS
    v_rows NUMBER;
BEGIN
    IF p_increase_percent IS NULL OR p_increase_percent < 0 THEN
        RAISE_APPLICATION_ERROR(-20010, 'Increase percentage must be zero or positive.');
    END IF;

    UPDATE Employees
    SET Salary = Salary * (1 + (p_increase_percent / 100))
    WHERE EmployeeID = p_employee_id;

    v_rows := SQL%ROWCOUNT;

    IF v_rows = 0 THEN
        RAISE_APPLICATION_ERROR(-20011, 'Employee not found: ' || p_employee_id);
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(
        'UpdateSalary success for employee ' || p_employee_id || ' by ' || p_increase_percent || '%'
    );
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('UpdateSalary failed: ' || SQLERRM);
        RAISE;
END UpdateSalary;
/

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id   IN Customers.CustomerID%TYPE,
    p_first_name    IN Customers.FirstName%TYPE,
    p_last_name     IN Customers.LastName%TYPE,
    p_dob           IN Customers.DateOfBirth%TYPE,
    p_email         IN Customers.Email%TYPE DEFAULT NULL,
    p_phone         IN Customers.Phone%TYPE DEFAULT NULL,
    p_address       IN Customers.Address%TYPE DEFAULT NULL
) IS
BEGIN
    INSERT INTO Customers (
        CustomerID, FirstName, LastName, DateOfBirth, Email, Phone, Address
    ) VALUES (
        p_customer_id, p_first_name, p_last_name, p_dob, p_email, p_phone, p_address
    );

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('AddNewCustomer success: CustomerID ' || p_customer_id);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('AddNewCustomer failed: duplicate CustomerID ' || p_customer_id);
        RAISE_APPLICATION_ERROR(-20020, 'Duplicate CustomerID: ' || p_customer_id);
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('AddNewCustomer failed: ' || SQLERRM);
        RAISE;
END AddNewCustomer;
/

PROMPT --- Demonstrating Exercise 2 procedures ---

BEGIN
    SafeTransferFunds(2001, 2005, 500);
END;
/

BEGIN
    UpdateSalary(5007, 5);
END;
/

BEGIN
    AddNewCustomer(1011, 'Test', 'Customer', DATE '1999-01-01', 'test@email.com', '9999999999', 'Test City');
    AddNewCustomer(1011, 'Duplicate', 'Row', DATE '2000-01-01');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Expected duplicate handling: ' || SQLERRM);
END;
/

BEGIN
    SafeTransferFunds(2005, 2001, 999999);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Expected insufficient funds: ' || SQLERRM);
END;
/

PROMPT Exercise2.sql finished.
SHOW ERRORS PROCEDURE SafeTransferFunds;
SHOW ERRORS PROCEDURE UpdateSalary;
SHOW ERRORS PROCEDURE AddNewCustomer;
