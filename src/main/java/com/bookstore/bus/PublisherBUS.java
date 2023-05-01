package com.bookstore.bus;

import com.bookstore.dao.PublisherDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.PublisherModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PublisherBUS implements IBUS<PublisherModel> {

  private final List<PublisherModel> publisherList = new ArrayList<>();
  private static PublisherBUS instance;

  public static PublisherBUS getInstance() {
    if (instance == null) {
      instance = new PublisherBUS();
    }
    return instance;
  }

  private PublisherBUS() {
    this.publisherList.addAll(PublisherDAO.getInstance().readDatabase());
  }

  @Override
  public List<PublisherModel> getAllModels() {
    return Collections.unmodifiableList(publisherList);
  }

  @Override
  public PublisherModel getModelById(int id) {
    for (PublisherModel publisherModel : publisherList) {
      if (publisherModel.getId() == id) {
        return publisherModel;
      }
    }
    return null;
  }

  public PublisherModel getModelByPublisherName(String name) {
    for (PublisherModel publisherModel : publisherList) {
      if (publisherModel.getName().equals(name)) {
        return publisherModel;
      }
    }

    PublisherModel publisherModel = PublisherDAO
      .getInstance()
      .getUserByPublisherName(name);
    if (publisherModel != null) {
      publisherList.add(publisherModel);
    }
    return publisherModel;
  }

  private PublisherModel mapToEntity(PublisherModel from) {
    PublisherModel to = new PublisherModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PublisherModel from, PublisherModel to) {
    to.setId(from.getId());
    to.setName(from.getName());
    to.setDescription(from.getDescription());
  }

  private boolean checkFilter(
    PublisherModel publisherModel,
    String value,
    String[] columns
  ) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (publisherModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "name" -> {
          if (
            publisherModel.getName().toLowerCase().contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        case "description" -> {
          if (
            publisherModel
              .getDescription()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(publisherModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(PublisherModel publisherModel, String value) {
    return (
      publisherModel.getId() == Integer.parseInt(value) ||
      publisherModel.getName().toLowerCase().contains(value.toLowerCase()) ||
      publisherModel
        .getDescription()
        .toLowerCase()
        .contains(value.toLowerCase())
    );
  }

  @Override
  public int addModel(PublisherModel publisherModel) {
    if (
      publisherModel.getName() == null || publisherModel.getName().isEmpty()
    ) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    if (
      publisherModel.getDescription() == null ||
      publisherModel.getDescription().isEmpty()
    ) {
      throw new IllegalArgumentException(
        "Description cannot be null or empty!"
      );
    }

    int id = PublisherDAO.getInstance().insert(mapToEntity(publisherModel));
    publisherModel.setId(id);
    publisherList.add(publisherModel);
    return id;
  }

  @Override
  public int updateModel(PublisherModel publisherModel) {
    int updatedRows = PublisherDAO.getInstance().update(publisherModel);
    if (updatedRows > 0) {
      for (int i = 0; i < publisherList.size(); i++) {
        if (publisherList.get(i).getId() == publisherModel.getId()) {
          publisherList.set(i, publisherModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    PublisherModel publisherModel = getModelById(id);
    if (publisherModel == null) {
      throw new IllegalArgumentException(
        "Publisher with ID " + id + " does not exist."
      );
    }
    int deletedRows = PublisherDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      publisherList.remove(publisherModel);
    }
    return deletedRows;
  }

  @Override
  public List<PublisherModel> searchModel(String value, String[] columns) {
    List<PublisherModel> results = new ArrayList<>();
    List<PublisherModel> entities = PublisherDAO
      .getInstance()
      .search(value, columns);
    for (PublisherModel entity : entities) {
      PublisherModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }
}
