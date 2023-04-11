package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.ProviderModel;

public class ProviderDAO implements IDAO<ProviderModel> {
  private static ProviderDAO instance;

  public static ProviderDAO getInstance() {
    if (instance == null) {
      instance = new ProviderDAO();
    }
    return instance;
  }

  private ProviderModel createProviderModelFromResultSet(ResultSet rs) throws SQLException {
    return new ProviderModel(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("description"));
  }

  @Override
  public ArrayList<ProviderModel> readDatabase() {
    ArrayList<ProviderModel> providerList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM providers")) {
      while (rs.next()) {
        ProviderModel providerModel = createProviderModelFromResultSet(rs);
        providerList.add(providerModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return providerList;
  }

  @Override
  public int insert(ProviderModel provider) {
    String insertSql = "INSERT INTO providers (name, description) VALUES (?, ?)";
    Object[] args = { provider.getName(), provider.getDescription() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(ProviderModel provider) {
    String updateSql = "UPDATE providers SET name = ?, description = ? WHERE id = ?";
    Object[] args = { provider.getName(), provider.getDescription(), provider.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM providers WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<ProviderModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM providers WHERE CONCAT(id, name, description) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in providers table
      String column = columnNames[0];
      query = "SELECT * FROM providers WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in providers table
      query = "SELECT id, name, description FROM providers WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<ProviderModel> providerList = new ArrayList<>();
        while (rs.next()) {
          ProviderModel providerModel = createProviderModelFromResultSet(rs);
          providerList.add(providerModel);
        }
        if (providerList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return providerList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public ProviderModel getProviderById(int id) {
    String query = "SELECT * FROM providers WHERE id = ?";
    Object[] args = { id };
    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, args);
        ResultSet rs = pst.executeQuery()) {
      if (rs.next()) {
        return createProviderModelFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
