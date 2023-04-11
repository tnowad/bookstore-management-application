package com.bookstore.database.factories;

import com.bookstore.models.OrderModel;
import com.github.javafaker.Faker;
import java.sql.Timestamp;

public class OrderFactory implements IFactory<OrderModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  public OrderModel create() {
    return new OrderModel(
        getNewId(),
        faker.number().numberBetween(1, 100),
        faker.number().numberBetween(1, 100),
        faker.number().numberBetween(1, 100),
        faker.number().randomDigit(),
        faker.number().randomDigit(),
        Timestamp.from(faker.date().birthday().toInstant()),
        Timestamp.from(faker.date().birthday().toInstant()),
        OrderModel.Status.values()[faker.number().numberBetween(0, 3)]);
  }
}
