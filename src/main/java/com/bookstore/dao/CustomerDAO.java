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

  private CustomerModel createCustomerModelFromResultSet(ResultSet rs) throws SQLException {
    return new CustomerModel(
        rs.getDate("purchaseHistory"),
        rs.getString("customerId"),
        rs.getString("invoiceId"),
        rs.getString("paymentId"));
  }

  @Override
  public ArrayList<CustomerModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<CustomerModel> customerList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect
        .executeQuery("SELECT * FROM `Customer` c, `User` u WHERE c.`customerID` = u.`userID`")) {
      while (rs.next()) {
        CustomerModel customerModel = createCustomerModelFromResultSet(rs);
        customerList.add(customerModel);
      }
    }
    return customerList;
  }

  @Override
  public int insert(CustomerModel customer) throws SQLException, ClassNotFoundException {
    // Connection conn = null;
    // try {
    //   conn = DatabaseConnect.getConnection();
    //   conn.setAutoCommit(false);
       UserDAO userDAO = new UserDAO();
       int userID = userDAO.insert(new UserModel());
       String insertCustomerQuery = "INSERT INTO `Customer` (`purchaseHistory`, `invoiceId`, `customerID`, `paymentId`) VALUES (?, ?, ?, ?)";
    //   PreparedStatement customerPs = conn.prepareStatement(insertCustomerQuery);
    //   customerPs.setDate(1, customer.getPurchaseHistory());
    //   customerPs.setString(2, customer.getInvoiceID());
    //   customerPs.setString(3, String.valueOf(userID));
    //   customerPs.setString(4, customer.getPaymentID());
    //   int rowsInserted = customerPs.executeUpdate();
    //   conn.commit();
    //   return rowsInserted;

    // } catch (SQLException e) {
    //   // Rollback transaction on error
    //   if (conn != null) {
    //     conn.rollback();
    //   }
    //   throw e;
    // } finally {
    //   // Close resources and connection
    //   try {
    //     if (conn != null) {
    //       conn.close();
    //     }
    //   } catch (SQLException e) {
    //     e.printStackTrace();
    //   }
    // }
    String insertSql = ""
  }

  // Update an existing customer in the database and return number of rows updated
  public int update(CustomerModel customer) throws SQLException {
    try (
        // Get a database connection
        Connection conn = DatabaseConnect.getConnection();
        // Prepare the SQL statement for updating customer data in Customer table
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE `Customer` SET `purchaseHistory` = ?, `invoiceId` = ?, `paymentId` = ? WHERE `customerId` = ?");) {
      // Set parameter values for the prepared statement
      ps.setDate(1, customer.getPurchaseHistory());
      ps.setString(2, customer.getInvoiceID());
      ps.setString(3, customer.getPaymentID());
      ps.setString(4, customer.getCustomerID());
      // Execute the SQL statement and get number of rows updated
      int rowsUpdated = ps.executeUpdate();
      // Update user data in User table using UserDAO
      UserDAO userDAO = new UserDAO();
      UserModel userModel = new UserModel();
      userModel.setID(customer.getCustomerID());
      userDAO.update(userModel);

      conn.commit();
      return rowsUpdated;
    } catch (SQLException e) {
      throw e;
    }
  }

  // Delete a customer and associated user record by user ID and return number of
  // rows deleted
  @Override
  public int delete(String userId) throws SQLException {
    try (
        // Get a database connection
        Connection conn = DatabaseConnect.getConnection();
        // Prepare the SQL statements for deleting customer and user records
        PreparedStatement ps1 = conn.prepareStatement("DELETE FROM `Customer` WHERE `customerId`=?");
        PreparedStatement ps2 = conn.prepareStatement("DELETE FROM `User` WHERE `userId`=?")) {
      // Set parameters for the prepared statements
      ps1.setString(1, userId);
      ps2.setString(1, userId);
      // Execute the SQL statements and get total number of rows deleted
      int rowsDeleted = ps1.executeUpdate() + ps2.executeUpdate();
      // Return the number of rows deleted
      return rowsDeleted;
    } catch (SQLException ex) {
      throw ex;
    }
  }

  // Search for customers based on a given condition and return results as
  // ArrayList
  @Override
  public List<CustomerModel> searchByCondition(String condition) throws SQLException {
    // Build the SQL query with passed condition for searching customer data with
    // left join to User table
    String query = "SELECT *"
        + "FROM User u LEFT JOIN Customer c ON u.user_id = c.user_id "
        + "WHERE " + condition;
    try (
        // Get a database connection
        Connection con = DatabaseConnect.getConnection();
        // Prepare the SQL statement with the built query
        PreparedStatement pst = con.prepareStatement(query);
        // Execute the SQL statement and get result set
        ResultSet resultSet = pst.executeQuery()) {
      // Create an ArrayList of customers to hold the retrieved ones
      ArrayList<CustomerModel> customerList = new ArrayList<>();
      // Loop through result set and retrieve customer data into CustomerModel class
      while (resultSet.next()) {
        CustomerModel customer = new CustomerModel();
        customer.setCustomerID(resultSet.getString("user_id"));
        customer.setPurchaseHistory(resultSet.getDate("purchase_history"));
        customer.setInvoiceID(resultSet.getString("invoice_id"));
        customer.setPaymentID(resultSet.getString("payment_id"));
        customerList.add(customer);
      }
      // Print a message if no records are found for the given search criteria
      if (customerList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      // Return the ArrayList of customers that meet the search criteria
      return customerList;
    }
  }

  // Overloaded method with column name added to restrict search to certain column
  @Override
  public List<CustomerModel> searchByCondition(String condition, String columnName) throws SQLException {
    // Build the SQL query with given condition and column name to search only for
    // customer data
    String query = "SELECT * FROM `Customer` c, `User` u WHERE u.user_id = c.user_id AND " + columnName + " LIKE ?";
    try (
        // Get a database connection
        Connection con = DatabaseConnect.getConnection();
        // Prepare the SQL statement with the built query
        PreparedStatement pst = con.prepareStatement(query);) {
      // Set wildcarded value to the prepared statement
      pst.setString(1, "%" + condition + "%");
      // Execute the SQL statement and get result set
      ResultSet resultSet = pst.executeQuery();
      // Create an ArrayList of customers to hold the retrieved ones
      List<CustomerModel> customerList = new ArrayList<>();
      // Loop through result set and retrieve customer data into CustomerModel class
      while (resultSet.next()) {
        CustomerModel customer = new CustomerModel();
        customer.setCustomerID(resultSet.getString("customerId"));
        customer.setPurchaseHistory(resultSet.getDate("purchaseHistory"));
        customer.setInvoiceID(resultSet.getString("invoiceID"));
        customer.setPaymentID(resultSet.getString("paymentID"));
        customerList.add(customer);
      }
      // Return the ArrayList of customers that meet the search criteria
      return customerList;
    } catch (SQLException e) {
      throw e;
    }
  }

}
