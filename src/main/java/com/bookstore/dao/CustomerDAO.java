package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.bookstore.model.*;

public class CustomerDAO implements DAOInterface<CustomerModel, UserModel> {
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
  public int insert(CustomerModel customer) throws SQLException {
    Connection conn = null;
    try {
      conn = DatabaseConnect.getConnection();
      conn.setAutoCommit(false);
      UserDAO userDAO = new UserDAO();
      int userID = userDAO.insert(new UserModel());
      // Insert customer data into Customer table with foreign key referencing User ID
      String insertCustomerQuery = "INSERT INTO `Customer` (`Purchase`, `Invoice_ID`, `User_ID`, `Payment_ID`) VALUES (?, ?, ?, ?)";
      PreparedStatement customerPs = conn.prepareStatement(insertCustomerQuery);
      customerPs.setDate(1, customer.getPurchaseHistory());
      customerPs.setString(2, customer.getInvoiceID());
      customerPs.setString(3, String.valueOf(userID));
      customerPs.setString(4, customer.getPaymentID());
      int rowsInserted = customerPs.executeUpdate();
      // Commit transaction on success
      conn.commit();
      return rowsInserted;
    } catch (SQLException e) {
      // Rollback transaction on error
      if (conn != null) {
        conn.rollback();
      }
      throw e;
    } finally {
      // Close resources and connection
      if (conn != null) {
        conn.close();
      }
    }
  }

  public int update(CustomerModel customer) throws SQLException {
    try (Connection conn = DatabaseConnect.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE `Customer` SET `Purchase` = ?, `Invoice_ID` = ?, `Payment_ID` = ? WHERE `User_ID` = ?");) {
      // Update customer data in Customer table
      ps.setDate(1, customer.getPurchaseHistory());
      ps.setString(2, customer.getInvoiceID());
      ps.setString(3, customer.getPaymentID());
      ps.setString(4, customer.getUserID());
      ps.executeUpdate();
      // Update user data in User table using UserDAO
      UserDAO userDAO = new UserDAO();
      int userID = userDAO.update(new UserModel());
      return 1; // Return 1 because only one row was updated
    } catch (SQLException ex) {
      throw ex;
    }
  }

}
