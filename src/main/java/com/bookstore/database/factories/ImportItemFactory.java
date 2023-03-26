package com.bookstore.database.factories;

import java.sql.ResultSet;
import com.bookstore.dao.DatabaseConnection;
import com.bookstore.dao.ImportDAO;
import com.github.javafaker.Faker;

public class ImportItemFactory implements IFactory<ImportDAO> {

  @Override
  public ImportDAO create() {

    Faker faker = new Faker();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM books")) {
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
        DatabaseConnection.executeUpdate(sql);
        count++;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
