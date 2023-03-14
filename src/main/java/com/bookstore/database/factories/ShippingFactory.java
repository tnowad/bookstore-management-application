package com.bookstore.database.factories;

import com.bookstore.model.ShippingModel;
import com.github.javafaker.Faker;

public class ShippingFactory implements IFactory<ShippingModel> {
    private static final Faker faker = new Faker();
    private static int id = 0;

    public ShippingModel create() {
        return new ShippingModel(
                ++id,
                faker.number().numberBetween(1, 100),
                faker.lorem().word(),
                faker.number().randomDigit(),
                ShippingModel.Status.values()[faker.number().numberBetween(0, 2)]);
    }
}
