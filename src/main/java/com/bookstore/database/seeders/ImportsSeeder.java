package com.bookstore.database.seeders;

import com.bookstore.dao.ImportDAO;
import com.bookstore.database.factories.ImportsFactory;

public class ImportsSeeder implements ISeeder {
  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        ImportDAO.getInstance().insert(new ImportsFactory().create());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
