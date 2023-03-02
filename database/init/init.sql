CREATE DATABASE bookstore;

USE bookstore;

-- Accounts table
CREATE TABLE IF NOT EXISTS accounts (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  status ENUM('active', 'inactive') NOT NULL DEFAULT 'active',
  PRIMARY KEY (id)
);

-- Users table
CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  account_id INT NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  role ENUM('admin', 'customer', 'employee') NOT NULL DEFAULT 'customer',
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES accounts(id),
);

-- Customers table
CREATE TABLE IF NOT EXISTS customers (
  id INT NOT NULL AUTO_INCREMENT,
  account_id INT NOT NULL,
  address VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES accounts(id)
);

-- Employees table
CREATE TABLE IF NOT EXISTS employees (
  id INT NOT NULL AUTO_INCREMENT,
  account_id INT NOT NULL,
  position VARCHAR(255) NOT NULL,
  salary INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES accounts(id)
);

-- Publishers table
CREATE TABLE IF NOT EXISTS publishers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Authors table
CREATE TABLE IF NOT EXISTS authors (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Providers table
CREATE TABLE IF NOT EXISTS providers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Books table
CREATE TABLE IF NOT EXISTS books (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price INT NOT NULL,
  available_quantity INT NOT NULL,
  original_quantity INT NOT NULL,
  status ENUM('available', 'unavailable') NOT NULL DEFAULT 'available',
  publisher_id INT NOT NULL,
  author_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (publisher_id) REFERENCES publishers(id),
  FOREIGN KEY (author_id) REFERENCES authors(id),
);

-- Promotions table
CREATE TABLE IF NOT EXISTS promotions (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  discount INT NOT NULL,
  condition VARCHAR(255) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  PRIMARY KEY (id)
);

-- Orders table
CREATE TABLE IF NOT EXISTS orders (
  id INT NOT NULL AUTO_INCREMENT,
  customer_id INT NOT NULL,
  total_price INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
);

-- OrderDetails table
CREATE TABLE IF NOT EXISTS order_details (
  id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  book_id INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (book_id) REFERENCES books(id),
);

-- Invoice table
CREATE TABLE IF NOT EXISTS invoices (
  id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  customer_id INT NOT NULL,
  employee_id INT NOT NULL,
  promotion_id INT NOT NULL,
  payment_id INT NOT NULL,
  status ENUM('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
  total_price INT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (payment_id) REFERENCES payments(id),
  FOREIGN KEY (employee_id) REFERENCES employees(id),
  FOREIGN KEY (promotion_id) REFERENCES promotions(id),
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
);

-- Payments table
CREATE TABLE IF NOT EXISTS payments (
  id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  customer_id INT NOT NULL,
  total_price INT NOT NULL,
  payment_method ENUM('cash', 'credit_card', 'debit_card') NOT NULL,
  payment_method_id INT NULL,
  status ENUM('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id)
);

-- PaymentMethods table
CREATE TABLE IF NOT EXISTS payment_methods (
  id INT NOT NULL AUTO_INCREMENT,
  payment_id INT NOT NULL,
  card_number VARCHAR(255) NOT NULL,
  card_holder VARCHAR(255) NOT NULL,
  cvv INT NOT NULL,
  expiration_date DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (payment_id) REFERENCES payments(id),
);

-- Imports table
CREATE TABLE IF NOT EXISTS imports (
  id INT NOT NULL AUTO_INCREMENT,
  provider_id INT NOT NULL,
  employee_id INT NOT NULL,
  total_price INT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (provider_id) REFERENCES providers(id),
  FOREIGN KEY (employee_id) REFERENCES employees(id),
);

-- ImportDetails table
CREATE TABLE IF NOT EXISTS import_details (
  id INT NOT NULL AUTO_INCREMENT,
  import_id INT NOT NULL,
  book_id INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (import_id) REFERENCES imports(id),
  FOREIGN KEY (book_id) REFERENCES books(id),
);