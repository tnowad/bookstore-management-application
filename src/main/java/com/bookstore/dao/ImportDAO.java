package com.bookstore.dao;

import com.bookstore.model.ImportModel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ImportDAO implements DAOInterface<ImportModel> {

  public static ImportDAO getInstance() {
    return new ImportDAO();
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

    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM imports")) {
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
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(ImportModel imports) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE imports SET provider_id = ?, employee_id = ?, total_price = ? WHERE id = ?";
    Object[] args = { imports.getProviderId(), imports.getEmployeeId(), imports.getTotalPrice(), imports.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM imports WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<ImportModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM imports WHERE " + columnName + " LIKE ?";

    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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
