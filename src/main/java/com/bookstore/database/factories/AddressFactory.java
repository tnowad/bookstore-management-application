package com.bookstore.database.factories;

import com.bookstore.model.AddressModel;
import com.github.javafaker.Faker;

public class AddressFactory implements IFactory<AddressModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  @Override
  public AddressModel create() {
    id++;
    return new AddressModel(
        id,
        faker.number().numberBetween(1, 100),
        faker.address().streetAddress(),
        faker.address().city(),
        faker.address().state(),
        faker.address().zipCode());
  }

}
