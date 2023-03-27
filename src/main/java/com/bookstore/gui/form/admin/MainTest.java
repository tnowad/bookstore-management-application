package com.bookstore.gui.form.admin;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.bookstore.model.EmployeeModel;
import com.bookstore.model.EmployeeModel.EmployeeType;

public class MainTest {
  public static void main(String[] args) {
    ArrayList<EmployeeModel> employees = new ArrayList<>();
    employees.add(new EmployeeModel(1, 50000, EmployeeType.EMPLOYEE_INVENTORY, "bao@gmail.com"));
    employees.add(new EmployeeModel(2, 40000, EmployeeType.EMPLOYEE_MANAGER, "chinh@example.com"));
    employees.add(new EmployeeModel(3, 60000, EmployeeType.EMPLOYEE_SALES, "tuan@example.com"));
    JFrame frame = new JFrame("Employee Dashboard");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new EmployeeDashboard(employees));
    frame.setPreferredSize(new Dimension(800, 600));
    frame.pack();
    frame.setLocationRelativeTo(frame);
    frame.setVisible(true);
  }

}
