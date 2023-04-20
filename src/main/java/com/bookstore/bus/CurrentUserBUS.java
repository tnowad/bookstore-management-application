package com.bookstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.CurrentUserDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.CurrentUserModel;

public class CurrentUserBUS implements IBUS<CurrentUserModel> {
  private final List<CurrentUserModel> currentUserList = new ArrayList<>();
  private static CurrentUserBUS instance;

  public static CurrentUserBUS getInstance() {
    if (instance == null) {
      instance = new CurrentUserBUS();
    }
    return instance;
  }

  private CurrentUserBUS() {
    this.currentUserList.addAll(CurrentUserDAO.getInstance().readDatabase());
  }

  @Override
  public List<CurrentUserModel> getAllModels() {
    return Collections.unmodifiableList(currentUserList);
  }

  @Override
  public CurrentUserModel getModelById(int id) {
    for (CurrentUserModel currentUserModel : currentUserList) {
      if (currentUserModel.getCurrentUserId() == id) {
        return currentUserModel;
      }
    }
    return null;
  }

  private CurrentUserModel mapToEntity(CurrentUserModel from) {
    return new CurrentUserModel(from.getCurrentUserId());
  }

  @Override
  public int addModel(CurrentUserModel currentUserModel) {
    int id = CurrentUserDAO.getInstance().insert(mapToEntity(currentUserModel));
    currentUserModel.setCurrentUserId(id);
    currentUserList.add(currentUserModel);
    return id;
  }

  @Override
  public int updateModel(CurrentUserModel currentUserModel) {
    int updatedRows = CurrentUserDAO.getInstance().update(mapToEntity(currentUserModel));
    if (updatedRows > 0) {
      for (int i = 0; i < currentUserList.size(); i++) {
        if (currentUserList.get(i).getCurrentUserId() == currentUserModel.getCurrentUserId()) {
          currentUserList.set(i, currentUserModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    CurrentUserModel currentUserModel = getModelById(id);
    if (currentUserModel == null) {
      throw new IllegalArgumentException("Current user with ID " + id + " does not exist.");
    }
    int deletedRows = CurrentUserDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      currentUserList.remove(currentUserModel);
    }
    return deletedRows;
  }

  @Override
  public List<CurrentUserModel> searchModel(String value, String[] columns) {
    throw new UnsupportedOperationException("Search method is not supported in CurrentUserBUS");
  }
}
