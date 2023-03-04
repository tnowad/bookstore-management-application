package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.model.*;

public class UserDAO implements DAOInterface<UserModel, UserModel> {
  public static UserDAO getInstance() {
    return new UserDAO();
  }

  private UserModel createUserModelFromResultSet(ResultSet rs) throws SQLException {
    return new UserModel(
        rs.getString("User_ID"),
        rs.getString("Account Type"),
        rs.getString("Name"),
        rs.getString("Email"),
        rs.getString("Phone Number"),
        rs.getString("Role"));
  }

  @Override
  public ArrayList<UserModel> readDatabase() throws SQLException {
    ArrayList<UserModel> users = new ArrayList<>();
    try (Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `users`");
        ResultSet rs = pst.executeQuery()) {
      while (rs.next()) {
        UserModel user = createUserModelFromResultSet(rs);
        users.add(user);
      }
    } catch (SQLException e) {
      throw e;
    }
    return users;
  }

  @Override
  public int update(UserModel user) throws SQLException {
    try (Connection conn = DatabaseConnect.getConnection();
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE users SET `Account Type` = ?, Name = ?, Email =?, `Phone Number` = ?, Role = ? WHERE User_ID = ?")) {
      pst.setString(1, user.getAccountType());
      pst.setString(2, user.getName());
      pst.setString(3, user.getEmail());
      pst.setString(4, user.getPhoneNumber());
      pst.setString(5, user.getRole());
      pst.setString(6, user.getID());
      return pst.executeUpdate();
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public ArrayList<UserModel> selectAll() throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
  }

  @Override
  public ArrayList<UserModel> searchByCondition(String condition) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
  }

  @Override
  public ArrayList<UserModel> searchByCondition(String condition, String columnName) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
  }

  @Override
  public int insert(UserModel user) throws SQLException {
    int result = 0;
    try (Connection conn = DatabaseConnect.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
            "INSERT INTO `users` (`User_ID`, `Account Type`, `Name`, `Email`, `Phone Number`, `Role`) VALUES (?,?,?,?,?,?)")) {
      pstmt.setString(1, user.getID());
      pstmt.setString(2, user.getAccountType());
      pstmt.setString(3, user.getName());
      pstmt.setString(4, user.getEmail());
      pstmt.setString(5, user.getPhoneNumber());
      pstmt.setString(6, user.getRole());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      throw e;
    }
    return result;
  }

  @Override
  public int delete(String userId) throws SQLException {
    int result = 0;
    try (Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM `User` WHERE User_ID=?")) {
      pst.setString(1, userId);
      result = pst.executeUpdate();
    } catch (SQLException e) {
      throw e;
    }
    return result;
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
}