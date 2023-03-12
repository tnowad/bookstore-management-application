package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.model.AddressModel;

public class AddressBUS extends BUSAbstract<AddressModel> {

  protected AddressBUS() throws SQLException, ClassNotFoundException {
    super();
    // TODO Auto-generated constructor stub
  }

  @Override
  protected ArrayList<AddressModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'readFromDatabase'");
  }

  @Override
  protected int getId(AddressModel t) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getId'");
  }

  @Override
  protected AddressModel mapToEntity(AddressModel t) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mapToEntity'");
  }

  @Override
  protected int insertModel(AddressModel model) throws SQLException, ClassNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'insertModel'");
  }

  @Override
  protected int updateModel(AddressModel model) throws SQLException, ClassNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateModel'");
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteModel'");
  }

  @Override
  protected boolean checkFilter(AddressModel t, String value, String columns) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'checkFilter'");
  }

  @Override
  protected void updateEntityFields(AddressModel from, AddressModel to) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEntityFields'");
  }

}
