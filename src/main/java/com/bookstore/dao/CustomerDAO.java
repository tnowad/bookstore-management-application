package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.*;

public class CustomerDAO implements IDAO<CustomerModel> {

  public static CustomerDAO getInstance() {
    return new CustomerDAO();
  }

  private CustomerModel createCustomerModelFromResultSet(ResultSet rs) throws SQLException {
    return new CustomerModel(
        rs.getString("accountId"),
        rs.getString("address"));
  }

  @Override
  public ArrayList<CustomerModel> readDatabase() throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM customers";
    ArrayList<CustomerModel> customerList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      while (rs.next()) {
        CustomerModel customer = createCustomerModelFromResultSet(rs);
        customerList.add(customer);
      }
    }
    return customerList;
  }

  @Override
  public int insert(CustomerModel customer) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO customers (accountId, address) VALUES (?, ?)";
    Object[] args = { customer.getAccountId(), customer.getAddress() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(CustomerModel customer) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE customers SET address = ? WHERE accountId = ?";
    Object[] args = { customer.getAddress(), customer.getAccountId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int accountId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE c, s FROM customers c JOIN users s ON c.accountId = s.accountId WHERE c.accountId=?";
    Object[] args = { accountId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<CustomerModel> search(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM customers WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<CustomerModel> customerList = new ArrayList<>();
        while (rs.next()) {
          CustomerModel customer = createCustomerModelFromResultSet(rs);
          customerList.add(customer);
        }
        if (customerList.isEmpty()) {
          System.out.println("No records found for the given condition: " +
              condition + " and column: " + columnName);
        }
        return customerList;
      }
    }
  }

}
