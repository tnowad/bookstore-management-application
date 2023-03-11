package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.dao.ProviderDAO;
import com.bookstore.model.ProviderModel;

public class ProviderBUS extends BUSAbstract<ProviderModel> {

  public ProviderBUS() throws ClassNotFoundException, SQLException {
    super();
  }

  @Override
  protected ArrayList<ProviderModel> readDatabase() throws ClassNotFoundException, SQLException {
    return ProviderDAO.getInstance().readDatabase();
  }

  @Override
  protected int getId(ProviderModel providerModel) {
    return providerModel.getId();
  }

  @Override
  protected ProviderModel createModel(ProviderModel providerModel) {
    String name = providerModel.getName();
    String description = providerModel.getDescription();
    int id = providerModel.getId();
    return new ProviderModel(id, name, description);
  }

  @Override
  protected int insert(ProviderModel providerModel) throws ClassNotFoundException, SQLException {
    return ProviderDAO.getInstance().insert(providerModel);
  }

  @Override
  protected void copyProperties(ProviderModel currentProviderModel, ProviderModel newProviderModel) {
    currentProviderModel.setName(newProviderModel.getName());
    currentProviderModel.setDescription(newProviderModel.getDescription());
  }

  @Override
  protected int updateModel(ProviderModel providerModel) throws ClassNotFoundException, SQLException {
    return ProviderDAO.getInstance().update(providerModel);
  }

  @Override
  protected int deleteModel(String id) throws ClassNotFoundException, SQLException {
    return ProviderDAO.getInstance().delete(id);
  }

  @Override
  protected boolean checkValue(ProviderModel providerModel, String value, String columns) {
    switch (columns.toLowerCase()) {
      case "provider_id":
        return Integer.toString(providerModel.getId()).equals(value);
      case "name":
        return providerModel.getName().toLowerCase().contains(value.toLowerCase());
      case "contact_info":
        return providerModel.getDescription().toLowerCase().contains(value.toLowerCase());
      default:
        return checkAllColumns(providerModel, value);
    }
  }

  private boolean checkAllColumns(ProviderModel providerModel, String value) {
    return Integer.toString(providerModel.getId()).equals(value)
        || providerModel.getName().toLowerCase().contains(value.toLowerCase())
        || providerModel.getDescription().toLowerCase().contains(value.toLowerCase());
  }

}
