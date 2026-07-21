-- ============================================================================
-- Banking Sample Data - Oracle Database 21c
-- Prerequisites: schema.sql executed successfully.
-- ============================================================================

WHENEVER SQLERROR EXIT SQL.SQLCODE;

PROMPT Loading sample banking data...

DELETE FROM Transactions;
DELETE FROM Accounts;
DELETE FROM Loans;
DELETE FROM Employees;
DELETE FROM Customers;
DELETE FROM AuditLog;
COMMIT;

INSERT INTO Customers (CustomerID, FirstName, LastName, DateOfBirth, Email, Phone, Address) VALUES
(1001, 'Arjun', 'Mehta', DATE '1958-04-12', 'arjun.mehta@email.com', '9000000001', '12 MG Road, Mumbai');
INSERT INTO Customers VALUES
(1002, 'Priya', 'Sharma', DATE '1990-07-22', 'priya.sharma@email.com', '9000000002', '45 Park Street, Kolkata');
INSERT INTO Customers VALUES
(1003, 'Rahul', 'Verma', DATE '1985-11-03', 'rahul.verma@email.com', '9000000003', '78 Brigade Road, Bengaluru');
INSERT INTO Customers VALUES
(1004, 'Sneha', 'Iyer', DATE '1962-01-18', 'sneha.iyer@email.com', '9000000004', '22 Anna Salai, Chennai');
INSERT INTO Customers VALUES
(1005, 'Vikram', 'Singh', DATE '1995-09-30', 'vikram.singh@email.com', '9000000005', '9 Connaught Place, Delhi');
INSERT INTO Customers VALUES
(1006, 'Ananya', 'Patel', DATE '1988-06-14', 'ananya.patel@email.com', '9000000006', '3 CG Road, Ahmedabad');
INSERT INTO Customers VALUES
(1007, 'Karthik', 'Nair', DATE '1955-12-25', 'karthik.nair@email.com', '9000000007', '56 Marine Drive, Kochi');
INSERT INTO Customers VALUES
(1008, 'Divya', 'Reddy', DATE '1992-03-08', 'divya.reddy@email.com', '9000000008', '11 HITEC City, Hyderabad');
INSERT INTO Customers VALUES
(1009, 'Mohit', 'Gupta', DATE '1980-08-19', 'mohit.gupta@email.com', '9000000009', '7 Mall Road, Jaipur');
INSERT INTO Customers VALUES
(1010, 'Lakshmi', 'Rao', DATE '1965-10-05', 'lakshmi.rao@email.com', '9000000010', '18 FC Road, Pune');

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, OpenDate, Status) VALUES
(2001, 1001, 'SAVINGS',  12500.00, DATE '2018-01-15', 'ACTIVE');
INSERT INTO Accounts VALUES (2002, 1002, 'CHECKING',  8500.00, DATE '2019-03-20', 'ACTIVE');
INSERT INTO Accounts VALUES (2003, 1003, 'SAVINGS',  15200.00, DATE '2017-06-10', 'ACTIVE');
INSERT INTO Accounts VALUES (2004, 1004, 'CURRENT',  22000.00, DATE '2016-11-01', 'ACTIVE');
INSERT INTO Accounts VALUES (2005, 1005, 'SAVINGS',   3200.00, DATE '2020-08-05', 'ACTIVE');
INSERT INTO Accounts VALUES (2006, 1006, 'CHECKING', 11400.00, DATE '2018-09-12', 'ACTIVE');
INSERT INTO Accounts VALUES (2007, 1007, 'SAVINGS',  18750.00, DATE '2015-04-22', 'ACTIVE');
INSERT INTO Accounts VALUES (2008, 1008, 'SAVINGS',   9100.00, DATE '2021-02-18', 'ACTIVE');
INSERT INTO Accounts VALUES (2009, 1009, 'CURRENT',  10500.00, DATE '2019-12-30', 'ACTIVE');
INSERT INTO Accounts VALUES (2010, 1010, 'SAVINGS',  5600.00, DATE '2022-07-07', 'ACTIVE');

INSERT INTO Transactions (TransactionID, AccountID, TransactionType, Amount, TransactionDate, Description) VALUES
(3001, 2001, 'DEPOSIT',       5000.00, TRUNC(SYSDATE, 'MM') + 2, 'Salary credit');
INSERT INTO Transactions VALUES (3002, 2001, 'WITHDRAWAL',    1500.00, TRUNC(SYSDATE, 'MM') + 5, 'ATM withdrawal');
INSERT INTO Transactions VALUES (3003, 2002, 'DEPOSIT',       3000.00, TRUNC(SYSDATE, 'MM') + 1, 'Transfer in');
INSERT INTO Transactions VALUES (3004, 2003, 'WITHDRAWAL',    2000.00, TRUNC(SYSDATE, 'MM') + 8, 'Bill payment');
INSERT INTO Transactions VALUES (3005, 2004, 'DEPOSIT',      10000.00, TRUNC(SYSDATE, 'MM') + 3, 'Business deposit');
INSERT INTO Transactions VALUES (3006, 2005, 'DEPOSIT',       1200.00, ADD_MONTHS(TRUNC(SYSDATE, 'MM'), -1) + 10, 'Prior month deposit');
INSERT INTO Transactions VALUES (3007, 2006, 'WITHDRAWAL',     900.00, TRUNC(SYSDATE, 'MM') + 6, 'Shopping');
INSERT INTO Transactions VALUES (3008, 2007, 'DEPOSIT',       4500.00, TRUNC(SYSDATE, 'MM') + 4, 'Pension credit');
INSERT INTO Transactions VALUES (3009, 2008, 'TRANSFER_IN',   2500.00, TRUNC(SYSDATE, 'MM') + 7, 'Inbound transfer');
INSERT INTO Transactions VALUES (3010, 2009, 'WITHDRAWAL',    1100.00, TRUNC(SYSDATE, 'MM') + 9, 'Utility payment');
INSERT INTO Transactions VALUES (3011, 2010, 'DEPOSIT',       1800.00, TRUNC(SYSDATE, 'MM') + 2, 'Freelance income');
INSERT INTO Transactions VALUES (3012, 2002, 'WITHDRAWAL',     600.00, TRUNC(SYSDATE, 'MM') + 11, 'Fuel');
INSERT INTO Transactions VALUES (3013, 2003, 'DEPOSIT',       2200.00, TRUNC(SYSDATE, 'MM') + 12, 'Refund');
INSERT INTO Transactions VALUES (3014, 2004, 'WITHDRAWAL',    3500.00, TRUNC(SYSDATE, 'MM') + 13, 'Vendor payment');
INSERT INTO Transactions VALUES (3015, 2006, 'DEPOSIT',       1600.00, TRUNC(SYSDATE, 'MM') + 14, 'Bonus');
INSERT INTO Transactions VALUES (3016, 2007, 'WITHDRAWAL',     750.00, TRUNC(SYSDATE, 'MM') + 15, 'Medical');
INSERT INTO Transactions VALUES (3017, 2008, 'DEPOSIT',       1300.00, TRUNC(SYSDATE, 'MM') + 16, 'Gift');
INSERT INTO Transactions VALUES (3018, 2009, 'TRANSFER_OUT',   800.00, TRUNC(SYSDATE, 'MM') + 17, 'Outbound transfer');
INSERT INTO Transactions VALUES (3019, 2010, 'WITHDRAWAL',     400.00, TRUNC(SYSDATE, 'MM') + 18, 'Groceries');
INSERT INTO Transactions VALUES (3020, 2001, 'DEPOSIT',       2100.00, TRUNC(SYSDATE, 'MM') + 19, 'Interest');
INSERT INTO Transactions VALUES (3021, 2003, 'WITHDRAWAL',     950.00, ADD_MONTHS(TRUNC(SYSDATE, 'MM'), -2) + 5, 'Old withdrawal');
INSERT INTO Transactions VALUES (3022, 2005, 'DEPOSIT',        700.00, TRUNC(SYSDATE, 'MM') + 20, 'Cash deposit');
INSERT INTO Transactions VALUES (3023, 2002, 'DEPOSIT',       1400.00, TRUNC(SYSDATE, 'MM') + 21, 'UPI credit');
INSERT INTO Transactions VALUES (3024, 2004, 'DEPOSIT',       5000.00, TRUNC(SYSDATE, 'MM') + 22, 'Client payment');
INSERT INTO Transactions VALUES (3025, 2006, 'WITHDRAWAL',    1200.00, TRUNC(SYSDATE, 'MM') + 23, 'Insurance premium');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, TermYears, StartDate, DueDate, Status) VALUES
(4001, 1001, 500000.00,  9.50, 15, DATE '2019-05-01', SYSDATE + 20, 'ACTIVE');
INSERT INTO Loans VALUES (4002, 1002, 250000.00, 10.25, 10, DATE '2020-01-15', ADD_MONTHS(SYSDATE, 24), 'ACTIVE');
INSERT INTO Loans VALUES (4003, 1003, 750000.00,  8.75, 20, DATE '2018-08-20', ADD_MONTHS(SYSDATE, 60), 'ACTIVE');
INSERT INTO Loans VALUES (4004, 1004, 320000.00,  9.00, 12, DATE '2021-03-10', SYSDATE + 15, 'ACTIVE');
INSERT INTO Loans VALUES (4005, 1005, 180000.00, 11.00,  7, DATE '2022-06-01', ADD_MONTHS(SYSDATE, 36), 'ACTIVE');
INSERT INTO Loans VALUES (4006, 1006, 420000.00,  9.25, 15, DATE '2017-11-11', ADD_MONTHS(SYSDATE, 48), 'ACTIVE');
INSERT INTO Loans VALUES (4007, 1007, 600000.00,  8.50, 18, DATE '2016-04-05', SYSDATE + 28, 'ACTIVE');
INSERT INTO Loans VALUES (4008, 1008, 150000.00, 10.50,  5, DATE '2023-02-28', ADD_MONTHS(SYSDATE, 18), 'ACTIVE');
INSERT INTO Loans VALUES (4009, 1009, 280000.00,  9.75, 10, DATE '2020-09-09', SYSDATE + 10, 'ACTIVE');
INSERT INTO Loans VALUES (4010, 1010, 350000.00,  9.10, 12, DATE '2019-12-12', ADD_MONTHS(SYSDATE, 30), 'ACTIVE');

INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary, HireDate) VALUES
(5001, 'Neha', 'Kapoor', 'Operations',  65000.00, DATE '2015-02-01');
INSERT INTO Employees VALUES (5002, 'Rohan', 'Desai', 'Operations',  72000.00, DATE '2016-06-15');
INSERT INTO Employees VALUES (5003, 'Meera', 'Joshi', 'Credit',      81000.00, DATE '2014-09-20');
INSERT INTO Employees VALUES (5004, 'Sanjay', 'Malhotra', 'Credit',   88000.00, DATE '2013-11-10');
INSERT INTO Employees VALUES (5005, 'Pooja', 'Bansal', 'HR',         58000.00, DATE '2018-01-08');
INSERT INTO Employees VALUES (5006, 'Amit', 'Khanna', 'HR',          62000.00, DATE '2017-04-22');
INSERT INTO Employees VALUES (5007, 'Isha', 'Menon', 'IT',           95000.00, DATE '2012-07-30');
INSERT INTO Employees VALUES (5008, 'Varun', 'Saxena', 'IT',        102000.00, DATE '2011-03-18');
INSERT INTO Employees VALUES (5009, 'Nisha', 'Pillai', 'Branch',    54000.00, DATE '2019-10-05');
INSERT INTO Employees VALUES (5010, 'Harish', 'Dutta', 'Branch',     59000.00, DATE '2020-05-12');

COMMIT;

PROMPT Sample data loaded successfully.
