package com.bookstore.database.seeders;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.database.factories.EmployeeFactory;

public class EmployeeSeeder implements ISeeder {
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                EmployeeDAO.getInstance().insert(new EmployeeFactory().create());
            } catch (Exception e) {
            }
        }
    }
}
