package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.model.AuthorModel;

public class AuthorBUS extends BUSAbstract<AuthorModel> {

  private final List<AuthorModel> authorList = new ArrayList<>();
  private final AuthorDAO authorDAO;

  protected AuthorBUS(AuthorDAO authorDAO) throws SQLException, ClassNotFoundException {
    this.authorDAO = authorDAO;
    this.authorList.addAll(authorDAO.readDatabase());
  }

  @Override
  protected ArrayList<AuthorModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return authorDAO.readDatabase();
  }

  @Override
  protected int getId(AuthorModel authorModel) {
    return authorModel.getId();
  }

  @Override
  protected AuthorModel mapToEntity(AuthorModel from) {
    AuthorModel to = new AuthorModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(AuthorModel from, AuthorModel to) {
    to.setName(from.getName());
    to.setDescription(from.getDescription());
  }

  @Override
  protected boolean checkFilter(AuthorModel authorModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        return authorModel.getId() == Integer.parseInt(value);
      case "name":
        return authorModel.getName().toLowerCase().contains(value.toLowerCase());
      case "description":
        return authorModel.getDescription().toLowerCase().contains(value.toLowerCase());
      default:
        return checkAllColumns(authorModel, value);
    }
  }

  protected boolean checkAllColumns(AuthorModel authorModel, String value) {
    return authorModel.getId() == Integer.parseInt(value)
        || authorModel.getName().toLowerCase().contains(value.toLowerCase())
        || authorModel.getDescription().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  protected int insertModel(AuthorModel authorModel) throws SQLException, ClassNotFoundException {
    if (authorModel.getName() == null || authorModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    if (authorModel.getDescription() == null || authorModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }
    return add(authorModel);
  }

  @Override
  protected int updateModel(AuthorModel authorModel) throws SQLException, ClassNotFoundException {
    return update(authorModel);
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<AuthorModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public AuthorModel getAuthorModel(int id) {
    return getModel(id);
  }

  public List<AuthorModel> getAuthorList() {
    return Collections.unmodifiableList(authorList);
  }
}
