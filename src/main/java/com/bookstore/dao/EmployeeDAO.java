package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.model.EmployeeModel;

public class EmployeeDAO implements DAOInterface<EmployeeModel> {

  @Override
  public int insert(EmployeeModel e) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'insert'");
  }

  @Override
  public int update(EmployeeModel e) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public int delete(String id) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public ArrayList<EmployeeModel> selectAll() throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
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
    // TODO Auto-generated method stub
    ArrayList<EmployeeModel> employees = new ArrayList<>();
    String sql = "SELECT * FROM `Employee`";
    try (Connection connection = DatabaseConnect.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        EmployeeModel employee = new EmployeeModel();
        employee.setEmployeeId(String.valueOf(resultSet.getString("User_ID")));
        employee.setWorkSchedule(resultSet.getDate("Work Schedule"));
        employee.setSalary(resultSet.getDouble("Salary"));
        employee.setEmployeeType(resultSet.getString("Employee Type"));
        employee.setContactInformation(resultSet.getString("Contact Information"));
        employees.add(employee);
      }
    } catch (SQLException e) {
      System.err.println("Error occurred while retrieving customers: " + e.getMessage());
      throw e;
    }
    return employees;
  }

}
