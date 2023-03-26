package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ProviderDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.ProviderModel;

public class ProviderBUS implements IBUS<ProviderModel> {

  private final List<ProviderModel> providerList = new ArrayList<>();
  private static ProviderBUS instance;

  public static ProviderBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new ProviderBUS();
    }
    return instance;
  }

  private ProviderBUS() throws SQLException, ClassNotFoundException {
    this.providerList.addAll(ProviderDAO.getInstance().readDatabase());
  }

  @Override
  public List<ProviderModel> getAllModels() {
    return Collections.unmodifiableList(providerList);
  }

  @Override
  public ProviderModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (ProviderModel providerModel : providerList) {
      if (providerModel.getId() == id) {
        return providerModel;
      }
    }
    return null;
  }

  private ProviderModel mapToEntity(ProviderModel from) {
    ProviderModel to = new ProviderModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(ProviderModel from, ProviderModel to) {
    to.setName(from.getName());
    to.setDescription(from.getDescription());
  }

  private boolean checkFilter(ProviderModel providerModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id":
          if (providerModel.getId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "name":
          if (providerModel.getName().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "description":
          if (providerModel.getDescription().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        default:
          if (checkAllColumns(providerModel, value)) {
            return true;
          }
          break;
      }
    }
    return false;
  }

  private boolean checkAllColumns(ProviderModel providerModel, String value) {
    return providerModel.getId() == Integer.parseInt(value)
        || providerModel.getName().toLowerCase().contains(value.toLowerCase())
        || providerModel.getDescription().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int addModel(ProviderModel providerModel) throws SQLException, ClassNotFoundException {
    if (providerModel.getName() == null || providerModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    if (providerModel.getDescription() == null || providerModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }

    int id = ProviderDAO.getInstance().insert(mapToEntity(providerModel));
    providerModel.setId(id);
    providerList.add(providerModel);
    return id;
  }

  @Override
  public int updateModel(ProviderModel providerModel) throws SQLException, ClassNotFoundException {
    int updatedRows = ProviderDAO.getInstance().update(providerModel);
    if (updatedRows > 0) {
      for (int i = 0; i < providerList.size(); i++) {
        if (providerList.get(i).getId() == providerModel.getId()) {
          providerList.set(i, providerModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    ProviderModel providerModel = getModelById(id);
    if (providerModel == null) {
      throw new IllegalArgumentException("Provider with ID " + id + " does not exist.");
    }
    int deletedRows = ProviderDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      providerList.remove(providerModel);
    }
    return deletedRows;
  }

  @Override
  public List<ProviderModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
    List<ProviderModel> results = new ArrayList<>();
    try {
      List<ProviderModel> entities = ProviderDAO.getInstance().search(value, columns);
      for (ProviderModel entity : entities) {
        ProviderModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for provider: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for provider: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No provider found with the specified search criteria.");
    }

    return results;
  }
}
