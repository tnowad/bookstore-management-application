package com.bookstore.database.seeders;

import com.bookstore.database.factories.ImportItemFactory;

public class ImportDetailSeeder implements ISeeder {

  @Override
  public void run() {
    new ImportItemFactory().create();
  }
}
