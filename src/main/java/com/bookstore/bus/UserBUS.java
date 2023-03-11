package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.dao.UserDAO;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.*;
import com.bookstore.util.PasswordUtil;

public class UserBUS {
  private ArrayList<UserModel> userList;

  public UserBUS() throws ClassNotFoundException, SQLException {
    userList = UserDAO.getInstance().readDatabase();
  }

  public void readDatabase() throws ClassNotFoundException, SQLException {
    userList = UserDAO.getInstance().readDatabase();
  }

  public UserModel getUserModel(String username) {
    if (userList != null) {
      for (UserModel userModel : userList) {
        if (userModel.getUsername().equals(username)) {
          return userModel;
        }
      }
    }
    return null;
  }

  public static UserModel login(String username, String password) throws SQLException, ClassNotFoundException {
    UserModel userModel = UserDAO.getInstance().getAccountByUsername(username);
    if (userModel != null && PasswordUtil.checkPassword(password, userModel.getPassword())) {
      return userModel;
    }
    return null;
  }

  private void setUserProperties(UserModel userModel, String username, String password, String email, String phone,
      Role role, Status status, String name) {
    userModel.setUsername(username);
    userModel.setPassword(password);
    userModel.setEmail(email);
    userModel.setPhone(phone);
    userModel.setRole(role);
    userModel.setStatus(status);
    userModel.setName(name);
  }

  public int addUser(String username, String password, String email, String phone, Role role, Status status,
      String name) throws ClassNotFoundException, SQLException {
    UserModel userModel = new UserModel();
    setUserProperties(userModel, username, password, email, phone, role, status, name);
    int added = UserDAO.getInstance().insert(userModel);
    if (added == 1) {
      userList.add(userModel);
    }
    return added;
  }

  public int updateUser(String username, String password, String email, String phone, Role role, Status status,
      String name) throws ClassNotFoundException, SQLException {
    UserModel user = UserDAO.getInstance().getAccountByUsername(username);
    if (user == null) {
      return 0;
    } else {
      setUserProperties(user, username, password, email, phone, role, status, name);
      int updated = UserDAO.getInstance().update(user);
      if (updated == 1) {
        for (int i = 0; i < userList.size(); i++) {
          UserModel u = userList.get(i);
          if (u.getUsername().equals(username)) {
            userList.set(i, user);
          }
        }
      }
      return updated;
    }
  }

  public int deleteUser(String username) throws ClassNotFoundException, SQLException {
    UserModel user = UserDAO.getInstance().getAccountByUsername(username);
    if (user == null) {
      return 0;
    } else {
      user.setStatus(UserModel.Status.DELETED);
      int updated = UserDAO.getInstance().update(user);
      if (updated == 1) {
        return updated;
      }
      return 0;
    }
  }

  public ArrayList<UserModel> searchUser(String value, String columns) {
    ArrayList<UserModel> results = new ArrayList<>();
    for (UserModel userModel : userList) {
      if (checkUserValue(userModel, value, columns)) {
        results.add(userModel);
      }
    }
    return results;
  }

  private boolean checkUserValue(UserModel userModel, String value, String columns) {
    switch (columns.toLowerCase()) {
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
    return String.valueOf(userModel.getId()).equals(value)
        || userModel.getUsername().toLowerCase().contains(value.toLowerCase())
        || userModel.getPassword().toLowerCase().contains(value.toLowerCase())
        || userModel.getStatus().toString().toLowerCase().contains(value.toLowerCase())
        || userModel.getName().toLowerCase().contains(value.toLowerCase())
        || userModel.getEmail().toLowerCase().contains(value.toLowerCase())
        || userModel.getPhone().equals(value)
        || userModel.getCreatedAt().toString().contains(value)
        || userModel.getUpdatedAt().toString().contains(value)
        || userModel.getRole().toString().toLowerCase().contains(value.toLowerCase());
  }

  public ArrayList<UserModel> getUserList() {
    return userList;
  }
}
