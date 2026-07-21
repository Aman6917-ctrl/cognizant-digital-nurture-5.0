-- ============================================================================
-- Exercise 1 - Control Structures
-- Prerequisites: 01-Schema/schema.sql and sample_data.sql
-- ============================================================================

SET SERVEROUTPUT ON SIZE UNLIMITED;

PROMPT ===== Exercise 1 - Scenario 1: Senior citizen loan interest adjustment =====

DECLARE
    v_age           NUMBER;
    v_loans_updated NUMBER := 0;
BEGIN
    FOR rec IN (
        SELECT CustomerID, FirstName, LastName, DateOfBirth
        FROM Customers
        ORDER BY CustomerID
    ) LOOP
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, rec.DateOfBirth) / 12);

        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = GREATEST(InterestRate - 1, 0)
            WHERE CustomerID = rec.CustomerID
              AND Status = 'ACTIVE';

            v_loans_updated := v_loans_updated + SQL%ROWCOUNT;

            DBMS_OUTPUT.PUT_LINE(
                'Customer ' || rec.CustomerID || ': ' || rec.FirstName || ' ' || rec.LastName
                || ' | Age=' || v_age || ' | Loan rate reduced by 1%'
            );
        ELSE
            DBMS_OUTPUT.PUT_LINE(
                'Customer ' || rec.CustomerID || ': ' || rec.FirstName || ' ' || rec.LastName
                || ' | Age=' || v_age || ' | No rate change'
            );
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 1 complete. Loans updated: ' || v_loans_updated);
END;
/

PROMPT ===== Exercise 1 - Scenario 2: VIP flag based on total balance =====

DECLARE
    v_column_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_column_exists
    FROM user_tab_columns
    WHERE table_name = 'CUSTOMERS'
      AND column_name = 'ISVIP';

    IF v_column_exists = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE Customers ADD (IsVIP CHAR(1) DEFAULT ''N'' CHECK (IsVIP IN (''Y'', ''N'')))';
        DBMS_OUTPUT.PUT_LINE('Column IsVIP added to Customers.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Column IsVIP already exists.');
    END IF;
END;
/

DECLARE
    v_total_balance NUMBER;
BEGIN
    FOR rec IN (
        SELECT c.CustomerID, c.FirstName, c.LastName
        FROM Customers c
        ORDER BY c.CustomerID
    ) LOOP
        SELECT NVL(SUM(a.Balance), 0)
        INTO v_total_balance
        FROM Accounts a
        WHERE a.CustomerID = rec.CustomerID
          AND a.Status = 'ACTIVE';

        IF v_total_balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'Y' WHERE CustomerID = rec.CustomerID;
        ELSE
            UPDATE Customers SET IsVIP = 'N' WHERE CustomerID = rec.CustomerID;
        END IF;

        DBMS_OUTPUT.PUT_LINE(
            'Customer ' || rec.CustomerID || ' | Balance=' || v_total_balance
            || ' | IsVIP=' || CASE WHEN v_total_balance > 10000 THEN 'Y' ELSE 'N' END
        );
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 2 complete.');
END;
/

PROMPT ===== Exercise 1 - Scenario 3: Loan due reminders (next 30 days) =====

DECLARE
    v_count NUMBER := 0;
BEGIN
    FOR rec IN (
        SELECT l.LoanID,
               l.DueDate,
               c.CustomerID,
               c.FirstName,
               c.LastName,
               c.Phone
        FROM Loans l
        JOIN Customers c ON c.CustomerID = l.CustomerID
        WHERE l.Status = 'ACTIVE'
          AND l.DueDate BETWEEN TRUNC(SYSDATE) AND TRUNC(SYSDATE) + 30
        ORDER BY l.DueDate
    ) LOOP
        v_count := v_count + 1;
        DBMS_OUTPUT.PUT_LINE(
            'REMINDER: Customer ' || rec.FirstName || ' ' || rec.LastName
            || ' (ID ' || rec.CustomerID || ', Phone ' || NVL(rec.Phone, 'N/A') || ')'
            || ' - Loan ' || rec.LoanID || ' due on '
            || TO_CHAR(rec.DueDate, 'DD-MON-YYYY')
        );
    END LOOP;

    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No loans due within the next 30 days.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Scenario 3 complete. Reminders sent: ' || v_count);
    END IF;
END;
/

PROMPT Exercise1.sql finished.
