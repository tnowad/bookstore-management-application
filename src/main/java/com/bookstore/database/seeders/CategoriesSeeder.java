package com.bookstore.database.seeders;

import com.bookstore.database.factories.CategoriesFactory;

public class CategoriesSeeder implements ISeeder {

  @Override
  public void run() {
    new CategoriesFactory().create();
  }

}
