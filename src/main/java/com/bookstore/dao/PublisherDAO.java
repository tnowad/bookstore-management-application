package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.PublisherModel;

public class PublisherDAO implements IDAO<PublisherModel> {
  private static PublisherDAO instance;

  public static PublisherDAO getInstance() {
    if (instance == null) {
      instance = new PublisherDAO();
    }
    return instance;
  }

  private PublisherModel createPublisherModelFromResultSet(ResultSet rs) throws SQLException {
    return new PublisherModel(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("description"));
  }

  @Override
  public ArrayList<PublisherModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<PublisherModel> publisherList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM publishers")) {
      while (rs.next()) {
        PublisherModel publisherModel = createPublisherModelFromResultSet(rs);
        publisherList.add(publisherModel);
      }
    }
    return publisherList;
  }

  @Override
  public int insert(PublisherModel publisher) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO publishers (name, description) VALUES (?, ?)";
    Object[] args = { publisher.getName(), publisher.getDescription() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(PublisherModel publisher) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE publishers SET name = ?, description = ? WHERE id = ?";
    Object[] args = { publisher.getName(), publisher.getDescription(), publisher.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM publishers WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<PublisherModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM publishers WHERE CONCAT(id, name, description) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in publishers table
      String column = columnNames[0];
      query = "SELECT * FROM publishers WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in publishers table
      query = "SELECT id, name, description FROM publishers WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<PublisherModel> publisherList = new ArrayList<>();
        while (rs.next()) {
          PublisherModel publisherModel = createPublisherModelFromResultSet(rs);
          publisherList.add(publisherModel);
        }
        if (publisherList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return publisherList;
      }
    }
  }
}
