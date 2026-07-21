INSERT INTO department (name) VALUES ('Engineering');
INSERT INTO department (name) VALUES ('Human Resources');
INSERT INTO department (name) VALUES ('Finance');

INSERT INTO employee (name, salary, permanent, department_id) VALUES ('Alice Chen', 95000.00, TRUE, 1);
INSERT INTO employee (name, salary, permanent, department_id) VALUES ('Bob Martinez', 88000.00, TRUE, 1);
INSERT INTO employee (name, salary, permanent, department_id) VALUES ('Carol Singh', 72000.00, FALSE, 2);
INSERT INTO employee (name, salary, permanent, department_id) VALUES ('David Kim', 91000.00, TRUE, 3);
INSERT INTO employee (name, salary, permanent, department_id) VALUES ('Eve Temp', 45000.00, FALSE, 2);

INSERT INTO skill (name) VALUES ('Java');
INSERT INTO skill (name) VALUES ('Spring Boot');
INSERT INTO skill (name) VALUES ('SQL');
INSERT INTO skill (name) VALUES ('AWS');

INSERT INTO employee_skill (employee_id, skill_id) VALUES (1, 1);
INSERT INTO employee_skill (employee_id, skill_id) VALUES (1, 2);
INSERT INTO employee_skill (employee_id, skill_id) VALUES (2, 1);
INSERT INTO employee_skill (employee_id, skill_id) VALUES (2, 3);
INSERT INTO employee_skill (employee_id, skill_id) VALUES (4, 4);
