package com.bookstore.database.seeders;

import com.bookstore.dao.PaymentMethodDAO;
import com.bookstore.database.factories.PaymentMethodFactory;

public class PaymentMethodSeeder implements ISeeder {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        PaymentMethodDAO.getInstance().insert(new PaymentMethodFactory().create());
      } catch (Exception e) {
      }
    }
  }

}
