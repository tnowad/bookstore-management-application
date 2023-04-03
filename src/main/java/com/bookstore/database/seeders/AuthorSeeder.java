package com.bookstore.database.seeders;

import java.sql.SQLException;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.database.factories.AuthorFactory;

public class AuthorSeeder implements ISeeder {

  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        AuthorDAO.getInstance().insert(new AuthorFactory().create());
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
