package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.WarehouseModel;

public class WarehouseDAO implements DAOInterface<WarehouseModel> {
  public static WarehouseDAO getInstance() {
    return new WarehouseDAO();
  }

  // Helper method to create UserModel object from ResultSet
  private WarehouseModel creatWarehouseModelFromResultSet(ResultSet rs) throws SQLException {
    return new WarehouseModel(
        rs.getString("warehouseID"),
        rs.getString("ISBN"));
  }

  @Override
  public ArrayList<WarehouseModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<WarehouseModel> warehouseList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM warehouse")) {
      while (rs.next()) {
        WarehouseModel warehouseModel = creatWarehouseModelFromResultSet(rs);
        warehouseList.add(warehouseModel);
      }
    }
    return warehouseList;
  }

  @Override
  public int insert(WarehouseModel warehouse) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO warehouse (warehouseId, ISBN) VALUES (?, ?)";
    Object[] args = { warehouse.getWarehouseID(), warehouse.getISBN() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(WarehouseModel warehouse) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE warehouse SET ISBN = ? WHERE warehouseId = ?";
    Object[] args = { warehouse.getISBN(), warehouse.getWarehouseID() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String warehouseId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM warehouse WHERE warehouseId = ?";
    Object[] args = { warehouseId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<WarehouseModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM warehouse";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<WarehouseModel> warehouseList = new ArrayList<>();
      while (rs.next()) {
        WarehouseModel warehouseModel = creatWarehouseModelFromResultSet(rs);
        warehouseList.add(warehouseModel);
      }
      if (warehouseList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return warehouseList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<WarehouseModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM warehouse WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<WarehouseModel> warehouseList = new ArrayList<>();
        while (rs.next()) {
          WarehouseModel warehouseModel = creatWarehouseModelFromResultSet(rs);
          warehouseList.add(warehouseModel);
        }
        if (warehouseList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return warehouseList;
      }
    }
  }

}
