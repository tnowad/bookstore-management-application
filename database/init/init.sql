DROP DATABASE IF EXISTS bookstore;

CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE
    `books` (
        `isbn` VARCHAR(20) NOT NULL,
        `title` NVARCHAR (255) NOT NULL,
        `description` NVARCHAR (255) NOT NULL,
        `image` VARCHAR(255) NOT NULL,
        `price` INT NOT NULL,
        `quantity` INT NOT NULL,
        `status` ENUM (
            'available',
            'unavailable',
            'deleted'
        ) NOT NULL DEFAULT "available",
        `publisher_id` INT,
        `author_id` INT,
        PRIMARY KEY (`isbn`)
    );

CREATE TABLE
    `categories` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` NVARCHAR (100) NOT NULL,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `categories_books` (
        `categories_id` INT,
        `books_isbn` VARCHAR(20),
        PRIMARY KEY (`categories_id`, `books_isbn`)
    );

CREATE TABLE
    `publishers` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` NVARCHAR (100) NOT NULL,
        `description` NVARCHAR (255) NOT NULL,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `authors` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` NVARCHAR (100) NOT NULL,
        `description` NVARCHAR (255) NOT NULL,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `imports` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `provider_id` INT NULL,
        `employee_id` INT NOT NULL,
        `total_price` DECIMAL(11, 0) NOT NULL,
        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `import_items` (
        `import_id` INT NOT NULL,
        `book_isbn` VARCHAR(20) NOT NULL,
        `quantity` INT NOT NULL,
        `price` INT NOT NULL
    );

CREATE TABLE
    `providers` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` NVARCHAR (100) NOT NULL,
        `description` NVARCHAR (255) NOT NULL,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `users` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `username` VARCHAR(100) NOT NULL,
        `password` VARCHAR(100) NOT NULL,
        `status` ENUM ('active', 'inactive', 'banned') NOT NULL DEFAULT "active",
        `name` NVARCHAR (100) NOT NULL,
        `email` VARCHAR(255),
        `phone` VARCHAR(50),
        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `role` ENUM (
            'customer',
            'employee',
            'admin'
        ) NOT NULL DEFAULT "customer",
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `addresses` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `user_id` INT NOT NULL,
        `street` NVARCHAR (255) NOT NULL,
        `city` NVARCHAR (255) NOT NULL,
        `state` NVARCHAR (255) NOT NULL,
        `zip` NVARCHAR (20) NOT NULL,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `current_user_id` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `user_id` INT NOT NULL,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `employees` (
        `user_id` INT NOT NULL,
        `salary` INT,
        `employee_type` ENUM (
            'employee_manager',
            'employee_sales'
        ) NOT NULL DEFAULT 'employee_sales',
        `contact_information` NVARCHAR (255),
        PRIMARY KEY (`user_id`)
    );

CREATE TABLE
    `carts` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `user_id` INT NOT NULL,
        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `status` ENUM (
            'shopping',
            'pending',
            'reject',
            'accept'
        ) NOT NULL DEFAULT "shopping",
        `expires` DATETIME NULL,
        `promotion_id` INT NULL,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `cart_items` (
        `cart_id` INT NOT NULL,
        `book_isbn` VARCHAR(20) NOT NULL,
        `price` INT NOT NULL,
        `quantity` INT NOT NULL,
        `discount` INT NOT NULL
    );

CREATE TABLE
    `promotions` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `description` NVARCHAR (255) NOT NULL,
        `quantity` INT NOT NULL,
        `start_date` DATE NOT NULL,
        `end_date` DATE NOT NULL,
        `condition_apply` NVARCHAR (255),
        `discount_percent` INT,
        `discount_amount` INT,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `orders` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `cart_id` INT NOT NULL,
        `customer_id` INT NOT NULL,
        `employee_id` INT NOT NULL,
        `total` INT NOT NULL,
        `paid` INT NOT NULL,
        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `status` ENUM ('pending', 'solved') NOT NULL DEFAULT "pending",
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `shipping` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `order_id` int NOT NULL,
        `shipping_method` NVARCHAR (255),
        `address_id` int NOT NULL,
        `status` ENUM ('pending') NOT NULL DEFAULT "pending",
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `payments` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `order_id` INT NOT NULL,
        `user_id` INT NOT NULL,
        `amount` INT NOT NULL,
        `payment_method` ENUM ('cash', 'credit') NOT NULL DEFAULT 'cash',
        `payment_method_id` INT,
        `status` ENUM ('pending', 'success', 'failed') NOT NULL DEFAULT 'pending',
        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `payment_methods` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `payment_id` NVARCHAR (255) NOT NULL,
        `card_number` NVARCHAR (255) NOT NULL,
        `card_holder` NVARCHAR (255) NOT NULL,
        `expiration_date` DATE NOT NULL,
        `customer_id` INT NOT NULL,
        PRIMARY KEY (`id`)
    );

ALTER TABLE `addresses`
ADD
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `books`
ADD
    FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`);

ALTER TABLE `books`
ADD
    FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`);

ALTER TABLE `cart_items`
ADD
    FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `cart_items`
ADD
    FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

ALTER TABLE `carts`
ADD
    FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`);

ALTER TABLE `carts`
ADD
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `categories_books`
ADD
    FOREIGN KEY (`books_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `categories_books`
ADD
    FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`);

ALTER TABLE `employees`
ADD
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `import_items`
ADD
    FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `import_items`
ADD
    FOREIGN KEY (`import_id`) REFERENCES `imports` (`id`);

ALTER TABLE `imports`
ADD
    FOREIGN KEY (`employee_id`) REFERENCES `employees` (`user_id`);

ALTER TABLE `imports`
ADD
    FOREIGN KEY (`provider_id`) REFERENCES `providers` (`id`);

ALTER TABLE `orders`
ADD
    FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

ALTER TABLE `orders`
ADD
    FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`);

ALTER TABLE `orders`
ADD
    FOREIGN KEY (`employee_id`) REFERENCES `employees` (`user_id`);

ALTER TABLE `payment_methods`
ADD
    FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`);

ALTER TABLE `payments`
ADD
    FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `payments`
ADD
    FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`);

ALTER TABLE `payments`
ADD
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `current_user_id`
ADD
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `shipping`
ADD
    FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`);

ALTER TABLE `shipping`
ADD
    FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);