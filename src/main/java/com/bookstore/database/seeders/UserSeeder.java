package com.bookstore.database.seeders;

import com.bookstore.dao.UserDAO;
import com.bookstore.database.factories.UserFactory;

public class UserSeeder implements ISeeder {
  public void run() {
    for (int i = 0; i < 100; i++) {
      UserDAO.getInstance().insert(new UserFactory().create());
    }
  }
}
