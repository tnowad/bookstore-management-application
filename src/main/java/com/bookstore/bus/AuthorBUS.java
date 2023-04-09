package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.AuthorModel;

public class AuthorBUS implements IBUS<AuthorModel> {

  private final List<AuthorModel> authorList = new ArrayList<>();
  private static AuthorBUS instance;

  public static AuthorBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new AuthorBUS();
    }
    return instance;
  }

  private AuthorBUS() throws SQLException, ClassNotFoundException {
    this.authorList.addAll(AuthorDAO.getInstance().readDatabase());
  }

  @Override
  public List<AuthorModel> getAllModels() {
    return Collections.unmodifiableList(authorList);
  }

  @Override
  public AuthorModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (AuthorModel authorModel : authorList) {
      if (authorModel.getId() == id) {
        return authorModel;
      }
    }
    return null;
  }

  private AuthorModel mapToEntity(AuthorModel from) {
    AuthorModel to = new AuthorModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(AuthorModel from, AuthorModel to) {
    to.setId(from.getId());
    to.setName(from.getName());
    to.setDescription(from.getDescription());
  }

  private boolean checkFilter(AuthorModel authorModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (authorModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "name" -> {
          if (authorModel.getName().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "description" -> {
          if (authorModel.getDescription().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(authorModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(AuthorModel authorModel, String value) {
    return authorModel.getId() == Integer.parseInt(value)
        || authorModel.getName().toLowerCase().contains(value.toLowerCase())
        || authorModel.getDescription().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int addModel(AuthorModel authorModel) throws SQLException, ClassNotFoundException {
    if (authorModel.getName() == null || authorModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    if (authorModel.getDescription() == null || authorModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }

    int id = AuthorDAO.getInstance().insert(mapToEntity(authorModel));
    authorModel.setId(id);
    authorList.add(authorModel);
    return id;
  }

  @Override
  public int updateModel(AuthorModel authorModel) throws SQLException, ClassNotFoundException {
    int updatedRows = AuthorDAO.getInstance().update(authorModel);
    if (updatedRows > 0) {
      for (int i = 0; i < authorList.size(); i++) {
        if (authorList.get(i).getId() == authorModel.getId()) {
          authorList.set(i, authorModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    AuthorModel authorModel = getModelById(id);
    if (authorModel == null) {
      throw new IllegalArgumentException("Author with ID " + id + " does not exist.");
    }
    int deletedRows = AuthorDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      authorList.remove(authorModel);
    }
    return deletedRows;
  }

  @Override
  public List<AuthorModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
    List<AuthorModel> results = new ArrayList<>();
    try {
      List<AuthorModel> entities = AuthorDAO.getInstance().search(value, columns);
      for (AuthorModel entity : entities) {
        AuthorModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for author: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for author: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No author found with the specified search criteria.");
    }

    return results;
  }
}
