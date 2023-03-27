package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.EmployeeModel;
import com.bookstore.model.EmployeeModel.EmployeeType;

public class EmployeeDAO implements IDAO<EmployeeModel> {
  private static EmployeeDAO instance;

  public static EmployeeDAO getInstance() {
    if (instance == null) {
      instance = new EmployeeDAO();
    }
    return instance;
  }

  private EmployeeModel createEmployeeModelFromResultSet(ResultSet rs) throws SQLException {
    return new EmployeeModel(
        rs.getInt("user_id"),
        rs.getInt("salary"),
        EmployeeType.valueOf(rs.getString("employee_type").toLowerCase()),
        rs.getString("contact_information"));
  }

  @Override
  public ArrayList<EmployeeModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<EmployeeModel> employeesList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM employees")) {
      while (rs.next()) {
        EmployeeModel employeeModel = createEmployeeModelFromResultSet(rs);
        employeesList.add(employeeModel);
      }
    }
    return employeesList;
  }

  @Override
  public int insert(EmployeeModel employee) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO employees (user_id, salary, employee_type, contact_information) VALUES (?, ?, ?, ?)";
    Object[] args = { employee.getUserId(), employee.getSalary(), employee.getEmployeeType().name(),
        employee.getContactInformation() };
    return DatabaseConnection.executeUpdate(insertSql, args);
  }

  @Override
  public int update(EmployeeModel employee) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE employees SET salary = ?, employee_type = ?, contact_information = ? WHERE user_id = ?";
    Object[] args = { employee.getSalary(), employee.getEmployeeType().name(), employee.getContactInformation(),
        employee.getUserId() };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  public int updateStatus(int userId, EmployeeType role) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE employees SET employee_type = ? WHERE user_id = ?";
    Object[] args = { role, userId };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  public int updateSalary(int userId, int salary) {
    String updateSql = "UPDATE employees SET salary = ? WHERE user_id = ?";
    Object[] args = { salary, userId };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM employees WHERE user_id = ?";
    Object[] args = { id };
    return DatabaseConnection.executeUpdate(deleteSql, args);
  }

  @Override
  public List<EmployeeModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {

    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM employees WHERE CONCAT(user_id, salary, employee_type, contact_information) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in employees table
      String column = columnNames[0];
      query = "SELECT * FROM employees WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in employees table
      query = "SELECT user_id, salary, employee_type, contact_information FROM employees WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<EmployeeModel> employeesList = new ArrayList<>();
        while (rs.next()) {
          EmployeeModel employeeModel = createEmployeeModelFromResultSet(rs);
          employeesList.add(employeeModel);
        }
        if (employeesList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return employeesList;
      }
    }
  }

  public EmployeeModel getEmployeeById(int id) throws SQLException {
    String query = "SELECT * FROM employees WHERE user_id = ?";
    Object[] args = { id };
    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, args)) {
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          return createEmployeeModelFromResultSet(rs);
        }
      }
    }
    return null;
  }
}