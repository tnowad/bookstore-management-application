package com.bookstore.database.factories;

import java.sql.Timestamp;

import com.bookstore.model.PaymentModel;
import com.github.javafaker.Faker;

public class PaymentFactory implements IFactory<PaymentModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  public PaymentModel create() {
    return new PaymentModel(
        getNewId(),
        faker.number().numberBetween(1, 100),
        faker.number().numberBetween(1, 100),
        faker.number().numberBetween(1, 100),
        PaymentModel.PaymentMethod.values()[faker.number().numberBetween(0, 1)],
        faker.number().numberBetween(1, 100),
        PaymentModel.PaymentStatus.values()[faker.number().numberBetween(0, 2)],
        Timestamp.from(faker.date().birthday().toInstant()),
        Timestamp.from(faker.date().birthday().toInstant()));

  }
}
