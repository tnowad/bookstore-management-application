USE bookstore;
-- Accounts
INSERT INTO accounts (username, password, status) VALUES ('admin', 'admin', 'active');
INSERT INTO accounts (username, password, status) VALUES ('customer', 'customer', 'active');
INSERT INTO accounts (username, password, status) VALUES ('employee', 'employee', 'active');
-- Users
INSERT INTO users (account_id, first_name, last_name, email, phone, role) VALUES (2, 'John', 'Doe', 'johndoe@example.com', '1234567890', 'customer');
INSERT INTO users (account_id, first_name, last_name, email, phone, role) VALUES (3, 'Jane', 'Doe', 'janedoe@example.com', '0987654321', 'employee');
-- Customers
INSERT INTO customers (account_id, address) VALUES (2, '123 Main St.');
-- Employees
INSERT INTO employees (account_id, work_schedule, salary, employee_type, contact_information) VALUES (3, '2020-01-01', 100000, 'Manager', '1234567890');
