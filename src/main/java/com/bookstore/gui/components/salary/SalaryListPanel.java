package com.bookstore.gui.components.salary;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.models.EmployeeModel;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
    title = new JLabel();
    contend = new JPanel();
    sumSalaryPanel = new JPanel();
    totalText = new JLabel();
    setSumSalary = new JTextField();
    table = new JPanel();
    headerTable = new JPanel();
    panel = new JPanel();
    serialText = new JLabel();
    idText = new JLabel();
    nameText = new JLabel();
    salaryText = new JLabel();
    typeText = new JLabel();
    contactText = new JLabel();
    scrollPane = new JScrollPane();
    contendTable = new JPanel();

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
    contendTable.setLayout(new java.awt.GridLayout(0, 1, 0, 15));
    int serial = 0;
    for (EmployeeModel employee : listEmployee) {
      SalaryPanel salaryPanel = new SalaryPanel(serial, employee);
      contendTable.add(salaryPanel);
      serial = serial + 1;
    }
    contendTable.revalidate();
    contendTable.repaint();
  }

  private JLabel totalText;
  private JTextField setSumSalary;
  private JPanel sumSalaryPanel;
  private JPanel contend;
  private JPanel contendTable;
  private JLabel contactText;
  private JPanel headerTable;
  private JLabel idText;
  private JLabel nameText;
  private JLabel salaryText;
  private JLabel typeText;
  private JPanel panel;
  private JScrollPane scrollPane;
  private JLabel serialText;
  private JPanel table;
  private JLabel title;
}
