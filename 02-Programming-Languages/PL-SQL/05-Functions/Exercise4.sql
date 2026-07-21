-- ============================================================================
-- Exercise 4 - Functions
-- Prerequisites: schema.sql and sample_data.sql
-- ============================================================================

SET SERVEROUTPUT ON SIZE UNLIMITED;

CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER
IS
BEGIN
    IF p_dob IS NULL THEN
        RAISE_APPLICATION_ERROR(-20050, 'Date of birth cannot be null.');
    END IF;

    IF p_dob > SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20051, 'Date of birth cannot be in the future.');
    END IF;

    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END CalculateAge;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount    IN NUMBER,
    p_interest_rate  IN NUMBER,
    p_years          IN NUMBER
) RETURN NUMBER
IS
    v_monthly_rate NUMBER;
    v_months       NUMBER;
    v_emi          NUMBER;
BEGIN
    IF p_loan_amount IS NULL OR p_loan_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20052, 'Loan amount must be greater than zero.');
    END IF;

    IF p_years IS NULL OR p_years <= 0 THEN
        RAISE_APPLICATION_ERROR(-20053, 'Loan term in years must be greater than zero.');
    END IF;

    IF p_interest_rate IS NULL OR p_interest_rate < 0 THEN
        RAISE_APPLICATION_ERROR(-20054, 'Interest rate must be zero or positive.');
    END IF;

    v_months := p_years * 12;
    v_monthly_rate := (p_interest_rate / 100) / 12;

    IF v_monthly_rate = 0 THEN
        RETURN ROUND(p_loan_amount / v_months, 2);
    END IF;

    v_emi := p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)
             / (POWER(1 + v_monthly_rate, v_months) - 1);

    RETURN ROUND(v_emi, 2);
END CalculateMonthlyInstallment;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN Accounts.AccountID%TYPE,
    p_amount     IN NUMBER
) RETURN BOOLEAN
IS
    v_balance Accounts.Balance%TYPE;
BEGIN
    IF p_amount IS NULL OR p_amount <= 0 THEN
        RETURN FALSE;
    END IF;

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id
      AND Status = 'ACTIVE';

    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END HasSufficientBalance;
/

PROMPT --- Demonstrating Exercise 4 functions ---

DECLARE
    v_age NUMBER;
    v_emi NUMBER;
    v_ok  BOOLEAN;
BEGIN
    v_age := CalculateAge(DATE '1962-01-18');
    DBMS_OUTPUT.PUT_LINE('CalculateAge sample: ' || v_age);

    v_emi := CalculateMonthlyInstallment(500000, 9.5, 15);
    DBMS_OUTPUT.PUT_LINE('CalculateMonthlyInstallment sample EMI: ' || v_emi);

    v_ok := HasSufficientBalance(2001, 1000);
    IF v_ok THEN
        DBMS_OUTPUT.PUT_LINE('HasSufficientBalance(2001, 1000): TRUE');
    ELSE
        DBMS_OUTPUT.PUT_LINE('HasSufficientBalance(2001, 1000): FALSE');
    END IF;

    v_ok := HasSufficientBalance(2005, 999999);
    IF v_ok THEN
        DBMS_OUTPUT.PUT_LINE('HasSufficientBalance(2005, 999999): TRUE');
    ELSE
        DBMS_OUTPUT.PUT_LINE('HasSufficientBalance(2005, 999999): FALSE');
    END IF;
END;
/

PROMPT Exercise4.sql finished.
SHOW ERRORS FUNCTION CalculateAge;
SHOW ERRORS FUNCTION CalculateMonthlyInstallment;
SHOW ERRORS FUNCTION HasSufficientBalance;
