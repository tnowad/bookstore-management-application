package com.bookstore.database.seeders;

import java.sql.SQLException;

import com.bookstore.dao.UserDAO;
import com.bookstore.database.factories.UserFactory;

public class UserSeeder {
  public void run() {
    for (int i = 0; i < 1000; i++) {
      try {
        UserDAO.getInstance().insert(new UserFactory().create());
      } catch (ClassNotFoundException | SQLException e) {
      }
    }
  }
}
