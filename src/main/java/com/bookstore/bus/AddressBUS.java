package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.AddressDAO;
import com.bookstore.model.AddressModel;

public class AddressBUS extends BUSInterface<AddressModel> {

  private final List<AddressModel> addressList = new ArrayList<>();
  private final AddressDAO addressDAO = AddressDAO.getInstance();

  public AddressBUS() throws SQLException, ClassNotFoundException {
    this.addressList.addAll(addressDAO.readDatabase());
  }

  @Override
  protected ArrayList<AddressModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return addressDAO.readDatabase();
  }

  @Override
  public int getId(AddressModel addressModel) {
    return addressModel.getId();
  }

  public AddressModel getAddressModel(int id) {
    return getModel(id);
  }

  public List<AddressModel> getAddressList() throws NullPointerException {
    return Collections.unmodifiableList(addressList);
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
    return switch (column.toLowerCase()) {
      case "id" -> addressModel.getId() == Integer.parseInt(value);
      case "user_id" -> addressModel.getUserId() == Integer.parseInt(value);
      case "street" -> addressModel.getStreet().toLowerCase().contains(value.toLowerCase());
      case "city" -> addressModel.getCity().toLowerCase().contains(value.toLowerCase());
      case "state" -> addressModel.getState().toLowerCase().contains(value.toLowerCase());
      case "zip" -> addressModel.getZip().toLowerCase().contains(value.toLowerCase());
      default -> checkAllColumns(addressModel, value);
    };
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
  public int insertModel(AddressModel addressModel) throws SQLException, ClassNotFoundException {
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
  public int updateModel(AddressModel addressModel) throws SQLException, ClassNotFoundException {
    return update(addressModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<AddressModel> searchAddress(String value, String columns) {
    return search(value, columns);
  }

}
