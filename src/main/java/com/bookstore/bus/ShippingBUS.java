package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ShippingDAO;
import com.bookstore.model.ShippingModel;

public class ShippingBUS extends BUSAbstract<ShippingModel> {

  private final List<ShippingModel> shippingList = new ArrayList<>();
  private final ShippingDAO shippingDAO;

  protected ShippingBUS(ShippingDAO shippingDAO) throws SQLException, ClassNotFoundException {
    this.shippingDAO = shippingDAO;
    this.shippingList.addAll(shippingDAO.readDatabase());
  }

  @Override
  protected ArrayList<ShippingModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return shippingDAO.readDatabase();
  }

  @Override
  protected int getId(ShippingModel shippingModel) {
    return shippingModel.getId();
  }

  @Override
  protected ShippingModel mapToEntity(ShippingModel from) {
    ShippingModel to = new ShippingModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(ShippingModel from, ShippingModel to) {
    to.setOrderId(from.getOrderId());
    to.setShippingMethod(from.getShippingMethod());
    to.setAddressId(from.getAddressId());
    to.setStatus(from.getStatus());
  }

  @Override
  protected boolean checkFilter(ShippingModel shippingModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        return shippingModel.getId() == Integer.parseInt(value);
      case "order_id":
        return shippingModel.getOrderId() == Integer.parseInt(value);
      case "shipping_method":
        return shippingModel.getShippingMethod().equalsIgnoreCase(value);
      case "address_id":
        return shippingModel.getAddressId() == Integer.parseInt(value);
      case "status":
        return shippingModel.getStatus().toString().equalsIgnoreCase(value);
      default:
        return checkAllColumns(shippingModel, value);
    }
  }

  protected boolean checkAllColumns(ShippingModel shippingModel, String value) {
    return shippingModel.getId() == Integer.parseInt(value)
        || shippingModel.getOrderId() == Integer.parseInt(value)
        || shippingModel.getShippingMethod().equalsIgnoreCase(value)
        || shippingModel.getAddressId() == Integer.parseInt(value)
        || shippingModel.getStatus().toString().equalsIgnoreCase(value);
  }

  @Override
  protected int insertModel(ShippingModel shippingModel) throws SQLException, ClassNotFoundException {
    if (shippingModel.getOrderId() <= 0) {
      throw new IllegalArgumentException("Order ID must be greater than 0!");
    }
    if (shippingModel.getShippingMethod() == null || shippingModel.getShippingMethod().isEmpty()) {
      throw new IllegalArgumentException("Shipping method cannot be null or empty!");
    }
    if (shippingModel.getAddressId() <= 0) {
      throw new IllegalArgumentException("Address ID must be greater than 0!");
    }
    if (shippingModel.getStatus() == null) {
      throw new IllegalArgumentException("Status cannot be null or empty!");
    }
    return add(shippingModel);
  }

  @Override
  protected int updateModel(ShippingModel shippingModel) throws SQLException, ClassNotFoundException {
    return update(shippingModel);
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<ShippingModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public ShippingModel getShippingModel(int id) {
    return getModel(id);
  }

  public List<ShippingModel> getShippingList() {
    return Collections.unmodifiableList(shippingList);
  }
}
