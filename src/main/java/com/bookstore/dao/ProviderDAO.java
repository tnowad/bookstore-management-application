package com.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.ProviderModel;

public class ProviderDAO implements DAOInterface<ProviderModel> {
  public static ProviderDAO getInstance() {
    return new ProviderDAO();
  }

  private ProviderModel createProviderModelFromResultSet(ResultSet rs) throws SQLException {
    return new ProviderModel(
        rs.getString("providerID"),
        rs.getString("nameProvider"),
        rs.getString("gnrID"));
  }

  @Override
  public ArrayList<ProviderModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<ProviderModel> providerList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM provider")) {
      while (rs.next()) {
        ProviderModel providerModel = createProviderModelFromResultSet(rs);
        providerList.add(providerModel);
      }
    }
    return providerList;
  }

  @Override
  public int insert(ProviderModel provider) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO provider (providerId, nameProvider, gnrId) VALUES (?, ?, ?)";
    Object[] args = { provider.getProviderId(), provider.getNameProvider(), provider.getGnrId() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(ProviderModel provider) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE provider SET nameProvider = ?, gnrId = ? WHERE providerId = ?";
    Object[] args = { provider.getNameProvider(), provider.getGnrId(), provider.getProviderId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String providerId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM provider WHERE providerId = ?";
    Object[] args = { providerId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<ProviderModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM provider";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + columnName + " LIKE '%" + condition + "%'";
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<ProviderModel> providerList = new ArrayList<>();
      while (rs.next()) {
        ProviderModel providerModel = createProviderModelFromResultSet(rs);
        providerList.add(providerModel);
      }
      if (providerList.isEmpty() && condition != null && !condition.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return providerList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<ProviderModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM provider";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<ProviderModel> providerList = new ArrayList<>();
      while (rs.next()) {
        ProviderModel providerModel = createProviderModelFromResultSet(rs);
        providerList.add(providerModel);
      }
      if (providerList.isEmpty()) {
        throw new SQLException("No records found for the given condition: " + condition);
      }
      return providerList;
    } catch (SQLException e) {
      throw e;
    }
  }

}
