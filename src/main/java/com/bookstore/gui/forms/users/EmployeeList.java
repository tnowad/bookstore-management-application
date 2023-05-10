package com.bookstore.gui.forms.users;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.EmployeeType;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.theme.ThemeFont;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.UserModel;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeList extends JPanel {

  private Button searchButton;
  private Button addEmployeeButton;
  private JTable employeeTableList;
  private Label employeeListLabel;
  private JPanel addEmployeePanel;

  private JPanel headerPanel;
  private JPanel tableContainerPanel;
  private JPanel groupTopHeaderPanel;
  private JPanel groupBottomHeaderPanel;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane2;
  private JTextField searchEmployeeTxtField;
  private static EmployeeList instance;

  UserBUS userBus = UserBUS.getInstance();
  List<UserModel> employeeList = userBus.getAllModels();

  public EmployeeList() {
    initComponents();
    listCustomer();
    search();
  }

  public static EmployeeList getInstance() {
    if (instance == null) {
      instance = new EmployeeList();
    }
    return instance;
  }

  private void search() {
    searchButton.addActionListener(e -> {
      String text = searchEmployeeTxtField.getText();
      if (text == null || text.isBlank()) {
        JOptionPane.showMessageDialog(
          null,
          "Search field cannot be empty. Please try again!"
        );
        showTable();
      } else {
        DefaultTableModel model = new DefaultTableModel();
        employeeTableList.setModel(model);
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Status");
        for (UserModel customer : employeeList) {
          if (customer.getName().toLowerCase().contains(text.toLowerCase())) {
            model.addRow(
              new Object[] {
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getStatus(),
              }
            );
            employeeTableList.setModel(model);
          }
        }
        if (employeeTableList.getRowCount() == 0) {
          JOptionPane.showMessageDialog(null, "No employee found!");
          showTable();
        }
      }
    });
  }

  private void listCustomer() {
    showTable();
  }

  private void showTable() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Id");
    model.addColumn("Name");
    model.addColumn("Email");
    model.addColumn("Phone");
    model.addColumn("Status");
    for (UserModel employee : employeeList) {
      if (employee.getRole() == UserRole.EMPLOYEE) {
        EmployeeBUS employeeBUS = EmployeeBUS.getInstance();
        EmployeeModel employeeModel = employeeBUS.getModelById(
          employee.getId()
        );
        if (employeeModel.getEmployeeType() == EmployeeType.EMPLOYEE_SALES) {
          model.addRow(
            new Object[] {
              employee.getId(),
              employee.getName(),
              employee.getEmail(),
              employee.getPhone(),
              employee.getStatus(),
            }
          );
          employeeTableList.setModel(model);
        }
      }
    }
  }

  private void initComponents() {
    headerPanel = new JPanel();
    groupTopHeaderPanel = new JPanel();
    employeeListLabel = new Label("Customer List");
    addEmployeePanel = new JPanel();
    addEmployeeButton = new Button("Add Customer");
    addEmployeeButton.setPreferredSize(new Dimension(200, 40));
    groupBottomHeaderPanel = new JPanel();
    searchEmployeeTxtField = new JTextField();
    searchButton = new Button("Search");
    jScrollPane1 = new JScrollPane();
    tableContainerPanel = new JPanel();
    jScrollPane2 = new JScrollPane();
    employeeTableList = new JTable();

    setMinimumSize(new Dimension(1180, 620));
    setPreferredSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    headerPanel.setLayout(new GridLayout(2, 1));

    groupTopHeaderPanel.setLayout(new GridLayout(1, 2));

    groupTopHeaderPanel.add(employeeListLabel);

    addEmployeePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    addEmployeePanel.add(addEmployeeButton);

    groupTopHeaderPanel.add(addEmployeePanel);

    headerPanel.add(groupTopHeaderPanel);

    searchEmployeeTxtField.setFont(new ThemeFont().getSmallFont());
    searchEmployeeTxtField.setPreferredSize(new Dimension(450, 30));
    groupBottomHeaderPanel.add(searchEmployeeTxtField);

    searchButton.setMaximumSize(new Dimension(75, 30));
    searchButton.setMinimumSize(new Dimension(75, 30));

    groupBottomHeaderPanel.add(searchButton);

    headerPanel.add(groupBottomHeaderPanel);

    add(headerPanel, BorderLayout.PAGE_START);

    tableContainerPanel.setLayout(new BorderLayout());

    employeeTableList.getTableHeader().setReorderingAllowed(false);

    jScrollPane2.setViewportView(employeeTableList);

    tableContainerPanel.add(jScrollPane2, BorderLayout.CENTER);

    jScrollPane1.setViewportView(tableContainerPanel);
    jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

    add(jScrollPane1, BorderLayout.CENTER);
  }
}
