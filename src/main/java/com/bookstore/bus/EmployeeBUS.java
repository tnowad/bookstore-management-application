package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.EmployeeModel;
import com.bookstore.model.EmployeeModel.EmployeeType;

public class EmployeeBUS implements IBUS<EmployeeModel> {

  private final List<EmployeeModel> employeeList = new ArrayList<>();
  private static EmployeeBUS instance;

  public static EmployeeBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new EmployeeBUS();
    }
    return instance;
  }

  private EmployeeBUS() throws SQLException, ClassNotFoundException {
    this.employeeList.addAll(EmployeeDAO.getInstance().readDatabase());
  }

  @Override
  public List<EmployeeModel> getAllModels() {
    return Collections.unmodifiableList(employeeList);
  }

  @Override
  public EmployeeModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (EmployeeModel employeeModel : employeeList) {
      if (employeeModel.getUserId() == id) {
        return employeeModel;
      }
    }
    return null;
  }

  private EmployeeModel mapToEntity(EmployeeModel from) {
    EmployeeModel to = new EmployeeModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(EmployeeModel from, EmployeeModel to) {
    to.setUserId(from.getUserId());
    to.setSalary(from.getSalary());
    to.setEmployeeType(from.getEmployeeType());
    to.setContactInformation(from.getContactInformation());
  }

  private boolean checkFilter(EmployeeModel employeeModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "user_id":
          if (employeeModel.getUserId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "salary":
          if (employeeModel.getSalary() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "employee_type":
          if (employeeModel.getEmployeeType().toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "contact_information":
          if (employeeModel.getContactInformation().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        default:
          if (checkAllColumns(employeeModel, value)) {
            return true;
          }
          break;
      }
    }
    return false;
  }

  private boolean checkAllColumns(EmployeeModel employeeModel, String value) {
    return employeeModel.getUserId() == Integer.parseInt(value)
        || employeeModel.getSalary() == Integer.parseInt(value)
        || employeeModel.getEmployeeType().toString().toLowerCase().contains(value.toLowerCase())
        || employeeModel.getContactInformation().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int addModel(EmployeeModel employeeModel) throws SQLException, ClassNotFoundException {
    if (employeeModel.getUserId() <= 0) {
      throw new IllegalArgumentException("Invalid user ID!");
    }
    if (employeeModel.getSalary() < 0) {
      throw new IllegalArgumentException("Salary cannot be negative!");
    }
    if (employeeModel.getContactInformation() == null || employeeModel.getContactInformation().isEmpty()) {
      throw new IllegalArgumentException("Contact information cannot be null or empty!");
    }
    employeeModel.setEmployeeType(
        employeeModel.getEmployeeType() != null ? employeeModel.getEmployeeType() : EmployeeType.employee_sales);

    int userId = EmployeeDAO.getInstance().insert(mapToEntity(employeeModel));
    employeeModel.setUserId(userId);
    employeeList.add(employeeModel);
    return userId;
  }

  @Override
  public int updateModel(EmployeeModel employeeModel) throws SQLException, ClassNotFoundException {
    int updatedRows = EmployeeDAO.getInstance().update(employeeModel);
    if (updatedRows > 0) {
      for (int i = 0; i < employeeList.size(); i++) {
        if (employeeList.get(i).getUserId() == employeeModel.getUserId()) {
          employeeList.set(i, employeeModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  public int updateStatus(int userId, EmployeeType role) throws ClassNotFoundException, SQLException {
    int success = EmployeeDAO.getInstance().updateStatus(userId, role);
    if (success == 1) {
      for (EmployeeModel employee : employeeList) {
        if (employee.getUserId() == userId) {
          employee.setEmployeeType(role);
          return 1;
        }
      }
    }
    return 0;
  }

  public int updateSalary(int userId, int salary) throws ClassNotFoundException, SQLException {
    int success = EmployeeDAO.getInstance().updateSalary(userId, salary);
    if (success == 1) {
      for (EmployeeModel employee : employeeList) {
        if (employee.getUserId() == userId) {
          employee.setSalary(salary);
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    EmployeeModel employeeModel = getModelById(id);
    if (employeeModel == null) {
      throw new IllegalArgumentException("Employee with ID " + id + " does not exist.");
    }
    int deletedRows = EmployeeDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      employeeList.remove(employeeModel);
    }
    return deletedRows;
  }

  @Override
  public List<EmployeeModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
    List<EmployeeModel> results = new ArrayList<>();
    try {
      List<EmployeeModel> entities = EmployeeDAO.getInstance().search(value, columns);
      for (EmployeeModel entity : entities) {
        EmployeeModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for employee: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for employee: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No employee found with the specified search criteria.");
    }

    return results;
  }
}
