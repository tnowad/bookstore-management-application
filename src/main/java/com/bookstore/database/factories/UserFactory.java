package com.bookstore.database.factories;

import java.sql.Timestamp;

import com.bookstore.model.UserModel;
import com.github.javafaker.Faker;

public class UserFactory {
  private static final Faker faker = new Faker();
  private static int id = 0;

  public UserModel create() {
    return new UserModel(
        ++id,
        faker.name().username(),
        faker.internet().password(),
        UserModel.Status.values()[faker.number().numberBetween(0, 3)],
        faker.name().fullName(),
        faker.internet().emailAddress(),
        faker.phoneNumber().phoneNumber(),
        Timestamp.from(faker.date().birthday().toInstant()),
        Timestamp.from(faker.date().birthday().toInstant()),
        UserModel.Role.values()[faker.number().numberBetween(0, 3)]);
  }
}
