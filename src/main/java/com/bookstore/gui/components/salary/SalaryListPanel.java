package com.bookstore.gui.components.salary;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.models.EmployeeModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SalaryListPanel extends JPanel {

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
  private int sumSalary = 0;

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

    setPreferredSize(new Dimension(720, 444));
    setLayout(new BorderLayout());

    title.setFont(new Font("Segoe UI", 1, 18));
    title.setText("List Salary");
    add(title, BorderLayout.PAGE_START);

    contend.setLayout(new BorderLayout());

    table.setLayout(new BorderLayout());

    headerTable.setLayout(new GridLayout(1, 5));

    panel.setLayout(new GridLayout(1, 2));

    serialText.setFont(new Font("Segoe UI", 0, 16));
    serialText.setText("Serial");
    panel.add(serialText);

    idText.setFont(new Font("Segoe UI", 0, 16));
    idText.setText("Id");
    panel.add(idText);

    headerTable.add(panel);

    nameText.setFont(new Font("Segoe UI", 0, 16));
    nameText.setText("Name");
    headerTable.add(nameText);

    salaryText.setFont(new Font("Segoe UI", 0, 16));
    salaryText.setText("Salary");
    headerTable.add(salaryText);

    typeText.setFont(new Font("Segoe UI", 0, 16));
    typeText.setText("Type");
    headerTable.add(typeText);

    contactText.setFont(new Font("Segoe UI", 0, 16));
    contactText.setText("Contact");
    headerTable.add(contactText);

    table.add(headerTable, BorderLayout.NORTH);

    scrollPane.setViewportView(contendTable);

    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    table.add(scrollPane, BorderLayout.CENTER);

    contend.add(table, BorderLayout.CENTER);

    sumSalaryPanel.setPreferredSize(new Dimension(720, 50));
    sumSalaryPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

    totalText.setText("Total:");
    totalText.setPreferredSize(new Dimension(75, 30));
    sumSalaryPanel.add(totalText);

    setSumSalary.setPreferredSize(new Dimension(75, 30));
    setSumSalary.setEditable(false);
    sumSalaryPanel.add(setSumSalary);

    contend.add(sumSalaryPanel, BorderLayout.SOUTH);

    add(contend, BorderLayout.CENTER);
  }

  public void addTable() {
    contendTable.removeAll();
    contendTable.setLayout(new GridLayout(0, 1, 0, 15));
    int serial = 0;
    for (EmployeeModel employee : listEmployee) {
      SalaryPanel salaryPanel = new SalaryPanel(serial, employee);
      contendTable.add(salaryPanel);
      sumSalary = sumSalary + employee.getSalary();
      serial = serial + 1;
    }
    setSumSalary.setText("" + sumSalary);
    contendTable.revalidate();
    contendTable.repaint();
  }
}
