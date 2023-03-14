package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bookstore.model.PublisherModel;

public class PublisherDAO implements DAOInterface<PublisherModel> {

  public static PublisherDAO getInstance() {
    return new PublisherDAO();
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
  public List<PublisherModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM publishers";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<PublisherModel> publisherList = new ArrayList<>();
      while (rs.next()) {
        PublisherModel publisherModel = createPublisherModelFromResultSet(rs);
        publisherList.add(publisherModel);
      }
      if (publisherList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return publisherList;
    }
  }

  @Override
  public List<PublisherModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM publishers WHERE " + columnName + " LIKE ?";
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
