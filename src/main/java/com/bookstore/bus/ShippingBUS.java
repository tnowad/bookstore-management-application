package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ShippingDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.ShippingModel;
import com.bookstore.model.ShippingModel.Status;

public class ShippingBUS implements IBUS<ShippingModel> {

  private final List<ShippingModel> shippingList = new ArrayList<>();

  public ShippingBUS() throws SQLException, ClassNotFoundException {
    this.shippingList.addAll(ShippingDAO.getInstance().readDatabase());
  }

  @Override
  public List<ShippingModel> getAllModels() {
    return Collections.unmodifiableList(shippingList);
  }

  @Override
  public ShippingModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (ShippingModel shippingModel : shippingList) {
      if (shippingModel.getId() == id) {
        return shippingModel;
      }
    }
    return null;
  }

  public List<ShippingModel> getShippingList() throws NullPointerException {
    return Collections.unmodifiableList(shippingList);
  }

  private ShippingModel mapToEntity(ShippingModel from) {
    ShippingModel to = new ShippingModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(ShippingModel from, ShippingModel to) {
    to.setOrderId(from.getOrderId());
    to.setShippingMethod(from.getShippingMethod());
    to.setAddressId(from.getAddressId());
    to.setStatus(from.getStatus());
  }

  private boolean checkFilter(ShippingModel shippingModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> shippingModel.getId() == Integer.parseInt(value);
      case "order_id" -> shippingModel.getOrderId() == Integer.parseInt(value);
      case "shipping_method" -> shippingModel.getShippingMethod().toLowerCase().contains(value.toLowerCase());
      case "address_id" -> shippingModel.getAddressId() == Integer.parseInt(value);
      case "status" -> shippingModel.getStatus().toString().equalsIgnoreCase(value);
      default -> checkAllColumns(shippingModel, value);
    };
  }

  private boolean checkAllColumns(ShippingModel shippingModel, String value) {
    return shippingModel.getId() == Integer.parseInt(value)
        || shippingModel.getOrderId() == Integer.parseInt(value)
        || shippingModel.getShippingMethod().toLowerCase().contains(value.toLowerCase())
        || shippingModel.getAddressId() == Integer.parseInt(value)
        || shippingModel.getStatus().toString().equalsIgnoreCase(value);
  }

  @Override
  public int addModel(ShippingModel shippingModel) throws SQLException, ClassNotFoundException {
    if (shippingModel.getOrderId() <= 0) {
      throw new IllegalArgumentException("Order ID must be greater than 0!");
    }
    if (shippingModel.getShippingMethod() == null || shippingModel.getShippingMethod().isEmpty()) {
      throw new IllegalArgumentException("Shipping method cannot be null or empty!");
    }
    if (shippingModel.getAddressId() <= 0) {
      throw new IllegalArgumentException("Address ID must be greater than 0!");
    }

    shippingModel.setStatus(
        shippingModel.getStatus() != null ? shippingModel.getStatus() : Status.pending);

    int id = ShippingDAO.getInstance().insert(mapToEntity(shippingModel));
    shippingModel.setId(id);
    shippingList.add(shippingModel);
    return id;
  }

  @Override
  public int updateModel(ShippingModel shippingModel) throws SQLException, ClassNotFoundException {
    int updatedRows = ShippingDAO.getInstance().update(shippingModel);
    if (updatedRows > 0) {
      for (int i = 0; i < shippingList.size(); i++) {
        if (shippingList.get(i).getId() == shippingModel.getId()) {
          shippingList.set(i, shippingModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  public int updateStatus(int orderId, Status status) throws ClassNotFoundException, SQLException {
    int success = ShippingDAO.getInstance().updateStatus(orderId, status);
    if (success == 1) {
      for (ShippingModel shipping : shippingList) {
        if (shipping.getOrderId() == orderId) {
          shipping.setStatus(status);
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    ShippingModel shippingModel = getModelById(id);
    if (shippingModel == null) {
      throw new IllegalArgumentException("Shipping with ID " + id + " does not exist.");
    }
    int deletedRows = ShippingDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      shippingList.remove(shippingModel);
    }
    return deletedRows;
  }

  @Override
  public List<ShippingModel> searchModel(String value, String columns) throws SQLException, ClassNotFoundException {
    List<ShippingModel> results = new ArrayList<>();
    try {
      List<ShippingModel> entities = ShippingDAO.getInstance().search(value, columns);
      for (ShippingModel entity : entities) {
        ShippingModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for shipping: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for shipping: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No shipping found with the specified search criteria.");
    }

    return results;
  }
}
