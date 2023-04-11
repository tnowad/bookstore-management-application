package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.AuthorModel;

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
  public ArrayList<AuthorModel> readDatabase() {
    ArrayList<AuthorModel> authorList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM authors")) {
      while (rs.next()) {
        AuthorModel authorModel = createAuthorModelFromResultSet(rs);
        authorList.add(authorModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return authorList;
  }

  @Override
  public int insert(AuthorModel author) {
    String insertSql = "INSERT INTO authors (name, description) VALUES (?, ?)";
    Object[] args = { author.getName(), author.getDescription() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(AuthorModel author) {
    String updateSql = "UPDATE authors SET name = ?, description = ? WHERE id = ?";
    Object[] args = { author.getName(), author.getDescription(), author.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM authors WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<AuthorModel> search(String condition, String[] columnNames) {
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

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
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
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
