-- ============================================================================
-- Exercise 7 - Packages
-- Prerequisites: schema.sql and sample_data.sql
-- ============================================================================

SET SERVEROUTPUT ON SIZE UNLIMITED;

CREATE OR REPLACE PACKAGE CustomerManagement IS
    PROCEDURE AddNewCustomer (
        p_customer_id   IN Customers.CustomerID%TYPE,
        p_first_name    IN Customers.FirstName%TYPE,
        p_last_name     IN Customers.LastName%TYPE,
        p_dob           IN Customers.DateOfBirth%TYPE,
        p_email         IN Customers.Email%TYPE DEFAULT NULL,
        p_phone         IN Customers.Phone%TYPE DEFAULT NULL,
        p_address       IN Customers.Address%TYPE DEFAULT NULL
    );

    PROCEDURE UpdateCustomer (
        p_customer_id   IN Customers.CustomerID%TYPE,
        p_first_name    IN Customers.FirstName%TYPE DEFAULT NULL,
        p_last_name     IN Customers.LastName%TYPE DEFAULT NULL,
        p_email         IN Customers.Email%TYPE DEFAULT NULL,
        p_phone         IN Customers.Phone%TYPE DEFAULT NULL,
        p_address       IN Customers.Address%TYPE DEFAULT NULL
    );

    FUNCTION GetCustomerBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement IS
    PROCEDURE AddNewCustomer (
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
        DBMS_OUTPUT.PUT_LINE('CustomerManagement.AddNewCustomer: ' || p_customer_id);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20080, 'Customer already exists: ' || p_customer_id);
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END AddNewCustomer;

    PROCEDURE UpdateCustomer (
        p_customer_id   IN Customers.CustomerID%TYPE,
        p_first_name    IN Customers.FirstName%TYPE DEFAULT NULL,
        p_last_name     IN Customers.LastName%TYPE DEFAULT NULL,
        p_email         IN Customers.Email%TYPE DEFAULT NULL,
        p_phone         IN Customers.Phone%TYPE DEFAULT NULL,
        p_address       IN Customers.Address%TYPE DEFAULT NULL
    ) IS
        v_rows NUMBER;
    BEGIN
        UPDATE Customers
        SET FirstName = NVL(p_first_name, FirstName),
            LastName  = NVL(p_last_name, LastName),
            Email     = NVL(p_email, Email),
            Phone     = NVL(p_phone, Phone),
            Address   = NVL(p_address, Address)
        WHERE CustomerID = p_customer_id;

        v_rows := SQL%ROWCOUNT;

        IF v_rows = 0 THEN
            RAISE_APPLICATION_ERROR(-20081, 'Customer not found: ' || p_customer_id);
        END IF;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('CustomerManagement.UpdateCustomer: ' || p_customer_id);
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0)
        INTO v_total
        FROM Accounts
        WHERE CustomerID = p_customer_id
          AND Status = 'ACTIVE';

        RETURN v_total;
    END GetCustomerBalance;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE EmployeeManagement IS
    PROCEDURE HireEmployee (
        p_employee_id   IN Employees.EmployeeID%TYPE,
        p_first_name    IN Employees.FirstName%TYPE,
        p_last_name     IN Employees.LastName%TYPE,
        p_department    IN Employees.Department%TYPE,
        p_salary        IN Employees.Salary%TYPE,
        p_hire_date     IN Employees.HireDate%TYPE DEFAULT SYSDATE
    );

    PROCEDURE UpdateEmployee (
        p_employee_id   IN Employees.EmployeeID%TYPE,
        p_department    IN Employees.Department%TYPE DEFAULT NULL,
        p_salary        IN Employees.Salary%TYPE DEFAULT NULL
    );

    FUNCTION CalculateAnnualSalary (
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement IS
    PROCEDURE HireEmployee (
        p_employee_id   IN Employees.EmployeeID%TYPE,
        p_first_name    IN Employees.FirstName%TYPE,
        p_last_name     IN Employees.LastName%TYPE,
        p_department    IN Employees.Department%TYPE,
        p_salary        IN Employees.Salary%TYPE,
        p_hire_date     IN Employees.HireDate%TYPE DEFAULT SYSDATE
    ) IS
    BEGIN
        INSERT INTO Employees (
            EmployeeID, FirstName, LastName, Department, Salary, HireDate
        ) VALUES (
            p_employee_id, p_first_name, p_last_name, p_department, p_salary, p_hire_date
        );
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('EmployeeManagement.HireEmployee: ' || p_employee_id);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20082, 'Employee already exists: ' || p_employee_id);
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END HireEmployee;

    PROCEDURE UpdateEmployee (
        p_employee_id   IN Employees.EmployeeID%TYPE,
        p_department    IN Employees.Department%TYPE DEFAULT NULL,
        p_salary        IN Employees.Salary%TYPE DEFAULT NULL
    ) IS
        v_rows NUMBER;
    BEGIN
        UPDATE Employees
        SET Department = NVL(p_department, Department),
            Salary     = NVL(p_salary, Salary)
        WHERE EmployeeID = p_employee_id;

        v_rows := SQL%ROWCOUNT;

        IF v_rows = 0 THEN
            RAISE_APPLICATION_ERROR(-20083, 'Employee not found: ' || p_employee_id);
        END IF;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('EmployeeManagement.UpdateEmployee: ' || p_employee_id);
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END UpdateEmployee;

    FUNCTION CalculateAnnualSalary (
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER IS
        v_monthly Employees.Salary%TYPE;
    BEGIN
        SELECT Salary
        INTO v_monthly
        FROM Employees
        WHERE EmployeeID = p_employee_id;

        RETURN ROUND(v_monthly * 12, 2);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20084, 'Employee not found: ' || p_employee_id);
    END CalculateAnnualSalary;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE AccountOperations IS
    PROCEDURE OpenAccount (
        p_account_id    IN Accounts.AccountID%TYPE,
        p_customer_id   IN Accounts.CustomerID%TYPE,
        p_account_type  IN Accounts.AccountType%TYPE,
        p_open_balance  IN Accounts.Balance%TYPE DEFAULT 0
    );

    PROCEDURE CloseAccount (
        p_account_id IN Accounts.AccountID%TYPE
    );

    FUNCTION GetTotalBalance (
        p_customer_id IN Accounts.CustomerID%TYPE
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations IS
    PROCEDURE OpenAccount (
        p_account_id    IN Accounts.AccountID%TYPE,
        p_customer_id   IN Accounts.CustomerID%TYPE,
        p_account_type  IN Accounts.AccountType%TYPE,
        p_open_balance  IN Accounts.Balance%TYPE DEFAULT 0
    ) IS
        v_customer_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_customer_exists FROM Customers WHERE CustomerID = p_customer_id;

        IF v_customer_exists = 0 THEN
            RAISE_APPLICATION_ERROR(-20090, 'Customer not found: ' || p_customer_id);
        END IF;

        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, Status)
        VALUES (p_account_id, p_customer_id, p_account_type, NVL(p_open_balance, 0), 'ACTIVE');

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('AccountOperations.OpenAccount: ' || p_account_id);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20091, 'Account already exists: ' || p_account_id);
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END OpenAccount;

    PROCEDURE CloseAccount (
        p_account_id IN Accounts.AccountID%TYPE
    ) IS
        v_balance Accounts.Balance%TYPE;
        v_rows    NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = p_account_id
          AND Status = 'ACTIVE'
        FOR UPDATE;

        IF v_balance <> 0 THEN
            RAISE_APPLICATION_ERROR(-20092, 'Account balance must be zero before closing.');
        END IF;

        UPDATE Accounts SET Status = 'CLOSED' WHERE AccountID = p_account_id;
        v_rows := SQL%ROWCOUNT;

        IF v_rows = 0 THEN
            RAISE_APPLICATION_ERROR(-20093, 'Active account not found: ' || p_account_id);
        END IF;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('AccountOperations.CloseAccount: ' || p_account_id);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20094, 'Account not found: ' || p_account_id);
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END CloseAccount;

    FUNCTION GetTotalBalance (
        p_customer_id IN Accounts.CustomerID%TYPE
    ) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0)
        INTO v_total
        FROM Accounts
        WHERE CustomerID = p_customer_id
          AND Status = 'ACTIVE';

        RETURN v_total;
    END GetTotalBalance;
END AccountOperations;
/

PROMPT --- Demonstrating Exercise 7 packages ---

BEGIN
    CustomerManagement.AddNewCustomer(
        1012, 'Riya', 'Sen', DATE '1993-05-17', 'riya.sen@email.com', '9888888888', 'Salt Lake, Kolkata'
    );
    CustomerManagement.UpdateCustomer(1012, p_phone => '9876543210');
    DBMS_OUTPUT.PUT_LINE('Balance customer 1001: ' || CustomerManagement.GetCustomerBalance(1001));
END;
/

BEGIN
    EmployeeManagement.HireEmployee(5011, 'Tarun', 'Bhatt', 'IT', 78000, SYSDATE);
    EmployeeManagement.UpdateEmployee(5011, p_salary => 82000);
    DBMS_OUTPUT.PUT_LINE('Annual salary 5011: ' || EmployeeManagement.CalculateAnnualSalary(5011));
END;
/

BEGIN
    AccountOperations.OpenAccount(2011, 1012, 'SAVINGS', 2500);
    DBMS_OUTPUT.PUT_LINE('Total balance 1012: ' || AccountOperations.GetTotalBalance(1012));
END;
/

PROMPT Exercise7.sql finished.
SHOW ERRORS PACKAGE CustomerManagement;
SHOW ERRORS PACKAGE BODY CustomerManagement;
SHOW ERRORS PACKAGE EmployeeManagement;
SHOW ERRORS PACKAGE BODY EmployeeManagement;
SHOW ERRORS PACKAGE AccountOperations;
SHOW ERRORS PACKAGE BODY AccountOperations;
