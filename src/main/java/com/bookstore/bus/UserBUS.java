package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.UserDAO;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;
import com.bookstore.util.PasswordUtil;

public class UserBUS extends BUSAbstract<UserModel> {
  private final List<UserModel> userList = new ArrayList<>();
  private final UserDAO userDAO = UserDAO.getInstance();

  public UserBUS() throws SQLException, ClassNotFoundException {
    this.userList.addAll(userDAO.readDatabase());
  }

  @Override
  protected ArrayList<UserModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return userDAO.readDatabase();
  }

  public UserModel login(String username, String password) throws SQLException, ClassNotFoundException {
    UserModel userModel = UserDAO.getInstance().getUserByUsername(username);
    if (userModel != null && PasswordUtil.checkPassword(password, userModel.getPassword())) {
      return userModel;
    }
    return null;
  }

  @Override
  protected int getId(UserModel t) {
    return t.getId();
  }

  @Override
  protected UserModel mapToEntity(UserModel from) {
    UserModel to = new UserModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(UserModel from, UserModel to) {
    to.setUsername(from.getUsername());
    to.setStatus(from.getStatus());
    to.setName(from.getName());
    to.setEmail(from.getEmail());
    to.setPhone(from.getPhone());
    to.setRole(from.getRole());
  }

  @Override
  protected boolean checkFilter(UserModel userModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        return userModel.getId() == Integer.parseInt(value);
      case "username":
        return userModel.getUsername().equalsIgnoreCase(value);
      case "status":
        return userModel.getStatus().toString().equalsIgnoreCase(value);
      case "name":
        return userModel.getName().equalsIgnoreCase(value);
      case "email":
        return userModel.getEmail().equalsIgnoreCase(value);
      case "phone":
        return userModel.getPhone().equals(value);
      case "role":
        return userModel.getRole().toString().equalsIgnoreCase(value);
      default:
        return checkAllColumns(userModel, value);
    }
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
  public int insertModel(UserModel userModel) throws SQLException, ClassNotFoundException {
    if (userModel.getUsername() == null || userModel.getUsername().isEmpty() ||
        userModel.getStatus() == null || userModel.getName() == null || userModel.getName().isEmpty() ||
        userModel.getPassword() == null || userModel.getPassword().isEmpty() ||
        userModel.getRole() == null) {
      throw new IllegalArgumentException("Invalid input values. Please check the input and try again.");
    }
    boolean hasPhone = userModel.getPhone() != null && !userModel.getPhone().isEmpty();
    boolean hasEmail = userModel.getEmail() != null && !userModel.getEmail().isEmpty();
    if (!hasPhone && !hasEmail) {
      throw new IllegalArgumentException("At least one of 'phone' or 'email' is required.");
    }
    if (hasEmail && !isValidEmailAddress(userModel.getEmail())) {
      throw new IllegalArgumentException("Invalid email address.");
    }
    userModel.setRole(userModel.getRole() != null ? userModel.getRole() : Role.CUSTOMER);
    return add(userModel);
  }

  @Override
  public int updateModel(UserModel userModel) throws SQLException, ClassNotFoundException {
    return update(userModel);
  }

  private boolean isValidEmailAddress(String email) {
    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    return email.matches(regex);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    UserModel userModel = getModel(id);
    if (userModel == null) {
      return 0;
    }
    int deleted = userDAO.delete(userModel.getId());
    if (deleted > 0) {
      userList.removeIf(user -> user.getId() == id);
    }
    return deleted;
  }

  public List<UserModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public UserModel getUserModel(int id) {
    return getModel(id);
  }

  public List<UserModel> getUserList() {
    return Collections.unmodifiableList(userList);
  }
}
