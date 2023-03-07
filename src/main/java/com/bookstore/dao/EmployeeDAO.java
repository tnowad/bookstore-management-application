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

  @Override
  public ArrayList<EmployeeModel> readDatabase() throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM employees";
    ArrayList<EmployeeModel> employeeList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      while (rs.next()) {
        EmployeeModel employee = createEmployeeFromResultSet(rs);
        employeeList.add(employee);
      }
    }
    return employeeList;
  }

  @Override
  public int insert(EmployeeModel employee) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO employees (employee_id, work_schedule, salary, employee_type, contact_information, good_notes_receipt_id, invoice_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { employee.getEmployeeID(), employee.getWorkSchedule(), employee.getSalary(),
        employee.getEmployeeType(), employee.getContactInformation(), employee.getGoodNotesReceiptId(),
        employee.getInvoiceId() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(EmployeeModel employee) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE employees SET work_schedule=?, salary=?, employee_type=?, contact_information=?, good_notes_receipt_id=?, invoice_id=? WHERE employee_id=?";
    Object[] args = { employee.getWorkSchedule(), employee.getSalary(), employee.getEmployeeType(),
        employee.getContactInformation(), employee.getGoodNotesReceiptId(), employee.getInvoiceId(),
        employee.getEmployeeID() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String employeeId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM employees WHERE employee_id=?";
    Object[] args = { employeeId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<EmployeeModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM employees";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    if (columnName != null && !columnName.isEmpty()) {
      query += " ORDER BY " + columnName;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<EmployeeModel> employeeList = new ArrayList<>();
      while (rs.next()) {
        EmployeeModel employee = createEmployeeFromResultSet(rs);
        employeeList.add(employee);
      }
      if (employeeList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return employeeList;
    }
  }

  @Override
  public List<EmployeeModel> searchByCondition(String condition)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM good_notes_receipt";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<EmployeeModel> employeeList = new ArrayList<>();
      while (rs.next()) {
        EmployeeModel employeeModel = createEmployeeFromResultSet(rs);
        employeeList.add(employeeModel);
      }
      if (employeeList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return employeeList;
    } catch (SQLException e) {
      throw e;
    }
  }

  private EmployeeModel createEmployeeFromResultSet(ResultSet rs) throws SQLException {
    EmployeeModel employee = new EmployeeModel();
    employee.setEmployeeID(rs.getString("employee_id"));
    employee.setWorkSchedule(rs.getDate("work_schedule"));
    employee.setSalary(rs.getDouble("salary"));
    employee.setEmployeeType(rs.getString("employee_type"));
    employee.setContactInformation(rs.getString("contact_information"));
    employee.setGoodNotesReceiptId(rs.getString("good_notes_receipt_id"));
    employee.setInvoiceId(rs.getString("invoice_id"));
    return employee;
  }

}
