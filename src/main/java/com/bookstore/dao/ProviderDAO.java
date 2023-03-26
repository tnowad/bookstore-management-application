package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.ProviderModel;

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
  public ArrayList<ProviderModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<ProviderModel> providerList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM providers")) {
      while (rs.next()) {
        ProviderModel providerModel = createProviderModelFromResultSet(rs);
        providerList.add(providerModel);
      }
    }
    return providerList;
  }

  @Override
  public int insert(ProviderModel provider) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO providers (name, description) VALUES (?, ?)";
    Object[] args = { provider.getName(), provider.getDescription() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(ProviderModel provider) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE providers SET name = ?, description = ? WHERE id = ?";
    Object[] args = { provider.getName(), provider.getDescription(), provider.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM providers WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<ProviderModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {
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

    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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
    }
  }

  public ProviderModel getProviderById(int id) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM providers WHERE id = ?";
    Object[] args = { id };
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, args); ResultSet rs = pst.executeQuery()) {
      if (rs.next()) {
        return createProviderModelFromResultSet(rs);
      }
    }
    return null;
  }
}
