package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.AuthorModel;

public class AuthorDAO implements DAOInterface<AuthorModel> {

  public static AuthorDAO getInstance() {
    return new AuthorDAO();
  }

  private AuthorModel createAuthorModelFromResultSet(ResultSet rs) throws SQLException {
    return new AuthorModel(
        rs.getString("name"),
        rs.getString("nationality"),
        rs.getString("authorId"),
        rs.getString("ISBN"));
  }

  @Override
  public ArrayList<AuthorModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<AuthorModel> authorList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM `author`")) {
      while (rs.next()) {
        AuthorModel authorModel = createAuthorModelFromResultSet(rs);
        authorList.add(authorModel);
      }
    }
    return authorList;
  }

  @Override
  public int insert(AuthorModel authorModel) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO author (name, nationality, authorID, ISBN) VALUES (?,?,?,?)";
    Object[] args = { authorModel.getName(), authorModel.getNationality(), authorModel.getAuthorID(),
        authorModel.getISBN() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(AuthorModel authorModel) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE author SET name = ?, nationality = ?, ISBN = ? WHERE authorID = ?";
    Object[] args = { authorModel.getName(), authorModel.getNationality(), authorModel.getISBN(),
        authorModel.getAuthorID() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM author WHERE authorID = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<AuthorModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM author";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<AuthorModel> authorList = new ArrayList<>();
      while (rs.next()) {
        AuthorModel authorModel = createAuthorModelFromResultSet(rs);
        authorList.add(authorModel);
      }
      if (authorList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return authorList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<AuthorModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM author WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<AuthorModel> accountList = new ArrayList<>();
        while (rs.next()) {
          AuthorModel accountModel = createAuthorModelFromResultSet(rs);
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
