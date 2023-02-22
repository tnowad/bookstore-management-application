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

CREATE TABLE
    roles (
        role_id INT PRIMARY KEY,
        role_title VARCHAR(50)
    );

CREATE TABLE
    accounts (
        account_id INT NOT NULL AUTO_INCREMENT,
        account_name VARCHAR(255) NOT NULL,
        account_password VARCHAR(255) NOT NULL,
        account_email VARCHAR(255) NOT NULL,
        account_phone VARCHAR(255) NOT NULL,
        account_address VARCHAR(255) NOT NULL,
        role_id INT NOT NULL,
        PRIMARY KEY (account_id),
        FOREIGN KEY (role_id) REFERENCES roles(role_id)
    );