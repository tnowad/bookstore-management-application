package com.bookstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.AddressDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.AddressModel;

public class AddressBUS implements IBUS<AddressModel> {

  private final List<AddressModel> addressList = new ArrayList<>();
  private static AddressBUS instance;

  public static AddressBUS getInstance() {
    if (instance == null) {
      instance = new AddressBUS();
    }
    return instance;
  }

  private AddressBUS() {
    this.addressList.addAll(AddressDAO.getInstance().readDatabase());
  }

  @Override
  public List<AddressModel> getAllModels() {
    return Collections.unmodifiableList(addressList);
  }

  @Override
  public AddressModel getModelById(int id) {
    for (AddressModel addressModel : addressList) {
      if (addressModel.getId() == id) {
        return addressModel;
      }
    }
    return null;
  }

  private AddressModel mapToEntity(AddressModel from) {
    AddressModel to = new AddressModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(AddressModel from, AddressModel to) {
    to.setId(from.getId());
    to.setUserId(from.getUserId());
    to.setStreet(from.getStreet());
    to.setCity(from.getCity());
    to.setState(from.getState());
    to.setZip(from.getZip());
  }

  private boolean checkFilter(AddressModel addressModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (addressModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "user_id" -> {
          if (addressModel.getUserId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "street" -> {
          if (addressModel.getStreet().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "city" -> {
          if (addressModel.getCity().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "state" -> {
          if (addressModel.getState().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "zip" -> {
          if (addressModel.getZip().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(addressModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(AddressModel addressModel, String value) {
    return addressModel.getId() == Integer.parseInt(value)
        || addressModel.getUserId() == Integer.parseInt(value)
        || addressModel.getStreet().toLowerCase().contains(value.toLowerCase())
        || addressModel.getCity().toLowerCase().contains(value.toLowerCase())
        || addressModel.getState().toLowerCase().contains(value.toLowerCase())
        || addressModel.getZip().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int addModel(AddressModel addressModel) {
    if (addressModel.getStreet() == null || addressModel.getStreet().isEmpty()) {
      throw new IllegalArgumentException("Street cannot be null or empty!");
    }
    if (addressModel.getCity() == null || addressModel.getCity().isEmpty()) {
      throw new IllegalArgumentException("City cannot be null or empty!");
    }
    if (addressModel.getState() == null || addressModel.getState().isEmpty()) {
      throw new IllegalArgumentException("State cannot be null or empty!");
    }

    int id = AddressDAO.getInstance().insert(mapToEntity(addressModel));
    addressModel.setId(id);
    addressList.add(addressModel);
    return id;
  }

  @Override
  public int updateModel(AddressModel addressModel) {
    int updatedRows = AddressDAO.getInstance().update(addressModel);
    if (updatedRows > 0) {
      for (int i = 0; i < addressList.size(); i++) {
        if (addressList.get(i).getId() == addressModel.getId()) {
          addressList.set(i, addressModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    AddressModel addressModel = getModelById(id);
    if (addressModel == null) {
      throw new IllegalArgumentException("Address with ID " + id + " does not exist.");
    }
    int deletedRows = AddressDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      addressList.remove(addressModel);
    }
    return deletedRows;
  }

  @Override
  public List<AddressModel> searchModel(String value, String[] columns) {
    List<AddressModel> results = new ArrayList<>();
    List<AddressModel> entities = AddressDAO.getInstance().search(value, columns);
    for (AddressModel entity : entities) {
      AddressModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }
}
