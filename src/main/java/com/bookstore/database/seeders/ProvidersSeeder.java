package com.bookstore.database.seeders;

import com.bookstore.dao.ProviderDAO;
import com.bookstore.database.factories.ProviderFactory;

public class ProvidersSeeder implements ISeeder {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                ProviderDAO.getInstance().insert(new ProviderFactory().create());
            } catch (Exception e) {
            }
        }
    }
}
