package com.bookstore.database.factories;

import com.bookstore.model.OrderModel;
import com.github.javafaker.Faker;
import java.sql.Timestamp;

public class OrderFactory implements IFactory<OrderModel> {
    private static final Faker faker = new Faker();
    private static int id = 0;

    public OrderModel create() {
        return new OrderModel(
                ++id,
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
