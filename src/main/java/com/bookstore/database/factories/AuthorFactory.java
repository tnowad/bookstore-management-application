package com.bookstore.database.factories;

import com.bookstore.model.AuthorModel;
import com.github.javafaker.Faker;

public class AuthorFactory implements IFactory<AuthorModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  public AuthorModel create() {
    return new AuthorModel(
        ++id,
        faker.name().fullName(),
        faker.lorem().sentence(10));
  }
}
