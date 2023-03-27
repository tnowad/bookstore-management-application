package com.bookstore.database.factories;

import java.sql.ResultSet;
import com.bookstore.dao.DatabaseConnection;
import com.bookstore.model.CategoryModel;
import com.github.javafaker.Faker;

public class CategoriesFactory implements IFactory<CategoryModel> {
  @Override
  public CategoryModel create() {
    Faker faker = new Faker();

    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM books")) {
      int count = 0;
      while (rs.next()
          || count < 10) {
        int categoryId = faker.number().numberBetween(1, 10);
        String bookIsbn = rs.getString("isbn");
        String sql = "INSERT INTO `categories_books` (`categories_id`, `books_isbn`) VALUES (" + categoryId
            + ", '"
            + bookIsbn + "')";
        DatabaseConnection.executeUpdate(sql);
        count++;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
