package com.bookstore.database.factories;

import com.bookstore.model.AddressModel;
import com.github.javafaker.Faker;

public class AddressFactory implements IFactory<AddressModel> {
  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  @Override
  public AddressModel create() {

    return new AddressModel(
        getNewId(),
        faker.number().numberBetween(1, 100),
        faker.address().streetAddress(),
        faker.address().city(),
        faker.address().state(),
        faker.address().zipCode());
  }

}
