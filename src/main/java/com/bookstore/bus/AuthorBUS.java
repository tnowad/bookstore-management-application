package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.model.AuthorModel;

public class AuthorBUS implements BUSInterface<AuthorModel> {

  private final List<AuthorModel> authorList = new ArrayList<>();

  public AuthorBUS() throws SQLException, ClassNotFoundException {
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

  public List<AuthorModel> getAuthorList() throws NullPointerException {
    return Collections.unmodifiableList(authorList);
  }

  private AuthorModel mapToEntity(AuthorModel from) {
    AuthorModel to = new AuthorModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(AuthorModel from, AuthorModel to) {
    to.setName(from.getName());
    to.setDescription(from.getDescription());
  }

  private boolean checkFilter(AuthorModel authorModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> authorModel.getId() == Integer.parseInt(value);
      case "name" -> authorModel.getName().toLowerCase().contains(value.toLowerCase());
      case "description" -> authorModel.getDescription().toLowerCase().contains(value.toLowerCase());
      default -> checkAllColumns(authorModel, value);
    };
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
    AuthorModel existingAuthor = getModelById(authorModel.getId());
    if (existingAuthor == null) {
      return 0;
    }

    updateEntityFields(authorModel, existingAuthor);
    try {
      AuthorDAO.getInstance().update(mapToEntity(existingAuthor));
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException("Failed to update author: " + e.getMessage());
    }
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
  public List<AuthorModel> searchModel(String value, String columns) throws SQLException, ClassNotFoundException {
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
