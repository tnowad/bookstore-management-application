package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.model.AuthorModel;

public class AuthorBUS extends BUSAbstract<AuthorModel> {

  private final List<AuthorModel> authorList = new ArrayList<>();
  private final AuthorDAO authorDAO = AuthorDAO.getInstance();

  public AuthorBUS() throws SQLException, ClassNotFoundException {
    this.authorList.addAll(authorDAO.readDatabase());
  }

  @Override
  protected ArrayList<AuthorModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return authorDAO.readDatabase();
  }

  @Override
  public int getId(AuthorModel authorModel) {
    return authorModel.getId();
  }

  public AuthorModel getAuthorModel(int id) {
    return getModel(id);
  }

  public List<AuthorModel> getAuthorList() throws NullPointerException {
    return Collections.unmodifiableList(authorList);
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
  public int insertModel(AuthorModel authorModel) throws SQLException, ClassNotFoundException {
    if (authorModel.getName() == null || authorModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    if (authorModel.getDescription() == null || authorModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }
    return add(authorModel);
  }

  @Override
  public int updateModel(AuthorModel authorModel) throws SQLException, ClassNotFoundException {
    return update(authorModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<AuthorModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

}
