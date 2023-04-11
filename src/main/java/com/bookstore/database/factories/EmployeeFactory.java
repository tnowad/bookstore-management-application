package com.bookstore.database.factories;

import com.bookstore.models.EmployeeModel;
import com.github.javafaker.Faker;

public class EmployeeFactory implements IFactory<EmployeeModel> {

  private static final Faker faker = new Faker();

  @Override
  public EmployeeModel create() {
    return new EmployeeModel(
        faker.number().numberBetween(1, 50),
        faker.number().randomDigit(),
        EmployeeModel.EmployeeType.values()[faker.number().numberBetween(0, 3)],
        faker.internet().emailAddress());
  }
}
