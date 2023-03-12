package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.model.EmployeeModel;
import com.bookstore.model.EmployeeModel.EmployeeType;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Status;

public class EmployeeBUS extends BUSAbstract<EmployeeModel> {
  private final List<EmployeeModel> employeeList = new ArrayList<>();
  private final EmployeeDAO employeeDao;

  public EmployeeBUS(EmployeeDAO employeeDao) throws SQLException, ClassNotFoundException {
    this.employeeDao = employeeDao;
    this.employeeList.addAll(employeeDao.readDatabase());
  }

  @Override
  protected ArrayList<EmployeeModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return employeeDao.readDatabase();
  }

  @Override
  protected int getId(EmployeeModel t) {
    return t.getUserId();
  }

  @Override
  protected EmployeeModel mapToEntity(EmployeeModel to) {
    updateEntityFields(to, to);
    return to;
  }

  @Override
  protected void updateEntityFields(EmployeeModel from, EmployeeModel to) {
    to.setSalary(from.getSalary());
    to.setEmployeeType(from.getEmployeeType());
    to.setContactInformation(from.getContactInformation());
  }

  @Override
  protected boolean checkFilter(EmployeeModel employeeModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "user_id":
        return employeeModel.getUserId() == Integer.parseInt(value);
      case "salary":
        return employeeModel.getSalary() == Integer.parseInt(value);
      case "employee_type":
        return employeeModel.getEmployeeType().toString().toLowerCase().equals(value.toLowerCase());
      case "contact_information":
        return employeeModel.getContactInformation().toLowerCase().contains(value.toLowerCase());
      default:
        return checkAllColumns(employeeModel, value);
    }
  }

  private boolean checkAllColumns(EmployeeModel employeeModel, String value) {
    return employeeModel.getUserId() == Integer.parseInt(value)
        || employeeModel.getSalary() == Integer.parseInt(value)
        || employeeModel.getEmployeeType().toString().toLowerCase().equals(value.toLowerCase())
        || employeeModel.getContactInformation().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  protected int insertModel(EmployeeModel employeeModel) throws SQLException, ClassNotFoundException {
    if (employeeModel.getSalary() < 0 || employeeModel.getContactInformation().isEmpty()) {
      throw new IllegalArgumentException("Invalid input. Please check the input and try again.");
    }
    employeeModel.setEmployeeType(
        employeeModel.getEmployeeType() != null ? employeeModel.getEmployeeType() : EmployeeType.EMPLOYEE_SALES);
    return add(employeeModel);
  }

  @Override
  protected int updateModel(EmployeeModel employeeModel) throws SQLException, ClassNotFoundException {
    return update(employeeModel);
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    EmployeeModel employeeModel = getModel(id);
    if (employeeModel == null) {
      return 0;
    }
    UserDAO userDao = UserDAO.getInstance();
    UserModel userModel = userDao.getUserById(employeeModel.getUserId());
    if (userModel != null) {
      userModel.setStatus(Status.DELETED);
      userDao.update(userModel);
    }
    int deleted = employeeDao.delete(employeeModel.getUserId());
    if (deleted > 0) {
      boolean removed = employeeList.removeIf(emp -> emp.getUserId() == employeeModel.getUserId());
      if (!removed) {
        System.out.println("The employee with ID " + id + " was not found in the employeeList.");
      }
    }
    return deleted;
  }

  public List<EmployeeModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public EmployeeModel getEmployeeModel(int userId) {
    return getModel(userId);
  }

  public List<EmployeeModel> getEmployeeList() {
    return Collections.unmodifiableList(employeeList);
  }
}
