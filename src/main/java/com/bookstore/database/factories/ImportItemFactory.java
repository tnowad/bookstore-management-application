package com.bookstore.database.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.bookstore.dao.DatabaseConnect;
import com.github.javafaker.Faker;

public class ImportItemFactory implements IFactory {
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/bookstore";

  static final String USER = "root";
  static final String PASS = "admin123";

  @Override
  public Object create() {

    Faker faker = new Faker();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM books")) {
      Class.forName(JDBC_DRIVER);
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
      Statement stmt = conn.createStatement();
      int count = 0;
      while (rs.next()
          || count < 10) {
        int importId = faker.number().numberBetween(1, 10);
        String bookIsbn = rs.getString("isbn");
        String quantity = rs.getString("quantity");
        String price = rs.getString("price");
        String sql = "INSERT INTO `import_items` (`import_id`, `book_isbn`, `quantity`, `price`) VALUES (" + importId
            + ", '"
            + bookIsbn + "', " + quantity + ", " + price + ")";
        stmt.executeUpdate(sql);
        count++;
      }
    } catch (Exception e) {
    }
    return null;
  }
}
