package com.bookstore.database.seeders;

import com.bookstore.dao.ShippingDAO;
import com.bookstore.database.factories.ShippingFactory;

public class ShippingSeeder implements ISeeder {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        ShippingDAO.getInstance().insert(new ShippingFactory().create());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
