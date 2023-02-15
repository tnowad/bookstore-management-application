/* Create database name bookstore and create table books, put in it 3 record */

CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE books (id INT, name VARCHAR(20));

INSERT INTO books VALUES (1, 'book1');

INSERT INTO books VALUES (2, 'book2');