CREATE DATABASE bookstore;

USE bookstore;

-- Accounts table
CREATE TABLE accounts (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  status ENUM ('active', 'inactive') NOT NULL DEFAULT 'active',
  PRIMARY KEY (id)
);

-- Users table
CREATE TABLE users (
  account_id INT NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NULL,
  phone VARCHAR(255) NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  role ENUM ('admin', 'customer', 'employee') NOT NULL DEFAULT 'customer',
  PRIMARY KEY (account_id),
  FOREIGN KEY (account_id) REFERENCES accounts (id)
);

-- Customers table
CREATE TABLE customers (
  account_id int NOT NULL,
  address VARCHAR(255) NOT NULL,
  PRIMARY KEY (account_id),
  FOREIGN KEY (account_id) REFERENCES accounts (id)
);

-- Employees table
CREATE TABLE employees (
  account_id INT NOT NULL,
  work_schedule DATE,
  salary DOUBLE,
  employee_type VARCHAR(255),
  contact_information VARCHAR(15),
  PRIMARY KEY (account_id),
  FOREIGN KEY (account_id) REFERENCES accounts (id)
);

-- Publishers table
CREATE TABLE publishers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (isbn) REFERENCES books (isbn)
);

-- Authors table
CREATE TABLE authors (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
);

-- Providers table
CREATE TABLE providers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Books table
CREATE TABLE books (
  isbn VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price FLOAT NOT NULL,
  available_quantity INT NOT NULL,
  original_quantity INT NOT NULL,
  status ENUM ('available', 'unavailable') NOT NULL DEFAULT 'available',
  publisher_id INT NULL,
  author_id INT NULL,
  PRIMARY KEY (isbn),
  FOREIGN KEY (publisher_id) REFERENCES publishers (id),
  FOREIGN KEY (author_id) REFERENCES authors (id)
);


-- Promotions table
CREATE TABLE promotions (
  int id INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(255) NOT NULL,
  quantity INT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  condition VARCHAR(255) NOT NULL,
  discount_percent FLOAT NULL DEFAULT 0,
  discount_amount FLOAT NULL DEFAULT 0,
  PRIMARY KEY (id),
);

/*
  Explaination:
  - A promotion has a discount percent and max discount amount
  - A promotion can be applied to a book or a category of books
  - A promotion can be applied to a customer or a group of customers
  Example:
  - A promotion can be applied to all books with a discount percent of 50% and max discount amount of 100,000 VND
  Caculate:
  - If a book has a price of 500,000 VND, the discount amount is 100,000 VND
  - If a book has a price of 200,000 VND, the discount amount is 100,000 VND
  - If a book has a price of 50,000 VND, the discount amount is 25,000 VND

  quantity: 100
  discount_percent: 50
  discount_amount: 100,000
  condition: 'book_id:1'
  => 100 books with id 1 will be discounted 50% and max discount amount is 100,000 VND

  quantity: 100
  discount_percent: 50
  discount_amount: 100,000
  condition: NULL
  => 100 books will be discounted 50% and max discount amount is 100,000 VND

*/

-- Orders table
CREATE TABLE orders (
  id INT NOT NULL AUTO_INCREMENT,
  total_price INT NOT NULL,
  customer_id INT NOT NULL,
  invoice_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES customers (account_id),
  FOREIGN KEY (invoice_id) REFERENCES invoices (invoice_id),
);

-- OrderDetails table
CREATE TABLE order_details (
  order_id INT NOT NULL,
  book_isbn VARCHAR(255) NOT NULL,
  book_price INT NOT NULL,
  quantity INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (book_isbn) REFERENCES books (isbn),
);

-- Invoice table
CREATE TABLE invoices (
  id INT NOT NULL,
  order_id INT NOT NULL,
  customer_id INT NOT NULL,
  employee_id INT NOT NULL,
  promotion_id INT NOT NULL,
  payment_id INT NULL,
  total_price INT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  status ENUM ('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (promotion_id) REFERENCES promotions (id),
  FOREIGN KEY (payment_id) REFERENCES payments (id),
  FOREIGN KEY (customer_id) REFERENCES customers (id),
  FOREIGN KEY (employee_id) REFERENCES employees (id),
);

-- Payments table
/*
  Explaination:
  - A payment can be a cash, credit card or debit card
  - When a payment is made, the payment status will be changed to 'approved'
  - If payment is cash, the payment method id will be NULL
  - If payment is credit card or debit card, the payment method id will be the id of the payment method
  - When employee approves a payment, the payment status will be changed to 'approved'
  - When employee rejects a payment, the payment status will be changed to 'rejected'
  - When a payment is rejected, the payment status will be changed to 'rejected'
  - When a payment is approved, the payment status will be changed to 'approved'
*/
*/
CREATE TABLE payments (
  id INT NOT NULL,
  order_id INT NOT NULL,
  customer_id INT NOT NULL,
  total_price INT NOT NULL,
  payment_method ENUM ('cash', 'credit_card', 'debit_card') NOT NULL,
  payment_method_id INT NULL,
  status ENUM ('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (customer_id) REFERENCES customers (id),
  FOREIGN KEY (payment_method_id) REFERENCES payment_methods (id)
);

-- PaymentMethods table
/*
  Explaination:
  - A payment method can be a credit card, debit card or cash
  - A payment method can be used for multiple payments
  - If a payment method is a credit card or debit card, it will have a card number, card holder and expiration date
  - If change information of a payment method, it will create a new payment method
*/
CREATE TABLE payment_methods (
  id INT NOT NULL AUTO_INCREMENT,
  payment_id VARCHAR(15) NOT NULL,
  card_number VARCHAR(255) NOT NULL,
  card_holder VARCHAR(255) NOT NULL,
  expiration_date DATE NOT NULL,
  PRIMARY KEY (id),
);

-- Imports table
CREATE TABLE imports (
  id INT NOT NULL AUTO_INCREMENT,
  provider_id INT NOT NULL,
  employee_id INT NOT NULL,
  total_price INT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (provider_id) REFERENCES providers (id),
  FOREIGN KEY (employee_id) REFERENCES employees (id),
);

-- ImportDetails table
CREATE TABLE import_details (
  import_id VARCHAR(15) NOT NULL,
  book_isbn VARCHAR(255) NOT NULL,
  quantity INT NOT NULL,
  price INT NOT NULL,
  FOREIGN KEY (import_id) REFERENCES imports (id),
  FOREIGN KEY (book_isbn) REFERENCES books (isbn),
);