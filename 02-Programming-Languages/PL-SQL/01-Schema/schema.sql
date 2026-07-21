-- ============================================================================
-- Banking Database Schema - Oracle Database 21c
-- Run as a user with CREATE TABLE privileges (e.g. BANK_APP).
-- ============================================================================

WHENEVER SQLERROR EXIT SQL.SQLCODE;

PROMPT Creating banking schema objects...

BEGIN
    FOR c IN (
        SELECT table_name
        FROM user_tables
        WHERE table_name IN (
            'TRANSACTIONS', 'ACCOUNTS', 'LOANS', 'EMPLOYEES',
            'CUSTOMERS', 'AUDITLOG'
        )
    ) LOOP
        EXECUTE IMMEDIATE 'DROP TABLE ' || c.table_name || ' CASCADE CONSTRAINTS PURGE';
    END LOOP;
END;
/

CREATE TABLE Customers (
    CustomerID      NUMBER(10)       NOT NULL,
    FirstName       VARCHAR2(50)     NOT NULL,
    LastName        VARCHAR2(50)     NOT NULL,
    DateOfBirth     DATE             NOT NULL,
    Email           VARCHAR2(100),
    Phone           VARCHAR2(20),
    Address         VARCHAR2(200),
    LastModified    DATE             DEFAULT SYSDATE NOT NULL,
    CONSTRAINT pk_customers PRIMARY KEY (CustomerID),
    CONSTRAINT chk_customers_email CHECK (Email IS NULL OR INSTR(Email, '@') > 0)
);

CREATE TABLE Accounts (
    AccountID       NUMBER(10)       NOT NULL,
    CustomerID      NUMBER(10)       NOT NULL,
    AccountType     VARCHAR2(20)     NOT NULL,
    Balance         NUMBER(15, 2)    DEFAULT 0 NOT NULL,
    OpenDate        DATE             DEFAULT SYSDATE NOT NULL,
    Status          VARCHAR2(10)     DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (AccountID),
    CONSTRAINT fk_accounts_customer FOREIGN KEY (CustomerID)
        REFERENCES Customers (CustomerID),
    CONSTRAINT chk_accounts_type CHECK (AccountType IN ('SAVINGS', 'CHECKING', 'CURRENT')),
    CONSTRAINT chk_accounts_balance CHECK (Balance >= 0),
    CONSTRAINT chk_accounts_status CHECK (Status IN ('ACTIVE', 'CLOSED', 'FROZEN'))
);

CREATE TABLE Transactions (
    TransactionID   NUMBER(10)       NOT NULL,
    AccountID       NUMBER(10)       NOT NULL,
    TransactionType VARCHAR2(20)     NOT NULL,
    Amount          NUMBER(15, 2)    NOT NULL,
    TransactionDate DATE             DEFAULT SYSDATE NOT NULL,
    Description     VARCHAR2(200),
    CONSTRAINT pk_transactions PRIMARY KEY (TransactionID),
    CONSTRAINT fk_transactions_account FOREIGN KEY (AccountID)
        REFERENCES Accounts (AccountID),
    CONSTRAINT chk_transactions_type CHECK (
        TransactionType IN ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER_IN', 'TRANSFER_OUT')
    ),
    CONSTRAINT chk_transactions_amount CHECK (Amount > 0)
);

CREATE TABLE Loans (
    LoanID          NUMBER(10)       NOT NULL,
    CustomerID      NUMBER(10)       NOT NULL,
    LoanAmount      NUMBER(15, 2)    NOT NULL,
    InterestRate    NUMBER(5, 2)     NOT NULL,
    TermYears       NUMBER(3)        NOT NULL,
    StartDate       DATE             NOT NULL,
    DueDate         DATE             NOT NULL,
    Status          VARCHAR2(20)     DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT pk_loans PRIMARY KEY (LoanID),
    CONSTRAINT fk_loans_customer FOREIGN KEY (CustomerID)
        REFERENCES Customers (CustomerID),
    CONSTRAINT chk_loans_amount CHECK (LoanAmount > 0),
    CONSTRAINT chk_loans_rate CHECK (InterestRate >= 0 AND InterestRate <= 100),
    CONSTRAINT chk_loans_term CHECK (TermYears > 0),
    CONSTRAINT chk_loans_status CHECK (Status IN ('ACTIVE', 'PAID', 'DEFAULT'))
);

CREATE TABLE Employees (
    EmployeeID      NUMBER(10)       NOT NULL,
    FirstName       VARCHAR2(50)     NOT NULL,
    LastName        VARCHAR2(50)     NOT NULL,
    Department      VARCHAR2(50)     NOT NULL,
    Salary          NUMBER(12, 2)    NOT NULL,
    HireDate        DATE             DEFAULT SYSDATE NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY (EmployeeID),
    CONSTRAINT chk_employees_salary CHECK (Salary > 0)
);

CREATE TABLE AuditLog (
    AuditID         NUMBER GENERATED ALWAYS AS IDENTITY,
    TableName       VARCHAR2(50)     NOT NULL,
    Operation       VARCHAR2(20)     NOT NULL,
    RecordID        NUMBER,
    Details         VARCHAR2(4000),
    AuditDate       TIMESTAMP        DEFAULT SYSTIMESTAMP NOT NULL,
    CONSTRAINT pk_auditlog PRIMARY KEY (AuditID)
);

CREATE INDEX idx_accounts_customer ON Accounts (CustomerID);
CREATE INDEX idx_transactions_account ON Transactions (AccountID);
CREATE INDEX idx_transactions_date ON Transactions (TransactionDate);
CREATE INDEX idx_loans_customer ON Loans (CustomerID);
CREATE INDEX idx_loans_duedate ON Loans (DueDate);

PROMPT Schema created successfully.
COMMIT;
