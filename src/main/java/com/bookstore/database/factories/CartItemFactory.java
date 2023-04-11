package com.bookstore.database.factories;

import java.sql.ResultSet;

import com.bookstore.dao.DatabaseConnection;
import com.bookstore.models.CartModel;
import com.github.javafaker.Faker;

public class CartItemFactory implements IFactory<CartModel> {
  @Override
  public CartModel create() {
    Faker faker = new Faker();

    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM books")) {
      int count = 0;
      while (rs.next()
          || count < 10) {
        int cartId = faker.number().numberBetween(1, 10);
        String bookIsbn = rs.getString("isbn");
        String quantity = rs.getString("quantity");
        String price = rs.getString("price");
        int discount = faker.number().numberBetween(0, 50);
        String sql = "INSERT INTO `cart_items` (`cart_id`, `book_isbn`, `quantity`, `price`, `discount`) VALUES ("
            + cartId
            + ", '"
            + bookIsbn + "', " + quantity + ", " + price + ", " + discount + ")";
        DatabaseConnection.executeUpdate(sql);
        count++;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
