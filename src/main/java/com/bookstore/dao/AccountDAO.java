package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.AccountModel;

public class AccountDAO implements DAOInterface<AccountModel> {

  public static AccountDAO getInstance() {
    return new AccountDAO();
  }

  private AccountModel createAccountModelFromResultSet(ResultSet rs) throws SQLException {
    return new AccountModel(
        rs.getString("username"),
        rs.getString("password"),
        rs.getString("status"),
        rs.getString("accountId"));
  }

  @Override
  public ArrayList<AccountModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<AccountModel> accountList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM `user`")) {
      while (rs.next()) {
        AccountModel accountModel = createAccountModelFromResultSet(rs);
        accountList.add(accountModel);
      }
    }
    return accountList;
  }

  @Override
  public int insert(AccountModel account) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO `account` (`accountId`, `username`, `password`, `status`) VALUES (?, ?, ?, ?)";
    Object[] args = { account.getAccountID(), account.getUsername(), account.getPassword(), account.getStatus() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(AccountModel account) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE `account` SET `username`=?, `password`=?, `status`=? WHERE `accountId`=?";
    Object[] args = { account.getUsername(), account.getPassword(), account.getStatus(), account.getAccountID() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String accountId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM `account` WHERE `accountId`=?";
    Object[] args = { accountId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<AccountModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM `account`";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<AccountModel> accountList = new ArrayList<>();
      while (rs.next()) {
        AccountModel accountModel = createAccountModelFromResultSet(rs);
        accountList.add(accountModel);
      }
      if (accountList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return accountList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<AccountModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM `account` WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<AccountModel> accountList = new ArrayList<>();
        while (rs.next()) {
          AccountModel accountModel = createAccountModelFromResultSet(rs);
          accountList.add(accountModel);
        }
        if (accountList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return accountList;
      }
    }
  }
}