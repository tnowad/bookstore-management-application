package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.bookstore.model.*;

public class CustomerDAO implements DAOInterface<CustomerModel> {
  public static CustomerDAO getInstance() {
    return new CustomerDAO();
  }

  @Override
  public ArrayList<CustomerModel> readDatabase() throws SQLException {
    ArrayList<CustomerModel> customerList = new ArrayList<>();
    String sql = "SELECT * FROM `Customer` c, `User` u WHERE c.`UserID` = u.`UserID`";
    try (Connection connection = DatabaseConnect.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        CustomerModel customer = new CustomerModel();
        customer.setUserID(resultSet.getString("User_ID"));
        customer.setPurchaseHistory(resultSet.getDate("Purchase History"));
        customer.setInvoiceID(resultSet.getString("Invoice_ID"));
        customer.setPaymentID(resultSet.getString("Payment_ID"));
        customerList.add(customer);
      }
    } catch (SQLException e) {
      System.err.println("Error occurred while retrieving customers: " + e.getMessage());
      throw e;
    }
    return customerList;
  }

  @Override
  public int delete(String userId) throws SQLException {
    int result = 0;
    try (Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "DELETE FROM `customer` c, `users` u WHERE c.User_ID= u.User_ID and c.User_ID=?")) {
      pst.setString(1, userId);
      result = pst.executeUpdate();
    } catch (SQLException e) {
      throw e;
    }
    return result;
  }

  @Override
  public ArrayList<CustomerModel> selectAll() throws SQLException {
    ArrayList<CustomerModel> customerList = new ArrayList<>();
    String query = "SELECT * FROM User u LEFT JOIN Customer c ON u.User_ID = c.User_ID";
    try (Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet resultSet = pst.executeQuery()) {
      while (resultSet.next()) {
        CustomerModel customer = new CustomerModel();
        customer.setUserID(resultSet.getString("User_ID"));
        customer.setPurchaseHistory(resultSet.getDate("Purchase History"));
        customer.setInvoiceID(resultSet.getString("Invoice_ID"));
        customer.setPaymentID(resultSet.getString("Payment_ID"));
        customerList.add(customer);
      }
    }
    return customerList;
  }

  @Override
  public ArrayList<CustomerModel> searchByCondition(String condition) throws SQLException {
    StringBuilder sb = new StringBuilder(
        "SELECT * FROM User u LEFT JOIN Customer c ON u.User_ID = c.User_ID WHERE ");
    sb.append(condition);
    String query = sb.toString();
    try (Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet resultSet = pst.executeQuery()) {
      ArrayList<CustomerModel> customerList = new ArrayList<>();
      while (resultSet.next()) {
        CustomerModel customer = new CustomerModel();
        customer.setUserID(resultSet.getString("User_ID"));
        customer.setPurchaseHistory(resultSet.getDate("Purchase History"));
        customer.setInvoiceID(resultSet.getString("Invoice_ID"));
        customer.setPaymentID(resultSet.getString("Payment_ID"));
        customerList.add(customer);
      }
      return customerList;
    }
  }

  @Override
  public ArrayList<CustomerModel> searchByCondition(String condition, String columnName) throws SQLException {
    String query = "SELECT * FROM `Customer` c, `User` u WHERE u.User_ID = c.User_ID AND " + columnName + " LIKE ?";
    try (Connection con = DatabaseConnect.getConnection()) {
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, "%" + condition + "%");
      ResultSet resultSet = pst.executeQuery();
      ArrayList<CustomerModel> customerList = new ArrayList<>();
      while (resultSet.next()) {
        CustomerModel customer = new CustomerModel();
        customer.setUserID(resultSet.getString("User_ID"));
        customer.setPurchaseHistory(resultSet.getDate("Purchase History"));
        customer.setInvoiceID(resultSet.getString("Invoice_ID"));
        customer.setPaymentID(resultSet.getString("Payment_ID"));
        customerList.add(customer);
      }
      return customerList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public int insert(CustomerModel cus) throws SQLException {
    Connection con = DatabaseConnect.getConnection();
    try {
      // First, insert the user data into the User table
      String userInsertSql = "INSERT INTO `User` (`Email`) VALUES (?)";
      PreparedStatement userPst = con.prepareStatement(userInsertSql, Statement.RETURN_GENERATED_KEYS);
      userPst.setString(1, cus.getEmail());
      userPst.executeUpdate();

      ResultSet generatedKeys = userPst.getGeneratedKeys();
      int userId;
      if (generatedKeys.next()) {
        userId = generatedKeys.getInt(1);
      } else {
        throw new SQLException("Creating user failed, no ID obtained.");
      }

      // Second, insert the customer data with the foreign key referencing the newly
      // inserted user id
      String customerInsertSql = "INSERT INTO `Customer` (`User_ID`, `Name`, `Address`, `Phone_Number`) VALUES (?, ?, ?, ?)";
      PreparedStatement customerPst = con.prepareStatement(customerInsertSql);
      customerPst.setInt(1, userId);
      customerPst.setString(2, cus.getName());
      customerPst.setString(3, cus.getAddress());
      customerPst.setString(4, cus.getPhoneNumber());

      return customerPst.executeUpdate();
    } catch (SQLException e) {
      throw e;
    } finally {
      con.close();
    }
  }

  @Override
  public int update(CustomerModel e) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

}
