-- Payroll adjustments applied after base seed data
UPDATE employee SET salary = 98000.00 WHERE id = 1;
UPDATE employee SET salary = 90500.00 WHERE id = 2;
UPDATE employee SET salary = 75000.00 WHERE id = 3;

INSERT INTO employee (name, salary, department_id) VALUES ('Eve Thompson', 87000.00, 1);

INSERT INTO employee_skill (employee_id, skill_id) VALUES (5, 2);
INSERT INTO employee_skill (employee_id, skill_id) VALUES (5, 3);
