package com.bookstore.database.factories;

import java.util.concurrent.*;

import com.bookstore.model.PaymentMethodModel;
import com.github.javafaker.Faker;

public class PaymentMethodFactory implements IFactory<PaymentMethodModel> {

  private static final Faker faker = new Faker();
  private static int id = 0;

  @Override
  public PaymentMethodModel create() {
    return new PaymentMethodModel(
        ++id,
        faker.finance().iban(),
        faker.finance().creditCard(),
        faker.name().fullName(),
        faker.date().future(365, TimeUnit.DAYS),
        faker.number().randomDigitNotZero());
  }
}