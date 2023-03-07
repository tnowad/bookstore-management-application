USE bookstore;

-- Accounts
INSERT INTO
  accounts (username, password, status)
VALUES
  ('admin', '$2a$12$cCFhZJLkVZ3BUeRszsLkseHCHGeXpJK7s3DdweN7dSHP.fzF8U6S2', 'active');

INSERT INTO
  accounts (username, password, status)
VALUES
  ('customer', '$2a$12$foCx0AYFiEnPsMjZNy/tTOe6i.orMQ/jH9ZLu.9i6/tKMDWC8JeAy', 'active');

INSERT INTO
  accounts (username, password, status)
VALUES
  ('employee', '$2a$12$oR4/FbnH4dZEzQvTXG3Lx.c5P9cqnX8NYEXZUSdiRkCOF4U8mMln6', 'active');

-- Users
INSERT INTO
  users (
    account_id,
    first_name,
    last_name,
    email,
    phone,
    role
  )
VALUES
  (
    2,
    'John',
    'Doe',
    'johndoe@example.com',
    '1234567890',
    'customer'
  );

INSERT INTO
  users (
    account_id,
    first_name,
    last_name,
    email,
    phone,
    role
  )
VALUES
  (
    3,
    'Jane',
    'Doe',
    'janedoe@example.com',
    '0987654321',
    'employee'
  );

-- Customers
INSERT INTO
  customers (account_id, address)
VALUES
  (2, '123 Main St.');

-- Employees
INSERT INTO
  employees (
    account_id,
    work_schedule,
    salary,
    employee_type,
    contact_information
  )
VALUES
  (3, '2020-01-01', 100000, 'Manager', '1234567890');