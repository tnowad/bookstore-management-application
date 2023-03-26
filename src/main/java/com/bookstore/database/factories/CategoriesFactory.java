package com.bookstore.database.factories;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.bookstore.dao.DatabaseConnection;
import com.github.javafaker.Faker;

public class CategoriesFactory implements IFactory {
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/bookstore";

  static final String USER = "root";
  static final String PASS = "admin123";

  @Override
  public Object create() {
    Faker faker = new Faker();

    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM books")) {
      Class.forName(JDBC_DRIVER);
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
      Statement stmt = conn.createStatement();
      int count = 0;
      while (rs.next()
          || count < 10) {
        int categoryId = faker.number().numberBetween(1, 10);
        String bookIsbn = rs.getString("isbn");
        String sql = "INSERT INTO `categories_books` (`categories_id`, `books_isbn`) VALUES (" + categoryId
            + ", '"
            + bookIsbn + "')";
        stmt.executeUpdate(sql);
        count++;
      }
    } catch (Exception e) {
    }
    return null;
  }

}
