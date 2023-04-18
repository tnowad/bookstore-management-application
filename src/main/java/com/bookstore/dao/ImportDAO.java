package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.ImportModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImportDAO implements IDAO<ImportModel> {
  private static ImportDAO instance;

  public static ImportDAO getInstance() {
    if (instance == null) {
      instance = new ImportDAO();
    }
    return instance;
  }

  private ImportModel createImportModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    int providerId = rs.getInt("provider_id");
    int employeeId = rs.getInt("employee_id");
    Double totalPrice = rs.getDouble("total_price");
    Date createdAt = rs.getDate("created_at");
    Date updatedAt = rs.getDate("updated_at");

    return new ImportModel(id, providerId, employeeId, totalPrice, createdAt, updatedAt);
  }

  @Override
  public ArrayList<ImportModel> readDatabase() {
    ArrayList<ImportModel> importsList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM imports")) {
      while (rs.next()) {
        ImportModel ImportModel = createImportModelFromResultSet(rs);
        importsList.add(ImportModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return importsList;
  }

  @Override
  public int insert(ImportModel imports) {
    String insertSql = "INSERT INTO imports (provider_id, employee_id, total_price) VALUES (?, ?, ?)";
    Object[] args = { imports.getProviderId(), imports.getEmployeeId(), imports.getTotalPrice() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(ImportModel imports) {
    String updateSql = "UPDATE imports SET provider_id = ?, employee_id = ?, total_price = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
    Object[] args = { imports.getProviderId(), imports.getEmployeeId(), imports.getTotalPrice(), imports.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM imports WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<ImportModel> search(String condition, String[] columnNames) {

    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM imports WHERE CONCAT(id, provider_id, employee_id, total_price, created_at, updated_at) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in imports table
      String column = columnNames[0];
      query = "SELECT * FROM imports WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in imports table
      query = "SELECT id, provider_id, employee_id, total_price, created_at, updated_at FROM imports WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<ImportModel> importsList = new ArrayList<>();
        while (rs.next()) {
          ImportModel importModel = createImportModelFromResultSet(rs);
          importsList.add(importModel);
        }

        if (importsList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }

        return importsList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
