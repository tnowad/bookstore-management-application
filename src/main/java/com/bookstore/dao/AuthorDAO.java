package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.AuthorModel;

public class AuthorDAO implements IDAO<AuthorModel> {
  private static AuthorDAO instance;

  public static AuthorDAO getInstance() {
    if (instance == null) {
      instance = new AuthorDAO();
    }
    return instance;
  }

  private AuthorModel createAuthorModelFromResultSet(ResultSet rs) throws SQLException {
    return new AuthorModel(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("description"));
  }

  @Override
  public ArrayList<AuthorModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<AuthorModel> authorList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM authors")) {
      while (rs.next()) {
        AuthorModel authorModel = createAuthorModelFromResultSet(rs);
        authorList.add(authorModel);
      }
    }
    return authorList;
  }

  @Override
  public int insert(AuthorModel author) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO authors (name, description) VALUES (?, ?)";
    Object[] args = { author.getName(), author.getDescription() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(AuthorModel author) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE authors SET name = ?, description = ? WHERE id = ?";
    Object[] args = { author.getName(), author.getDescription(), author.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM authors WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<AuthorModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM authors WHERE CONCAT(id, name, description) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in authors table
      String column = columnNames[0];
      query = "SELECT * FROM authors WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in authors table
      query = "SELECT id, name, description FROM authors WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<AuthorModel> authorList = new ArrayList<>();
        while (rs.next()) {
          AuthorModel authorModel = createAuthorModelFromResultSet(rs);
          authorList.add(authorModel);
        }
        if (authorList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return authorList;
      }
    }
  }
}
