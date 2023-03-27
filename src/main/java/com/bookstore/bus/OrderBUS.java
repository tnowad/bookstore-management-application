package com.bookstore.bus;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.OrderDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.OrderModel;
import com.bookstore.model.OrderModel.Status;

public class OrderBUS implements IBUS<OrderModel> {

  private final List<OrderModel> orderList = new ArrayList<>();
  private static OrderBUS instance;

  public static OrderBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new OrderBUS();
    }
    return instance;
  }

  private OrderBUS() throws SQLException, ClassNotFoundException {
    this.orderList.addAll(OrderDAO.getInstance().readDatabase());
  }

  @Override
  public List<OrderModel> getAllModels() {
    return Collections.unmodifiableList(orderList);
  }

  @Override
  public OrderModel getModelById(int id) throws SQLException, ClassNotFoundException {
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
    to.setCartId(from.getCartId());
    to.setEmployeeId(from.getCustomerId());
    to.setEmployeeId(from.getEmployeeId());
    to.setTotal(from.getTotal());
    to.setPaid(from.getPaid());
    to.setCreatedAt(from.getCreatedAt());
    to.setUpdatedAt(from.getUpdatedAt());
    to.setStatus(from.getStatus());
  }

  private boolean checkFilter(OrderModel orderModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (orderModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "cart_id" -> {
          if (orderModel.getCartId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "customer_id" -> {
          if (orderModel.getCustomerId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "employee_id" -> {
          if (orderModel.getEmployeeId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "total" -> {
          if (orderModel.getTotal() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "paid" -> {
          if (orderModel.getPaid() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "created_at" -> {
          if (orderModel.getCreatedAt().toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "updated_at" -> {
          if (orderModel.getUpdatedAt().toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "status" -> {
          if (orderModel.getStatus().toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(orderModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(OrderModel orderModel, String value) {
    return orderModel.getId() == Integer.parseInt(value)
        || orderModel.getCartId() == Integer.parseInt(value)
        || orderModel.getCustomerId() == Integer.parseInt(value)
        || orderModel.getEmployeeId() == Integer.parseInt(value)
        || orderModel.getTotal() == Integer.parseInt(value)
        || orderModel.getPaid() == Integer.parseInt(value)
        || orderModel.getCreatedAt().toString().toLowerCase().contains(value.toLowerCase())
        || orderModel.getUpdatedAt().toString().toLowerCase().contains(value.toLowerCase())
        || orderModel.getStatus().toString().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int addModel(OrderModel orderModel) throws SQLException, ClassNotFoundException {
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
    orderModel.setStatus(orderModel.getStatus() != null ? orderModel.getStatus() : Status.PENDING);

    int id = OrderDAO.getInstance().insert(mapToEntity(orderModel));
    orderModel.setId(id);
    orderList.add(orderModel);
    return id;
  }

  @Override
  public int updateModel(OrderModel orderModel) throws SQLException, ClassNotFoundException {
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

  public int updatePaid(int id, int paid) throws SQLException, ClassNotFoundException {
    OrderModel orderModel = getModelById(id);
    if (orderModel == null) {
      throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
    }
    orderModel.setPaid(paid);
    orderModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
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
        if (order.getCartId() == cartId) {
          order.setStatus(status);
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
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
  public List<OrderModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
    List<OrderModel> results = new ArrayList<>();
    try {
      List<OrderModel> entities = OrderDAO.getInstance().search(value, columns);
      for (OrderModel entity : entities) {
        OrderModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for order: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for order: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No order found with the specified search criteria.");
    }

    return results;
  }
}
