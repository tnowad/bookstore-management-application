package com.bookstore.gui.forms.users;

import com.bookstore.bus.UserBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.theme.ThemeFont;
import com.bookstore.models.UserModel;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomerList extends JPanel {

  private Button searchButton;
  private Button addCustomerButton;
  private JTable customerTableList;
  private Label customerListLabel;
  private JPanel addCustomerPanel;

  private JPanel headerPanel;
  private JPanel tableContainerPanel;
  private JPanel groupTopHeaderPanel;
  private JPanel groupBottomHeaderPanel;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane2;
  private JTextField searchCustomerTxtFld;

  private static CustomerList instance;

  UserBUS userBus = UserBUS.getInstance();
  List<UserModel> customersList = userBus.getAllModels();

  public CustomerList() {
    initComponents();
    listCustomer();
    search();
  }

  public static CustomerList getInstance() {
    if (instance == null) {
      instance = new CustomerList();
    }
    return instance;
  }

  private void search() {
    searchButton.addActionListener(e -> {
      String text = searchCustomerTxtFld.getText();
      if (text == null || text.isBlank()) {
        JOptionPane.showMessageDialog(
          null,
          "Search field cannot be empty. Please try again!"
        );
        showTable();
      } else {
        DefaultTableModel model = new DefaultTableModel();
        customerTableList.setModel(model);
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Status");
        for (UserModel customer : customersList) {
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
            customerTableList.setModel(model);
          }
        }
        if (customerTableList.getRowCount() == 0) {
          JOptionPane.showMessageDialog(null, "No customers found!");
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
    for (UserModel customer : customersList) {
      if (customer.getRole() == UserRole.CUSTOMER) {
        model.addRow(
          new Object[] {
            customer.getId(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getStatus(),
          }
        );
        customerTableList.setModel(model);
      }
    }
  }

  private void initComponents() {
    headerPanel = new JPanel();
    groupTopHeaderPanel = new JPanel();
    customerListLabel = new Label("Customer List");
    addCustomerPanel = new JPanel();
    addCustomerButton = new Button("Add Customer");
    addCustomerButton.setPreferredSize(new Dimension(200, 40));
    groupBottomHeaderPanel = new JPanel();
    searchCustomerTxtFld = new JTextField();
    searchButton = new Button("Search");
    jScrollPane1 = new JScrollPane();
    tableContainerPanel = new JPanel();
    jScrollPane2 = new JScrollPane();
    customerTableList = new JTable();

    setMinimumSize(new Dimension(1180, 620));
    setPreferredSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    headerPanel.setLayout(new GridLayout(2, 1));

    groupTopHeaderPanel.setLayout(new GridLayout(1, 2));

    groupTopHeaderPanel.add(customerListLabel);

    addCustomerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    addCustomerPanel.add(addCustomerButton);

    groupTopHeaderPanel.add(addCustomerPanel);

    headerPanel.add(groupTopHeaderPanel);

    searchCustomerTxtFld.setFont(new ThemeFont().getSmallFont());
    searchCustomerTxtFld.setPreferredSize(new Dimension(450, 30));
    groupBottomHeaderPanel.add(searchCustomerTxtFld);

    searchButton.setMaximumSize(new Dimension(75, 30));
    searchButton.setMinimumSize(new Dimension(75, 30));

    groupBottomHeaderPanel.add(searchButton);

    headerPanel.add(groupBottomHeaderPanel);

    add(headerPanel, BorderLayout.PAGE_START);

    tableContainerPanel.setLayout(new BorderLayout());

    customerTableList.getTableHeader().setReorderingAllowed(false);

    jScrollPane2.setViewportView(customerTableList);

    tableContainerPanel.add(jScrollPane2, BorderLayout.CENTER);

    jScrollPane1.setViewportView(tableContainerPanel);

    add(jScrollPane1, BorderLayout.CENTER);
  }
}
