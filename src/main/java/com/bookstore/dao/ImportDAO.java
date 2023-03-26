package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.ImportModel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    BigDecimal totalPrice = rs.getBigDecimal("total_price");
    Timestamp createdAt = rs.getTimestamp("created_at");
    Timestamp updatedAt = rs.getTimestamp("updated_at");

    return new ImportModel(id, providerId, employeeId, totalPrice, createdAt, updatedAt);
  }

  @Override
  public ArrayList<ImportModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<ImportModel> importsList = new ArrayList<>();

    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM imports")) {
      while (rs.next()) {
        ImportModel ImportModel = createImportModelFromResultSet(rs);
        importsList.add(ImportModel);
      }
    }

    return importsList;
  }

  @Override
  public int insert(ImportModel imports) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO imports (provider_id, employee_id, total_price) VALUES (?, ?, ?)";
    Object[] args = { imports.getProviderId(), imports.getEmployeeId(), imports.getTotalPrice() };
    return DatabaseConnection.executeUpdate(insertSql, args);
  }

  @Override
  public int update(ImportModel imports) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE imports SET provider_id = ?, employee_id = ?, total_price = ? WHERE id = ?";
    Object[] args = { imports.getProviderId(), imports.getEmployeeId(), imports.getTotalPrice(), imports.getId() };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM imports WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnection.executeUpdate(deleteSql, args);
  }

  @Override
  public List<ImportModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {

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
          ImportModel ImportModel = createImportModelFromResultSet(rs);
          importsList.add(ImportModel);
        }

        if (importsList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }

        return importsList;
      }
    }
  }
}
