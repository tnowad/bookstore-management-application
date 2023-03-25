package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.UserDAO;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;
import com.bookstore.model.UserModel.Status;
import com.bookstore.util.PasswordUtil;

public class UserBUS implements BUSInterface<UserModel> {
  private final List<UserModel> userList = new ArrayList<>();

  public UserBUS() throws SQLException, ClassNotFoundException {
    this.userList.addAll(UserDAO.getInstance().readDatabase());
  }

  public UserModel login(String username, String password) throws SQLException, ClassNotFoundException {
    UserModel userModel = UserDAO.getInstance().getUserByUsername(username);
    if (userModel != null && PasswordUtil.checkPassword(password, userModel.getPassword())) {
      return userModel;
    }
    return null;
  }

  @Override
  public List<UserModel> getAllModels() {
    return Collections.unmodifiableList(userList);
  }

  @Override
  public UserModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (UserModel userModel : userList) {
      if (userModel.getId() == id) {
        return userModel;
      }
    }

    UserModel userModel = UserDAO.getInstance().getUserById(id);
    if (userModel != null) {
      userList.add(userModel);
    }
    return userModel;
  }

  private UserModel mapToEntity(UserModel from) {
    UserModel to = new UserModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(UserModel from, UserModel to) {
    to.setUsername(from.getUsername());
    to.setPassword(from.getPassword());
    to.setStatus(from.getStatus());
    to.setName(from.getName());
    to.setEmail(from.getEmail());
    to.setPhone(from.getPhone());
    to.setRole(from.getRole());
  }

  private boolean checkFilter(UserModel userModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> userModel.getId() == Integer.parseInt(value);
      case "username" -> userModel.getUsername().equalsIgnoreCase(value);
      case "status" -> userModel.getStatus().toString().equalsIgnoreCase(value);
      case "name" -> userModel.getName().equalsIgnoreCase(value);
      case "email" -> userModel.getEmail().equalsIgnoreCase(value);
      case "phone" -> userModel.getPhone().equals(value);
      case "role" -> userModel.getRole().toString().equalsIgnoreCase(value);
      default -> checkAllColumns(userModel, value);
    };
  }

  private boolean checkAllColumns(UserModel userModel, String value) {
    return userModel.getId() == Integer.parseInt(value)
        || userModel.getUsername().equalsIgnoreCase(value)
        || userModel.getStatus().toString().equalsIgnoreCase(value)
        || userModel.getName().equalsIgnoreCase(value)
        || userModel.getEmail().equalsIgnoreCase(value)
        || userModel.getPhone().equals(value)
        || userModel.getRole().toString().equalsIgnoreCase(value);
  }

  @Override
  public int addModel(UserModel userModel) throws SQLException, ClassNotFoundException {
    if (userModel.getUsername() == null || userModel.getUsername().isEmpty()
        || userModel.getName() == null || userModel.getName().isEmpty()
        || userModel.getPassword() == null || userModel.getPassword().isEmpty()) {
      throw new IllegalArgumentException(
          "Username, name and password cannot be empty. Please check the input and try again.");
    }

    boolean hasPhone = userModel.getPhone() != null && !userModel.getPhone().isEmpty();
    boolean hasEmail = userModel.getEmail() != null && !userModel.getEmail().isEmpty();

    if (!hasPhone && !hasEmail) {
      throw new IllegalArgumentException("At least one of 'phone' or 'email' is required.");
    }
    // if (hasEmail && !isValidEmailAddress(userModel.getEmail())) {
    // throw new IllegalArgumentException("Invalid email address.");
    // }
    userModel.setRole(userModel.getRole() != null ? userModel.getRole() : Role.CUSTOMER);
    userModel.setStatus(userModel.getStatus() != null ? userModel.getStatus() : Status.ACTIVE);

    int id = UserDAO.getInstance().insert(mapToEntity(userModel));
    userModel.setId(id);
    userList.add(userModel);
    return id;
  }

  // private boolean isValidEmailAddress(String email) {
  // return true;
  // }

  @Override
  public int updateModel(UserModel userModel) throws SQLException, ClassNotFoundException {
    UserModel existingUser = getModelById(userModel.getId());
    if (existingUser == null) {
      return 0;
    }

    updateEntityFields(userModel, existingUser);
    try {
      UserDAO.getInstance().update(mapToEntity(existingUser));
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException("Failed to update user: " + e.getMessage());
    }
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    UserModel userModel = getModelById(id);
    if (userModel == null) {
      throw new IllegalArgumentException("User with ID " + id + " does not exist.");
    }
    int deletedRows = UserDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      userList.remove(userModel);
    }
    return deletedRows;
  }

  @Override
  public List<UserModel> searchModel(String value, String columns) throws SQLException, ClassNotFoundException {
    List<UserModel> results = new ArrayList<>();
    try {
      List<UserModel> entities = UserDAO.getInstance().search(value, columns);
      for (UserModel entity : entities) {
        UserModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for users: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for users: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No users found with the specified search criteria.");
    }

    return results;
  }
}
