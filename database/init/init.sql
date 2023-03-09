CREATE DATABASE bookstore;

USE bookstore;

/**
 * Table: books
 * Columns:
 * - isbn: The International Standard Book Number (ISBN) is a unique numeric commercial book identifier.
 * - title: The title of the book.
 * - description: The description of the book.
 * - price: The price of the book.
 * - quantity: The quantity of the book.
 * - status: The status of the book.
 * - publisher_id: The id of the publisher.
 * - author_id: The id of the author.
 * Primary key: isbn
 * Foreign keys:
 * - publisher_id references publishers(id)
 * - author_id references authors(id)
 */
CREATE TABLE
  `books` (
    `isbn` VARCHAR(20) NOT NULL,
    `title` NVARCHAR (255) NOT NULL,
    `description` NVARCHAR (255) NOT NULL,
    `price` INT NOT NULL,
    `quantity` INT NOT NULL,
    `status` ENUM ('available', 'unavailable', 'deleted') NOT NULL DEFAULT "available",
    `publisher_id` INT,
    `author_id` INT,
    PRIMARY KEY (`isbn`)
  );

/**
 * Table: categories
 * Columns:
 * - id: The id of the category.
 * - name: The name of the category.
 * Primary key: id
 */
CREATE TABLE
  `categories` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` NVARCHAR (100) NOT NULL,
    PRIMARY KEY (`id`)
  );

/**
 * Table: categories_books
 * Columns:
 * - categories_id: The id of the category.
 * - books_isbn: The isbn of the book.
 * Primary key: categories_id, books_isbn
 * Foreign keys:
 * - categories_id references categories(id)
 * - books_isbn references books(isbn)
 */
CREATE TABLE
  `categories_books` (
    `categories_id` INT,
    `books_isbn` VARCHAR(20),
    PRIMARY KEY (`categories_id`, `books_isbn`)
  );

/**
 * Table: publishers
 * Columns:
 * - id: The id of the publisher.
 * - name: The name of the publisher.
 * - description: The description of the publisher.
 * Primary key: id
 */
CREATE TABLE
  `publishers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` NVARCHAR (100) NOT NULL,
    `description` NVARCHAR (255) NOT NULL,
    PRIMARY KEY (`id`)
  );

/**
 * Table: authors
 * Columns:
 * - id: The id of the author.
 * - name: The name of the author.
 * - description: The description of the author.
 * Primary key: id
 */
CREATE TABLE
  `authors` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` NVARCHAR (100) NOT NULL,
    `description` NVARCHAR (255) NOT NULL,
    PRIMARY KEY (`id`)
  );

/**
 * Table: imports
 * Columns:
 * - id: The id of the import.
 * - provider_id: The id of the provider.
 * - employee_id: The id of the employee.
 * - total_price: The total price of the import.
 * - created_at: The time when the import is created.
 * - updated_at: The time when the import is updated.
 * Primary key: id
 * Foreign keys:
 * - provider_id references providers(id)
 * - employee_id references employees(user_id)
 */
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

/**
 * Table: import_items
 * Columns:
 * - import_id: The id of the import.
 * - book_isbn: The isbn of the book.
 * - quantity: The quantity of the book.
 * - price: The price of the book.
 * Primary key: import_id, book_isbn
 * Foreign keys:
 * - import_id references imports(id)
 * - book_isbn references books(isbn)
 */
CREATE TABLE
  `import_items` (
    `import_id` INT NOT NULL,
    `book_isbn` VARCHAR(20) NOT NULL,
    `quantity` INT NOT NULL,
    `price` INT NOT NULL
  );

/**
 * Table: providers
 * Columns:
 * - id: The id of the provider.
 * - name: The name of the provider.
 * - description: The description of the provider.
 * Primary key: id
 */
CREATE TABLE
  `providers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` NVARCHAR (100) NOT NULL,
    `description` NVARCHAR (255) NOT NULL,
    PRIMARY KEY (`id`)
  );

/**
 * Table: users
 * Columns:
 * - id: The id of the user.
 * - username: The username of the user.
 * - password: The password of the user.
 * - status: The status of the user.
 * - name: The name of the user.
 * - email: The email of the user.
 * - phone: The phone of the user.
 * - created_at: The time when the user is created.
 * - updated_at: The time when the user is updated.
 * - role: The role of the user.
 * Primary key: id
 */
CREATE TABLE
  `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `status` ENUM ('active', 'banned') NOT NULL DEFAULT "active",
    `name` NVARCHAR (100) NOT NULL,
    `email` NVARCHAR (255),
    `phone` VARCHAR(15),
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `role` ENUM ('customer', 'employee', 'manager') NOT NULL DEFAULT "customer",
    PRIMARY KEY (`id`)
  );

/**
 * Table: addresses
 * Columns:
 * - id: The id of the address.
 * - user_id: The id of the user.
 * - street: The street of the address.
 * - city: The city of the address.
 * - state: The state of the address.
 * - zip: The zip of the address.
 * Primary key: id
 * Foreign keys:
 * - user_id references users(id)
 */
CREATE TABLE
  `addresses` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `street` NVARCHAR (255) NOT NULL,
    `city` NVARCHAR (255) NOT NULL,
    `state` NVARCHAR (255) NOT NULL,
    `zip` NVARCHAR (255) NOT NULL,
    PRIMARY KEY (`id`)
  );

/**
 * Table: employees
 * Columns:
 * - user_id: The id of the user.
 * - salary: The salary of the employee.
 * - employee_type: The type of the employee.
 * - contact_information: The contact information of the employee.
 * Primary key: user_id
 * Foreign keys:
 * - user_id references users(id)
 */
CREATE TABLE
  `employees` (
    `user_id` INT NOT NULL,
    `salary` DOUBLE,
    `employee_type` ENUM ('employee') NOT NULL,
    `contact_information` NVARCHAR (255),
    PRIMARY KEY (`user_id`)
  );

/**
 * Table: carts
 * Columns:
 * - id: The id of the cart.
 * - user_id: The id of the user.
 * - created_at: The time when the cart is created.
 * - status: The status of the cart.
 * - expires: The time when the cart expires.
 * - promotion_id: The id of the promotion.
 * Primary key: id
 * Foreign keys:
 * - user_id references users(id)
 * - promotion_id references promotions(id)
 */
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

/**
 * Table: cart_items
 * Columns:
 * - cart_id: The id of the cart.
 * - book_isbn: The isbn of the book.
 * - price: The price of the book.
 * - quantity: The quantity of the book.
 * - discount: The discount of the book.
 * Primary key: cart_id, book_isbn
 * Foreign keys:
 * - cart_id references carts(id)
 * - book_isbn references books(isbn)
 */
CREATE TABLE
  `cart_items` (
    `cart_id` INT NOT NULL,
    `book_isbn` NVARCHAR (255) NOT NULL,
    `price` INT NOT NULL,
    `quantity` INT NOT NULL,
    `discount` INT NOT NULL
  );

/**
 * Table: promotions
 * Columns:
 * - id: The id of the promotion.
 * - description: The description of the promotion.
 * - quantity: The quantity of the promotion.
 * - start_date: The start date of the promotion.
 * - end_date: The end date of the promotion.
 * - condition_apply: The condition apply of the promotion.
 * - discount_percent: The discount percent of the promotion.
 * - discount_amount: The discount amount of the promotion.
 * Primary key: id
 */
CREATE TABLE
  `promotions` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` NVARCHAR (255) NOT NULL,
    `quantity` INT NOT NULL,
    `start_date` DATE NOT NULL,
    `end_date` DATE NOT NULL,
    `condition_apply` NVARCHAR (255),
    `discount_percent` DOUBLE (8, 2),
    `discount_amount` DOUBLE (8, 2),
    PRIMARY KEY (`id`)
  );

/**
 * Table: orders
 * Columns:
 * - id: The id of the order.
 * - cart_id: The id of the cart.
 * - customer_id: The id of the customer.
 * - employee_id: The id of the employee.
 * - total: The total of the order.
 * - paid: The paid of the order.
 * - created_at: The time when the order is created.
 * - updated_at: The time when the order is updated.
 * - status: The status of the order.
 * Primary key: id
 * Foreign keys:
 * - cart_id references carts(id)
 * - customer_id references users(id)
 * - employee_id references users(id)
 */
CREATE TABLE
  `orders` (
    `id` INT NOT NULL AUTO_INCREMENT,
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

/**
 * Table: order_items
 * Columns:
 * - order_id: The id of the order.
 * - book_isbn: The isbn of the book.
 * - price: The price of the book.
 * - quantity: The quantity of the book.
 * - discount: The discount of the book.
 * Primary key: order_id, book_isbn
 * Foreign keys:
 * - order_id references orders(id)
 * - book_isbn references books(isbn)
 */
CREATE TABLE
  `shipping` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `order_id` int NOT NULL,
    `shipping_method` NVARCHAR (255),
    `address_id` int NOT NULL,
    `status` ENUM ('pending') NOT NULL DEFAULT "pending",
    PRIMARY KEY (`id`)
  );

/**
 * Table: order_items
 * Columns:
 * - order_id: The id of the order.
 * - book_isbn: The isbn of the book.
 * - price: The price of the book.
 * - quantity: The quantity of the book.
 * - discount: The discount of the book.
 * Primary key: order_id, book_isbn
 * Foreign keys:
 * - order_id references orders(id)
 * - book_isbn references books(isbn)
 */
CREATE TABLE
  `payments` (
    `id` INT NOT NULL AUTO_INCREMENT,
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

/**
 * Table: order_items
 * Columns:
 * - order_id: The id of the order.
 * - book_isbn: The isbn of the book.
 * - price: The price of the book.
 * - quantity: The quantity of the book.
 * - discount: The discount of the book.
 * Primary key: order_id, book_isbn
 * Foreign keys:
 * - order_id references orders(id)
 * - book_isbn references books(isbn)
 */
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

ALTER TABLE `imports` ADD FOREIGN KEY (`employee_id`) REFERENCES `employees` (`user_id`);

ALTER TABLE `imports` ADD FOREIGN KEY (`provider_id`) REFERENCES `providers` (`id`);

ALTER TABLE `import_items` ADD FOREIGN KEY (`import_id`) REFERENCES `imports` (`id`);

ALTER TABLE `import_items` ADD FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `books` ADD FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`);

ALTER TABLE `books` ADD FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`);

ALTER TABLE `categories_books` ADD FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`);

ALTER TABLE `categories_books` ADD FOREIGN KEY (`books_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `employees` (`user_id`);

ALTER TABLE `addresses` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `carts` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `carts` ADD FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`);

ALTER TABLE `cart_items` ADD FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

ALTER TABLE `cart_items` ADD FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`);

ALTER TABLE `payments` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `payments` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `payments` ADD FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`);

ALTER TABLE `payment_methods` ADD FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`employee_id`) REFERENCES `employees` (`user_id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

ALTER TABLE `shipping` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `shipping` ADD FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`);