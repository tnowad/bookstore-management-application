package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.PublisherModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PublisherDAO implements IDAO<PublisherModel> {

  private static PublisherDAO instance;

  public static PublisherDAO getInstance() {
    if (instance == null) {
      instance = new PublisherDAO();
    }
    return instance;
  }

  private PublisherModel createPublisherModelFromResultSet(ResultSet rs)
    throws SQLException {
    return new PublisherModel(
      rs.getInt("id"),
      rs.getString("name"),
      rs.getString("description")
    );
  }

  @Override
  public ArrayList<PublisherModel> readDatabase() {
    ArrayList<PublisherModel> publisherList = new ArrayList<>();
    try (
      ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM publishers")
    ) {
      while (rs.next()) {
        PublisherModel publisherModel = createPublisherModelFromResultSet(rs);
        publisherList.add(publisherModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return publisherList;
  }

  @Override
  public int insert(PublisherModel publisher) {
    String insertSql =
      "INSERT INTO publishers (name, description) VALUES (?, ?)";
    Object[] args = { publisher.getName(), publisher.getDescription() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PublisherModel publisher) {
    String updateSql =
      "UPDATE publishers SET name = ?, description = ? WHERE id = ?";
    Object[] args = {
      publisher.getName(),
      publisher.getDescription(),
      publisher.getId(),
    };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM publishers WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PublisherModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "Search condition cannot be empty or null"
      );
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query =
        "SELECT * FROM publishers WHERE CONCAT(id, name, description) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in publishers table
      String column = columnNames[0];
      query = "SELECT * FROM publishers WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in publishers table
      query =
        "SELECT id, name, description FROM publishers WHERE CONCAT(" +
        String.join(", ", columnNames) +
        ") LIKE ?";
    }

    try (
      PreparedStatement pst = DatabaseConnection.getPreparedStatement(
        query,
        "%" + condition + "%"
      )
    ) {
      try (ResultSet rs = pst.executeQuery()) {
        List<PublisherModel> publisherList = new ArrayList<>();
        while (rs.next()) {
          PublisherModel publisherModel = createPublisherModelFromResultSet(rs);
          publisherList.add(publisherModel);
        }
        if (publisherList.isEmpty()) {
          throw new SQLException(
            "No records found for the given condition: " + condition
          );
        }
        return publisherList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public PublisherModel getUserByPublisherName(String name) {
    String query = "SELECT * FROM publishers WHERE name = ?";
    Object[] args = { name };
    try (
      PreparedStatement pst = DatabaseConnection.getPreparedStatement(
        query,
        args
      );
      ResultSet rs = pst.executeQuery()
    ) {
      if (rs.next()) {
        return createPublisherModelFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
