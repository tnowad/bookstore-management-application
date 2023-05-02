package com.bookstore.gui.components.users;

import com.bookstore.bus.UserBUS;
import com.bookstore.models.UserModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.List;
import javax.swing.*;

public class UserListPanel extends javax.swing.JPanel {

  private static UserListPanel instance;
  private Font font = new java.awt.Font("Segoe UI", 3, 14);

  private UserListPanel() {
    initComponents();
    actionCard();
    addTable();
  }

  public static UserListPanel getInstance() {
    if (instance == null) {
      instance = new UserListPanel();
    }
    return instance;
  }

  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    titlePanel = new javax.swing.JLabel();
    contend = new javax.swing.JPanel();
    cartPanel = new javax.swing.JPanel();
    employeeCart = new javax.swing.JPanel();
    iconEmployee = new javax.swing.JLabel();
    textEmployee = new javax.swing.JLabel();
    newEmployee = new javax.swing.JLabel();
    quantityEmployee = new javax.swing.JLabel();
    adminCart = new javax.swing.JPanel();
    iconAdmin = new javax.swing.JLabel();
    textAdmin = new javax.swing.JLabel();
    newAdmin = new javax.swing.JLabel();
    quantityAdmin = new javax.swing.JLabel();
    customerCart = new javax.swing.JPanel();
    iconCustomer = new javax.swing.JLabel();
    textCustomer = new javax.swing.JLabel();
    newCustomer = new javax.swing.JLabel();
    quantityCustomer = new javax.swing.JLabel();
    contendTable = new javax.swing.JPanel();
    buttonPanel = new javax.swing.JPanel();
    buttonFilter = new javax.swing.JPanel();
    filterAll = new javax.swing.JButton();
    filterAdmin = new javax.swing.JButton();
    filterEmployee = new javax.swing.JButton();
    filterCustomer = new javax.swing.JButton();
    buttonAction = new javax.swing.JPanel();
    buttonExport = new javax.swing.JButton();
    buttonImport = new javax.swing.JButton();
    buttonCreate = new javax.swing.JButton();
    buttonBanned = new javax.swing.JButton();
    tablePanel = new javax.swing.JPanel();
    headerTable = new javax.swing.JPanel();
    serialText = new javax.swing.JLabel();
    nameText = new javax.swing.JLabel();
    phoneText = new javax.swing.JLabel();
    emailText = new javax.swing.JLabel();
    roleText = new javax.swing.JTextField();
    statusText = new javax.swing.JTextField();
    scrollPane = new javax.swing.JScrollPane();
    table = new javax.swing.JPanel();

    setLayout(new java.awt.BorderLayout());

    titlePanel.setFont(font);
    titlePanel.setText("User List");
    add(titlePanel, java.awt.BorderLayout.NORTH);

    contend.setLayout(new java.awt.BorderLayout(0, 5));

    cartPanel.setLayout(new java.awt.GridLayout(1, 0, 50, 0));

    employeeCart.setBackground(new java.awt.Color(255, 204, 255));
    employeeCart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    employeeCart.setLayout(new java.awt.BorderLayout());

    iconEmployee.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/employeeCart.png")
      )
    );
    employeeCart.add(iconEmployee, java.awt.BorderLayout.WEST);

    textEmployee.setFont(font);
    textEmployee.setForeground(new java.awt.Color(255, 51, 51));
    textEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    textEmployee.setText("EMPLOYEE");
    employeeCart.add(textEmployee, java.awt.BorderLayout.PAGE_START);

    newEmployee.setFont(new java.awt.Font("Segoe UI", 3, 12));
    newEmployee.setText("New User: 100");
    employeeCart.add(newEmployee, java.awt.BorderLayout.PAGE_END);

    quantityEmployee.setFont(new java.awt.Font("Segoe UI", 0, 16));
    quantityEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    quantityEmployee.setText("10000");
    employeeCart.add(quantityEmployee, java.awt.BorderLayout.CENTER);

    cartPanel.add(employeeCart);

    adminCart.setBackground(new java.awt.Color(220, 207, 189));
    adminCart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    adminCart.setPreferredSize(new java.awt.Dimension(145, 100));
    adminCart.setLayout(new java.awt.BorderLayout());

    iconAdmin.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/adminCart.png")
      )
    );
    adminCart.add(iconAdmin, java.awt.BorderLayout.WEST);

    textAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14));
    textAdmin.setForeground(new java.awt.Color(255, 51, 51));
    textAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    textAdmin.setText("ADMIN");
    adminCart.add(textAdmin, java.awt.BorderLayout.PAGE_START);

    newAdmin.setFont(new java.awt.Font("Segoe UI", 3, 12));
    newAdmin.setText("New User: 100");
    adminCart.add(newAdmin, java.awt.BorderLayout.PAGE_END);

    quantityAdmin.setFont(new java.awt.Font("Segoe UI", 0, 16));
    quantityAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    quantityAdmin.setText("10000");
    adminCart.add(quantityAdmin, java.awt.BorderLayout.CENTER);

    cartPanel.add(adminCart);

    customerCart.setBackground(new java.awt.Color(242, 149, 158));
    customerCart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    customerCart.setPreferredSize(new java.awt.Dimension(145, 100));
    customerCart.setLayout(new java.awt.BorderLayout());

    iconCustomer.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/customerCart.png")
      )
    );
    customerCart.add(iconCustomer, java.awt.BorderLayout.WEST);

    textCustomer.setFont(font);
    textCustomer.setForeground(new java.awt.Color(255, 51, 51));
    textCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    textCustomer.setText("CUSTOMER");
    customerCart.add(textCustomer, java.awt.BorderLayout.PAGE_START);

    newCustomer.setFont(new java.awt.Font("Segoe UI", 3, 12));
    newCustomer.setText("New User: 100");
    customerCart.add(newCustomer, java.awt.BorderLayout.PAGE_END);

    quantityCustomer.setFont(new java.awt.Font("Segoe UI", 0, 16));
    quantityCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    quantityCustomer.setText("10000");
    customerCart.add(quantityCustomer, java.awt.BorderLayout.CENTER);

    cartPanel.add(customerCart);

    contend.add(cartPanel, java.awt.BorderLayout.PAGE_START);

    contendTable.setLayout(new java.awt.BorderLayout());

    buttonPanel.setBackground(new java.awt.Color(255, 255, 255));
    buttonPanel.setPreferredSize(new java.awt.Dimension(688, 40));
    buttonPanel.setLayout(new java.awt.BorderLayout());

    buttonFilter.setBackground(new java.awt.Color(255, 255, 255));
    buttonFilter.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

    filterAll.setText("All");
    filterAll.setBorder(null);
    filterAll.addActionListener(findAllUser);
    buttonFilter.add(filterAll);

    filterAdmin.setText("Admin");
    filterAdmin.setToolTipText("");
    filterAdmin.setBorder(null);
    filterAdmin.addActionListener(findAdmin);
    buttonFilter.add(filterAdmin);

    filterEmployee.setText("Employee");
    filterEmployee.setBorder(null);
    filterEmployee.addActionListener(findEmployee);
    buttonFilter.add(filterEmployee);

    filterCustomer.setText("Customer");
    filterCustomer.setBorder(null);
    filterCustomer.addActionListener(findCustomer);
    buttonFilter.add(filterCustomer);

    buttonPanel.add(buttonFilter, java.awt.BorderLayout.WEST);

    buttonAction.setLayout(new java.awt.GridLayout(1, 0, 0, 5));

    buttonExport.setText("Xuất File");
    buttonExport.setPreferredSize(new java.awt.Dimension(70, 23));
    buttonAction.add(buttonExport);

    buttonImport.setText("Nhập File");
    buttonAction.add(buttonImport);

    buttonCreate.setText("Create");
    buttonCreate.addActionListener(actionCreate);
    buttonAction.add(buttonCreate);

    buttonBanned.setText("Banned");
    buttonBanned.addActionListener(actionDelete);
    buttonAction.add(buttonBanned);

    buttonPanel.add(buttonAction, java.awt.BorderLayout.EAST);

    contendTable.add(buttonPanel, java.awt.BorderLayout.PAGE_START);

    tablePanel.setLayout(new java.awt.BorderLayout());

    headerTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    headerTable.setPreferredSize(new java.awt.Dimension(670, 40));
    java.awt.GridBagLayout headerTableLayout = new java.awt.GridBagLayout();
    headerTableLayout.columnWeights =
      new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
    headerTable.setLayout(headerTableLayout);

    serialText.setFont(new java.awt.Font("Segoe UI", 0, 14));
    serialText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    serialText.setText("Serial");
    serialText.setMaximumSize(new java.awt.Dimension(16, 18));
    serialText.setMinimumSize(new java.awt.Dimension(16, 18));
    serialText.setName("");
    serialText.setPreferredSize(new java.awt.Dimension(16, 19));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 18;
    gridBagConstraints.ipady = 35;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
    headerTable.add(serialText, gridBagConstraints);

    nameText.setFont(new java.awt.Font("Segoe UI", 0, 14));
    nameText.setText("Name");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 99;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
    headerTable.add(nameText, gridBagConstraints);

    phoneText.setFont(new java.awt.Font("Segoe UI", 0, 14));
    phoneText.setText("Phone");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 70;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
    headerTable.add(phoneText, gridBagConstraints);

    emailText.setFont(new java.awt.Font("Segoe UI", 0, 14));
    emailText.setText("Email");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 120;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
    headerTable.add(emailText, gridBagConstraints);

    roleText.setEditable(false);
    roleText.setFont(new java.awt.Font("Segoe UI", 0, 14));
    roleText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
    roleText.setText("ROLE");
    roleText.setBorder(null);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 5;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.ipady = 20;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
    headerTable.add(roleText, gridBagConstraints);

    statusText.setEditable(false);
    statusText.setFont(new java.awt.Font("Segoe UI", 0, 14));
    statusText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
    statusText.setText("STATUS");
    statusText.setBorder(null);
    statusText.setMinimumSize(new java.awt.Dimension(50, 26));
    statusText.setPreferredSize(new java.awt.Dimension(50, 26));

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 6;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.ipadx = 28;
    gridBagConstraints.ipady = 15;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 6);
    headerTable.add(statusText, gridBagConstraints);

    tablePanel.add(headerTable, java.awt.BorderLayout.PAGE_START);

    scrollPane.setViewportView(table);

    tablePanel.add(scrollPane, java.awt.BorderLayout.CENTER);

    contendTable.add(tablePanel, java.awt.BorderLayout.CENTER);

    contend.add(contendTable, java.awt.BorderLayout.CENTER);

    add(contend, java.awt.BorderLayout.CENTER);
  } // </editor-fold>//GEN-END:initComponents

  public void actionCard() {
    int QuantityAdminNew = 0;
    int QuantityCustomerNew = 0;
    int QuantityEmployeeNew = 0;
    QuantityAdmin = 0;
    QuantityCustomer = 0;
    QuantityEmployee = 0;
    QuantityUser = 0;
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    for (UserModel user : userList) {
      LocalDateTime getTime = user.getCreatedAt();
      LocalDateTime timeNow = LocalDateTime.now();
      Duration duration = Duration.between(getTime, timeNow);
      long daysDiff = duration.toMillis();
      if (!user.getStatus().toString().equals("BANNED")) {
        QuantityUser = QuantityUser + 1;
        if (user.getRole().toString().equals("ADMIN")) {
          QuantityAdmin = QuantityAdmin + 1;
          if (daysDiff <= 7) {
            QuantityAdminNew = QuantityAdminNew + 1;
          }
        } else if (user.getRole().toString().equals("EMPLOYEE")) {
          QuantityEmployee = QuantityEmployee + 1;
          if (daysDiff <= 7) {
            QuantityEmployeeNew = QuantityEmployeeNew + 1;
          }
        } else {
          QuantityCustomer = QuantityCustomer + 1;
          if (daysDiff <= 7) {
            QuantityCustomerNew = QuantityCustomerNew + 1;
          }
        }
      }
    }
    newAdmin.setText("New User: " + QuantityAdminNew);
    newCustomer.setText("New User: " + QuantityCustomerNew);
    newEmployee.setText("New User: " + QuantityEmployeeNew);

    quantityAdmin.setText("" + QuantityAdmin);
    quantityCustomer.setText("" + QuantityCustomer);
    quantityEmployee.setText("" + QuantityEmployee);
  }

  public void addTable() {
    table.removeAll();
    table.setLayout(new GridLayout(0, 1));
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    int serial = 0;
    for (UserModel user : userList) {
      if (!user.getStatus().toString().equals("BANNED")) {
        UserPanel userForm = new UserPanel(
          serial,
          user.getId(),
          user.getUsername(),
          user.getPassword(),
          user.getStatus(),
          user.getName(),
          user.getEmail(),
          user.getPhone(),
          user.getRole(),
          user.getCreatedAt(),
          user.getUpdatedAt()
        );
        table.add(userForm);
        serial++;
      }
    }
    table.revalidate();
    table.repaint();
  }

  public void receiveValue(String value) {
    String[] columns = new String[] { "name" };
    table.removeAll();
    List<UserModel> list = UserBUS.getInstance().searchModel(value, columns);
    table.setLayout(new GridLayout(0, 1, 10, 10));
    for (UserModel user : list) {
      if (!user.getStatus().toString().equals("BANNED")) {
        UserPanel userForm = new UserPanel(
          1,
          user.getId(),
          user.getUsername(),
          user.getPassword(),
          user.getStatus(),
          user.getName(),
          user.getEmail(),
          user.getPhone(),
          user.getRole(),
          user.getCreatedAt(),
          user.getUpdatedAt()
        );
        table.add(userForm);
      }
    }
    table.revalidate();
    table.repaint();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel adminCart;
  private javax.swing.JPanel buttonAction;
  private javax.swing.JButton buttonBanned;
  private javax.swing.JButton buttonCreate;
  private javax.swing.JButton buttonExport;
  private javax.swing.JPanel buttonFilter;
  private javax.swing.JButton buttonImport;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JPanel cartPanel;
  private javax.swing.JPanel contend;
  private javax.swing.JPanel contendTable;
  private javax.swing.JPanel customerCart;
  private javax.swing.JLabel emailText;
  private javax.swing.JPanel employeeCart;
  private javax.swing.JButton filterAdmin;
  private javax.swing.JButton filterAll;
  private javax.swing.JButton filterCustomer;
  private javax.swing.JButton filterEmployee;
  private javax.swing.JPanel headerTable;
  private javax.swing.JLabel iconAdmin;
  private javax.swing.JLabel iconCustomer;
  private javax.swing.JLabel iconEmployee;
  private javax.swing.JLabel nameText;
  private javax.swing.JLabel newAdmin;
  private javax.swing.JLabel newCustomer;
  private javax.swing.JLabel newEmployee;
  private javax.swing.JLabel phoneText;
  private javax.swing.JLabel quantityAdmin;
  private javax.swing.JLabel quantityCustomer;
  private javax.swing.JLabel quantityEmployee;
  private javax.swing.JTextField roleText;
  private javax.swing.JScrollPane scrollPane;
  private javax.swing.JLabel serialText;
  private javax.swing.JTextField statusText;
  private javax.swing.JPanel table;
  private javax.swing.JPanel tablePanel;
  private javax.swing.JLabel textAdmin;
  private javax.swing.JLabel textCustomer;
  private javax.swing.JLabel textEmployee;
  private javax.swing.JLabel titlePanel;
  private int QuantityAdmin;
  private int QuantityCustomer;
  private int QuantityUser;
  private int QuantityEmployee;
  // End of variables declaration//GEN-END:variables

  public ActionListener actionCreate = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      CreateUserFrame createUserFrame = new CreateUserFrame();
      createUserFrame.setVisible(true);
    }
  };
  public ActionListener actionDelete = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      for (Component component : table.getComponents()) {
        JPanel subPanel = (JPanel) component;
        for (Component subComponent : subPanel.getComponents()) {
          if (
            subComponent instanceof JCheckBox &&
            ((JCheckBox) subComponent).isSelected()
          ) {
            Component[] components = subPanel.getComponents();
            boolean foundTextField = false;
            for (Component c : components) {
              if (c instanceof JTextField && !foundTextField) {
                foundTextField = true;
                System.out.println(((JTextField) c).getText());
                int id = Integer.parseInt(((JTextField) c).getText());
                int deletedRows = UserBUS.getInstance().deleteModel(id);
                if (deletedRows == 1) {
                  JOptionPane.showMessageDialog(
                    null,
                    " Account lock successful !"
                  );
                }
              }
            }
          }
        }
      }
      tablePanel.revalidate();
      tablePanel.repaint();
    }
  };

  public ActionListener findAllUser = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      addTable();
    }
  };

  public ActionListener findAdmin = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      table.removeAll();
      table.setLayout(new GridLayout(0, 1));
      int serial = 0;
      List<UserModel> userList = UserBUS.getInstance().getAllModels();
      for (UserModel user : userList) {
        if (
          user.getRole().toString().equals("ADMIN") &&
          !user.getStatus().toString().equals("BANNED")
        ) {
          UserPanel userForm = new UserPanel(
            serial,
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getStatus(),
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getRole(),
            user.getCreatedAt(),
            user.getUpdatedAt()
          );
          table.add(userForm);
          serial++;
        }
      }
      table.revalidate();
      table.repaint();
    }
  };
  public ActionListener findEmployee = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      table.removeAll();
      table.setLayout(new GridLayout(0, 1));
      int serial = 0;
      List<UserModel> userList = UserBUS.getInstance().getAllModels();
      for (UserModel user : userList) {
        if (
          user.getRole().toString().equals("EMPLOYEE") &&
          !user.getStatus().toString().equals("BANNED")
        ) {
          UserPanel userForm = new UserPanel(
            serial,
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getStatus(),
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getRole(),
            user.getCreatedAt(),
            user.getUpdatedAt()
          );
          table.add(userForm);
          serial++;
        }
      }
      table.revalidate();
      table.repaint();
    }
  };
  public ActionListener findCustomer = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      table.removeAll();
      table.setLayout(new GridLayout(0, 1));
      int serial = 0;
      List<UserModel> userList = UserBUS.getInstance().getAllModels();
      for (UserModel user : userList) {
        if (
          user.getRole().toString().equals("CUSTOMER") &&
          !user.getStatus().toString().equals("BANNED")
        ) {
          UserPanel userForm = new UserPanel(
            serial,
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getStatus(),
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getRole(),
            user.getCreatedAt(),
            user.getUpdatedAt()
          );
          table.add(userForm);
          serial++;
        }
      }
      table.revalidate();
      table.repaint();
    }
  };
}
