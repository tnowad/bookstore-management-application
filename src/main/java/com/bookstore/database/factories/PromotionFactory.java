package com.bookstore.database.factories;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.bookstore.model.PromotionModel;
import com.github.javafaker.Faker;

public class PromotionFactory implements IFactory<PromotionModel> {
    private static final Faker faker = new Faker();
    private static int id = 0;

    public PromotionModel create() {
        return new PromotionModel(
                ++id,
                faker.commerce().productName(),
                faker.number().numberBetween(1, 100),
                // faker.date().between(new Date(), Date.from(Instant.now().plus(1,
                // ChronoUnit.DAYS))),
                // faker.date().between(
                // faker.date().between(new Date(), Date.from(Instant.now().plus(1,
                // ChronoUnit.DAYS))),
                // Date.from(Instant.now().plus(1, ChronoUnit.WEEKS))),
                faker.date().future(10, TimeUnit.DAYS),
                faker.date().future(30, TimeUnit.DAYS),
                faker.lorem().sentence(),
                faker.number().numberBetween(1, 100),
                faker.number().numberBetween(5000, 50000));
    }
}
