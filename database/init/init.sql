CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE
    publishers (
        publisher_id INT NOT NULL AUTO_INCREMENT,
        publisher_name VARCHAR(255) NOT NULL,
        publisher_description VARCHAR(255) NOT NULL,
        PRIMARY KEY (publisher_id)
    );

CREATE TABLE
    authors (
        author_id INT NOT NULL AUTO_INCREMENT,
        author_name VARCHAR(255) NOT NULL,
        author_description VARCHAR(255) NOT NULL,
        PRIMARY KEY (author_id)
    );

CREATE TABLE
    books (
        book_id INT NOT NULL AUTO_INCREMENT,
        book_name VARCHAR(255) NOT NULL,
        book_description VARCHAR(255) NOT NULL,
        book_price DECIMAL(10, 2) NOT NULL,
        book_image VARCHAR(255) NOT NULL,
        book_isbn VARCHAR(255) NOT NULL,
        book_pages INT NOT NULL,
        book_year INT NOT NULL,
        publisher_id INT NOT NULL,
        author_id INT NOT NULL,
        PRIMARY KEY (book_id),
        FOREIGN KEY (publisher_id) REFERENCES publishers(publisher_id),
        FOREIGN KEY (author_id) REFERENCES authors(author_id)
    );

CREATE TABLE
    genre (
        genre_id INT NOT NULL AUTO_INCREMENT,
        genre_name VARCHAR(255) NOT NULL,
        PRIMARY KEY (genre_id)
    );

CREATE TABLE
    book_genre (
        book_id INT NOT NULL,
        genre_id INT NOT NULL,
        PRIMARY KEY (book_id, genre_id),
        FOREIGN KEY (book_id) REFERENCES books(book_id),
        FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
    );

INSERT INTO
    publishers (
        publisher_name,
        publisher_description
    )
VALUES (
        'Random House',
        'A major American publisher founded in 1927.'
    ), (
        'HarperCollins',
        'One of the world''s largest publishing companies.'
    ), (
        'Penguin Random House',
        'A multinational publishing company formed in 2013.'
    );

INSERT INTO
    authors (
        author_name,
        author_description
    )
VALUES (
        'J.K. Rowling',
        'British author best known for the Harry Potter series.'
    ), (
        'Stephen King',
        'American author of horror and suspense novels.'
    ), (
        'Dan Brown',
        'American author of thriller novels.'
    );

INSERT INTO
    books (
        book_name,
        book_description,
        book_price,
        book_image,
        book_isbn,
        book_pages,
        book_year,
        publisher_id,
        author_id
    )
VALUES (
        'Harry Potter and the Philosopher''s Stone',
        'The first book in the Harry Potter series.',
        10.99,
        'https://example.com/harry-potter-1.jpg',
        '9780590353403',
        223,
        1997,
        1,
        1
    ), (
        'The Shining',
        'A horror novel about a family who becomes trapped in a haunted hotel.',
        12.99,
        'https://example.com/the-shining.jpg',
        '9780385121675',
        447,
        1977,
        2,
        2
    ), (
        'The Da Vinci Code',
        'A thriller novel about a symbologist who investigates a murder at the Louvre museum.',
        8.99,
        'https://example.com/the-da-vinci-code.jpg',
        '9780385504201',
        454,
        2003,
        3,
        3
    );

INSERT INTO genre (genre_name)
VALUES ('Fantasy'), ('Horror'), ('Thriller');

INSERT INTO
    book_genre (book_id, genre_id)
VALUES (1, 1), (2, 2), (3, 3), (1, 3);

SELECT * FROM publishers;

SELECT * FROM authors;

SELECT
    books.book_name,
    books.book_description,
    books.book_price,
    books.book_image,
    books.book_isbn,
    books.book_pages,
    books.book_year,
    publishers.publisher_name,
    authors.author_name
FROM books
    INNER JOIN publishers ON books.publisher_id = publishers.publisher_id
    INNER JOIN authors ON books.author_id = authors.author_id;

SELECT * FROM genre;

SELECT
    books.book_name,
    genre.genre_name
FROM books
    INNER JOIN book_genre ON books.book_id = book_genre.book_id
    INNER JOIN genre ON book_genre.genre_id = genre.genre_id;