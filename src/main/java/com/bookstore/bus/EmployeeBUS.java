package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import com.bookstore.dao.EmployeeDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.model.EmployeeModel;
import com.bookstore.model.UserModel;
import com.bookstore.model.EmployeeModel.EmployeeType;
import com.bookstore.model.UserModel.Status;

public class EmployeeBUS {
  private ArrayList<EmployeeModel> employeeList;

  public EmployeeBUS() throws ClassNotFoundException, SQLException {
    employeeList = EmployeeDAO.getInstance().readDatabase();
  }

  public void readDatabase() throws ClassNotFoundException, SQLException {
    employeeList = EmployeeDAO.getInstance().readDatabase();
  }

  public EmployeeModel getEmployeeModel(int userId) {
    if (employeeList != null) {
      for (EmployeeModel employeeModel : employeeList) {
        if (employeeModel.getUserId() == userId) {
          return employeeModel;
        }
      }
    }
    return null;
  }

  private void setEmployeeProperties(EmployeeModel employeeModel, int userId, int salary, EmployeeType employeeType,
      String contactInformation) {
    employeeModel.setUserId(userId);
    employeeModel.setSalary(salary);
    employeeModel.setEmployeeType(employeeType);
    employeeModel.setContactInformation(contactInformation);
  }

  public int addEmployee(int userId, int salary, EmployeeType employeeType, String contactInformation)
      throws ClassNotFoundException, SQLException {
    EmployeeModel employeeModel = new EmployeeModel();
    setEmployeeProperties(employeeModel, userId, salary, employeeType, contactInformation);
    int added = EmployeeDAO.getInstance().insert(employeeModel);
    if (added == 1) {
      employeeList.add(employeeModel);
    }
    return added;
  }

  public int updateEmployee(int userId, int salary, EmployeeType employeeType, String contactInformation)
      throws ClassNotFoundException, SQLException {
    EmployeeModel employeeModel = EmployeeDAO.getInstance().getEmployeeById(userId);
    if (employeeModel == null) {
      return 0;
    } else {
      setEmployeeProperties(employeeModel, userId, salary, employeeType, contactInformation);
      int updated = EmployeeDAO.getInstance().update(employeeModel);
      if (updated == 1) {
        for (int i = 0; i < employeeList.size(); i++) {
          EmployeeModel e = employeeList.get(i);
          if (e.getUserId() == userId) {
            employeeList.set(i, employeeModel);
          }
        }
      }
      return updated;
    }
  }

  public int deleteEmployee(int userId) throws ClassNotFoundException, SQLException {
    EmployeeModel employeeModel = EmployeeDAO.getInstance().getEmployeeById(userId);
    if (employeeModel == null) {
      return 0;
    }
    UserModel userModel = UserDAO.getInstance().getUserById(employeeModel.getUserId());
    if (userModel == null) {
      return 0;
    }
    try {
      int updated = EmployeeDAO.getInstance().delete(String.valueOf(employeeModel.getUserId()));
      if (updated != 1) {
        return 0;
      }
      userModel.setStatus(Status.DELETED);
      updated = UserDAO.getInstance().update(userModel);
      if (updated != 1) {
        return 0;
      }
      return updated;
    } catch (IllegalArgumentException ex) {
      throw new RuntimeException("Failed to delete employee", ex);
    }
  }

  public ArrayList<EmployeeModel> searchEmployee(String value, String columns) {
    ArrayList<EmployeeModel> results = new ArrayList<>();
    for (EmployeeModel employeeModel : employeeList) {
      if (checkEmployeeValue(employeeModel, value, columns)) {
        results.add(employeeModel);
      }
    }
    return results;
  }

  private boolean checkEmployeeValue(EmployeeModel employeeModel, String value, String columns) {
    switch (columns.toLowerCase()) {
      case "user_id":
        return Integer.toString(employeeModel.getUserId()).equals(value);
      case "salary":
        return Integer.toString(employeeModel.getSalary()).equals(value);
      case "employee_type":
        return employeeModel.getEmployeeType().toString().toLowerCase().contains(value.toLowerCase());
      case "contact_information":
        return employeeModel.getContactInformation().equalsIgnoreCase(value);
      default:
        return checkAllColumns(employeeModel, value);
    }
  }

  private boolean checkAllColumns(EmployeeModel employeeModel, String value) {
    return Integer.toString(employeeModel.getUserId()).equals(value)
        || Integer.toString(employeeModel.getSalary()).equals(value)
        || employeeModel.getEmployeeType().toString().toLowerCase().contains(value.toLowerCase())
        || employeeModel.getContactInformation().toLowerCase().contains(value.toLowerCase());
  }

  public ArrayList<EmployeeModel> getEmployeeList() {
    return employeeList;
  }
}
