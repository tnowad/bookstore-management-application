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