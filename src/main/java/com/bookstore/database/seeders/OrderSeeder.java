package com.bookstore.database.seeders;

import com.bookstore.dao.OrderDAO;
import com.bookstore.database.factories.OrderFactory;

public class OrderSeeder implements ISeeder {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        OrderDAO.getInstance().insert(new OrderFactory().create());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
