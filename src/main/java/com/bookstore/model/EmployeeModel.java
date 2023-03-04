package com.bookstore.model;

import java.sql.Date;

public class EmployeeModel {
  private String userId;
  private Date workSchedule;
  private Double salary;
  private String employeeType;
  private String contactInformation;
  private String goodNotesReceiptId;
  private String invoiceId;

  public EmployeeModel() {
  }

  public EmployeeModel(String userId, Date workSchedule, Double salary, String employeeType, String contactInformation,
      String goodNotesReceiptId, String invoiceId) {
    this.userId = userId;
    this.workSchedule = workSchedule;
    this.salary = salary;
    this.employeeType = employeeType;
    this.contactInformation = contactInformation;
    this.goodNotesReceiptId = goodNotesReceiptId;
    this.invoiceId = invoiceId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Date getWorkSchedule() {
    return this.workSchedule;
  }

  public void setWorkSchedule(Date workSchedule) {
    this.workSchedule = workSchedule;
  }

  public Double getSalary() {
    return this.salary;
  }

  public void setSalary(Double salary) {
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

  public EmployeeModel salary(Double salary) {
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

  public String getGoodNotesReceiptId() {
    return goodNotesReceiptId;
  }

  public void setGoodNotesReceiptId(String goodNotesReceiptId) {
    this.goodNotesReceiptId = goodNotesReceiptId;
  }

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }
}