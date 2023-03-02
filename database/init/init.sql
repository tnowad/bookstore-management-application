CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE publishers (
  publisher_id INT NOT NULL AUTO_INCREMENT,
  publisher_name VARCHAR(255) NOT NULL,
  publisher_description VARCHAR(255) NOT NULL,
  PRIMARY KEY (publisher_id)
);

CREATE TABLE authors (
  author_id INT NOT NULL AUTO_INCREMENT,
  author_name VARCHAR(255) NOT NULL,
  author_description VARCHAR(255) NOT NULL,
  PRIMARY KEY (author_id)
);

CREATE TABLE producers (
  producer_id INT NOT NULL AUTO_INCREMENT,
  producer_name VARCHAR(255) NOT NULL,
  producer_description VARCHAR(255) NOT NULL,
  PRIMARY KEY (producer_id)
);

CREATE TABLE books (
  book_name VARCHAR(255) NOT NULL,
  book_description VARCHAR(255) NOT NULL,
  book_price DECIMAL(10, 2) NOT NULL,
  book_image VARCHAR(255) NOT NULL,
  book_isbn VARCHAR(255) NOT NULL,
  book_pages INT NOT NULL,
  book_year INT NOT NULL,
  publisher_id INT,
  author_id INT,
  PRIMARY KEY (book_isbn),
  FOREIGN KEY (publisher_id) REFERENCES publishers(publisher_id),
  FOREIGN KEY (author_id) REFERENCES authors(author_id)
);

CREATE TABLE products (
  product_id INT NOT NULL,
  product_name VARCHAR(255) NOT NULL,
  product_price DECIMAL(10, 2) NOT NULL,
  product_image VARCHAR(255) NOT NULL,
  product_year INT NOT NULL,
  producer_id INT NOT NULL,
  PRIMARY KEY (product_id),
  Foreign Key (producer_id) REFERENCES producers(producer_id)
);

CREATE TABLE genre (
  genre_id INT NOT NULL AUTO_INCREMENT,
  genre_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (genre_id)
);

CREATE TABLE book_genre (
  book_id INT NOT NULL,
  genre_id INT NOT NULL,
  PRIMARY KEY (book_id, genre_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id),
  FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);

CREATE TABLE accounts (
  account_id INT NOT NULL AUTO_INCREMENT,
  account_username VARCHAR(255) NOT NULL,
  account_password VARCHAR(255) NOT NULL,
  account_type ENUM(
    'customer',
    'employee',
    'administrator'
  ),
  PRIMARY KEY (account_id),
);

CREATE TABLE employees (
  employee_id INT NOT NULL,
  employee_type ENUM(
    'employee_sales',
    'employee_inventory',
    'employee_',
    'manager'
  ),
  employee_salary INT NOT NULL,
  Foreign Key (account_id) REFERENCES accounts(account_id)
);