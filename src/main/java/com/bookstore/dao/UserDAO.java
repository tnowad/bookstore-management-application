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
  public ArrayList<UserModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<UserModel> userList = new ArrayList<>();

    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM users")) {
      while (rs.next()) {
        UserModel userModel = createUserModelFromResultSet(rs);
        userList.add(userModel);
      }
    }
    return userList;
  }

  @Override
  public int insert(UserModel user) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO users (username, password, status, name, email, phone, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { user.getUsername(), user.getPassword(), user.getStatus().toString().toUpperCase(),
        user.getName(), user.getEmail(), user.getPhone(), user.getRole().toString().toUpperCase() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(UserModel user) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE users SET username = ?, password = ?, status = ?, name = ?, email = ?, phone = ?, role = ? WHERE id = ?";
    Object[] args = { user.getUsername(), user.getPassword(), user.getStatus().toString().toUpperCase(),
        user.getName(), user.getEmail(), user.getPhone(), user.getRole().toString().toUpperCase(), user.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  public int updateStatus(String username, Status status) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE books SET status = ? WHERE username = ?";
    Object[] args = { status, username };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  public int updateRole(String username, Role role) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE books SET role = ? WHERE username = ?";
    Object[] args = { role, username };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String updateStatusSql = "UPDATE users SET status = ? WHERE id = ?";
    Object[] args = { UserModel.Status.banned.toString().toUpperCase(), id };
    return DatabaseConnect.executeUpdate(updateStatusSql, args);
  }

  @Override
  public List<UserModel> search(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM users WHERE " + columnName + " LIKE ?";

    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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

  public UserModel getUserByUsername(String username) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM users WHERE username = ?";
    Object[] args = { username };
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, args); ResultSet rs = pst.executeQuery()) {
      if (rs.next()) {
        return createUserModelFromResultSet(rs);
      }
    }
    return null;
  }

  public UserModel getUserById(int id) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM users WHERE id = ?";
    Object[] args = { id };
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, args); ResultSet rs = pst.executeQuery()) {
      if (rs.next()) {
        return createUserModelFromResultSet(rs);
      }
    }
    return null;
  }

}
