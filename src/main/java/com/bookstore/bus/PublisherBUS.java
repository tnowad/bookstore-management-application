package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.PublisherDAO;
import com.bookstore.model.PublisherModel;

public class PublisherBUS implements BUSInterface<PublisherModel> {

  private final List<PublisherModel> publisherList = new ArrayList<>();

  public PublisherBUS() throws SQLException, ClassNotFoundException {
    this.publisherList.addAll(PublisherDAO.getInstance().readDatabase());
  }

  @Override
  public List<PublisherModel> getAllModels() {
    return Collections.unmodifiableList(publisherList);
  }

  @Override
  public PublisherModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (PublisherModel publisherModel : publisherList) {
      if (publisherModel.getId() == id) {
        return publisherModel;
      }
    }
    return null;
  }

  public List<PublisherModel> getPublisherList() throws NullPointerException {
    return Collections.unmodifiableList(publisherList);
  }

  private PublisherModel mapToEntity(PublisherModel from) {
    PublisherModel to = new PublisherModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PublisherModel from, PublisherModel to) {
    to.setName(from.getName());
    to.setDescription(from.getDescription());
  }

  private boolean checkFilter(PublisherModel publisherModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> publisherModel.getId() == Integer.parseInt(value);
      case "name" -> publisherModel.getName().toLowerCase().contains(value.toLowerCase());
      case "description" -> publisherModel.getDescription().toLowerCase().contains(value.toLowerCase());
      default -> checkAllColumns(publisherModel, value);
    };
  }

  private boolean checkAllColumns(PublisherModel publisherModel, String value) {
    return publisherModel.getId() == Integer.parseInt(value)
        || publisherModel.getName().toLowerCase().contains(value.toLowerCase())
        || publisherModel.getDescription().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int addModel(PublisherModel publisherModel) throws SQLException, ClassNotFoundException {
    if (publisherModel.getName() == null || publisherModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    if (publisherModel.getDescription() == null || publisherModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }

    int id = PublisherDAO.getInstance().insert(mapToEntity(publisherModel));
    publisherModel.setId(id);
    publisherList.add(publisherModel);
    return id;
  }

  @Override
  public int updateModel(PublisherModel publisherModel) throws SQLException, ClassNotFoundException {
    PublisherModel existingPublisher = getModelById(publisherModel.getId());
    if (existingPublisher == null) {
      return 0;
    }

    updateEntityFields(publisherModel, existingPublisher);
    try {
      PublisherDAO.getInstance().update(mapToEntity(existingPublisher));
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException("Failed to update publisher: " + e.getMessage());
    }
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    PublisherModel publisherModel = getModelById(id);
    if (publisherModel == null) {
      throw new IllegalArgumentException("Publisher with ID " + id + " does not exist.");
    }
    int deletedRows = PublisherDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      publisherList.remove(publisherModel);
    }
    return deletedRows;
  }

  @Override
  public List<PublisherModel> searchModel(String value, String columns) throws SQLException, ClassNotFoundException {
    List<PublisherModel> results = new ArrayList<>();
    try {
      List<PublisherModel> entities = PublisherDAO.getInstance().search(value, columns);
      for (PublisherModel entity : entities) {
        PublisherModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for publisher: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for publisher: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No publisher found with the specified search criteria.");
    }

    return results;
  }
}
