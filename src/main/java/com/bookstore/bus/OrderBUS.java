package com.bookstore.bus;

import com.bookstore.dao.OrderDAO;
import com.bookstore.enums.OrderStatus;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.OrderModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderBUS implements IBUS<OrderModel> {

  private final List<OrderModel> orderList = new ArrayList<>();
  private static OrderBUS instance;

  public static OrderBUS getInstance() {
    if (instance == null) {
      instance = new OrderBUS();
    }
    return instance;
  }

  private OrderBUS() {
    this.orderList.addAll(OrderDAO.getInstance().readDatabase());
  }

  @Override
  public List<OrderModel> getAllModels() {
    return Collections.unmodifiableList(orderList);
  }

  @Override
  public OrderModel getModelById(int id) {
    for (OrderModel orderModel : orderList) {
      if (orderModel.getId() == id) {
        return orderModel;
      }
    }
    return null;
  }

  private OrderModel mapToEntity(OrderModel from) {
    OrderModel to = new OrderModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(OrderModel from, OrderModel to) {
    to.setId(from.getId());
    to.setCartId(from.getCartId());
    to.setCustomerId(from.getCustomerId());
    to.setEmployeeId(from.getEmployeeId());
    to.setTotal(from.getTotal());
    to.setPaid(from.getPaid());
    to.setCreatedAt(from.getCreatedAt());
    to.setUpdatedAt(from.getUpdatedAt());
    to.setStatus(from.getStatus());
  }

  private boolean checkFilter(
    OrderModel orderModel,
    String value,
    String[] columns
  ) {
    for (String column : columns) {
      if (checkColumn(orderModel, column, value)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkColumn(
    OrderModel orderModel,
    String column,
    String value
  ) {
    switch (column.toLowerCase()) {
      case "id" -> {
        return orderModel.getId() == Integer.parseInt(value);
      }
      case "cart_id" -> {
        return orderModel.getCartId() == Integer.parseInt(value);
      }
      case "customer_id" -> {
        return orderModel.getCustomerId() == Integer.parseInt(value);
      }
      case "employee_id" -> {
        return orderModel.getEmployeeId() == Integer.parseInt(value);
      }
      case "total" -> {
        return orderModel.getTotal() == Integer.parseInt(value);
      }
      case "paid" -> {
        return orderModel.getPaid() == Integer.parseInt(value);
      }
      case "created_at" -> {
        return orderModel
          .getCreatedAt()
          .toString()
          .toLowerCase()
          .contains(value.toLowerCase());
      }
      case "updated_at" -> {
        return orderModel
          .getUpdatedAt()
          .toString()
          .toLowerCase()
          .contains(value.toLowerCase());
      }
      case "status" -> {
        return orderModel
          .getStatus()
          .toString()
          .toLowerCase()
          .contains(value.toLowerCase());
      }
      default -> {
        return checkAllColumns(orderModel, value);
      }
    }
  }

  private boolean checkAllColumns(OrderModel orderModel, String value) {
    return (
      orderModel.getId() == Integer.parseInt(value) ||
      orderModel.getCartId() == Integer.parseInt(value) ||
      orderModel.getCustomerId() == Integer.parseInt(value) ||
      orderModel.getEmployeeId() == Integer.parseInt(value) ||
      orderModel.getTotal() == Integer.parseInt(value) ||
      orderModel.getPaid() == Integer.parseInt(value) ||
      orderModel
        .getCreatedAt()
        .toString()
        .toLowerCase()
        .contains(value.toLowerCase()) ||
      orderModel
        .getUpdatedAt()
        .toString()
        .toLowerCase()
        .contains(value.toLowerCase()) ||
      orderModel
        .getStatus()
        .toString()
        .toLowerCase()
        .contains(value.toLowerCase())
    );
  }

  @Override
  public int addModel(OrderModel orderModel) {
    if (orderModel.getCartId() <= 0) {
      throw new IllegalArgumentException("Cart ID must be greater than 0!");
    }
    if (orderModel.getCustomerId() <= 0) {
      throw new IllegalArgumentException("Customer ID must be greater than 0!");
    }
    if (orderModel.getEmployeeId() <= 0) {
      throw new IllegalArgumentException("Employee ID must be greater than 0!");
    }
    if (orderModel.getTotal() <= 0) {
      throw new IllegalArgumentException("Total must be greater than 0!");
    }
    if (orderModel.getPaid() < 0) {
      throw new IllegalArgumentException("Paid cannot be negative!");
    }
    orderModel.setStatus(
      orderModel.getStatus() != null
        ? orderModel.getStatus()
        : OrderStatus.PENDING
    );

    int id = OrderDAO.getInstance().insert(mapToEntity(orderModel));
    orderModel.setId(id);
    orderList.add(orderModel);
    return id;
  }

  @Override
  public int updateModel(OrderModel orderModel) {
    int updatedRows = OrderDAO.getInstance().update(orderModel);
    if (updatedRows > 0) {
      for (int i = 0; i < orderList.size(); i++) {
        if (orderList.get(i).getId() == orderModel.getId()) {
          orderList.set(i, orderModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  public int updatePaid(int id, int paid) {
    OrderModel orderModel = getModelById(id);
    if (orderModel == null) {
      throw new IllegalArgumentException(
        "Order with ID " + id + " does not exist."
      );
    }
    orderModel.setPaid(paid);
    int updatedRows = updateModel(orderModel);
    if (updatedRows > 0) {
      return orderModel.getTotal() - orderModel.getPaid();
    }
    return -1;
  }

  public int updateStatus(int cartId, String status) {
    int success = OrderDAO.getInstance().updateStatus(cartId, status);
    if (success == 1) {
      for (OrderModel order : orderList) {
        if (order.getCartId() == cartId) {
          OrderStatus roleEnum = OrderStatus.valueOf(status.toUpperCase());
          order.setStatus(roleEnum);
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) {
    OrderModel orderModel = getModelById(id);
    if (orderModel == null) {
      throw new IllegalArgumentException(
        "Order with ID " + id + " does not exist."
      );
    }
    int deletedRows = OrderDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      orderList.remove(orderModel);
    }
    return deletedRows;
  }

  @Override
  public List<OrderModel> searchModel(String value, String[] columns) {
    List<OrderModel> results = new ArrayList<>();
    List<OrderModel> entities = OrderDAO.getInstance().search(value, columns);
    for (OrderModel entity : entities) {
      OrderModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  @Override
  public void refreshData() {
    throw new UnsupportedOperationException(
      "Unimplemented method 'refreshData'"
    );
  }

  public int calculateTotalRevenue() {
    return 0;
  }
}
