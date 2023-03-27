package com.bookstore.database.factories;

import com.bookstore.model.PublisherModel;
import com.github.javafaker.Faker;

public class PublishersFactory implements IFactory<PublisherModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  public PublisherModel create() {
    return new PublisherModel(
        getNewId(),
        faker.company().name(),
        faker.company().catchPhrase());
  }
}
