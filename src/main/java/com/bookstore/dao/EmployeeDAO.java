package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
    int userId = rs.getInt("user_id");
    int salary = rs.getInt("salary");
    EmployeeType employeeType = EmployeeType.valueOf(rs.getString("employee_type").toUpperCase());
    String information = rs.getString("contact_information");
    return new EmployeeModel(userId, salary, employeeType, information);
  }

  @Override
  public ArrayList<EmployeeModel> readDatabase() {
    ArrayList<EmployeeModel> employeesList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM employees")) {
      while (rs.next()) {
        EmployeeModel employeeModel = createEmployeeModelFromResultSet(rs);
        employeesList.add(employeeModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employeesList;
  }

  @Override
  public int insert(EmployeeModel employee) {
    String insertSql = "INSERT INTO employees (user_id, salary, employee_type, contact_information) VALUES (?, ?, ?, ?)";
    Object[] args = { employee.getUserId(), employee.getSalary(), employee.getEmployeeType().toString().toUpperCase(),
        employee.getContactInformation() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(EmployeeModel employee) {
    String updateSql = "UPDATE employees SET salary = ?, employee_type = ?, contact_information = ? WHERE user_id = ?";
    Object[] args = { employee.getSalary(), employee.getEmployeeType().toString().toUpperCase(),
        employee.getContactInformation(),
        employee.getUserId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateStatus(int userId, EmployeeType role) {
    String updateSql = "UPDATE employees SET employee_type = ? WHERE user_id = ?";
    Object[] args = { userId, role };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateSalary(int userId, int salary) {
    String updateSql = "UPDATE employees SET salary = ? WHERE user_id = ?";
    Object[] args = { salary, userId };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM employees WHERE user_id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<EmployeeModel> search(String condition, String[] columnNames) {

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
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public EmployeeModel getEmployeeById(int id) {
    String query = "SELECT * FROM employees WHERE user_id = ?";
    Object[] args = { id };
    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, args)) {
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          return createEmployeeModelFromResultSet(rs);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}