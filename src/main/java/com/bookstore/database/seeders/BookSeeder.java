package com.bookstore.database.seeders;

import java.sql.SQLException;

import com.bookstore.dao.BookDAO;
import com.bookstore.database.factories.BookFactory;

public class BookSeeder implements ISeeder {
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        BookDAO.getInstance().insert(new BookFactory().create());
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
