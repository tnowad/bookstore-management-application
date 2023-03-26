-- Active: 1676977930889@@127.0.0.1@3306@bookstore

INSERT INTO categories (name)
VALUES ('Fiction'), ('Non-fiction'), ('Mystery'), ('Romance'), ('Science fiction'), ('Fantasy');

INSERT INTO
    publishers (name, description)
VALUES (
        'Random House',
        '123 Main St, Anytown USA'
    ), (
        'Penguin',
        '456 Maple Ave, Somewhere USA'
    ), (
        'HarperCollins',
        '789 Oak St, Nowhere USA'
    );

INSERT INTO
    authors (name, description)
VALUES (
        'J.K. Rowling',
        'Author of the Harry Potter series'
    ), (
        'Stephen King',
        'Author of horror and suspense novels'
    ), (
        'Dan Brown',
        'Author of thrillers like The Da Vinci Code'
    ), (
        'John Grisham',
        'Author of legal thrillers'
    ), (
        'Agatha Christie',
        'Author of detective novels'
    );

INSERT INTO
    books (
        isbn,
        title,
        description,
        image,
        price,
        quantity,
        status,
        publisher_id,
        author_id
    )
VALUES (
        '9780061120084',
        'To Kill a Mockingbird',
        'A novel by Harper Lee',
        'https://example.com/to-kill-a-mockingbird.jpg',
        10,
        50,
        'available',
        3,
        1
    ), (
        '9781400078776',
        'The Kite Runner',
        'A novel by Khaled Hosseini',
        'https://example.com/the-kite-runner.jpg',
        15,
        30,
        'available',
        2,
        2
    ), (
        '9780440237685',
        'The Catcher in the Rye',
        'A novel by J.D. Salinger',
        'https://example.com/the-catcher-in-the-rye.jpg',
        12,
        25,
        'available',
        1,
        3
    );

INSERT INTO
    employees (
        user_id,
        salary,
        employee_type,
        contact_information
    )
VALUES (
        3,
        5000,
        'employee_manage',
        '123 Main St, Anytown USA'
    );

INSERT INTO
    `users` (
        `username`,
        `password`,
        `status`,
        `name`,
        `email`,
        `phone`,
        `role`
    )
VALUES (
        'johnsmith',
        'password1',
        'active',
        'John Smith',
        'john.smith@example.com',
        '123-456-7890',
        'customer'
    ), (
        'janedoe',
        'password2',
        'active',
        'Jane Doe',
        'jane.doe@example.com',
        '234-567-8901',
        'customer'
    ), (
        'admin',
        'password3',
        'active',
        'Admin User',
        'admin@example.com',
        '345-678-9012',
        'admin'
    );

INSERT INTO
    carts (user_id, status)
VALUES (1, 'shopping'), (2, 'shopping'), (3, 'shopping'), (1, 'pending'), (2, 'pending'), (3, 'pending'), (1, 'reject'), (2, 'reject'), (3, 'reject'), (1, 'accept'), (2, 'accept'), (3, 'accept');

INSERT INTO addresses (id, userId, street, city, state, zip) VALUES
(1, 101, '123 Main St', 'New York', 'NY', '10001'),
(2, 102, '456 Elm St', 'Los Angeles', 'CA', '90001'),
(3, 103, '789 Oak St', 'Chicago', 'IL', '60601'),
(4, 104, '321 Pine St', 'Houston', 'TX', '77001'),
(5, 105, '654 Maple St', 'Phoenix', 'AZ', '85001'),
(6, 106, '987 Cedar St', 'Philadelphia', 'PA', '19019'),
(7, 107, '246 Birch St', 'San Antonio', 'TX', '78201'),
(8, 108, '135 Walnut St', 'San Diego', 'CA', '92101'),
(9, 109, '864 Cherry St', 'Dallas', 'TX', '75201'),
(10, 110, '579 Spruce St', 'San Jose', 'CA', '95101'),
(11, 111, '802 Laurel St', 'Austin', 'TX', '78701'),
(12, 112, '753 Magnolia St', 'Jacksonville', 'FL', '32099'),
(13, 113, '496 Poplar St', 'Fort Worth', 'TX', '76101'),
(14, 114, '127 Cedar St', 'Columbus', 'OH', '43085'),
(15, 115, '358 Pine St', 'San Francisco', 'CA', '94101'),
(16, 116, '689 Oak St', 'Charlotte', 'NC', '28201'),
(17, 117, '910 Maple St', 'Indianapolis', 'IN', '46201'),
(18, 118, '243 Elm St', 'Seattle', 'WA', '98101'),
(19, 119, '576 Birch St', 'Denver', 'CO', '80201'),
(20, 120, '809 Walnut St', 'Washington', 'DC', '20001');
