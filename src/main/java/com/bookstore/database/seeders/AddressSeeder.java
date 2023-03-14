package com.bookstore.database.seeders;

import com.bookstore.dao.AddressDAO;
import com.bookstore.database.factories.AddressFactory;

public class AddressSeeder implements ISeeder {

  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        AddressDAO.getInstance().insert(new AddressFactory().create());
      } catch (Exception e) {
      }
    }
  }
}
