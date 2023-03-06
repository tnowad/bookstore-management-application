CREATE DATABASE bookstore;

USE bookstore;

-- Accounts table
CREATE TABLE accounts (
  id VARCHAR(15) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  status ENUM ('active', 'inactive') NOT NULL DEFAULT 'active',
  PRIMARY KEY (id)
);

-- Users table
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  account_id VARCHAR(15) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  role ENUM ('admin', 'customer', 'employee') NOT NULL DEFAULT 'customer',
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES accounts (id),
);

-- Customers table
CREATE TABLE customers (
  id INT NOT NULL AUTO_INCREMENT,
  account_id VARCHAR(15) NOT NULL,
  invoice_id VARCHAR(15) NOT NULL,
  payment_id VARCHAR(15) NOT NULL,
  purchase_history DATE,
  address VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES accounts (id)
);

-- Employees table
CREATE TABLE employees (
  id INT NOT NULL AUTO_INCREMENT,
  account_id VARCHAR(15) NOT NULL,
  work_schedule DATE,
  salary DOUBLE,
  employee_type VARCHAR(255),
  contact_information VARCHAR(15),
  invoice_id VARCHAR(15),
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES user (id) FOREIGN KEY (invoice_id) REFERENCES invoice (id),
  FOREIGN KEY (good_notes_receipt_id) REFERENCES good_notes_receipt (id)
);

-- Publishers table
CREATE TABLE publishers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  isbn INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (isbn) REFERENCES books (id)
);

-- Authors table
CREATE TABLE authors (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  nationality VARCHAR(255) NOT NULL,
  isbn INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (isbn) REFERENCES books (id)
);

-- Providers table
CREATE TABLE providers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Books table
CREATE TABLE books (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price FLOAT NOT NULL,
  available_quantity INT NOT NULL,
  original_quantity INT NOT NULL,
  status ENUM ('available', 'unavailable') NOT NULL DEFAULT 'available',
  publisher_id INT NOT NULL,
  author_id INT NOT NULL,
  PRIMARY KEY (id),
);

-- Promotions table
CREATE TABLE promotions (
  id VARCHAR(15) NOT NULL,
  description VARCHAR(255) NOT NULL,
  amount INT NOT NULL,
  promotion_type VARCHAR(255) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  invoice_id VARCHAR(15) NOT NULL,
  discount_amount FLOAT NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (invoice_id) REFERENCES invoices (id)
);

-- Orders table
CREATE TABLE orders (
  id VARCHAR(15) NOT NULL,
  total_price INT NOT NULL,
  shipping_infomation VARCHAR(255) NOT NULL,
  account_id VARCHAR(15) NOT NULL,
  invoice_id VARCHAR(15) NOT NULL,
  isbn INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES customers (account_id),
  FOREIGN KEY (invoice_id) REFERENCES invoices (invoice_id),
  FOREIGN KEY (isbn) REFERENCES books (id)
);

-- OrderDetails table
CREATE TABLE order_details (
  id VARCHAR(15) NOT NULL,
  order_id VARCHAR(15) NOT NULL,
  book_id VARCHAR(15) NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (book_id) REFERENCES books (id),
);

-- Invoice table
CREATE TABLE invoices (
  id VARCHAR(15) NOT NULL,
  order_id VARCHAR(15) NOT NULL,
  customer_id VARCHAR(15) NOT NULL,
  employee_id VARCHAR(15) NOT NULL,
  promotion_id VARCHAR(15) NOT NULL,
  payment_id VARCHAR(15) NOT NULL,
  status ENUM ('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
  total_price INT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (payment_id) REFERENCES payments (id),
  FOREIGN KEY (employee_id) REFERENCES employees (id),
  FOREIGN KEY (promotion_id) REFERENCES promotions (id),
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (customer_id) REFERENCES customers (id),
);

-- Payments table
CREATE TABLE payments (
  id VARCHAR(15) NOT NULL,
  order_id VARCHAR(15) NOT NULL,
  customer_id VARCHAR(15) NOT NULL,
  total_price VARCHAR(15) NOT NULL,
  payment_method ENUM ('cash', 'credit_card', 'debit_card') NOT NULL,
  payment_method_id VARCHAR(15) NULL,
  status ENUM ('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (customer_id) REFERENCES customers (id),
  FOREIGN KEY (payment_method_id) REFERENCES payment_methods (id)
);

-- PaymentMethods table
CREATE TABLE payment_methods (
  id VARCHAR(15) NOT NULL,
  payment_id VARCHAR(15) NOT NULL,
  card_number VARCHAR(255) NOT NULL,
  card_holder VARCHAR(255) NOT NULL,
  expiration_date DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (payment_id) REFERENCES payments (id),
);

-- Imports table
CREATE TABLE imports (
  id VARCHAR(15) NOT NULL,
  provider_id VARCHAR(15) NOT NULL,
  employee_id VARCHAR(15) NOT NULL,
  total_price VARCHAR(15) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (provider_id) REFERENCES providers (id),
  FOREIGN KEY (employee_id) REFERENCES employees (id),
);

-- ImportDetails table
CREATE TABLE import_details (
  id VARCHAR(15) NOT NULL AUTO_INCREMENT,
  import_id VARCHAR(15) NOT NULL,
  book_id VARCHAR(15) NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (import_id) REFERENCES imports (id),
  FOREIGN KEY (book_id) REFERENCES books (id),
);