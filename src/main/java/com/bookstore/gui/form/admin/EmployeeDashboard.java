package com.bookstore.gui.form.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bookstore.model.EmployeeModel;

public class EmployeeDashboard extends JPanel implements ActionListener {
  private JTable employeeTable;
  private DefaultTableModel tableModel;
  private JButton addButton;
  private JButton editButton;
  private JButton deleteButton;
  private JButton saveButton;
  private JButton backButton;
  private List<EmployeeModel> employeeList;

  public EmployeeDashboard(List<EmployeeModel> employeeList) {
    this.employeeList = employeeList;
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(800, 600));

    // Create table
    String[] columnNames = { "User ID", "Salary", "Employee Type", "Contact Information" };
    tableModel = new DefaultTableModel(columnNames, 0);
    employeeTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(employeeTable);
    add(scrollPane, BorderLayout.CENTER);

    // Create buttons
    addButton = new JButton("Add");
    editButton = new JButton("Edit");
    deleteButton = new JButton("Delete");
    saveButton = new JButton("Save");
    backButton = new JButton("Back");

    // Add buttons to panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addButton);
    buttonPanel.add(editButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(backButton);
    add(buttonPanel, BorderLayout.SOUTH);

    // Add action listeners to buttons
    addButton.addActionListener(this);
    editButton.addActionListener(this);
    deleteButton.addActionListener(this);
    saveButton.addActionListener(this);
    backButton.addActionListener(this);

    // Populate table with data
    for (EmployeeModel employee : employeeList) {
      Object[] rowData = { employee.getUserId(), employee.getSalary(), employee.getEmployeeType(),
          employee.getContactInformation() };
      tableModel.addRow(rowData);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO: Implement button actions
  }
}
