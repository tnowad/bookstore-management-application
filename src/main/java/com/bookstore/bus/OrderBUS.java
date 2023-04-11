package com.bookstore.bus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.OrderDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.OrderModel;
import com.bookstore.models.OrderModel.Status;

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
    to.setCart_id(from.getCart_id());
    to.setEmployee_id(from.getCustomer_id());
    to.setEmployee_id(from.getEmployee_id());
    to.setTotal(from.getTotal());
    to.setPaid(from.getPaid());
    to.setCreated_at(from.getCreated_at());
    to.setUpdated_at(from.getUpdated_at());
    to.setStatus(from.getStatus());
  }

  private boolean checkFilter(OrderModel orderModel, String value, String[] columns) {
    for (String column : columns) {
      if (checkColumn(orderModel, column, value)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkColumn(OrderModel orderModel, String column, String value) {
    switch (column.toLowerCase()) {
      case "id" -> {
        return orderModel.getId() == Integer.parseInt(value);
      }
      case "cart_id" -> {
        return orderModel.getCart_id() == Integer.parseInt(value);
      }
      case "customer_id" -> {
        return orderModel.getCustomer_id() == Integer.parseInt(value);
      }
      case "employee_id" -> {
        return orderModel.getEmployee_id() == Integer.parseInt(value);
      }
      case "total" -> {
        return orderModel.getTotal() == Integer.parseInt(value);
      }
      case "paid" -> {
        return orderModel.getPaid() == Integer.parseInt(value);
      }
      case "created_at" -> {
        return orderModel.getCreated_at().toString().toLowerCase().contains(value.toLowerCase());
      }
      case "updated_at" -> {
        return orderModel.getUpdated_at().toString().toLowerCase().contains(value.toLowerCase());
      }
      case "status" -> {
        return orderModel.getStatus().toString().toLowerCase().contains(value.toLowerCase());
      }
      default -> {
        return checkAllColumns(orderModel, value);
      }
    }
  }

  private boolean checkAllColumns(OrderModel orderModel, String value) {
    return orderModel.getId() == Integer.parseInt(value)
        || orderModel.getCart_id() == Integer.parseInt(value)
        || orderModel.getCustomer_id() == Integer.parseInt(value)
        || orderModel.getEmployee_id() == Integer.parseInt(value)
        || orderModel.getTotal() == Integer.parseInt(value)
        || orderModel.getPaid() == Integer.parseInt(value)
        || orderModel.getCreated_at().toString().toLowerCase().contains(value.toLowerCase())
        || orderModel.getUpdated_at().toString().toLowerCase().contains(value.toLowerCase())
        || orderModel.getStatus().toString().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int addModel(OrderModel orderModel) {
    if (orderModel.getCart_id() <= 0) {
      throw new IllegalArgumentException("Cart ID must be greater than 0!");
    }
    if (orderModel.getCustomer_id() <= 0) {
      throw new IllegalArgumentException("Customer ID must be greater than 0!");
    }
    if (orderModel.getEmployee_id() <= 0) {
      throw new IllegalArgumentException("Employee ID must be greater than 0!");
    }
    if (orderModel.getTotal() <= 0) {
      throw new IllegalArgumentException("Total must be greater than 0!");
    }
    if (orderModel.getPaid() < 0) {
      throw new IllegalArgumentException("Paid cannot be negative!");
    }
    orderModel.setStatus(orderModel.getStatus() != null ? orderModel.getStatus() : Status.PENDING);

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
      throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
    }
    orderModel.setPaid(paid);
    orderModel.setUpdated_at(new Timestamp(System.currentTimeMillis()));
    int updatedRows = updateModel(orderModel);
    if (updatedRows > 0) {
      return orderModel.getTotal() - orderModel.getPaid();
    }
    return -1;
  }

  public int updateStatus(int cartId, Status status) {
    int success = OrderDAO.getInstance().updateStatus(cartId, status);
    if (success == 1) {
      for (OrderModel order : orderList) {
        if (order.getCart_id() == cartId) {
          order.setStatus(status);
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
      throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
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

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No order found with the specified search criteria.");
    }

    return results;
  }
}
