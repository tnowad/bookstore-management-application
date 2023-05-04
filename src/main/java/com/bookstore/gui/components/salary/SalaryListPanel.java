package com.bookstore.gui.components.salary;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.models.EmployeeModel;
import java.util.List;

public class SalaryListPanel extends javax.swing.JPanel {

  private static SalaryListPanel instance;
  EmployeeBUS employeeBUS = EmployeeBUS.getInstance();
  List<EmployeeModel> listEmployee = employeeBUS.getAllModels();

  public SalaryListPanel() {
    initComponents();
    addTable();
  }

  public static SalaryListPanel getInstance() {
    if (instance == null) {
      instance = new SalaryListPanel();
    }
    return instance;
  }

  private void initComponents() {
    title = new javax.swing.JLabel();
    contend = new javax.swing.JPanel();
    sumSalaryPanel = new javax.swing.JPanel();
    totalText = new javax.swing.JLabel();
    setSumSalary = new javax.swing.JTextField();
    table = new javax.swing.JPanel();
    headerTable = new javax.swing.JPanel();
    panel = new javax.swing.JPanel();
    serialText = new javax.swing.JLabel();
    idText = new javax.swing.JLabel();
    nameText = new javax.swing.JLabel();
    salaryText = new javax.swing.JLabel();
    typeText = new javax.swing.JLabel();
    contactText = new javax.swing.JLabel();
    scrollPane = new javax.swing.JScrollPane();
    contendTable = new javax.swing.JPanel();

    setPreferredSize(new java.awt.Dimension(720, 444));
    setLayout(new java.awt.BorderLayout());

    title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    title.setText("List Salary");
    add(title, java.awt.BorderLayout.PAGE_START);

    contend.setLayout(new java.awt.BorderLayout());

    table.setLayout(new java.awt.BorderLayout());

    headerTable.setLayout(new java.awt.GridLayout(1, 5));

    panel.setLayout(new java.awt.GridLayout(1, 2));

    serialText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    serialText.setText("Serial");
    panel.add(serialText);

    idText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    idText.setText("Id");
    panel.add(idText);

    headerTable.add(panel);

    nameText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    nameText.setText("Name");
    headerTable.add(nameText);

    salaryText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    salaryText.setText("Salary");
    headerTable.add(salaryText);

    typeText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    typeText.setText("Type");
    headerTable.add(typeText);

    contactText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    contactText.setText("Contact");
    headerTable.add(contactText);

    table.add(headerTable, java.awt.BorderLayout.NORTH);

    scrollPane.setViewportView(contendTable);

    table.add(scrollPane, java.awt.BorderLayout.CENTER);

    contend.add(table, java.awt.BorderLayout.CENTER);

    sumSalaryPanel.setPreferredSize(new java.awt.Dimension(720, 50));
    sumSalaryPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10)
    );

    totalText.setText("Total:");
    totalText.setPreferredSize(new java.awt.Dimension(75, 30));
    sumSalaryPanel.add(totalText);

    setSumSalary.setText("1000");
    setSumSalary.setPreferredSize(new java.awt.Dimension(75, 30));
    sumSalaryPanel.add(setSumSalary);

    contend.add(sumSalaryPanel, java.awt.BorderLayout.SOUTH);


    add(contend, java.awt.BorderLayout.CENTER);
  }

  public void addTable() {
    contendTable.removeAll();
    contendTable.setLayout(new java.awt.GridLayout(0, 1,0,15));
    int serial = 0;
    for (EmployeeModel employee : listEmployee) {
      SalaryPanel salaryPanel = new SalaryPanel(serial, employee);
      contendTable.add(salaryPanel);
      serial = serial+1;
    }
    contendTable.revalidate();
    contendTable.repaint();
  }

  private javax.swing.JLabel totalText;
  private javax.swing.JTextField setSumSalary;
  private javax.swing.JPanel sumSalaryPanel;
  private javax.swing.JPanel contend;
  private javax.swing.JPanel contendTable;
  private javax.swing.JLabel contactText;
  private javax.swing.JPanel headerTable;
  private javax.swing.JLabel idText;
  private javax.swing.JLabel nameText;
  private javax.swing.JLabel salaryText;
  private javax.swing.JLabel typeText;
  private javax.swing.JPanel panel;
  private javax.swing.JScrollPane scrollPane;
  private javax.swing.JLabel serialText;
  private javax.swing.JPanel table;
  private javax.swing.JLabel title;
}
