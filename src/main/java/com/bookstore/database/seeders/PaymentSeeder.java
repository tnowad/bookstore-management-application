package com.bookstore.database.seeders;

import com.bookstore.dao.PaymentDAO;
import com.bookstore.database.factories.PaymentFactory;

public class PaymentSeeder implements ISeeder {
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                PaymentDAO.getInstance().insert(new PaymentFactory().create());
            } catch (Exception e) {
            }
        }
    }
}
