CREATE TABLE IF NOT EXISTS department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    salary DECIMAL(12, 2) NOT NULL,
    permanent BOOLEAN NOT NULL DEFAULT TRUE,
    department_id BIGINT,
    CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES department (id)
);
