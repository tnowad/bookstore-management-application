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
        rs.getString("location"),
        rs.getString("name"),
        rs.getString("ISBN"),
        rs.getString("publisherID"));
  }

  @Override
  public ArrayList<PublisherModel> readDatabase() throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM Publisher";
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      ArrayList<PublisherModel> publisherList = new ArrayList<>();
      while (rs.next()) {
        PublisherModel publisherModel = createPublisherModelFromResultSet(rs);
        publisherList.add(publisherModel);
      }
      return publisherList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public int insert(PublisherModel publisher) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO Publisher (publisherId, location, name, ISBN) VALUES (?, ?, ?, ?)";
    Object[] args = { publisher.getPublisherId(), publisher.getLocation(), publisher.getName(),
        publisher.getISBN() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(PublisherModel publisher) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE Publisher SET location = ?, name = ? WHERE publisherId = ?";
    Object[] args = { publisher.getLocation(), publisher.getName(), publisher.getPublisherId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String publisherId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM Publisher WHERE publisherId = ?";
    Object[] args = { publisherId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  public List<PublisherModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM publisher";
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
        throw new SQLException("No records found for the given condition: " + condition);
      }
      return publisherList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<PublisherModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM Publisher WHERE " + columnName + " LIKE ?";
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
