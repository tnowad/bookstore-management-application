package com.bookstore.database.seeders;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.database.factories.CategoryFactory;

public class CategorySeeder implements ISeeder {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                CategoryDAO.getInstance().insert(new CategoryFactory().create());
            } catch (Exception e) {
            }
        }
    }
}
