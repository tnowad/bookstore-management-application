-- Here is the SQL script to insert the data into the database tables.

-- fake data demo

INSERT INTO
    `authors` (`name`, `description`)
VALUES (
        'Eric Gamma',
        'A computer scientist and author who co-authored the book "Design Patterns"'
    ), (
        'Andrew Ng',
        'A computer scientist and AI researcher who co-founded Google Brain and Coursera'
    ), (
        'Robert C. Martin',
        'A software engineer and author who wrote the book "Clean Code"'
    ), (
        'Steve McConnell',
        'A software engineer and author who wrote the book "Code Complete"'
    ), (
        'Martin Fowler',
        'A software engineer and author who wrote the book "Refactoring"'
    ), (
        'Bjarne Stroustrup',
        'A computer scientist and creator of the C++ programming language'
    ), (
        'Yukihiro Matsumoto',
        'A computer scientist and creator of the Ruby programming language'
    ), (
        'Brian Kernighan',
        'A computer scientist and author who co-wrote the book "The C Programming Language"'
    ), (
        'Dennis Ritchie',
        'A computer scientist and creator of the C programming language'
    ), (
        'Grace Hopper',
        'A computer scientist and pioneer of computer programming'
    ), (
        'Linus Torvalds',
        'A software engineer and creator of the Linux operating system'
    ), (
        'Ada Lovelace',
        'A mathematician and writer who is credited with writing the first algorithm'
    ), (
        'Donald Knuth',
        'A computer scientist and author who wrote "The Art of Computer Programming" series'
    ), (
        'Guido van Rossum',
        'A computer scientist and creator of the Python programming language'
    );

INSERT INTO
    `publishers` (`name`, `description`)
VALUES (
        'O Reilly Media',
        'A technology and business book publisher'
    ), (
        'Addison-Wesley',
        'An academic and professional book publisher'
    ), (
        'Prentice Hall',
        'A higher education and professional book publisher'
    ), (
        'Manning Publications',
        'A technology book publisher'
    ), (
        'Pearson Education',
        'A global education company'
    ), (
        'MIT Press',
        'An academic book publisher'
    ), (
        'No Starch Press',
        'An independent technology book publisher'
    );

INSERT INTO
    `books` (
        `isbn`,
        `title`,
        `description`,
        `image`,
        `price`,
        `quantity`,
        `status`,
        `publisher_id`,
        `author_id`
    )
VALUES (
        '978-0-306-40615-7',
        'The Art of Computer Programming',
        'A comprehensive guide to computer programming',
        'https://example.com/book1.jpg',
        50,
        10,
        'available',
        1,
        1
    ), (
        '978-0-596-52068-7',
        'Programming Collective Intelligence',
        'A practical guide to building intelligent web applications',
        'https://example.com/book2.jpg',
        30,
        5,
        'available',
        2,
        2
    ), (
        '978-0-201-87701-3',
        'The C Programming Language',
        'A classic book on the C programming language',
        'https://example.com/book3.jpg',
        25,
        15,
        'available',
        3,
        3
    ), (
        '978-0-321-58495-5',
        'Effective Java',
        'A guide to writing effective Java code',
        'https://example.com/book4.jpg',
        40,
        8,
        'unavailable',
        4,
        4
    ), (
        '978-0-596-00733-4',
        'Python for Data Analysis',
        'A practical guide to data analysis using Python',
        'https://example.com/book5.jpg',
        35,
        12,
        'available',
        5,
        5
    ), (
        '978-1-59327-827-6',
        'Black Hat Python',
        'A book on Python programming for hackers and pentesters',
        'https://example.com/book6.jpg',
        45,
        3,
        'available',
        6,
        6
    ), (
        '978-0-201-63362-5',
        'Design Patterns',
        'A guide to object-oriented design patterns',
        'https://example.com/book7.jpg',
        20,
        20,
        'available',
        7,
        7
    ), (
        '978-1-61729-207-3',
        'Deep Learning with Python',
        'A practical guide to deep learning using Python',
        'https://example.com/book8.jpg',
        55,
        6,
        'available',
        8,
        8
    ), (
        '978-0-596-51813-4',
        'Learning Python',
        'A comprehensive guide to learning Python programming',
        'https://example.com/book9.jpg',
        30,
        10,
        'deleted',
        9,
        9
    ), (
        '978-1-119-34224-4',
        'Blockchain Basics',
        'A beginners guide to blockchain technology',
        'https://example.com/book10.jpg',
        25,
        15,
        'available',
        10,
        10
    ), (
        '978-0-596-00572-9',
        'Learning SQL',
        'A beginners guide to SQL programming',
        'https://example.com/book12.jpg',
        15,
        25,
        'available',
        12,
        12
    ), (
        '978-0-13-110362-7',
        'Refactoring',
        'Improving the design of existing code',
        'https://example.com/book13.jpg',
        30,
        10,
        'available',
        13,
        13
    ), (
        '978-0-672-33620-3',
        'PHP and MySQL Web Development',
        'A guide to building dynamic, database-driven websites',
        'https://example.com/book14.jpg',
        40,
        5,
        'available',
        14,
        14
    ), (
        '978-1-61729-453-4',
        'Hands-On Machine Learning with Scikit-Learn, Keras, and TensorFlow',
        'A practical guide to machine learning with Python',
        'https://example.com/book15.jpg',
        50,
        7,
        'available',
        15,
        15
    ), (
        '978-0-201-89527-0',
        'The Mythical Man-Month',
        'Essays on software engineering',
        'https://example.com/book16.jpg',
        20,
        20,
        'unavailable',
        16,
        16
    ), (
        '978-0-7356-3677-6',
        'Windows Internals',
        'A comprehensive guide to the internals of the Windows operating system',
        'https://example.com/book17.jpg',
        60,
        3,
        'available',
        17,
        17
    ), (
        '978-0-13-235088-4',
        'The Pragmatic Programmer',
        'From Journeyman to Master',
        'https://example.com/book18.jpg',
        35,
        8,
        'available',
        11,
        18
    );

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