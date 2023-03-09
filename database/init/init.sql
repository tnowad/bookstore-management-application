CREATE DATABASE bookstore;

USE bookstore;
CREATE TABLE `accounts` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) UNIQUE NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `status` ENUM ('active', 'inactive') NOT NULL DEFAULT "active"
);

CREATE TABLE `users` (
  `account_id` INT PRIMARY KEY NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255),
  `phone` VARCHAR(255),
  `created_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  `role` ENUM ('admin', 'customer', 'employee') NOT NULL DEFAULT "customer"
);

CREATE TABLE `customers` (
  `account_id` int PRIMARY KEY NOT NULL,
  `address` VARCHAR(255)
);

CREATE TABLE `employees` (
  `account_id` INT PRIMARY KEY NOT NULL,
  `salary` DOUBLE,
  `employee_type` ENUM ('employee_cashier', 'employee_manager', 'employee_stock', 'employee_delivery', 'employee_order', 'employee_accountant', 'employee_marketing', 'employee_hr', 'employee_security', 'employee_receptionist', 'employee_cleaner', 'employee_sales'),
  `contact_information` VARCHAR(15)
);

CREATE TABLE `publishers` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL
);

CREATE TABLE `authors` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL
);

CREATE TABLE `providers` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL
);

CREATE TABLE `books` (
  `isbn` VARCHAR(255) PRIMARY KEY NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `price` FLOAT NOT NULL,
  `available_quantity` INT NOT NULL,
  `original_quantity` INT NOT NULL,
  `status` ENUM ('available', 'unavailable') NOT NULL DEFAULT "available",
  `publisher_id` INT,
  `author_id` INT
);

CREATE TABLE `imports` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `provider_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `total_price` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `import_details` (
  `import_id` INT NOT NULL,
  `book_isbn` VARCHAR(255) NOT NULL,
  `quantity` INT NOT NULL,
  `price` INT NOT NULL
);

CREATE TABLE `promotions` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `quantity` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `condition_apply` VARCHAR(255),
  `discount_percent` FLOAT DEFAULT 0,
  `discount_amount` FLOAT DEFAULT 0
);

CREATE TABLE `orders` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `total_price` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `invoice_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  `status` ENUM ('pending', 'approved', 'rejected') NOT NULL DEFAULT "pending"
);

CREATE TABLE `order_details` (
  `order_id` INT NOT NULL,
  `book_isbn` VARCHAR(255) NOT NULL,
  `book_price` INT NOT NULL,
  `quantity` INT NOT NULL
);

CREATE TABLE `payment_methods` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `payment_id` VARCHAR(15) NOT NULL,
  `card_number` VARCHAR(255) NOT NULL,
  `card_holder` VARCHAR(255) NOT NULL,
  `expiration_date` DATE NOT NULL
);

CREATE TABLE `payments` (
  `id` INT PRIMARY KEY NOT NULL,
  `order_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `total_price` INT NOT NULL,
  `payment_method` ENUM ('cash', 'credit_card', 'debit_card') NOT NULL,
  `payment_method_id` INT,
  `status` ENUM ('pending', 'approved', 'rejected') NOT NULL DEFAULT "pending",
  `created_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `invoices` (
  `id` INT PRIMARY KEY NOT NULL,
  `order_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `promotion_id` INT NOT NULL,
  `payment_id` INT,
  `total_price` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
  `status` ENUM ('pending', 'approved', 'rejected') NOT NULL DEFAULT "pending"
);

ALTER TABLE `users` ADD FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

ALTER TABLE `customers` ADD FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

ALTER TABLE `employees` ADD FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

ALTER TABLE `books` ADD FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`);

ALTER TABLE `books` ADD FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`);

ALTER TABLE `imports` ADD FOREIGN KEY (`provider_id`) REFERENCES `providers` (`id`);

ALTER TABLE `imports` ADD FOREIGN KEY (`employee_id`) REFERENCES `employees` (`account_id`);

ALTER TABLE `import_details` ADD FOREIGN KEY (`import_id`) REFERENCES `imports` (`id`);

ALTER TABLE `import_details` ADD FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `orders` ADD FOREIGN KEY (`customer_id`) REFERENCES `customers` (`account_id`);

ALTER TABLE `order_details` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `order_details` ADD FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `payments` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `payments` ADD FOREIGN KEY (`customer_id`) REFERENCES `customers` (`account_id`);

ALTER TABLE `payments` ADD FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`);

ALTER TABLE `invoices` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `invoices` ADD FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`);

ALTER TABLE `invoices` ADD FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`);

ALTER TABLE `invoices` ADD FOREIGN KEY (`customer_id`) REFERENCES `customers` (`account_id`);

ALTER TABLE `invoices` ADD FOREIGN KEY (`employee_id`) REFERENCES `employees` (`account_id`);
