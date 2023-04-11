package com.bookstore.database.factories;

import com.bookstore.models.BookModel;
import com.github.javafaker.Faker;

public class BookFactory implements IFactory<BookModel> {
  private static final Faker faker = new Faker();

  public BookModel create() {
    return new BookModel(
        faker.regexify("[0-9]{13}"),
        faker.book().title(),
        faker.lorem().paragraph(),
        faker.internet().image(),
        faker.number().numberBetween(1000, 100000),
        faker.number().numberBetween(0, 100),
        BookModel.Status.values()[faker.number().numberBetween(0, 3)],
        faker.number().numberBetween(1, 100),
        faker.number().numberBetween(1, 100));
  }
}
