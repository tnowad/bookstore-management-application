package com.bookstore.database.factories;

import com.bookstore.model.ProviderModel;
import com.github.javafaker.Faker;

public class ProviderFactory implements IFactory<ProviderModel> {
    private static final Faker faker = new Faker();
    private static int id = 0;

    @Override
    public ProviderModel create() {
        return new ProviderModel(
                ++id,
                faker.company().name(),
                faker.company().industry());
    }
}
