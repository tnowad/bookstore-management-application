// package name and import statements
package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.EmployeeModel;
import com.bookstore.model.UserModel;

// DAO class with DAOInterface implemented for Employee entity CRUD operations
public class EmployeeDAO implements DAOInterface<EmployeeModel> {

  // singleton instance getter to create a single instance in the application
  public static EmployeeDAO getInscance() {
    return new EmployeeDAO();
  }

  // interface's read method overridden to retrieve a list of employees from the
  // database
  @Override
  public ArrayList<EmployeeModel> readDatabase() throws SQLException {
    ArrayList<EmployeeModel> employeeList = new ArrayList<>();

    // SQL query to join Employee and User tables to fetch required data
    String sql = "SELECT * FROM `Employee` e, `User` u WHERE e.`employeeId` = u.`userID`";

    try (Connection connection = DatabaseConnect.getConnection(); // establishing DB connection
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) { // executing the prepared statement

      while (resultSet.next()) {
        EmployeeModel employee = new EmployeeModel();
        employee.setEmployeeID(String.valueOf(resultSet.getString("employeeID")));
        employee.setWorkSchedule(resultSet.getDate("workSchedule"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setEmployeeType(resultSet.getString("employeeType"));
        employee.setContactInformation(resultSet.getString("contactInformation"));
        employeeList.add(employee); // adding each retrieved employee model into the List
      }
      return employeeList; // returning final retrieved employee models list

    } catch (SQLException e) {
      System.err.println("Error occurred while retrieving employees: " + e.getMessage());
      throw e;
    }
  }

  // insert method to add an employee object into the database using transaction
  // management technique
  public int insert(EmployeeModel employee) throws SQLException {
    Connection conn = null;
    try {
      conn = DatabaseConnect.getConnection();
      conn.setAutoCommit(false);

      // Insert user data into User table.
      UserDAO userDAO = new UserDAO();
      int userID = userDAO.insert(new UserModel()); // inserting user to get the generated id

      // Insert employee data into Employee table, with foreign key referencing UserID
      // table
      String insertEmployeeQuery = "INSERT INTO `Employee` (`employeeId`, `contactInformation`, `salary`, `employeeType`, `workSchedule`) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement employeePS = conn.prepareStatement(insertEmployeeQuery);
      employeePS.setString(1, String.valueOf(userID));
      employeePS.setString(2, employee.getContactInformation());
      employeePS.setDouble(3, employee.getSalary());
      employeePS.setString(4, employee.getEmployeeType());
      employeePS.setDate(5, employee.getWorkSchedule());
      int rowsInserted = employeePS.executeUpdate();

      // commit if everything is okay
      conn.commit();
      return rowsInserted;

    } catch (SQLException e) {
      // rollback if any error occurs
      if (conn != null) {
        conn.rollback();
      }
      throw e;
    } finally {
      // close all resources
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  // interface's update CRUD method implementation by updating an existing
  // employee's details
  @Override
  public int update(EmployeeModel employee) throws SQLException {
    try (Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "UPDATE employees SET `workSchedule = ? `, `salary = ? `, `employeeType = ? `, `contactInformation = ? `, `goodnotesreceiptId = ?`, `invoiceId = ? ` WHERE `employeeId` = ?")) {
      pst.setDate(1, employee.getWorkSchedule());
      pst.setDouble(2, employee.getSalary());
      pst.setString(3, employee.getEmployeeType());
      pst.setString(4, employee.getContactInformation());
      pst.setString(5, employee.getInvoiceId());
      pst.setString(6, employee.getGoodNotesReceiptId());
      pst.setString(7, employee.getEmployeeID());
      int rowsUpdated = pst.executeUpdate();

      UserDAO userDAO = new UserDAO();
      UserModel userModel = new UserModel();
      userModel.setID(employee.getEmployeeID());
      userDAO.update(new UserModel());

      return rowsUpdated;
    } catch (SQLException e) {
      throw e;
    }
  }

  // interface's delete CRUD method implementation
  @Override
  public int delete(String userId) throws SQLException {
    try (Connection conn = DatabaseConnect.getConnection(); // getting the DB connection
        PreparedStatement psEmp = conn.prepareStatement("DELETE FROM `Employee` WHERE `employeeId`=?"); // employee
                                                                                                        // record
        // deletion
        // statement
        PreparedStatement psUser = conn.prepareStatement("DELETE FROM `User` WHERE `userId`=?")) { // corresponding
                                                                                                   // user record
                                                                                                   // deletion
                                                                                                   // statement
      // Deleting employee data from Employee table
      psEmp.setString(1, userId);
      int rowsDeleted = psEmp.executeUpdate();
      // Deleting user data from User table
      psUser.setString(1, userId);
      rowsDeleted += psUser.executeUpdate();
      return rowsDeleted;
    } catch (SQLException ex) {
      throw ex;
    }
  }

  // interface's searchByCondition CRUD method implementation with single search
  // parameter
  @Override
  public List<EmployeeModel> searchByCondition(String condition) throws SQLException {
    try (Connection con = DatabaseConnect.getConnection(); // getting DB connection
        PreparedStatement pst = con.prepareStatement(
            "SELECT * FROM `Employee` WHERE " + condition); // specifying where clause
        ResultSet rs = pst.executeQuery()) { // executing the query with prepared statement
      List<EmployeeModel> employeeList = new ArrayList<>(); // creating main list
      while (rs.next()) { // iterating over retrieved records and forming Employee Models
        EmployeeModel employee = new EmployeeModel();
        employee.setEmployeeID(rs.getString("user_id"));
        employee.setWorkSchedule(rs.getDate("work_schedule"));
        employee.setSalary(rs.getDouble("salary"));
        employee.setEmployeeType(rs.getString("employee_type"));
        employee.setContactInformation(rs.getString("contact_information"));
        employee.setGoodNotesReceiptId(rs.getString("good_notes_receipt_id"));
        employee.setInvoiceId(rs.getString("invoice_id"));
        employeeList.add(employee); // adding the current employee model into the List
      }
      return employeeList; // returning the final employee list

    } catch (SQLException e) {
      throw e;
    }
  }

  // interface's searchByCondition CRUD method implementation with multiple
  // parameters i.e. a filter column name and a search term
  @Override
  public List<EmployeeModel> searchByCondition(String condition, String columnName) throws SQLException {
    String query = "SELECT * FROM `Employee` e, `User` u WHERE e.employeeId = u.userId AND " + columnName + " LIKE ?";
    try (Connection con = DatabaseConnect.getConnection()) {
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, "%" + condition + "%");
      ResultSet resultSet = pst.executeQuery();
      List<EmployeeModel> employeeList = new ArrayList<>();
      while (resultSet.next()) {
        EmployeeModel employee = new EmployeeModel();
        employee.setEmployeeID(resultSet.getString("employeeId"));
        employee.setWorkSchedule(resultSet.getDate("workSchedule"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setEmployeeType(resultSet.getString("employeeType"));
        employee.setContactInformation(resultSet.getString("contactInformation"));
        employee.setGoodNotesReceiptId(resultSet.getString("goodnotesreceiptId"));
        employee.setInvoiceId(resultSet.getString("invoiceId"));
        employeeList.add(employee); // adding the specific employee model into the List
      }
      return employeeList; // returning the filtered records list based on search term
    } catch (SQLException e) {
      throw e;
    }
  }

}
