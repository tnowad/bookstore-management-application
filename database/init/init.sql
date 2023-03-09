CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE
  `categories` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `promotions` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255) NOT NULL,
    `quantity` INT NOT NULL,
    `start_date` DATE NOT NULL,
    `end_date` DATE NOT NULL,
    `condition_apply` VARCHAR(255),
    `discount_percent` DOUBLE (8, 2),
    `discount_amount` DOUBLE (8, 2),
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `books` (
    `isbn` VARCHAR(255) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `price` DOUBLE (8, 2) NOT NULL,
    `available_quantity` INT NOT NULL,
    `original_quantity` INT NOT NULL,
    `status` ENUM ('available') NOT NULL DEFAULT "available",
    `publisher_id` INT,
    `author_id` INT,
    PRIMARY KEY (`isbn`)
  );

CREATE TABLE
  `import_items` (
    `import_id` INT NOT NULL,
    `book_isbn` VARCHAR(255) NOT NULL,
    `quantity` INT NOT NULL,
    `price` INT NOT NULL
  );

CREATE TABLE
  `publishers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `payment_methods` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `payment_id` VARCHAR(255) NOT NULL,
    `card_number` VARCHAR(255) NOT NULL,
    `card_holder` VARCHAR(255) NOT NULL,
    `expiration_date` DATE NOT NULL,
    `customer_id` INT NOT NULL,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `addresses` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `street` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `state` VARCHAR(255) NOT NULL,
    `zip` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `authors` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `cart_items` (
    `cart_id` INT NOT NULL,
    `book_isbn` VARCHAR(255) NOT NULL,
    `price` INT NOT NULL,
    `quantity` INT NOT NULL,
    `discount` INT NOT NULL
  );

CREATE TABLE
  `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `status` ENUM ('active') NOT NULL DEFAULT "active",
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255),
    `phone` VARCHAR(255),
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `role` ENUM ('customer') NOT NULL DEFAULT "customer",
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `imports` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `provider_id` INT NOT NULL,
    `employee_id` INT NOT NULL,
    `total_price` INT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `providers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `carts` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` ENUM ('pending') NOT NULL DEFAULT "pending",
    `expires` TIMESTAMP NOT NULL,
    `promotion_id` int,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `employees` (
    `user_id` INT NOT NULL,
    `salary` DOUBLE,
    `employee_type` ENUM ('employee'),
    `contact_information` VARCHAR(255),
    PRIMARY KEY (`user_id`)
  );

CREATE TABLE
  `payments` (
    `id` INT NOT NULL,
    `order_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `amount` INT NOT NULL,
    `payment_method` ENUM ('cash') NOT NULL,
    `payment_method_id` INT,
    `status` ENUM ('pending') NOT NULL DEFAULT "pending",
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `orders` (
    `id` INT NOT NULL,
    `cart_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    `employee_id` INT NOT NULL,
    `total` INT NOT NULL,
    `paid` INT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` ENUM ('pending') NOT NULL DEFAULT "pending",
    PRIMARY KEY (`id`)
  );

CREATE TABLE
  `shipping` (
    `id` INT NOT NULL,
    `order_id` int NOT NULL,
    `shipping_method` VARCHAR(255),
    `address_id` int NOT NULL,
    `status` ENUM ('pending') NOT NULL DEFAULT "pending",
    PRIMARY KEY (`id`)
  );

ALTER TABLE `imports` ADD FOREIGN KEY (`provider_id`) REFERENCES `providers` (`id`);

ALTER TABLE `books` ADD FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`);

ALTER TABLE `import_items` ADD FOREIGN KEY (`import_id`) REFERENCES `imports` (`id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `employees` (`user_id`);

ALTER TABLE `cart_items` ADD FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `addresses` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `cart_items` ADD FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

ALTER TABLE `import_items` ADD FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `payments` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `carts` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `imports` ADD FOREIGN KEY (`employee_id`) REFERENCES `employees` (`user_id`);

ALTER TABLE `books` ADD FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`);

CREATE TABLE
  `categories_books` (
    `categories_id` INT,
    `books_isbn` VARCHAR(255),
    PRIMARY KEY (`categories_id`, `books_isbn`)
  );

ALTER TABLE `categories_books` ADD FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`);

ALTER TABLE `categories_books` ADD FOREIGN KEY (`books_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `payments` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`employee_id`) REFERENCES `employees` (`user_id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

ALTER TABLE `shipping` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `shipping` ADD FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`);

ALTER TABLE `payments` ADD FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`);

ALTER TABLE `payment_methods` ADD FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`);

ALTER TABLE `carts` ADD FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`);