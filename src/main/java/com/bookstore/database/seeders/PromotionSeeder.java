package com.bookstore.database.seeders;

import com.bookstore.dao.PromotionDAO;
import com.bookstore.database.factories.PromotionFactory;

public class PromotionSeeder implements ISeeder {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        PromotionDAO.getInstance().insert(new PromotionFactory().create());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
