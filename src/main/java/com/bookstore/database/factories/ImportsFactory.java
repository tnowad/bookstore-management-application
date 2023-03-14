package com.bookstore.database.factories;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.bookstore.model.ImportModel;
import com.github.javafaker.Faker;

public class ImportsFactory implements IFactory<ImportModel> {
    private static final Faker faker = new Faker();
    private static int id = 0;

    public ImportModel create() {
        return new ImportModel(
                ++id,
                faker.number().numberBetween(1, 100),
                faker.number().numberBetween(1, 100),
                new BigDecimal(faker.number().randomDouble(0, 20000, 500000)),
                Timestamp.from(faker.date().birthday().toInstant()),
                Timestamp.from(faker.date().birthday().toInstant()));
    }
}
