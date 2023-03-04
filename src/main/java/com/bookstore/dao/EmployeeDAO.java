package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.model.EmployeeModel;
import com.bookstore.model.UserModel;

public class EmployeeDAO implements DAOInterface<EmployeeModel, UserModel> {

  @Override
  public int insert(EmployeeModel employee) throws SQLException {

    int result = 0;
    try (Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "INSERT INTO `users` (`user_id`, `work_schedule`, `salary`, `employee_type`, `contact_information`, `good_notes_receipt`, `invoice_id`) VALUES (?,?,?,?,?,?,?)")) {

      pst.setString(1, employee.getUserId());
      pst.setDate(2, employee.getWorkSchedule());
      pst.setDouble(3, employee.getSalary());
      pst.setString(4, employee.getEmployeeType());
      pst.setString(5, employee.getContactInformation());
      pst.setString(6, employee.getGoodNotesReceiptId());
      pst.setString(7, employee.getInvoiceId());

      result = pst.executeUpdate();

    } catch (SQLException e) {
      throw e;
    }
    return result;
  }

  @Override
  public int update(EmployeeModel employee) throws SQLException {

    int result = 0;
    try (Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement(
            "UPDATE employees SET `work_schedule = ? `, `salary = ? `, `employee_type = ? `, `contact_information = ? `, `good_notes_receipt = ?`, `invoice_id = ? ` WHERE employee_code = ?")) {
      pst.setDate(1, employee.getWorkSchedule());
      pst.setDouble(2, employee.getSalary());
      pst.setString(3, employee.getEmployeeType());
      pst.setString(4, employee.getContactInformation());
      pst.setString(5, employee.getInvoiceId());
      pst.setString(6, employee.getGoodNotesReceiptId());
      pst.setString(7, employee.getUserId());

      result = pst.executeUpdate();
    } catch (SQLException e) {
      throw e;
    }
    return result;
  }

  @Override
  public int delete(String userId) throws SQLException {
    // TODO Auto-generated method stub
    int result = 0;
    try (Connection connection = DatabaseConnect.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE employee_code = ?")) {
      statement.setString(1, userId);
      result = statement.executeUpdate();
      if (result == 0) {
        throw new SQLException("No rows were deleted.");
      }
    } catch (SQLException e) {
      System.err.println("Error occurred while deleting employee: " + e.getMessage());
      throw e;
    }
    return result;
  }

  @Override
  public ArrayList<EmployeeModel> searchByCondition(String condition) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
  }

  @Override
  public ArrayList<EmployeeModel> searchByCondition(String condition, String columnName) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
  }

  @Override
  public ArrayList<EmployeeModel> readDatabase() throws SQLException {
    ArrayList<EmployeeModel> employees = new ArrayList<>();
    String sql = "SELECT * FROM `Employee`";
    try (Connection connection = DatabaseConnect.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        EmployeeModel employee = new EmployeeModel();
        employee.setUserId(String.valueOf(resultSet.getString("user_id")));
        employee.setWorkSchedule(resultSet.getDate("work_schedule"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setEmployeeType(resultSet.getString("employee_type"));
        employee.setContactInformation(resultSet.getString("contact_information"));
        employees.add(employee);
      }
    } catch (SQLException e) {
      System.err.println("Error occurred while retrieving customers: " + e.getMessage());
      throw e;
    }
    return employees;
  }
}
