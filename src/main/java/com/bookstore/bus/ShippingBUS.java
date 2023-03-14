package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ShippingDAO;
import com.bookstore.model.ShippingModel;

public class ShippingBUS extends BUSAbstract<ShippingModel> {

  private final List<ShippingModel> shippingList = new ArrayList<>();
  private final ShippingDAO shippingDAO = ShippingDAO.getInstance();

  public ShippingBUS() throws SQLException, ClassNotFoundException {
    this.shippingList.addAll(shippingDAO.readDatabase());
  }

  @Override
  protected ArrayList<ShippingModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return shippingDAO.readDatabase();
  }

  @Override
  public int getId(ShippingModel shippingModel) {
    return shippingModel.getId();
  }

  public ShippingModel getShippingModel(int id) {
    return getModel(id);
  }

  public List<ShippingModel> getShippingList() {
    return Collections.unmodifiableList(shippingList);
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
    return switch (column.toLowerCase()) {
      case "id" -> shippingModel.getId() == Integer.parseInt(value);
      case "order_id" -> shippingModel.getOrderId() == Integer.parseInt(value);
      case "shipping_method" -> shippingModel.getShippingMethod().equalsIgnoreCase(value);
      case "address_id" -> shippingModel.getAddressId() == Integer.parseInt(value);
      case "status" -> shippingModel.getStatus().toString().equalsIgnoreCase(value);
      default -> checkAllColumns(shippingModel, value);
    };
  }

  private boolean checkAllColumns(ShippingModel shippingModel, String value) {
    return shippingModel.getId() == Integer.parseInt(value)
        || shippingModel.getOrderId() == Integer.parseInt(value)
        || shippingModel.getShippingMethod().equalsIgnoreCase(value)
        || shippingModel.getAddressId() == Integer.parseInt(value)
        || shippingModel.getStatus().toString().equalsIgnoreCase(value);
  }

  @Override
  public int insertModel(ShippingModel shippingModel) throws SQLException, ClassNotFoundException {
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
  public int updateModel(ShippingModel shippingModel) throws SQLException, ClassNotFoundException {
    return update(shippingModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<ShippingModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

}
