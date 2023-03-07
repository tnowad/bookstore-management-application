// This is a java DAO class for performing CRUD(Create, Read, Update, Delete) operations in the database table - 'users'

package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.model.*; // Importing Model class - UserModel

public class UserDAO implements DAOInterface<UserModel> {

  // Method to Get Singleton instance of this DAO class
  public static UserDAO getInstance() {
    return new UserDAO();
  }

  private UserModel createUserModelFromResultSet(ResultSet rs) throws SQLException {
    UserModel userModel = new UserModel();
    userModel.setId(rs.getString("id"));
    userModel.setName(rs.getString("name"));
    userModel.setEmail(rs.getString("email"));
    userModel.setPhoneNumber(rs.getString("phoneNumber"));
    userModel.setRole(rs.getString("role"));
    userModel.setAccountType(rs.getString("accountType"));
    return userModel;
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
    String insertSql = "INSERT INTO users (id, name, email, phone_number, role, account_type) VALUES (?, ?, ?, ?, ?, ?)";
    Object[] args = { userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhoneNumber(),
        userModel.getRole(), userModel.getAccountType() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(UserModel userModel) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE users SET name=?, email=?, phone_number=?, role=?, account_type=? WHERE id=?";
    Object[] args = { userModel.getName(), userModel.getEmail(), userModel.getPhoneNumber(), userModel.getRole(),
        userModel.getAccountType(), userModel.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM users WHERE id=?";
    Object[] args = { id };
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
    String query = "SELECT * FROM ``users";
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