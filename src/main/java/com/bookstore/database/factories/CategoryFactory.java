package com.bookstore.database.factories;

import com.bookstore.model.CategoryModel;
import com.github.javafaker.Faker;

public class CategoryFactory implements IFactory<CategoryModel> {

    private static final Faker faker = new Faker();
    private static int id = 0;

    @Override
    public CategoryModel create() {
        return new CategoryModel(
                ++id,
                faker.commerce().productName());
    }
}
