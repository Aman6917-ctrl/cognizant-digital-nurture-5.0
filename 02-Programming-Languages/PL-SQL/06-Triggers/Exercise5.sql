-- ============================================================================
-- Exercise 5 - Triggers
-- Prerequisites: schema.sql and sample_data.sql
-- ============================================================================

SET SERVEROUTPUT ON SIZE UNLIMITED;

CREATE OR REPLACE TRIGGER trg_customers_last_modified
    BEFORE UPDATE ON Customers
    FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER trg_log_transaction
    AFTER INSERT ON Transactions
    FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TableName, Operation, RecordID, Details)
    VALUES (
        'TRANSACTIONS',
        'INSERT',
        :NEW.TransactionID,
        'Account=' || :NEW.AccountID
            || ', Type=' || :NEW.TransactionType
            || ', Amount=' || :NEW.Amount
            || ', Date=' || TO_CHAR(:NEW.TransactionDate, 'DD-MON-YYYY HH24:MI:SS')
    );
END;
/

CREATE OR REPLACE TRIGGER trg_check_transaction_rules
    BEFORE INSERT OR UPDATE ON Transactions
    FOR EACH ROW
DECLARE
    v_balance Accounts.Balance%TYPE;
BEGIN
    IF :NEW.Amount IS NULL OR :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20060, 'Transaction amount must be greater than zero.');
    END IF;

    IF :NEW.TransactionType = 'DEPOSIT' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20061, 'Deposit amount must be greater than zero.');
        END IF;
        RETURN;
    END IF;

    IF :NEW.TransactionType IN ('WITHDRAWAL', 'TRANSFER_OUT') THEN
        SELECT Balance
        INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID
          AND Status = 'ACTIVE';

        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(
                -20062,
                'Withdrawal exceeds available balance for account ' || :NEW.AccountID
            );
        END IF;
    END IF;
END;
/

PROMPT --- Demonstrating Exercise 5 triggers ---

UPDATE Customers
SET Phone = Phone
WHERE CustomerID = 1001;

DECLARE
    v_txn_id NUMBER;
BEGIN
    SELECT NVL(MAX(TransactionID), 3000) + 1 INTO v_txn_id FROM Transactions;

    INSERT INTO Transactions (TransactionID, AccountID, TransactionType, Amount, Description)
    VALUES (v_txn_id, 2001, 'DEPOSIT', 100, 'Trigger demo deposit');

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Valid deposit inserted. TransactionID=' || v_txn_id);
END;
/

DECLARE
    v_txn_id NUMBER;
BEGIN
    SELECT NVL(MAX(TransactionID), 3000) + 1 INTO v_txn_id FROM Transactions;

    INSERT INTO Transactions (TransactionID, AccountID, TransactionType, Amount, Description)
    VALUES (v_txn_id, 2005, 'WITHDRAWAL', 9999999, 'Should fail');

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Expected trigger rejection: ' || SQLERRM);
END;
/

PROMPT Exercise5.sql finished.
SHOW ERRORS TRIGGER trg_customers_last_modified;
SHOW ERRORS TRIGGER trg_log_transaction;
SHOW ERRORS TRIGGER trg_check_transaction_rules;
