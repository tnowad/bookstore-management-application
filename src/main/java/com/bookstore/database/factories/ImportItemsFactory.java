package com.bookstore.database.factories;

import com.bookstore.models.ImportItemsModel;
import com.github.javafaker.Faker;

public class ImportItemsFactory {
  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  public ImportItemsModel create() {
    return new ImportItemsModel(
        getNewId(),
        faker.number().numberBetween(1, 100),
        faker.code().isbn10(),
        faker.number().randomDouble(2, 1, 100));
  }
}
