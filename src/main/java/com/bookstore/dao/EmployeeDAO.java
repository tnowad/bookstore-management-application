package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.EmployeeModel;
import com.bookstore.model.EmployeeModel.EmployeeType;

public class EmployeeDAO implements DAOInterface<EmployeeModel> {

  public static EmployeeDAO getInstance() {
    return new EmployeeDAO();
  }

  private EmployeeModel createEmployeeModelFromResultSet(ResultSet rs) throws SQLException {
    return new EmployeeModel(
        rs.getInt("user_id"),
        rs.getInt("salary"),
        EmployeeType.valueOf(rs.getString("employee_type").toUpperCase()),
        rs.getString("contact_information"));
  }

  @Override
  public ArrayList<EmployeeModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<EmployeeModel> employeesList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM employees")) {
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
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(EmployeeModel employee) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE employees SET salary = ?, employee_type = ?, contact_information = ? WHERE user_id = ?";
    Object[] args = { employee.getSalary(), employee.getEmployeeType().name(), employee.getContactInformation(),
        employee.getUserId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM employees WHERE user_id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<EmployeeModel> search(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM employees WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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

  public EmployeeModel getEmployeeById(int id) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM employees WHERE user_id = ?";
    Object[] args = { id };
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, args)) {
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          return createEmployeeModelFromResultSet(rs);
        }
      }
    }
    return null;
  }
}