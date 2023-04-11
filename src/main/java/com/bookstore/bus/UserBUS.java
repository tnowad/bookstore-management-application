package com.bookstore.bus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.security.auth.login.LoginException;

import com.bookstore.dao.UserDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;
import com.bookstore.model.UserModel.Status;
import com.bookstore.util.PasswordUtil;

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

  public UserModel login(String username, String password) throws LoginException {
    UserModel userModel = UserDAO.getInstance().getUserByUsername(username);
    if (userModel == null) {
      throw new LoginException("User not found");
    }
    if (!PasswordUtil.checkPassword(password, userModel.getPassword())) {
      throw new LoginException("Incorrect password");
    }
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
    to.setRole(from.getRole());
  }

  private boolean checkFilter(UserModel userModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (userModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "username" -> {
          if (userModel.getUsername().equalsIgnoreCase(value)) {
            return true;
          }
        }
        case "status" -> {
          if (userModel.getStatus().toString().equalsIgnoreCase(value)) {
            return true;
          }
        }
        case "name" -> {
          if (userModel.getName().equalsIgnoreCase(value)) {
            return true;
          }
        }
        case "email" -> {
          if (userModel.getEmail().equalsIgnoreCase(value)) {
            return true;
          }
        }
        case "phone" -> {
          if (userModel.getPhone().equals(value)) {
            return true;
          }
        }
        case "role" -> {
          if (userModel.getRole().toString().equalsIgnoreCase(value)) {
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
    return userModel.getId() == Integer.parseInt(value)
        || userModel.getUsername().equalsIgnoreCase(value)
        || userModel.getStatus().toString().equalsIgnoreCase(value)
        || userModel.getName().equalsIgnoreCase(value)
        || userModel.getEmail().equalsIgnoreCase(value)
        || userModel.getPhone().equals(value)
        || userModel.getRole().toString().equalsIgnoreCase(value);
  }

  @Override
  public int addModel(UserModel userModel) {
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
    if (hasEmail && !isValidEmailAddress(userModel.getEmail())) {
      throw new IllegalArgumentException("Invalid email address.");
    }
    userModel.setRole(userModel.getRole() != null ? userModel.getRole() : Role.CUSTOMER);
    userModel.setStatus(userModel.getStatus() != null ? userModel.getStatus() : Status.ACTIVE);

    int id = UserDAO.getInstance().insert(mapToEntity(userModel));
    userModel.setId(id);
    userList.add(userModel);
    return id;
  }

  private boolean isValidEmailAddress(String email) {
    Pattern pattern = Pattern.compile("^\\S+@\\S+\\.\\S+$");

    if (email == null) {
      return false;
    }

    return pattern.matcher(email).matches();
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

  public int updateStatus(String username, Status status) {
    int success = UserDAO.getInstance().updateStatus(username, status);
    if (success == 1) {
      for (UserModel user : userList) {
        if (user.getUsername().equals(username)) {
          user.setStatus(status);
          return 1;
        }
      }
    }
    return 0;
  }

  public int updateRole(String username, Role role) {
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
      throw new IllegalArgumentException("User with ID " + id + " does not exist.");
    }
    int deletedRows = UserDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      userList.remove(userModel);
    }
    return deletedRows;
  }

  @Override
  public List<UserModel> searchModel(String value, String[] columns) {
    List<UserModel> results = new ArrayList<>();
    List<UserModel> entities = UserDAO.getInstance().search(value, columns);
    for (UserModel entity : entities) {
      UserModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    if (results.isEmpty()) {
      throw new IllegalArgumentException("No users found with the specified search criteria.");
    }

    return results;
  }

  public boolean checkForDuplicate(List<String> values, String[] columns) {
    Optional<UserModel> optionalUser = UserBUS.getInstance().getAllModels().stream()
        .filter(user -> {
          for (String value : values) {
            if (Arrays.asList(columns).contains("email") && !value.isEmpty() && user.getEmail().equals(value)) {
              return true;
            }
            if (Arrays.asList(columns).contains("phone") && !value.isEmpty() && user.getPhone().equals(value)) {
              return true;
            }
            if (Arrays.asList(columns).contains("username") && user.getUsername().equals(value)) {
              return true;
            }
          }
          return false;
        })
        .findFirst();
    return optionalUser.isPresent();
  }

}
