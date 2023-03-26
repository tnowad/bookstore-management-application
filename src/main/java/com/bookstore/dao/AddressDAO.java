package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.AddressModel;

public class AddressDAO implements IDAO<AddressModel> {
  private static AddressDAO instance;

  public static AddressDAO getInstance() {
    if (instance == null) {
      instance = new AddressDAO();
    }
    return instance;
  }

  private AddressModel createAddressModelFromResultSet(ResultSet rs) throws SQLException {
    return new AddressModel(
        rs.getInt("id"),
        rs.getInt("user_id"),
        rs.getString("street"),
        rs.getString("city"),
        rs.getString("state"),
        rs.getString("zip"));
  }

  @Override
  public ArrayList<AddressModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<AddressModel> addressList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM addresses")) {
      while (rs.next()) {
        AddressModel addressModel = createAddressModelFromResultSet(rs);
        addressList.add(addressModel);
      }
    }
    return addressList;
  }

  @Override
  public int insert(AddressModel address) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO addresses (user_id, street, city, state, zip) VALUES (?, ?, ?, ?, ?)";
    Object[] args = { address.getUserId(), address.getStreet(), address.getCity(),
        address.getState(), address.getZip() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(AddressModel address) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE addresses SET user_id = ?, street = ?, city = ?, state = ?, zip = ? WHERE id = ?";
    Object[] args = { address.getUserId(), address.getStreet(), address.getCity(),
        address.getState(), address.getZip(), address.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM addresses WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<AddressModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM addresses WHERE CONCAT(id, user_id, street, city, state, zip) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in addresses table
      String column = columnNames[0];
      query = "SELECT * FROM addresses WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in addresses table
      query = "SELECT id, user_id, street, city, state, zip FROM addresses WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<AddressModel> addressList = new ArrayList<>();
        while (rs.next()) {
          AddressModel addressModel = createAddressModelFromResultSet(rs);
          addressList.add(addressModel);
        }
        if (addressList.isEmpty()) {
          throw new SQLException("No addresses found for the given search condition: " + condition);
        }
        return addressList;
      }
    }
  }

}
