package com.bookstore.database.seeders;

import java.sql.SQLException;

import com.bookstore.dao.CartDAO;
import com.bookstore.database.factories.CartFactory;

public class CartSeeder implements ISeeder {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        CartDAO.getInstance().insert(new CartFactory().create());
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
