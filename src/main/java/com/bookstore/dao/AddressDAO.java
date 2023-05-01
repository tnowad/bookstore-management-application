package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.AddressModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressDAO implements IDAO<AddressModel> {

  private static AddressDAO instance;

  public static AddressDAO getInstance() {
    if (instance == null) {
      instance = new AddressDAO();
    }
    return instance;
  }

  private AddressModel createAddressModelFromResultSet(ResultSet rs)
    throws SQLException {
    return new AddressModel(
      rs.getInt("id"),
      rs.getInt("user_id"),
      rs.getString("street"),
      rs.getString("city"),
      rs.getString("state"),
      rs.getString("zip")
    );
  }

  @Override
  public ArrayList<AddressModel> readDatabase() {
    ArrayList<AddressModel> addressList = new ArrayList<>();
    try (
      ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM addresses")
    ) {
      while (rs.next()) {
        AddressModel addressModel = createAddressModelFromResultSet(rs);
        addressList.add(addressModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return addressList;
  }

  @Override
  public int insert(AddressModel address) {
    String insertSql =
      "INSERT INTO addresses (user_id, street, city, state, zip) VALUES (?, ?, ?, ?, ?)";
    Object[] args = {
      address.getUserId(),
      address.getStreet(),
      address.getCity(),
      address.getState(),
      address.getZip(),
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(AddressModel address) {
    String updateSql =
      "UPDATE addresses SET user_id = ?, street = ?, city = ?, state = ?, zip = ? WHERE id = ?";
    Object[] args = {
      address.getUserId(),
      address.getStreet(),
      address.getCity(),
      address.getState(),
      address.getZip(),
      address.getId(),
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
    String deleteSql = "DELETE FROM addresses WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<AddressModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "Search condition cannot be empty or null"
      );
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query =
        "SELECT * FROM addresses WHERE CONCAT(id, user_id, street, city, state, zip) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in addresses table
      String column = columnNames[0];
      query = "SELECT * FROM addresses WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in addresses table
      query =
        "SELECT id, user_id, street, city, state, zip FROM addresses WHERE CONCAT(" +
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
        List<AddressModel> addressList = new ArrayList<>();
        while (rs.next()) {
          AddressModel addressModel = createAddressModelFromResultSet(rs);
          addressList.add(addressModel);
        }
        if (addressList.isEmpty()) {
          throw new SQLException(
            "No addresses found for the given search condition: " + condition
          );
        }
        return addressList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
