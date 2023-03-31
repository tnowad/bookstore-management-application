package com.bookstore.bus;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.model.CartItemsModel;

import java.sql.SQLException;
import java.util.List;

public class CartItemsBUSTest {

  private static CartItemsBUS cartItemsBUS;

  @BeforeAll
  public static void setUp() throws ClassNotFoundException, SQLException {
    cartItemsBUS = CartItemsBUS.getInstance();
  }

  @AfterAll
  public static void tearDown() {
    cartItemsBUS = null;
  }

  @Test
  public void testGetInstance() {
    Assertions.assertNotNull(cartItemsBUS);
  }

  @Test
  public void testUpdateModel() throws SQLException, ClassNotFoundException {
    CartItemsModel model = new CartItemsModel(79, "9780060256654", 5, 100, 1);
    model.setPrice(20);
    int updatedRows = cartItemsBUS.updateModel(model);
    Assertions.assertEquals(1, updatedRows);
    CartItemsModel updatedModel = cartItemsBUS.getModelById(79);
    Assertions.assertEquals(20, updatedModel.getPrice());
  }
}