CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE
  accounts (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status ENUM ('active', 'inactive') NOT NULL DEFAULT 'active',
    PRIMARY KEY (id)
  );

CREATE TABLE
  users (
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

CREATE TABLE
  customers (
    account_id int NOT NULL,
    address VARCHAR(255) NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
  );

CREATE TABLE
  employees (
    account_id INT NOT NULL,
    salary DOUBLE,
    employee_type enum (
      'employee_cashier',
      'employee_manager',
      'employee_stock',
      'employee_delivery',
      'employee_order',
      'employee_accountant',
      'employee_marketing',
      'employee_hr',
      'employee_security',
      'employee_receptionist',
      'employee_cleaner',
      'employee_sales'
    ) NULL,
    contact_information VARCHAR(15),
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
  );

CREATE TABLE
  publishers (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

CREATE TABLE
  authors (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

CREATE TABLE
  providers (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

CREATE TABLE
  books (
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

CREATE TABLE
  imports (
    id INT NOT NULL AUTO_INCREMENT,
    provider_id INT NOT NULL,
    employee_id INT NOT NULL,
    total_price INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (provider_id) REFERENCES providers (id),
    FOREIGN KEY (employee_id) REFERENCES employees (account_id)
  );

CREATE TABLE
  import_details (
    import_id INT NOT NULL,
    book_isbn VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price INT NOT NULL,
    FOREIGN KEY (import_id) REFERENCES imports (id),
    FOREIGN KEY (book_isbn) REFERENCES books (isbn)
  );

CREATE TABLE
  promotions (
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    condition_apply VARCHAR(255) NULL,
    discount_percent FLOAT NULL DEFAULT 0,
    discount_amount FLOAT NULL DEFAULT 0,
    PRIMARY KEY (id)
  );

CREATE TABLE
  orders (
    id INT NOT NULL AUTO_INCREMENT,
    total_price INT NOT NULL,
    customer_id INT NOT NULL,
    invoice_id INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status ENUM ('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers (account_id)
  );

CREATE TABLE
  order_details (
    order_id INT NOT NULL,
    book_isbn VARCHAR(255) NOT NULL,
    book_price INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (book_isbn) REFERENCES books (isbn)
  );

CREATE TABLE
  payment_methods (
    id INT NOT NULL AUTO_INCREMENT,
    payment_id VARCHAR(15) NOT NULL,
    card_number VARCHAR(255) NOT NULL,
    card_holder VARCHAR(255) NOT NULL,
    expiration_date DATE NOT NULL,
    PRIMARY KEY (id)
  );

CREATE TABLE
  payments (
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
    FOREIGN KEY (customer_id) REFERENCES customers (account_id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_methods (id)
  );

CREATE TABLE
  invoices (
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
    FOREIGN KEY (customer_id) REFERENCES customers (account_id),
    FOREIGN KEY (employee_id) REFERENCES employees (account_id)
  );