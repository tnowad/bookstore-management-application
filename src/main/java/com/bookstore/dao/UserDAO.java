package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.*;

public class UserDAO implements DAOInterface<UserModel> {

  private UserModel createUserModelFromResultSet(ResultSet rs) throws SQLException {
    return new UserModel(
        rs.getInt("id"),
        rs.getString("username"),
        rs.getString("password"),
        rs.getString("status"),
        rs.getString("name"),
        rs.getString("email"),
        rs.getString("phone"),
        rs.getTimestamp("created_at"),
        rs.getTimestamp("updated_at"),
        rs.getString("role"));
  }

  @Override
  public ArrayList<UserModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<UserModel> userList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM `users`")) {
      while (rs.next()) {
        UserModel userModel = createUserModelFromResultSet(rs);
        userList.add(userModel);
      }
    }
    return userList;
  }

  @Override
  public int insert(UserModel user) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO `users` (username, password, status, name, email, phone, role) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { user.getUsername(), user.getPassword(), user.getStatus(), user.getName(), user.getEmail(),
        user.getPhone(), user.getRole() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(UserModel user) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE `users` SET username = ?, password = ?, status = ?, name = ?, " +
        "email = ?, phone = ?, role = ? WHERE id = ?";
    Object[] args = { user.getUsername(), user.getPassword(), user.getStatus(), user.getName(), user.getEmail(),
        user.getPhone(), user.getRole(), user.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String userId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM users WHERE id = ?";
    Object[] args = { userId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<UserModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM `users`";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<UserModel> userList = new ArrayList<>();
      while (rs.next()) {
        UserModel userModel = createUserModelFromResultSet(rs);
        userList.add(userModel);
      }
      if (userList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return userList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<UserModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
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

}
