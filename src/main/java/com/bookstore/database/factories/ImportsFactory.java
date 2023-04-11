package com.bookstore.database.factories;

import java.sql.Timestamp;

import com.bookstore.models.ImportModel;
import com.github.javafaker.Faker;

public class ImportsFactory implements IFactory<ImportModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  public ImportModel create() {
    return new ImportModel(
        getNewId(),
        faker.number().numberBetween(1, 100),
        faker.number().numberBetween(1, 100),
        Double.valueOf(faker.number().randomDouble(0, 20000, 500000)),
        Timestamp.from(faker.date().birthday().toInstant()),
        Timestamp.from(faker.date().birthday().toInstant()));
  }
}
