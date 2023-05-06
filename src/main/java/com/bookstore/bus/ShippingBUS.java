package com.bookstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ShippingDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.ShippingModel;
import com.bookstore.enums.ShippingStatus;

public class ShippingBUS implements IBUS<ShippingModel> {

  private final List<ShippingModel> shippingList = new ArrayList<>();
  private static ShippingBUS instance;

  public static ShippingBUS getInstance() {
    if (instance == null) {
      instance = new ShippingBUS();
    }
    return instance;
  }

  private ShippingBUS() {
    this.shippingList.addAll(ShippingDAO.getInstance().readDatabase());
  }

  @Override
  public List<ShippingModel> getAllModels() {
    return Collections.unmodifiableList(shippingList);
  }

  @Override
  public ShippingModel getModelById(int id) {
    for (ShippingModel shippingModel : shippingList) {
      if (shippingModel.getId() == id) {
        return shippingModel;
      }
    }
    return null;
  }

  public ShippingModel getModelByOrderId(int orderId) {
    for (ShippingModel shippingModel : shippingList) {
      if (shippingModel.getOrderId() == orderId) {
        return shippingModel;
      }
    }
    return null;
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

  private boolean checkFilter(ShippingModel shippingModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (shippingModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "order_id" -> {
          if (shippingModel.getOrderId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "shipping_method" -> {
          if (shippingModel.getShippingMethod().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "address_id" -> {
          if (shippingModel.getAddressId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "status" -> {
          if (shippingModel.getStatus().toString().contains(value)) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(shippingModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(ShippingModel shippingModel, String value) {
    return shippingModel.getId() == Integer.parseInt(value)
        || shippingModel.getOrderId() == Integer.parseInt(value)
        || shippingModel.getShippingMethod().toLowerCase().contains(value.toLowerCase())
        || shippingModel.getAddressId() == Integer.parseInt(value)
        || shippingModel.getStatus().toString().equalsIgnoreCase(value);
  }

  @Override
  public int addModel(ShippingModel shippingModel) {
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
        shippingModel.getStatus() != null ? shippingModel.getStatus() : ShippingStatus.PENDING);

    int id = ShippingDAO.getInstance().insert(mapToEntity(shippingModel));
    shippingModel.setId(id);
    shippingList.add(shippingModel);
    return id;
  }

  @Override
  public int updateModel(ShippingModel shippingModel) {
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

  public int updateStatus(int orderId, ShippingStatus status) {
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
  public int deleteModel(int id) {
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
  public List<ShippingModel> searchModel(String value, String[] columns) {
    List<ShippingModel> results = new ArrayList<>();
    List<ShippingModel> entities = ShippingDAO.getInstance().search(value, columns);
    for (ShippingModel entity : entities) {
      ShippingModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  @Override
  public void refreshData() {
    shippingList.clear();
    shippingList.addAll(ShippingDAO.getInstance().readDatabase());
  }
}
