package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ProviderDAO;
import com.bookstore.model.ProviderModel;

public class ProviderBUS extends BUSAbstract<ProviderModel> {

  private final List<ProviderModel> providerList = new ArrayList<>();
  private final ProviderDAO providerDAO;

  protected ProviderBUS(ProviderDAO providerDAO) throws SQLException, ClassNotFoundException {
    this.providerDAO = providerDAO;
    this.providerList.addAll(providerDAO.readDatabase());
  }

  @Override
  protected ArrayList<ProviderModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return providerDAO.readDatabase();
  }

  @Override
  protected int getId(ProviderModel t) {
    return t.getId();
  }

  @Override
  protected ProviderModel mapToEntity(ProviderModel from) {
    ProviderModel to = new ProviderModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(ProviderModel from, ProviderModel to) {
    to.setName(from.getName());
    to.setDescription(from.getDescription());
  }

  @Override
  protected boolean checkFilter(ProviderModel providerModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        return providerModel.getId() == Integer.parseInt(value);
      case "name":
        return providerModel.getName().toLowerCase().contains(value.toLowerCase());
      case "description":
        return providerModel.getDescription().toLowerCase().contains(value.toLowerCase());
      default:
        return checkAllColumns(providerModel, value);
    }
  }

  protected boolean checkAllColumns(ProviderModel providerModel, String value) {
    return providerModel.getId() == Integer.parseInt(value)
        || providerModel.getName().toLowerCase().contains(value.toLowerCase())
        || providerModel.getDescription().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  protected int insertModel(ProviderModel providerModel) throws SQLException, ClassNotFoundException {
    if (providerModel.getName() == null || providerModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    if (providerModel.getDescription() == null || providerModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }
    return add(providerModel);
  }

  @Override
  protected int updateModel(ProviderModel providerModel) throws SQLException, ClassNotFoundException {
    return update(providerModel);
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<ProviderModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public ProviderModel getProviderModel(int id) {
    return getModel(id);
  }

  public List<ProviderModel> getProviderList() {
    return Collections.unmodifiableList(providerList);
  }
}
