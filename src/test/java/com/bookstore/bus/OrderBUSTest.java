package com.bookstore.bus;

import org.junit.jupiter.api.Test;

import com.bookstore.models.AddressModel;
import com.bookstore.models.OrderModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class OrderBUSTest {

  @Test
  public void testAddModelWithInvalidCartId() {
    OrderModel orderModel = new OrderModel();
    orderModel.setCartId(0);
    orderModel.setCustomerId(1);
    orderModel.setEmployeeId(1);
    orderModel.setTotal(100);
    orderModel.setPaid(0);

    assertThrows(IllegalArgumentException.class, () -> {
      OrderBUS.getInstance().addModel(orderModel);
    });
  }

  @Test
  public void testGetAllModels() {
    List<OrderModel> orderList = OrderBUS.getInstance().getAllModels();

    assertNotNull(orderList);
    assertFalse(orderList.isEmpty());
    assertEquals(20, orderList.size());
  }

  @Test
  public void testGetModelById() {
    OrderModel orderModel = OrderBUS.getInstance().getModelById(4);
    //AddressModel addressModel = AddressBUS.getInstance().getModelById(4);
    //assertNotNull(orderModel);
    assertEquals(4, orderModel.getId());
  }

  @Test
  public void testUpdateModel() {
    int id = 1;
    OrderModel orderModel = OrderBUS.getInstance().getModelById(id);
    orderModel.setPaid(50);

    int updatedRows = OrderBUS.getInstance().updateModel(orderModel);

    assertTrue(updatedRows > 0);
    assertEquals(50, OrderBUS.getInstance().getModelById(id).getPaid());
  }

  @Test
  public void testDeleteModel() {
    int id = 1;

    int deletedRows = OrderBUS.getInstance().deleteModel(id);

    assertTrue(deletedRows > 0);
    assertNull(OrderBUS.getInstance().getModelById(id));
  }

  @Test
  public void testSearchModel() {
    String value = "pending";
    String[] columns = { "status" };

    List<OrderModel> orderList = OrderBUS.getInstance().searchModel(value, columns);

    assertNotNull(orderList);
    assertFalse(orderList.isEmpty());
  }

}
