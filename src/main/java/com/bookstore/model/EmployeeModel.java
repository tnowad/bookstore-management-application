package com.bookstore.model;

import java.sql.Date;

public class EmployeeModel {
    private Date WorkSchedule;
    private float Salary;
    private String employeeType, contactInformation;

    public EmployeeModel() {
    }

    public EmployeeModel(Date WorkSchedule, float Salary, String employeeType, String contactInformation) {
        this.WorkSchedule = WorkSchedule;
        this.Salary = Salary;
        this.employeeType = employeeType;
        this.contactInformation = contactInformation;
    }

    public Date getWorkSchedule() {
        return this.WorkSchedule;
    }

    public void setWorkSchedule(Date WorkSchedule) {
        this.WorkSchedule = WorkSchedule;
    }

    public float getSalary() {
        return this.Salary;
    }

    public void setSalary(float Salary) {
        this.Salary = Salary;
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

    public EmployeeModel WorkSchedule(Date WorkSchedule) {
        setWorkSchedule(WorkSchedule);
        return this;
    }

    public EmployeeModel Salary(float Salary) {
        setSalary(Salary);
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
                ", employeeType='" + getEmployeeType() + "'" +
                ", contactInformation='" + getContactInformation() + "'" +
                "}";
    }

}
