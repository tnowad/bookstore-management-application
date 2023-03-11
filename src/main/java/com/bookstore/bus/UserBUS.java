package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.UserDAO;
import com.bookstore.model.UserModel;
import com.bookstore.util.PasswordUtil;

public class UserBUS extends BUSAbstract<UserModel> {

  public UserBUS() throws ClassNotFoundException, SQLException {
    super();
  }

  @Override
  protected ArrayList<UserModel> readDatabase() throws ClassNotFoundException, SQLException {
    return UserDAO.getInstance().readDatabase();
  }

  @Override
  protected int getId(UserModel userModel) {
    return userModel.getId();
  }

  protected UserModel createModel(UserModel userModel) {
    String username = userModel.getUsername();
    String password = userModel.getPassword();
    String email = userModel.getEmail();
    String phone = userModel.getPhone();
    UserModel.Role role = userModel.getRole();
    UserModel.Status status = userModel.getStatus();
    String name = userModel.getName();
    return new UserModel(-1, username, password, status, name, email, phone, null, null, role);
  }

  @Override
  protected int insert(UserModel userModel) throws ClassNotFoundException, SQLException {
    return UserDAO.getInstance().insert(userModel);
  }

  @Override
  protected void copyProperties(UserModel currentUserModel, UserModel newUserModel) {
    currentUserModel.setUsername(newUserModel.getUsername());
    currentUserModel.setPassword(newUserModel.getPassword());
    currentUserModel.setEmail(newUserModel.getEmail());
    currentUserModel.setPhone(newUserModel.getPhone());
    currentUserModel.setRole(newUserModel.getRole());
    currentUserModel.setStatus(newUserModel.getStatus());
    currentUserModel.setName(newUserModel.getName());
  }

  @Override
  protected int updateModel(UserModel userModel) throws ClassNotFoundException, SQLException {
    return UserDAO.getInstance().update(userModel);
  }

  @Override
  protected int deleteModel(String id) throws ClassNotFoundException, SQLException {
    int deletedRows = UserDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      UserModel userModel = new UserModel(Integer.parseInt(id), "", "", null, "", "", "", null, null, null);
      return UserDAO.getInstance().update(userModel);
    } else {
      return 0;
    }
  }

  @Override
  protected boolean checkValue(UserModel userModel, String value, String column) {
    switch (column.toLowerCase()) {
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
        return false;
    }
  }

  public UserModel login(String username, String password) throws SQLException, ClassNotFoundException {
    UserModel userModel = UserDAO.getInstance().getUserByUsername(username);
    if (userModel != null && PasswordUtil.checkPassword(password, userModel.getPassword())) {
      return userModel;
    }
    return null;
  }

  public List<UserModel> getUserList() throws ClassNotFoundException, SQLException {
    return Collections.unmodifiableList(readDatabase());
  }

}
