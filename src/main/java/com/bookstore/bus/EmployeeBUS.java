package com.bookstore.bus;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.enums.EmployeeType;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.EmployeeModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeBUS implements IBUS<EmployeeModel> {

  private final List<EmployeeModel> employeeList = new ArrayList<>();
  private static EmployeeBUS instance;

  public static EmployeeBUS getInstance() {
    if (instance == null) {
      instance = new EmployeeBUS();
    }
    return instance;
  }

  private EmployeeBUS() {
    this.employeeList.addAll(EmployeeDAO.getInstance().readDatabase());
  }

  @Override
  public List<EmployeeModel> getAllModels() {
    return Collections.unmodifiableList(employeeList);
  }

  @Override
  public EmployeeModel getModelById(int id) {
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

  private boolean checkFilter(
    EmployeeModel employeeModel,
    String value,
    String[] columns
  ) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "user_id" -> {
          if (employeeModel.getUserId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "salary" -> {
          if (employeeModel.getSalary() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "employee_type" -> {
          if (
            employeeModel
              .getEmployeeType()
              .toString()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        case "contact_information" -> {
          if (
            employeeModel
              .getContactInformation()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(employeeModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(EmployeeModel employeeModel, String value) {
    return (
      employeeModel.getUserId() == Integer.parseInt(value) ||
      employeeModel.getSalary() == Integer.parseInt(value) ||
      employeeModel
        .getEmployeeType()
        .toString()
        .toLowerCase()
        .contains(value.toLowerCase()) ||
      employeeModel
        .getContactInformation()
        .toLowerCase()
        .contains(value.toLowerCase())
    );
  }

  @Override
  public int addModel(EmployeeModel employeeModel) {
    if (employeeModel.getUserId() <= 0) {
      throw new IllegalArgumentException("Invalid user ID!");
    }
    if (employeeModel.getSalary() < 0) {
      throw new IllegalArgumentException("Salary cannot be negative!");
    }
    if (
      employeeModel.getContactInformation() == null ||
      employeeModel.getContactInformation().isEmpty()
    ) {
      throw new IllegalArgumentException(
        "Contact information cannot be null or empty!"
      );
    }
    employeeModel.setEmployeeType(
      employeeModel.getEmployeeType() != null
        ? employeeModel.getEmployeeType()
        : EmployeeType.EMPLOYEE_SALES
    );

    int userId = EmployeeDAO.getInstance().insert(mapToEntity(employeeModel));
    employeeModel.setUserId(userId);
    employeeList.add(employeeModel);
    return userId;
  }

  @Override
  public int updateModel(EmployeeModel employeeModel) {
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

  public int updateStatus(int userId, String role) {
    int success = EmployeeDAO.getInstance().updateStatus(userId, role);
    if (success == 1) {
      for (EmployeeModel employee : employeeList) {
        if (employee.getUserId() == userId) {
          EmployeeType roleEnum = EmployeeType.valueOf(role.toUpperCase());
          employee.setEmployeeType(roleEnum);
          return 1;
        }
      }
    }
    return 0;
  }

  public int updateSalary(int userId, int salary) {
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
  public int deleteModel(int id) {
    EmployeeModel employeeModel = getModelById(id);
    if (employeeModel == null) {
      throw new IllegalArgumentException(
        "Employee with ID " + id + " does not exist."
      );
    }
    int deletedRows = EmployeeDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      employeeList.remove(employeeModel);
    }
    return deletedRows;
  }

  @Override
  public List<EmployeeModel> searchModel(String value, String[] columns) {
    List<EmployeeModel> results = new ArrayList<>();
    List<EmployeeModel> entities = EmployeeDAO
      .getInstance()
      .search(value, columns);
    for (EmployeeModel entity : entities) {
      EmployeeModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  @Override
  public void refreshData() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'refreshData'");
  }
}
