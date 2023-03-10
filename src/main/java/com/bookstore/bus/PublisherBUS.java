package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.PublisherDAO;
import com.bookstore.model.PublisherModel;

public class PublisherBUS extends BUSAbstract<PublisherModel> {

  private final List<PublisherModel> publisherList = new ArrayList<>();
  private final PublisherDAO publisherDAO = PublisherDAO.getInstance();

  public PublisherBUS() throws SQLException, ClassNotFoundException {
    this.publisherList.addAll(publisherDAO.readDatabase());
  }

  @Override
  public ArrayList<PublisherModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return publisherDAO.readDatabase();
  }

  @Override
  public int getId(PublisherModel publisherModel) {
    return publisherModel.getId();
  }

  public PublisherModel getPublisherModel(int id) {
    return getModel(id);
  }

  public List<PublisherModel> getPublisherList() {
    return Collections.unmodifiableList(publisherList);
  }

  @Override
  protected PublisherModel mapToEntity(PublisherModel from) {
    PublisherModel to = new PublisherModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(PublisherModel from, PublisherModel to) {
    to.setName(from.getName());
    to.setDescription(from.getDescription());
  }

  @Override
  protected boolean checkFilter(PublisherModel publisherModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id" -> {
        if (Integer.parseInt(value) < 0) {
          throw new IllegalArgumentException("Id must be greater than 0!");
        }
        return publisherModel.getId() == Integer.parseInt(value);
      }
      case "name" -> {
        return publisherModel.getName().toLowerCase().contains(value.toLowerCase());
      }
      case "description" -> {
        return publisherModel.getDescription().toLowerCase().contains(value.toLowerCase());
      }
      default -> {
        return checkAllColumns(publisherModel, value);
      }
    }
  }

  private boolean checkAllColumns(PublisherModel publisherModel, String value) {
    return publisherModel.getId() == Integer.parseInt(value)
        || publisherModel.getName().toLowerCase().contains(value.toLowerCase())
        || publisherModel.getDescription().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int insertModel(PublisherModel publisherModel) throws SQLException, ClassNotFoundException {
    if (publisherModel.getName() == null || publisherModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Publisher name cannot be null or empty!");
    }
    if (publisherModel.getDescription() == null) {
      publisherModel.setDescription("");
    }
    return add(publisherModel);
  }

  @Override
  public int updateModel(PublisherModel publisherModel) throws SQLException, ClassNotFoundException {
    return update(publisherModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<PublisherModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

}
