package com.bookstore.database.seeders;

import java.sql.SQLException;

import com.bookstore.dao.BookDAO;
import com.bookstore.database.factories.BookFactory;

public class BookSeeder {
  public void run() {
    for (int i = 0; i < 1000; i++) {
      try {
        BookDAO.getInstance().insert(new BookFactory().create());
      } catch (ClassNotFoundException | SQLException e) {
      }
    }
  }
}
