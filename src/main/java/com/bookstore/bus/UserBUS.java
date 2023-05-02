package com.bookstore.bus;

import com.bookstore.dao.UserDAO;
import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.UserModel;
import com.bookstore.util.PasswordUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.security.auth.login.LoginException;

public class UserBUS implements IBUS<UserModel> {

  private final List<UserModel> userList = new ArrayList<>();
  private static UserBUS instance;

  public static UserBUS getInstance() {
    if (instance == null) {
      instance = new UserBUS();
    }
    return instance;
  }

  private UserBUS() {
    this.userList.addAll(UserDAO.getInstance().readDatabase());
  }

  public UserModel login(String username, String password)
    throws LoginException {
    UserModel userModel = UserDAO.getInstance().getUserByUsername(username);
    if (userModel == null) {
      throw new LoginException("User not found");
    }
    if (!PasswordUtils.checkPassword(password, userModel.getPassword())) {
      throw new LoginException("Incorrect password");
    }
    if (userModel.getStatus() == UserStatus.INACTIVE) {
      throw new LoginException("User is inactive");
    }
    if (userModel.getStatus() == UserStatus.BANNED) {
      throw new LoginException("User is banned");
    }
    return userModel;
  }

  public UserModel register(
    String username,
    String password,
    String name,
    String email,
    String phone
  ) {
    UserModel userModel = new UserModel();
    userModel.setUsername(username);
    userModel.setPassword(PasswordUtils.hashPassword(password));
    userModel.setName(name);
    userModel.setEmail(email);
    userModel.setPhone(phone);
    userModel.setStatus(UserStatus.ACTIVE);
    userModel.setRole(UserRole.CUSTOMER);
    userModel.setId(addModel(userModel));
    userModel = getModelById(userModel.getId());
    return userModel;
  }

  @Override
  public List<UserModel> getAllModels() {
    return Collections.unmodifiableList(userList);
  }

  @Override
  public UserModel getModelById(int id) {
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

  public UserModel getModelByUsername(String username) {
    for (UserModel userModel : userList) {
      if (userModel.getUsername().equals(username)) {
        return userModel;
      }
    }

    UserModel userModel = UserDAO.getInstance().getUserByUsername(username);
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
    to.setId(from.getId());
    to.setUsername(from.getUsername());
    to.setPassword(from.getPassword());
    to.setStatus(from.getStatus());
    to.setName(from.getName());
    to.setEmail(from.getEmail());
    to.setPhone(from.getPhone());
    to.setCreatedAt(from.getCreatedAt());
    to.setUpdatedAt(from.getUpdatedAt());
    to.setRole(from.getRole());
  }

  private boolean checkFilter(
    UserModel userModel,
    String value,
    String[] columns
  ) {
    value.toLowerCase();
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (userModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "username" -> {
          if (userModel.getUsername().toLowerCase().contains(value)) {
            return true;
          }
        }
        case "status" -> {
          if (userModel.getStatus().toString().equals(value)) {
            return true;
          }
        }
        case "name" -> {
          if (userModel.getName().contains(value)) {
            return true;
          }
        }
        case "email" -> {
          if (userModel.getEmail().contains(value)) {
            return true;
          }
        }
        case "phone" -> {
          if (userModel.getPhone().equals(value)) {
            return true;
          }
        }
        case "role" -> {
          if (userModel.getRole().toString().contains(value)) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(userModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(UserModel userModel, String value) {
    return (
      userModel.getId() == Integer.parseInt(value) ||
      userModel.getUsername().equalsIgnoreCase(value) ||
      userModel.getStatus().toString().equalsIgnoreCase(value) ||
      userModel.getName().equalsIgnoreCase(value) ||
      userModel.getEmail().equalsIgnoreCase(value) ||
      userModel.getPhone().equals(value) ||
      userModel.getRole().toString().equalsIgnoreCase(value)
    );
  }

  @Override
  public int addModel(UserModel userModel) {
    System.out.println(userModel);
    if (
      userModel.getUsername() == null ||
      userModel.getUsername().isEmpty() ||
      userModel.getName() == null ||
      userModel.getName().isEmpty() ||
      userModel.getPassword() == null ||
      userModel.getPassword().isEmpty()
    ) {
      throw new IllegalArgumentException(
        "Username, name and password cannot be empty. Please check the input and try again."
      );
    }

    boolean hasPhone =
      userModel.getPhone() != null && !userModel.getPhone().isEmpty();
    boolean hasEmail =
      userModel.getEmail() != null && !userModel.getEmail().isEmpty();

    if (!hasPhone && !hasEmail) {
      throw new IllegalArgumentException(
        "At least one of 'phone' or 'email' is required."
      );
    }
    if (hasEmail && !isValidEmailAddress(userModel.getEmail())) {
      throw new IllegalArgumentException("Invalid email address.");
    }

    if (hasPhone && !isValidPhoneNumber(userModel.getPhone())) {
      throw new IllegalArgumentException("Invalid number format.");
    }

    userModel.setRole(
      userModel.getRole() != null ? userModel.getRole() : UserRole.CUSTOMER
    );
    userModel.setStatus(
      userModel.getStatus() != null ? userModel.getStatus() : UserStatus.ACTIVE
    );
    try {
      int id = UserDAO.getInstance().insert(mapToEntity(userModel));
      userModel.setId(id);
      userList.add(userModel);
      return id;
    } catch (SQLException e) {
      throw new IllegalArgumentException(
        "Username already exists. Please try again."
      );
    }
  }

  private boolean isValidEmailAddress(String email) {
    // pattern to validate email
    Pattern pattern = Pattern.compile(
      "^[a-zA-Z0-9_+&*-]+(?:\\." +
      "[a-zA-Z0-9_+&*-]+)*@" +
      "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
      "A-Z]{2,7}$",
      Pattern.CASE_INSENSITIVE
    );
    // check if email is valid
    if (email == null) {
      return false;
    }

    return pattern.matcher(email).matches();
  }

  private boolean isValidPhoneNumber(String number) {
    Pattern pattern = Pattern.compile("^\\d{10}$");

    if (number == null) return false;

    return pattern.matcher(number).matches();
  }

  @Override
  public int updateModel(UserModel userModel) {
    int updatedRows = UserDAO.getInstance().update(userModel);
    if (updatedRows > 0) {
      for (int i = 0; i < userList.size(); i++) {
        if (userList.get(i).getId() == userModel.getId()) {
          userList.set(i, userModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  public int updateStatus(String username, String status) {
    int success = UserDAO.getInstance().updateStatus(username, status);
    if (success == 1) {
      for (UserModel user : userList) {
        if (user.getUsername().equals(username)) {
          UserStatus roleEnum = UserStatus.valueOf(status.toUpperCase());
          user.setStatus(roleEnum);
          return 1;
        }
      }
    }
    return 0;
  }

  public int updateRole(String username, UserRole role) {
    int success = UserDAO.getInstance().updateRole(username, role);
    if (success == 1) {
      for (UserModel user : userList) {
        if (user.getUsername().equals(username)) {
          user.setRole(role);
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) {
    UserModel userModel = getModelById(id);
    if (userModel == null) {
      throw new IllegalArgumentException(
        "User with ID " + id + " does not exist."
      );
    }
    int deletedRows = UserDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      userList.remove(userModel);
    }
    return deletedRows;
  }

  @Override
  public List<UserModel> searchModel(String value, String[] columns) {
    value.toLowerCase();
    List<UserModel> results = new ArrayList<>();
    List<UserModel> entities = UserDAO.getInstance().search(value, columns);
    for (UserModel entity : entities) {
      UserModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  public boolean checkForDuplicate(List<String> values, String[] columns) {
    Optional<UserModel> optionalUser = UserBUS
      .getInstance()
      .getAllModels()
      .stream()
      .filter(user -> {
        for (String value : values) {
          if (
            Arrays.asList(columns).contains("email") &&
            !value.isEmpty() &&
            user.getEmail().equals(value)
          ) {
            return true;
          }
          if (
            Arrays.asList(columns).contains("phone") &&
            !value.isEmpty() &&
            user.getPhone().equals(value)
          ) {
            return true;
          }
          if (
            Arrays.asList(columns).contains("username") &&
            user.getUsername().equals(value)
          ) {
            return true;
          }
        }

        return false;
      })
      .findFirst();
    return optionalUser.isPresent();
  }

  @Override
  public void refreshData() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'refreshData'"
    );
  }
}
