package com.bookstore.dao;

import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.interfaces.IDAO;
import com.bookstore.models.UserModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO implements IDAO<UserModel> {

  private static UserDAO instance;

  public static UserDAO getInstance() {
    if (instance == null) {
      instance = new UserDAO();
    }
    return instance;
  }

  private static UserModel createUserModelFromResultSet(ResultSet rs)
    throws SQLException {
    int id = rs.getInt("id");
    String username = rs.getString("username");
    String password = rs.getString("password");
    UserStatus status = UserStatus.valueOf(
      rs.getString("status").toUpperCase()
    );
    String name = rs.getString("name");
    String email = rs.getString("email");
    String phone = rs.getString("phone");
    LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
    LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
    UserRole role = UserRole.valueOf(rs.getString("role").toUpperCase());
    return new UserModel(
      id,
      username,
      password,
      status,
      name,
      email,
      phone,
      createdAt,
      updatedAt,
      role
    );
  }

  @Override
  public ArrayList<UserModel> readDatabase() {
    ArrayList<UserModel> userList = new ArrayList<>();
    try (
      ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM users")
    ) {
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
  public int insert(UserModel user) throws SQLException {
    String insertSql =
      "INSERT INTO users (username, password, status, name, email, phone, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    Object[] args = {
      user.getUsername(),
      user.getPassword(),
      user.getStatus().toString().toUpperCase(),
      user.getName(),
      user.getEmail(),
      user.getPhone(),
      user.getRole().toString().toUpperCase(),
    };
    return DatabaseConnection.executeUpdate(insertSql, args);
  }

  @Override
  public int update(UserModel user) {
    String updateSql =
      "UPDATE users SET username = ?, password = ?, status = ?, name = ?, email = ?, phone = ?, role = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
    Object[] args = {
      user.getUsername(),
      user.getPassword(),
      user.getStatus().toString().toUpperCase(),
      user.getName(),
      user.getEmail(),
      user.getPhone(),
      user.getRole().toString().toUpperCase(),
      user.getId(),
    };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateStatus(String username, String status) {
    String updateSql =
      "UPDATE users SET status = ?, updated_at = CURRENT_TIMESTAMP WHERE username = ?";
    Object[] args = { status, username };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateRole(String username, UserRole role) {
    String updateSql =
      "UPDATE users SET role = ?, updated_at = CURRENT_TIMESTAMP WHERE username = ?";
    Object[] args = { role, username };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String updateStatusSql = "UPDATE users SET status = ? WHERE id = ?";
    Object[] args = { UserStatus.BANNED.toString().toUpperCase(), id };
    try {
      return DatabaseConnection.executeUpdate(updateStatusSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<UserModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException(
          "Search condition cannot be empty or null"
        );
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query =
          "SELECT * FROM users WHERE CONCAT(id, username, password, status, name, email, phone, created_at, updated_at, role) LIKE ?";
      } else if (columnNames.length == 1) {
        // Search specific column in users table
        String column = columnNames[0];
        query = "SELECT * FROM users WHERE " + column + " LIKE ?";
      } else {
        // Search specific columns in users table
        query =
          "SELECT id, username, password, status, name, email, phone, created_at, updated_at, role FROM users WHERE CONCAT(" +
          String.join(", ", columnNames) +
          ") LIKE ?";
      }

      try (
        PreparedStatement pst = DatabaseConnection.getPreparedStatement(
          query,
          "%" + condition + "%"
        )
      ) {
        try (ResultSet rs = pst.executeQuery()) {
          List<UserModel> userList = new ArrayList<>();
          while (rs.next()) {
            UserModel userModel = createUserModelFromResultSet(rs);
            userList.add(userModel);
          }

          if (userList.isEmpty()) {
            throw new SQLException(
              "No records found for the given condition: " + condition
            );
          }

          return userList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public UserModel getUserByUsername(String username) {
    String query = "SELECT * FROM users WHERE username = ?";
    Object[] args = { username };
    try (
      PreparedStatement pst = DatabaseConnection.getPreparedStatement(
        query,
        args
      );
      ResultSet rs = pst.executeQuery()
    ) {
      if (rs.next()) {
        return createUserModelFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public UserModel getUserById(int id) {
    String query = "SELECT * FROM users WHERE id = ?";
    Object[] args = { id };
    try (
      PreparedStatement pst = DatabaseConnection.getPreparedStatement(
        query,
        args
      );
      ResultSet rs = pst.executeQuery()
    ) {
      if (rs.next()) {
        return createUserModelFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
