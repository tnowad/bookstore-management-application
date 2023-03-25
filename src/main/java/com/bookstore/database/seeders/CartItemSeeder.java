package com.bookstore.database.seeders;

import com.bookstore.database.factories.CartItemFactory;

public class CartItemSeeder implements ISeeder {

  @Override
  public void run() {
    new CartItemFactory().create();
  }
}
