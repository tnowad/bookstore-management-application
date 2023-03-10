package com.bookstore.model;

public class EmployeeModel {
  private int userId;
  private int salary;
  private EmployeeType employeeType;
  private String contactInformation;

  public EmployeeModel(int userId, int salary, EmployeeType employeeType, String contactInformation) {
    this.userId = userId;
    this.salary = salary;
    this.employeeType = employeeType;
    this.contactInformation = contactInformation;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public EmployeeType getEmployeeType() {
    return employeeType;
  }

  public void setEmployeeType(EmployeeType employeeType) {
    this.employeeType = employeeType;
  }

  public String getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
  }

  public enum EmployeeType {
    EMPLOYEE_MANAGER,
    EMPLOYEE_SALES,
    EMPLOYEE_INVENTORY,
    EMPLOYEE_ORDER
  }
}
