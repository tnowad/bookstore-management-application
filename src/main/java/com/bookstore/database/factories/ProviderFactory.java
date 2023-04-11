package com.bookstore.database.factories;

import com.bookstore.models.ProviderModel;
import com.github.javafaker.Faker;

public class ProviderFactory implements IFactory<ProviderModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  @Override
  public ProviderModel create() {
    return new ProviderModel(
        getNewId(),
        faker.company().name(),
        faker.company().industry());
  }
}
