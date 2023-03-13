package com.bookstore.database;

import java.sql.*;
import java.sql.Timestamp;

public class CreateTable {
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/bookstore";

  static final String USER = "root";
  static final String PASS = "admin123";

  public CreateTable() {
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName(JDBC_DRIVER);

      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      System.out.println("Creating table in given database...");
      stmt = conn.createStatement();

      String sql = "CREATE TABLE IF NOT EXISTS `books` (" +
          "isbn VARCHAR(20) NOT NULL, " +
          "title NVARCHAR(255) NOT NULL, " +
          "description NVARCHAR(255) NOT NULL, " +
          "image VARCHAR(255) NOT NULL, " +
          "price INT NOT NULL, " +
          "quantity INT NOT NULL, " +
          "status ENUM('available', 'unavailable', 'deleted') NOT NULL DEFAULT 'available', " +
          "publisher_id INT, " +
          "author_id INT, " +
          "PRIMARY KEY (isbn))";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `categories` (" +
          "id INT NOT NULL AUTO_INCREMENT, " +
          "name NVARCHAR(100) NOT NULL, " +
          "PRIMARY KEY (id))";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `categories_books` (" +
          "categories_id INT, " +
          "books_isbn VARCHAR(20), " +
          "PRIMARY KEY (categories_id, books_isbn), " +
          "FOREIGN KEY (categories_id) REFERENCES categories(id), " +
          "FOREIGN KEY (books_isbn) REFERENCES books(isbn))";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `publishers` (" +
          "id INT NOT NULL AUTO_INCREMENT, " +
          "name NVARCHAR(100) NOT NULL, " +
          "description NVARCHAR(255) NOT NULL, " +
          "PRIMARY KEY (id))";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `authors` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`name` NVARCHAR (100) NOT NULL,"
          + "`description` NVARCHAR (255) NOT NULL,"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `imports` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`provider_id` INT NULL,"
          + "`employee_id` INT NOT NULL,"
          + "`total_price` DECIMAL(11, 0) NOT NULL,"
          + "`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `import_items` ("
          + "`import_id` INT NOT NULL,"
          + "`book_isbn` VARCHAR(20) NOT NULL,"
          + "`quantity` INT NOT NULL,"
          + "`price` INT NOT NULL"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `providers` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`name` NVARCHAR (100) NOT NULL,"
          + "`description` NVARCHAR (255) NOT NULL,"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `users` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`username` VARCHAR(100) NOT NULL,"
          + "`password` VARCHAR(100) NOT NULL,"
          + " `status` ENUM ('active', 'inactive', 'banned') NOT NULL DEFAULT 'active',"
          + " `name` NVARCHAR (100) NOT NULL,"
          + " `email` VARCHAR(255),"
          + "`phone` VARCHAR(15),"
          + "`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "`role` ENUM ('customer', 'employee', 'admin') NOT NULL DEFAULT 'customer',"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `addresses` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`user_id` INT NOT NULL,"
          + "`street` NVARCHAR (255) NOT NULL,"
          + "`city` NVARCHAR (255) NOT NULL,"
          + "`state` NVARCHAR (255) NOT NULL,"
          + "`zip` NVARCHAR (20) NOT NULL,"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `employees` ("
          + "`user_id` INT NOT NULL,"
          + "`salary` INT,"
          + "`employee_type` ENUM ('employee_manager','employee_sales','employee_inventory','employee_order') NOT NULL DEFAULT 'employee_sales',"
          + "`contact_information` NVARCHAR (255),"
          + "PRIMARY KEY (`user_id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `promotions` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`description` NVARCHAR (255) NOT NULL,"
          + "`quantity` INT NOT NULL,"
          + "`start_date` DATE NOT NULL,"
          + "`end_date` DATE NOT NULL,"
          + "`condition_apply` NVARCHAR (255),"
          + "`discount_percent` INT,"
          + "`discount_amount` INT,"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `carts` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`user_id` INT NOT NULL,"
          + "`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "`status` ENUM ('shopping','pending','reject','accept') NOT NULL DEFAULT 'shopping',"
          + "`expires` TIMESTAMP NOT NULL ,"
          + "`promotion_id` INT NOT NULL,"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `cart_items` ("
          + "`cart_id` INT NOT NULL,"
          + "`book_isbn` VARCHAR(20) NOT NULL,"
          + "`price` INT NOT NULL,"
          + "`quantity` INT NOT NULL,"
          + "`discount` INT NOT NULL"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `orders` ("
          + "`id` INT NOT NULL AUTO_INCREMENT," +
          "`cart_id` INT NOT NULL,"
          + "`customer_id` INT NOT NULL,"
          + "`employee_id` INT NOT NULL,"
          + "`total` INT NOT NULL,"
          + "`paid` INT NOT NULL,"
          + "`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "`status` ENUM ('pending') NOT NULL DEFAULT 'pending',"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `shipping` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`order_id` int NOT NULL,"
          + "`shipping_method` NVARCHAR (255),"
          + "`address_id` int NOT NULL,"
          + "`status` ENUM ('pending') NOT NULL DEFAULT 'pending',"
          + "    PRIMARY KEY (`id`)"
          + "  );";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `payments` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`order_id` INT NOT NULL,"
          + "`user_id` INT NOT NULL,"
          + "`amount` INT NOT NULL,"
          + "`payment_method` ENUM ('cash') NOT NULL,"
          + "`payment_method_id` INT,"
          + "`status` ENUM ('pending') NOT NULL DEFAULT 'pending',"
          + "`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS `payment_methods` ("
          + "`id` INT NOT NULL AUTO_INCREMENT,"
          + "`payment_id` NVARCHAR (255) NOT NULL,"
          + "`card_number` NVARCHAR (255) NOT NULL,"
          + "`card_holder` NVARCHAR (255) NOT NULL,"
          + "`expiration_date` DATE NOT NULL,"
          + "`customer_id` INT NOT NULL,"
          + "PRIMARY KEY (`id`)"
          + ");";
      stmt.executeUpdate(sql);

      System.out.println("Table created successfully");
    } catch (SQLException se) {
      se.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          conn.close();
        }
      } catch (SQLException se) {
      }
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    System.out.println("Success");
  }

  public static void main(String[] args) {
    new CreateTable();
  }
}
