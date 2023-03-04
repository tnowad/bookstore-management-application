package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.bookstore.model.*;

public class CustomerDAO implements DAOInterface<CustomerModel> {
  public static CustomerDAO getInstance() {
    return new CustomerDAO();
  }

  @Override
  public ArrayList<CustomerModel> readDatabase() throws SQLException {
    ArrayList<CustomerModel> customers = new ArrayList<>();
    String sql = "SELECT * FROM `Customer`";
    try (Connection connection = DatabaseConnect.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        CustomerModel customer = new CustomerModel();
        customer.setCustomerID(String.valueOf(resultSet.getInt("User_ID")));
        customer.setPurchaseHistory(resultSet.getDate("Purchase History"));
        customers.add(customer);
      }
    } catch (SQLException e) {
      System.err.println("Error occurred while retrieving customers: " + e.getMessage());
      throw e;
    }
    return customers;
  }

  @Override
  public int delete(int id) throws SQLException {

  }

  @Override
  public ArrayList selectAll() throws SQLException {

  }

  @Override
  public ArrayList searchByCondition(String condition) throws SQLException {

  }

  @Override
  public ArrayList searchByCondition(String condition, String columnName) throws SQLException {

  }

  @Override
  public int insert(CustomerModel e) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'insert'");
  }

  @Override
  public int update(CustomerModel e) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

}
