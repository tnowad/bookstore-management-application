// Import required packages and classes
package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.*;

public class CustomerDAO implements DAOInterface<CustomerModel> {

  public static CustomerDAO getInstance() {
    return new CustomerDAO();
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

  public int insert(CustomerModel customer) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO customers (customer_id, purchase_history, invoice_id, payment_id) VALUES (?, ?, ?, ?)";
    Object[] args = { customer.getCustomerId(), customer.getPurchaseHistory(), customer.getInvoiceId(),
        customer.getPaymentId() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  public int update(CustomerModel customer) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE customers SET purchase_history=?, invoice_id=?, payment_id=? WHERE customer_id=?";
    Object[] args = { customer.getPurchaseHistory(), customer.getInvoiceId(), customer.getPaymentId(),
        customer.getCustomerId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  public int delete(String customerId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM customers WHERE customer_id=?";
    Object[] args = { customerId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<CustomerModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM customers";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<CustomerModel> customerList = new ArrayList<>();
      while (rs.next()) {
        CustomerModel customer = createCustomerModelFromResultSet(rs);
        customerList.add(customer);
      }
      if (customerList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return customerList;
    }
  }

  public List<CustomerModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM customers";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    if (columnName != null && !columnName.isEmpty()) {
      query += " ORDER BY " + columnName;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
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

  private CustomerModel createCustomerModelFromResultSet(ResultSet rs) throws SQLException {
    CustomerModel customer = new CustomerModel();
    customer.setCustomerId(rs.getString("customer_id"));
    customer.setPurchaseHistory(rs.getDate("purchase_history"));
    customer.setInvoiceId(rs.getString("invoice_id"));
    customer.setPaymentId(rs.getString("payment_id"));
    return customer;
  }

}
