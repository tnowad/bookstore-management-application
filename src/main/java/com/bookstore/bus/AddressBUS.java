package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.AddressDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.AddressModel;

public class AddressBUS implements IBUS<AddressModel> {
  private final List<AddressModel> addressList = new ArrayList<>();
  private static AddressBUS instance;

  public static AddressBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new AddressBUS();
    }
    return instance;
  }

  private AddressBUS() throws SQLException, ClassNotFoundException {
    this.addressList.addAll(AddressDAO.getInstance().readDatabase());
  }

  @Override
  public List<AddressModel> getAllModels() {
    return Collections.unmodifiableList(addressList);
  }

  @Override
  public AddressModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (AddressModel addressModel : addressList) {
      if (addressModel.getId() == id) {
        return addressModel;
      }
    }
    return null;
  }

  public List<AddressModel> getAddressList() throws NullPointerException {
    return Collections.unmodifiableList(addressList);
  }

  private AddressModel mapToEntity(AddressModel from) {
    AddressModel to = new AddressModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(AddressModel from, AddressModel to) {
    to.setUserId(from.getUserId());
    to.setStreet(from.getStreet());
    to.setCity(from.getCity());
    to.setState(from.getState());
    to.setZip(from.getZip());
  }

  private boolean checkFilter(AddressModel addressModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id":
          if (addressModel.getId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "user_id":
          if (addressModel.getUserId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "street":
          if (addressModel.getStreet().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "city":
          if (addressModel.getCity().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "state":
          if (addressModel.getState().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "zip":
          if (addressModel.getZip().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        default:
          if (checkAllColumns(addressModel, value)) {
            return true;
          }
          break;
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
  public int addModel(AddressModel addressModel) throws SQLException, ClassNotFoundException {
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
  public int updateModel(AddressModel addressModel) throws SQLException, ClassNotFoundException {
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
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    AddressModel addressModel = getModelById(id);
    if (addressModel == null) {
      throw new IllegalArgumentException("User with ID " + id + " does not exist.");
    }
    int deletedRows = AddressDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      addressList.remove(addressModel);
    }
    return deletedRows;
  }

  @Override
  public List<AddressModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
    List<AddressModel> results = new ArrayList<>();
    try {
      List<AddressModel> entities = AddressDAO.getInstance().search(value, columns);
      for (AddressModel entity : entities) {
        AddressModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for address: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for address: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No address found with the specified search criteria.");
    }

    return results;
  }
}
