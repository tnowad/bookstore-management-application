package com.bookstore.database.factories;

import com.bookstore.models.CategoryModel;
import com.github.javafaker.Faker;

public class CategoryFactory implements IFactory<CategoryModel> {

  private static final Faker faker = new Faker();
  private static int id = 0;

  private static int getNewId() {
    return ++id;
  }

  @Override
  public CategoryModel create() {
    return new CategoryModel(
        getNewId(),
        faker.commerce().productName());
  }
}
