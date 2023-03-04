package com.bookstore.model;

import java.sql.Date;

public class EmployeeModel {
  private String idEmployee;
  private Date workSchedule;
  private float salary;
  private String employeeType;
  private String contactInformation;

  public EmployeeModel() {
  }

  public EmployeeModel(String idEmployee, Date workSchedule, float salary, String employeeType,
      String contactInformation) {
    this.idEmployee = idEmployee;
    this.workSchedule = workSchedule;
    this.salary = salary;
    this.employeeType = employeeType;
    this.contactInformation = contactInformation;
  }

  public String getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(String idEmployee) {
    this.idEmployee = idEmployee;
  }

  public Date getWorkSchedule() {
    return this.workSchedule;
  }

  public void setWorkSchedule(Date workSchedule) {
    this.workSchedule = workSchedule;
  }

  public float getSalary() {
    return this.salary;
  }

  public void setSalary(float salary) {
    this.salary = salary;
  }

  public String getEmployeeType() {
    return this.employeeType;
  }

  public void setEmployeeType(String employeeType) {
    this.employeeType = employeeType;
  }

  public String getContactInformation() {
    return this.contactInformation;
  }

  public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
  }

  public EmployeeModel workSchedule(Date workSchedule) {
    setWorkSchedule(workSchedule);
    return this;
  }

  public EmployeeModel salary(float salary) {
    setSalary(salary);
    return this;
  }

  public EmployeeModel employeeType(String employeeType) {
    setEmployeeType(employeeType);
    return this;
  }

  public EmployeeModel contactInformation(String contactInformation) {
    setContactInformation(contactInformation);
    return this;
  }

  @Override
  public String toString() {
    return "{" +
        " WorkSchedule='" + getWorkSchedule() + "'" +
        ", Salary='" + getSalary() + "'" +
        ", Employee Type='" + getEmployeeType() + "'" +
        ", Contact Information='" + getContactInformation() + "'" +
        "}";
  }

}
