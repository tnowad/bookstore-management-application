package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.model.*;

public class UserDAO implements DAOInterface<UserModel> {

  public static UserDAO getInstance() {
    return new UserDAO();
  }

  private UserModel createUserModelFromResultSet(ResultSet rs) throws SQLException {
    return new UserModel(
        rs.getString("accountId"),
        rs.getString("firstName"),
        rs.getString("lastName"),
        rs.getString("email"),
        rs.getString("phone"),
        rs.getString("role"),
        rs.getDate("createdAt"),
        rs.getDate("updatedAt"));
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
  public int insert(UserModel userModel) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO users (accountId, firstName, lastName, email, phone, role, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { userModel.getAccountId(), userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(),
        userModel.getPhone(), userModel.getRole(), userModel.getCreatedAt(), userModel.getUpdatedAt() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(UserModel userModel) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE users SET firstName = ?, lastName = ?, email = ?, phone = ?, role = ?, updatedAt = ? WHERE accountId = ?";
    Object[] args = { userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getPhone(),
        userModel.getRole(), userModel.getUpdatedAt(), userModel.getAccountId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String accountId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM users WHERE accountId=?";
    Object[] args = { accountId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public ArrayList<UserModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM users";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      ArrayList<UserModel> userList = new ArrayList<>();
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
  public ArrayList<UserModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM users";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + columnName + " LIKE ?";
    }
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        ArrayList<UserModel> userList = new ArrayList<>();
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