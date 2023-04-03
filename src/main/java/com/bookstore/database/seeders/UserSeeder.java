package com.bookstore.database.seeders;

import java.sql.SQLException;

import com.bookstore.dao.UserDAO;
import com.bookstore.database.factories.UserFactory;

public class UserSeeder implements ISeeder {
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        UserDAO.getInstance().insert(new UserFactory().create());
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
