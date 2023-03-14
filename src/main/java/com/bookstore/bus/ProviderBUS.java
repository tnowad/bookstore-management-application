package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ProviderDAO;
import com.bookstore.model.ProviderModel;

public class ProviderBUS extends BUSAbstract<ProviderModel> {

  private final List<ProviderModel> providerList = new ArrayList<>();
  private final ProviderDAO providerDAO = ProviderDAO.getInstance();

  public ProviderBUS() throws SQLException, ClassNotFoundException {
    this.providerList.addAll(providerDAO.readDatabase());
  }

  @Override
  protected ArrayList<ProviderModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return providerDAO.readDatabase();
  }

  @Override
  public int getId(ProviderModel t) {
    return t.getId();
  }

  public ProviderModel getProviderModel(int id) {
    return getModel(id);
  }

  public List<ProviderModel> getProviderList() {
    return Collections.unmodifiableList(providerList);
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
    return switch (column.toLowerCase()) {
      case "id" -> providerModel.getId() == Integer.parseInt(value);
      case "name" -> providerModel.getName().toLowerCase().contains(value.toLowerCase());
      case "description" -> providerModel.getDescription().toLowerCase().contains(value.toLowerCase());
      default -> checkAllColumns(providerModel, value);
    };
  }

  private boolean checkAllColumns(ProviderModel providerModel, String value) {
    return providerModel.getId() == Integer.parseInt(value)
        || providerModel.getName().toLowerCase().contains(value.toLowerCase())
        || providerModel.getDescription().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int insertModel(ProviderModel providerModel) throws SQLException, ClassNotFoundException {
    if (providerModel.getName() == null || providerModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    if (providerModel.getDescription() == null || providerModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }
    return add(providerModel);
  }

  @Override
  public int updateModel(ProviderModel providerModel) throws SQLException, ClassNotFoundException {
    return update(providerModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<ProviderModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

}
