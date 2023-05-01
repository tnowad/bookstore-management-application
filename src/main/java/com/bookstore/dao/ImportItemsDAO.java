package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.ImportItemsModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImportItemsDAO implements IDAO<ImportItemsModel> {

  private static ImportItemsDAO instance;

  public static ImportItemsDAO getInstance() {
    if (instance == null) {
      instance = new ImportItemsDAO();
    }
    return instance;
  }

  private ImportItemsModel createImportItemsModelFromResultSet(ResultSet rs)
    throws SQLException {
    return new ImportItemsModel(
      rs.getInt("import_id"),
      rs.getInt("quantity"),
      rs.getString("book_isbn"),
      rs.getDouble("price")
    );
  }

  @Override
  public ArrayList<ImportItemsModel> readDatabase() {
    ArrayList<ImportItemsModel> importItemsList = new ArrayList<>();
    try (
      ResultSet rs = DatabaseConnection.executeQuery(
        "SELECT * FROM import_items"
      )
    ) {
      while (rs.next()) {
        ImportItemsModel ImportItemsModel = createImportItemsModelFromResultSet(
          rs
        );
        importItemsList.add(ImportItemsModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return importItemsList;
  }

  @Override
  public int insert(ImportItemsModel importItems) {
    String insertSql =
      "INSERT INTO import_items (import_id, quantity, book_isbn, price) VALUES (?, ?, ?, ?)";
    Object[] args = {
      importItems.getImportId(),
      importItems.getQuantity(),
      importItems.getBookIsbn(),
      importItems.getPrice(),
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(ImportItemsModel importItems) {
    String updateSql =
      "UPDATE import_items SET quantity = ?, book_isbn = ?, price = ? WHERE import_id = ?";
    Object[] args = {
      importItems.getQuantity(),
      importItems.getBookIsbn(),
      importItems.getPrice(),
      importItems.getPrice(),
      importItems.getImportId(),
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
    String deleteSql = "DELETE FROM import_items WHERE import_id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<ImportItemsModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "Search condition cannot be empty or null"
      );
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query =
        "SELECT * FROM import_items WHERE CONCAT(import_id, book_isbn, price, quantity) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in import_items table
      String column = columnNames[0];
      query = "SELECT * FROM import_items WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in import_items table
      query =
        "SELECT import_id, book_isbn, price, quantity FROM import_items WHERE CONCAT(" +
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
        List<ImportItemsModel> importItemsList = new ArrayList<>();
        while (rs.next()) {
          ImportItemsModel ImportItemsModel = createImportItemsModelFromResultSet(
            rs
          );
          importItemsList.add(ImportItemsModel);
        }
        if (importItemsList.isEmpty()) {
          throw new SQLException(
            "No import_items found for the given search condition: " + condition
          );
        }
        return importItemsList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
