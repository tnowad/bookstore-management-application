package com.bookstore.database.factories;

import com.bookstore.model.CartModel;
import com.github.javafaker.Faker;
import java.sql.Timestamp;

public class CartFactory implements IFactory<CartModel> {

    private static final Faker faker = new Faker();
    private static int id = 0;

    public CartModel create() {
        return new CartModel(
                ++id,
                faker.number().numberBetween(1, 100),
                Timestamp.from(faker.date().birthday().toInstant()),
                CartModel.Status.values()[faker.number().numberBetween(0, 3)],
                Timestamp.from(faker.date().birthday().toInstant()),
                faker.number().numberBetween(1, 100));
    }
}
