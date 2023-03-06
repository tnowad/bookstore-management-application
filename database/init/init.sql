CREATE DATABASE bookstore;

USE bookstore;

/**
 * @table accounts
 * Create accounts table to store account information of users
 * id: id of account (int, not null, primary key, auto increment, unique)
 * username: username of account (string, not null, unique)
 * password: hashed password of account (string, not null)
 * status: status of account (enum, not null, default: active)
 */
CREATE TABLE
  accounts (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status ENUM ('active', 'inactive') NOT NULL DEFAULT 'active',
    PRIMARY KEY (id)
  );

/**
 * @table users
 * Create users table to store user information
 * account_id: id of account (int, not null, primary key, foreign key, unique)
 * first_name: first name of user (string, not null)
 * last_name: last name of user (string, not null)
 * email: email of user (string, null)
 * phone: phone number of user (string, null)
 * created_at: created time of user (timestamp, not null, default: current timestamp)
 * updated_at: updated time of user (timestamp, not null, default: current timestamp, on update: current timestamp)
 * role: role of user (enum, not null, default: customer)
 */
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

/**
 * @table customers
 * Create customers table to store customer information
 * account_id: id of account (int, not null, primary key, foreign key, unique)
 * address: address of customer (string, null)
 */
CREATE TABLE
  customers (
    account_id int NOT NULL,
    address VARCHAR(255) NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
  );

/**
 * @table employees
 * Create employees table to store employee information
 * account_id: id of account (int, not null, primary key, foreign key, unique)
 * salary: salary of employee (double, null)
 * employee_type: type of employee (enum, null)
 * contact_information: contact information of employee (string, null)
 */
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

/**
  * @table publishers
  * Create publishers table to store publisher information
  * id: id of publisher (int, not null, primary key, auto increment, unique)
  * name: name of publisher (string, not null)
  * address: address of publisher (string, not null)
  */
CREATE TABLE
  publishers (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

/**
  * @table authors
  * Create authors table to store author information
  * id: id of author (int, not null, primary key, auto increment, unique)
  * first_name: first name of author (string, not null)
  * last_name: last name of author (string, not null)
  * description: description of author (string, not null)
  */
CREATE TABLE
  authors (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
CREATE TABLE
  providers (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
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

/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
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

/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
CREATE TABLE
  import_details (
    import_id INT NOT NULL,
    book_isbn VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price INT NOT NULL,
    FOREIGN KEY (import_id) REFERENCES imports (id),
    FOREIGN KEY (book_isbn) REFERENCES books (isbn)
  );

/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
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

/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
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

/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
CREATE TABLE
  order_details (
    order_id INT NOT NULL,
    book_isbn VARCHAR(255) NOT NULL,
    book_price INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (book_isbn) REFERENCES books (isbn)
  );

-- PaymentMethods table
/*
Explaination:
- A payment method can be a credit card, debit card or cash
- A payment method can be used for multiple payments
- If a payment method is a credit card or debit card, it will have a card number, card holder and expiration date
- If change information of a payment method, it will create a new payment method
 */
/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
CREATE TABLE
  payment_methods (
    id INT NOT NULL AUTO_INCREMENT,
    payment_id VARCHAR(15) NOT NULL,
    card_number VARCHAR(255) NOT NULL,
    card_holder VARCHAR(255) NOT NULL,
    expiration_date DATE NOT NULL,
    PRIMARY KEY (id)
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
/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
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

/**
  * @table categories
  * Create categories table to store category information
  * id: id of category (int, not null, primary key, auto increment, unique)
  * name: name of category (string, not null)
  * description: description of category (string, not null)
  */
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