-- Here is the SQL script to insert the data into the database tables.

-- fake data demo

INSERT INTO
    `users` (
        `username`,
        `password`,
        `name`,
        `email`,
        `phone`,
        `role`
    )
VALUES (
        'employee1',
        'password123',
        'Employee 1',
        'employee1@example.com',
        '+1-555-1111',
        'employee'
    ), (
        'employee2',
        'password456',
        'Employee 2',
        'employee2@example.com',
        '+1-555-2222',
        'employee'
    ), (
        'admin',
        'password789',
        'Admin User',
        'admin@example.com',
        '+1-555-3333',
        'admin'
    ), (
        'customer1',
        'password123',
        'Customer 1',
        'customer1@example.com',
        '+1-555-4444',
        'customer'
    ), (
        'customer2',
        'password123',
        'Customer 2',
        'customer2@example.com',
        '+1-555-5555',
        'customer'
    ), (
        'customer3',
        'password123',
        'Customer 3',
        'customer3@example.com',
        '+1-555-6666',
        'customer'
    ), (
        'customer4',
        'password123',
        'Customer 4',
        'customer4@example.com',
        '+1-555-7777',
        'customer'
    ), (
        'customer5',
        'password123',
        'Customer 5',
        'customer5@example.com',
        '+1-555-8888',
        'customer'
    );

INSERT INTO
    employees (
        user_id,
        salary,
        employee_type,
        contact_information
    )
SELECT
    id,
    50000,
    'employee_manager',
    '123 Main St, Anytown USA'
FROM users
WHERE username = 'employee1';

INSERT INTO
    employees (
        user_id,
        salary,
        employee_type,
        contact_information
    )
SELECT
    id,
    45000,
    'employee_sales',
    '456 High St, Anytown USA'
FROM users
WHERE username = 'employee2';