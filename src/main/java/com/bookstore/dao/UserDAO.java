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

  // Helper method to create UserModel object from ResultSet
  private UserModel createUserModelFromResultSet(ResultSet rs) throws SQLException {
    return new UserModel(
        rs.getString("User_ID"),
        rs.getString("Account Type"),
        rs.getString("Name"),
        rs.getString("Email"),
        rs.getString("Phone Number"),
        rs.getString("Role"));
  }

  // Read All Users from the table users and returns them as an ArrayList of
  // UserModel objects.
  @Override
  public ArrayList<UserModel> readDatabase() throws SQLException {
    ArrayList<UserModel> users = new ArrayList<>();
    try (
        Connection con = DatabaseConnect.getConnection(); // Established connection with Database
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `user`"); // SQL Statement to execute
        ResultSet rs = pst.executeQuery() // Executing the SQL Statement
    ) {
      while (rs.next()) {
        UserModel user = createUserModelFromResultSet(rs); // Creating UserModel object from ResultSet
        users.add(user); // Adding UserModel object into ArrayList
      }
    } catch (SQLException e) {
      throw e;
    }
    return users; // Returning ArrayList of UserModel objects
  }

  // Update the specific User entry in the table users. Takes UserModel object as
  // a parameter.
  @Override
  public int update(UserModel user) throws SQLException {
    try (
        Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE `user` SET `Account Type` = ?, Name = ?, Email =?, `Phone Number` = ?, Role = ? WHERE User_ID = ?")) {
      pst.setString(1, user.getAccountType()); // Setting the value of parameters in the query
      pst.setString(2, user.getName());
      pst.setString(3, user.getEmail());
      pst.setString(4, user.getPhoneNumber());
      pst.setString(5, user.getRole());
      pst.setString(6, user.getID());
      return pst.executeUpdate(); // Returns number of rows updated
    } catch (SQLException e) {
      throw e;
    }
  }

  // Search the table users based on some conditions passed as a String but this
  // method is not yet implemented.
  @Override
  public ArrayList<UserModel> searchByCondition(String condition) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
  }

  // Search the table users based on specific column name and condition passed as
  // String but this method is not yet implemented.
  @Override
  public ArrayList<UserModel> searchByCondition(String condition, String columnName) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
  }

  // Insert new row into the table users using UserModel object passed as a
  // parameter.
  @Override
  public int insert(UserModel user) throws SQLException {
    int result = 0;
    try (
        Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
        PreparedStatement pstmt = conn.prepareStatement(
            "INSERT INTO `user` (`User_ID`, `Account Type`, `Name`, `Email`, `Phone Number`, `Role`) VALUES (?,?,?,?,?,?)")) {
      pstmt.setString(1, user.getID()); // Setting the value of parameters in the query
      pstmt.setString(2, user.getAccountType());
      pstmt.setString(3, user.getName());
      pstmt.setString(4, user.getEmail());
      pstmt.setString(5, user.getPhoneNumber());
      pstmt.setString(6, user.getRole());
      result = pstmt.executeUpdate(); // Return number of rows inserted
    } catch (SQLException e) {
      throw e;
    }
    return result;
  }

  // Delete specific row from the table users based on User_ID passed as a
  // parameter.
  @Override
  public int delete(String userId) throws SQLException {
    try (
        Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM `user` WHERE userId=?")) {
      pst.setString(1, userId);
      return pst.executeUpdate();
    }
  }
}

// @Override
// public ArrayList<UserModel> selectAll() throws SQLException {
// ArrayList<UserModel> users = new ArrayList<>();
// try (Connection con = DatabaseConnect.getConnection();
// PreparedStatement pst = con.prepareStatement("SELECT * FROM `users`");
// ResultSet rs = pst.executeQuery()) {
// while (rs.next()) {
// UserModel user = createUserModelFromResultSet(rs);
// users.add(user);
// }
// } catch (SQLException e) {
// throw e;
// }
// return users;
// }

// @Override
// public ArrayList<UserModel> searchByCondition(String condition) throws
// SQLException {
// StringBuilder sb = new StringBuilder("SELECT * FROM `users` WHERE ");
// sb.append(condition);
// String query = sb.toString();
// try (Connection con = DatabaseConnect.getConnection()) {
// PreparedStatement pst = con.prepareStatement(query);
// ResultSet rs = pst.executeQuery();
// ArrayList<UserModel> users = new ArrayList<>();
// while (rs.next()) {
// UserModel user = createUserModelFromResultSet(rs);
// users.add(user);
// }
// return users;
// } catch (SQLException e) {
// throw e;
// }
// }

// @Override
// public ArrayList<UserModel> searchByCondition(String condition, String
// columnName) throws SQLException {
// String query = "SELECT * FROM `khachhang` WHERE " + columnName + " LIKE ?";
// try (Connection con = DatabaseConnect.getConnection()) {
// PreparedStatement pst = con.prepareStatement(query);
// pst.setString(1, "%" + condition + "%");
// ResultSet rs = pst.executeQuery();
// ArrayList<UserModel> users = new ArrayList<>();
// while (rs.next()) {
// UserModel user = createUserModelFromResultSet(rs);
// users.add(user);
// }
// return users;
// } catch (SQLException e) {
// throw e;
// }
// }