package com.bookstore.bus;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.bookstore.dao.OrderDAO;
import com.bookstore.model.OrderModel;

public class OrderBUS extends BUSAbstract<OrderModel> {

  private final List<OrderModel> orderList = new ArrayList<>();
  private final OrderDAO orderDAO;

  protected OrderBUS(OrderDAO orderDAO) throws SQLException, ClassNotFoundException {
    this.orderDAO = orderDAO;
    this.orderList.addAll(orderDAO.readDatabase());
  }

  @Override
  protected ArrayList<OrderModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return orderDAO.readDatabase();
  }

  @Override
  protected int getId(OrderModel orderModel) {
    return orderModel.getId();
  }

  @Override
  protected OrderModel mapToEntity(OrderModel from) {
    OrderModel to = new OrderModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(OrderModel from, OrderModel to) {
    to.setCart_id(from.getCart_id());
    to.setCustomer_id(from.getCustomer_id());
    to.setEmployee_id(from.getEmployee_id());
    to.setTotal(from.getTotal());
    to.setPaid(from.getPaid());
    to.setCreated_at(from.getCreated_at());
    to.setUpdated_at(from.getUpdated_at());
    to.setStatus(from.getStatus());
  }

  @Override
  protected boolean checkFilter(OrderModel orderModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        if (Integer.parseInt(value) <= 0) {
          throw new IllegalArgumentException("Id must be greater than 0!");
        }
        return orderModel.getId() == Integer.parseInt(value);
      case "cart_id":
        return orderModel.getCart_id() == Integer.parseInt(value);
      case "customer_id":
        return orderModel.getCustomer_id() == Integer.parseInt(value);
      case "employee_id":
        return orderModel.getEmployee_id() == Integer.parseInt(value);
      case "total":
        int total = Integer.parseInt(value);
        return orderModel.getTotal() == total;
      case "paid":
        int paid = Integer.parseInt(value);
        return orderModel.getPaid() == paid;
      case "created_at":
        long createdAtTimestamp = orderModel.getCreated_at().getTime() / 1000;
        return createdAtTimestamp == Long.parseLong(value);
      case "updated_at":
        long updatedAtTimestamp = orderModel.getUpdated_at().getTime() / 1000;
        return updatedAtTimestamp == Long.parseLong(value);
      case "status":
        return orderModel.getStatus().toString().equalsIgnoreCase(value);
      default:
        return checkAllColumns(orderModel, value);
    }
  }

  protected boolean checkAllColumns(OrderModel orderModel, String value) {
    return orderModel.getId() == Integer.parseInt(value)
        || orderModel.getCart_id() == Integer.parseInt(value)
        || orderModel.getCustomer_id() == Integer.parseInt(value)
        || orderModel.getEmployee_id() == Integer.parseInt(value)
        || orderModel.getTotal() == Integer.parseInt(value)
        || orderModel.getPaid() == Integer.parseInt(value)
        || orderModel.getStatus().toString().equalsIgnoreCase(value);
  }

  @Override
  protected int insertModel(OrderModel orderModel) throws SQLException, ClassNotFoundException {
    if (orderModel.getTotal() < 0) {
      throw new IllegalArgumentException("Total must be greater than 0!");
    }

    if (orderModel.getPaid() <= 0) {
      throw new IllegalArgumentException("Paid must be greater than 0!");
    }

    Date now = new Date();
    orderModel.setCreated_at(new Timestamp(now.getTime()));
    orderModel.setUpdated_at(new Timestamp(now.getTime()));
    return add(orderModel);
  }

  @Override
  protected int updateModel(OrderModel orderModel) throws SQLException, ClassNotFoundException {
    if (orderModel.getEmployee_id() <= 0) {
      throw new IllegalArgumentException("Employee id must be greater than 0!");
    }
    if (orderModel.getTotal() <= 0) {
      throw new IllegalArgumentException("Total must be greater than 0!");
    }
    if (orderModel.getPaid() <= 0) {
      throw new IllegalArgumentException("Paid must be greater than 0!");
    }
    orderModel.setUpdated_at(new Timestamp(System.currentTimeMillis()));
    return update(orderModel);
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<OrderModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public OrderModel getOrderModel(int id) {
    return getModel(id);
  }

  public List<OrderModel> getOrderList() {
    return Collections.unmodifiableList(orderList);
  }
}
