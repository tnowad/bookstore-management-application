package com.bookstore.database.seeders;

import com.bookstore.dao.ImportItemsDAO;
import com.bookstore.database.factories.ImportItemsFactory;

public class ImportItemsSeeder implements ISeeder {
  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      ImportItemsDAO.getInstance().insert(new ImportItemsFactory().create());
    }
  }
}
