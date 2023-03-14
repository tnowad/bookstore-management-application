package com.bookstore.database.seeders;

import com.bookstore.dao.PublisherDAO;
import com.bookstore.database.factories.PublishersFactory;

public class PublisherSeeder implements ISeeder {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                PublisherDAO.getInstance().insert(new PublishersFactory().create());
            } catch (Exception e) {
            }
        }
    }
}
