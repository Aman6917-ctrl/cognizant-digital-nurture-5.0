-- ============================================================================
-- Exercise 6 - Explicit Cursors
-- Prerequisites: schema.sql and sample_data.sql
-- ============================================================================

SET SERVEROUTPUT ON SIZE UNLIMITED;

CREATE OR REPLACE PROCEDURE GenerateMonthlyStatements IS
    CURSOR c_customers IS
        SELECT CustomerID, FirstName, LastName
        FROM Customers
        ORDER BY CustomerID;

    CURSOR c_monthly_txns (p_customer_id Customers.CustomerID%TYPE) IS
        SELECT t.TransactionID,
               t.AccountID,
               t.TransactionType,
               t.Amount,
               t.TransactionDate,
               t.Description
        FROM Transactions t
        JOIN Accounts a ON a.AccountID = t.AccountID
        WHERE a.CustomerID = p_customer_id
          AND t.TransactionDate >= TRUNC(SYSDATE, 'MM')
          AND t.TransactionDate < ADD_MONTHS(TRUNC(SYSDATE, 'MM'), 1)
        ORDER BY t.TransactionDate, t.TransactionID;

    v_txn_rec c_monthly_txns%ROWTYPE;
    v_txn_count NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('===== Monthly Statements: ' || TO_CHAR(SYSDATE, 'MON-YYYY') || ' =====');

    FOR cust IN c_customers LOOP
        v_txn_count := 0;
        DBMS_OUTPUT.PUT_LINE(
            CHR(10) || 'Customer ' || cust.CustomerID || ': ' || cust.FirstName || ' ' || cust.LastName
        );
        DBMS_OUTPUT.PUT_LINE(RPAD('-', 70, '-'));

        OPEN c_monthly_txns(cust.CustomerID);
        LOOP
            FETCH c_monthly_txns INTO v_txn_rec;
            EXIT WHEN c_monthly_txns%NOTFOUND;

            v_txn_count := v_txn_count + 1;
            DBMS_OUTPUT.PUT_LINE(
                TO_CHAR(v_txn_rec.TransactionDate, 'DD-MON') || ' | Acct '
                || v_txn_rec.AccountID || ' | ' || RPAD(v_txn_rec.TransactionType, 12)
                || ' | ' || LPAD(TO_CHAR(v_txn_rec.Amount, '999,999,990.00'), 14)
                || ' | ' || NVL(v_txn_rec.Description, '-')
            );
        END LOOP;
        CLOSE c_monthly_txns;

        IF v_txn_count = 0 THEN
            DBMS_OUTPUT.PUT_LINE('No transactions this month.');
        END IF;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || 'GenerateMonthlyStatements complete.');
END GenerateMonthlyStatements;
/

CREATE OR REPLACE PROCEDURE ApplyAnnualFee (
    p_fee_amount IN NUMBER DEFAULT 150
) IS
    CURSOR c_active_accounts IS
        SELECT AccountID, CustomerID, Balance, AccountType
        FROM Accounts
        WHERE Status = 'ACTIVE'
        FOR UPDATE;

    v_rec       c_active_accounts%ROWTYPE;
    v_txn_id    Transactions.TransactionID%TYPE;
    v_processed NUMBER := 0;
BEGIN
    IF p_fee_amount IS NULL OR p_fee_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20070, 'Annual fee must be greater than zero.');
    END IF;

    OPEN c_active_accounts;
    LOOP
        FETCH c_active_accounts INTO v_rec;
        EXIT WHEN c_active_accounts%NOTFOUND;

        IF v_rec.Balance >= p_fee_amount THEN
            SELECT NVL(MAX(TransactionID), 3000) + 1 INTO v_txn_id FROM Transactions;

            INSERT INTO Transactions (TransactionID, AccountID, TransactionType, Amount, Description)
            VALUES (
                v_txn_id,
                v_rec.AccountID,
                'WITHDRAWAL',
                p_fee_amount,
                'Annual maintenance fee'
            );

            UPDATE Accounts
            SET Balance = Balance - p_fee_amount
            WHERE CURRENT OF c_active_accounts;

            v_processed := v_processed + 1;
            DBMS_OUTPUT.PUT_LINE(
                'Fee applied to account ' || v_rec.AccountID || ' (Customer ' || v_rec.CustomerID || ')'
            );
        ELSE
            DBMS_OUTPUT.PUT_LINE(
                'Skipped account ' || v_rec.AccountID || ' - insufficient balance for fee'
            );
        END IF;
    END LOOP;
    CLOSE c_active_accounts;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('ApplyAnnualFee complete. Accounts charged: ' || v_processed);
EXCEPTION
    WHEN OTHERS THEN
        IF c_active_accounts%ISOPEN THEN
            CLOSE c_active_accounts;
        END IF;
        ROLLBACK;
        RAISE;
END ApplyAnnualFee;
/

CREATE OR REPLACE PROCEDURE UpdateLoanInterestRates (
    p_senior_discount IN NUMBER DEFAULT 0.5,
    p_standard_increase IN NUMBER DEFAULT 0.25
) IS
    CURSOR c_loans IS
        SELECT l.LoanID,
               l.CustomerID,
               l.InterestRate,
               TRUNC(MONTHS_BETWEEN(SYSDATE, c.DateOfBirth) / 12) AS CustomerAge
        FROM Loans l
        JOIN Customers c ON c.CustomerID = l.CustomerID
        WHERE l.Status = 'ACTIVE'
        FOR UPDATE OF l.InterestRate;

    v_rec c_loans%ROWTYPE;
    v_new_rate NUMBER;
    v_count  NUMBER := 0;
BEGIN
    OPEN c_loans;
    LOOP
        FETCH c_loans INTO v_rec;
        EXIT WHEN c_loans%NOTFOUND;

        IF v_rec.CustomerAge > 60 THEN
            v_new_rate := GREATEST(v_rec.InterestRate - p_senior_discount, 0);
        ELSE
            v_new_rate := LEAST(v_rec.InterestRate + p_standard_increase, 100);
        END IF;

        UPDATE Loans
        SET InterestRate = ROUND(v_new_rate, 2)
        WHERE CURRENT OF c_loans;

        v_count := v_count + 1;
        DBMS_OUTPUT.PUT_LINE(
            'Loan ' || v_rec.LoanID || ' rate ' || v_rec.InterestRate || '% -> ' || ROUND(v_new_rate, 2) || '%'
        );
    END LOOP;
    CLOSE c_loans;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('UpdateLoanInterestRates complete. Loans updated: ' || v_count);
EXCEPTION
    WHEN OTHERS THEN
        IF c_loans%ISOPEN THEN
            CLOSE c_loans;
        END IF;
        ROLLBACK;
        RAISE;
END UpdateLoanInterestRates;
/

PROMPT --- Demonstrating Exercise 6 cursor procedures ---

BEGIN
    GenerateMonthlyStatements;
END;
/

BEGIN
    ApplyAnnualFee(150);
END;
/

BEGIN
    UpdateLoanInterestRates(0.5, 0.25);
END;
/

PROMPT Exercise6.sql finished.
SHOW ERRORS PROCEDURE GenerateMonthlyStatements;
SHOW ERRORS PROCEDURE ApplyAnnualFee;
SHOW ERRORS PROCEDURE UpdateLoanInterestRates;
