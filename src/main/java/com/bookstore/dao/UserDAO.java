package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;
import com.bookstore.model.UserModel.Status;

public class UserDAO implements IDAO<UserModel> {
  private static UserDAO instance;

  public static UserDAO getInstance() {
    if (instance == null) {
      instance = new UserDAO();
    }
    return instance;
  }

  private static UserModel createUserModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String username = rs.getString("username");
    String password = rs.getString("password");
    Status status = Status.valueOf(rs.getString("status").toUpperCase());
    String name = rs.getString("name");
    String email = rs.getString("email");
    String phone = rs.getString("phone");
    Timestamp createdAt = rs.getTimestamp("created_at");
    Timestamp updatedAt = rs.getTimestamp("updated_at");
    Role role = Role.valueOf(rs.getString("role").toUpperCase());
    return new UserModel(id, username, password, status, name, email, phone, createdAt, updatedAt, role);
  }

  @Override
  public ArrayList<UserModel> readDatabase() {
    ArrayList<UserModel> userList = new ArrayList<>();

    ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM users");
    try {
      while (rs.next()) {
        UserModel userModel = createUserModelFromResultSet(rs);
        userList.add(userModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return userList;
  }

  @Override
  public int insert(UserModel user) {
    String insertSql = "INSERT INTO users (username, password, status, name, email, phone, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { user.getUsername(), user.getPassword(), user.getStatus().toString().toUpperCase(),
        user.getName(), user.getEmail(), user.getPhone(), user.getRole().toString().toUpperCase() };
    return DatabaseConnection.executeUpdate(insertSql, args);
  }

  @Override
  public int update(UserModel user) {
    String updateSql = "UPDATE users SET username = ?, password = ?, status = ?, name = ?, email = ?, phone = ?, role = ? WHERE id = ?";
    Object[] args = { user.getUsername(), user.getPassword(), user.getStatus().toString().toUpperCase(),
        user.getName(), user.getEmail(), user.getPhone(), user.getRole().toString().toUpperCase(), user.getId() };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  public int updateStatus(String username, Status status) {
    String updateSql = "UPDATE books SET status = ? WHERE username = ?";
    Object[] args = { status, username };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  public int updateRole(String username, Role role) throws ClassNotFoundException, SQLException {
    String updateSql = "UPDATE books SET role = ? WHERE username = ?";
    Object[] args = { role, username };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) {
    String updateStatusSql = "UPDATE users SET status = ? WHERE id = ?";
    Object[] args = { UserModel.Status.BANNED.toString().toUpperCase(), id };
    return DatabaseConnection.executeUpdate(updateStatusSql, args);
  }

  @Override
  public List<UserModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {

    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM users WHERE CONCAT(id, username, password, status, name, email, phone, created_at, updated_at, role) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in users table
      String column = columnNames[0];
      query = "SELECT * FROM users WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in users table
      query = "SELECT id, username, password, status, name, email, phone, created_at, updated_at, role FROM users WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<UserModel> userList = new ArrayList<>();
        while (rs.next()) {
          UserModel userModel = createUserModelFromResultSet(rs);
          userList.add(userModel);
        }

        if (userList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }

        return userList;
      }
    }
  }

  public UserModel getUserByUsername(String username) {
    String query = "SELECT * FROM users WHERE username = ?";
    Object[] args = { username };
    ResultSet resultSet = DatabaseConnection.executeQuery(query, args);
    try {
      if (resultSet.next()) {
        return createUserModelFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public UserModel getUserById(int id) {
    String query = "SELECT * FROM users WHERE id = ?";
    Object[] args = { id };

    ResultSet resultSet = DatabaseConnection.executeQuery(query, args);

    try {
      if (resultSet.next()) {
        return createUserModelFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

}
