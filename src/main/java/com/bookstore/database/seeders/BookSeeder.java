package com.bookstore.database.seeders;

import com.bookstore.dao.BookDAO;
import com.bookstore.database.factories.BookFactory;

public class BookSeeder implements ISeeder {
  public void run() {
    for (int i = 0; i < 100; i++) {
      BookDAO.getInstance().insert(new BookFactory().create());
    }
  }
}
