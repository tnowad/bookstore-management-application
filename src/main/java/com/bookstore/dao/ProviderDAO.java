package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.ProviderModel;

public class ProviderDAO implements IDAO<ProviderModel> {

  public static ProviderDAO getInstance() {
    return new ProviderDAO();
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
  public List<ProviderModel> search(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM providers WHERE " + columnName + " LIKE ?";
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
