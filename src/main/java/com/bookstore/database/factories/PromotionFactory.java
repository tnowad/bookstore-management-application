package com.bookstore.database.factories;

import java.util.concurrent.TimeUnit;

import com.bookstore.models.PromotionModel;
import com.github.javafaker.Faker;

public class PromotionFactory implements IFactory<PromotionModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  public PromotionModel create() {
    return new PromotionModel(
        getNewId(),
        faker.commerce().productName(),
        faker.number().numberBetween(1, 100),
        faker.date().future(10, TimeUnit.DAYS),
        faker.date().future(30, TimeUnit.DAYS),
        faker.lorem().sentence(),
        faker.number().numberBetween(1, 100),
        faker.number().numberBetween(5000, 50000));
  }
}
