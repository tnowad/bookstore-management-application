package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.AddressDAO;
import com.bookstore.model.AddressModel;

public class AddressBUS extends BUSAbstract<AddressModel> {

  private final List<AddressModel> addressList = new ArrayList<>();
  private final AddressDAO addressDAO;

  protected AddressBUS(AddressDAO addressDAO) throws SQLException, ClassNotFoundException {
    this.addressDAO = addressDAO;
    this.addressList.addAll(addressDAO.readDatabase());
  }

  @Override
  protected ArrayList<AddressModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return addressDAO.readDatabase();
  }

  @Override
  protected int getId(AddressModel addressModel) {
    return addressModel.getId();
  }

  @Override
  protected AddressModel mapToEntity(AddressModel from) {
    AddressModel to = new AddressModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(AddressModel from, AddressModel to) {
    to.setUserId(from.getUserId());
    to.setStreet(from.getStreet());
    to.setCity(from.getCity());
    to.setState(from.getState());
    to.setZip(from.getZip());
  }

  @Override
  protected boolean checkFilter(AddressModel addressModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        return addressModel.getId() == Integer.parseInt(value);
      case "user_id":
        return addressModel.getUserId() == Integer.parseInt(value);
      case "street":
        return addressModel.getStreet().toLowerCase().contains(value.toLowerCase());
      case "city":
        return addressModel.getCity().toLowerCase().contains(value.toLowerCase());
      case "state":
        return addressModel.getState().toLowerCase().contains(value.toLowerCase());
      case "zip":
        return addressModel.getZip().toLowerCase().contains(value.toLowerCase());
      default:
        return checkAllColumns(addressModel, value);
    }
  }

  protected boolean checkAllColumns(AddressModel addressModel, String value) {
    return addressModel.getId() == Integer.parseInt(value)
        || addressModel.getUserId() == Integer.parseInt(value)
        || addressModel.getStreet().toLowerCase().contains(value.toLowerCase())
        || addressModel.getCity().toLowerCase().contains(value.toLowerCase())
        || addressModel.getState().toLowerCase().contains(value.toLowerCase())
        || addressModel.getZip().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  protected int insertModel(AddressModel addressModel) throws SQLException, ClassNotFoundException {
    if (addressModel.getStreet() == null || addressModel.getStreet().isEmpty()) {
      throw new IllegalArgumentException("Street cannot be null or empty!");
    }
    if (addressModel.getCity() == null || addressModel.getCity().isEmpty()) {
      throw new IllegalArgumentException("City cannot be null or empty!");
    }
    if (addressModel.getState() == null || addressModel.getState().isEmpty()) {
      throw new IllegalArgumentException("State cannot be null or empty!");
    }
    return add(addressModel);
  }

  @Override
  protected int updateModel(AddressModel addressModel) throws SQLException, ClassNotFoundException {
    return update(addressModel);
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<AddressModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public AddressModel getAddressModel(int id) {
    return getModel(id);
  }

  public List<AddressModel> getAddressList() {
    return Collections.unmodifiableList(addressList);
  }
}
