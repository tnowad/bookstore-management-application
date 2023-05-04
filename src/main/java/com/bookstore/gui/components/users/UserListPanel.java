package com.bookstore.gui.components.users;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.carts.CartUserAdmin;
import com.bookstore.models.UserModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.List;
import javax.swing.*;

public class UserListPanel extends javax.swing.JPanel {

  private static UserListPanel instance;
  private int QuantityAdminNew = 0;
  private int QuantityCustomerNew = 0;
  private int QuantityEmployeeNew = 0;
  CartUserAdmin cartUserAdmin;

  UserBUS userBUS = UserBUS.getInstance();

  private UserListPanel() {
    actionCard();
    initComponents();
    addTable();
  }

  public static UserListPanel getInstance() {
    if (instance == null) {
      instance = new UserListPanel();
    }
    return instance;
  }

  private void initComponents() {

    titlePanel = new javax.swing.JLabel();
    contend = new javax.swing.JPanel();
    cartPanel = new javax.swing.JPanel();
    contendTable = new javax.swing.JPanel();
    buttonPanel = new javax.swing.JPanel();
    buttonFilter = new javax.swing.JPanel();
    filterAll = new javax.swing.JButton();
    filterAdmin = new javax.swing.JButton();
    filterEmployee = new javax.swing.JButton();
    filterCustomer = new javax.swing.JButton();
    buttonAction = new javax.swing.JPanel();
    buttonCreate = new javax.swing.JButton();
    buttonBanned = new javax.swing.JButton();
    tablePanel = new javax.swing.JPanel();
    headerTable = new javax.swing.JPanel();
    serialText = new javax.swing.JLabel();
    nameText = new javax.swing.JLabel();
    phoneText = new javax.swing.JLabel();
    emailText = new javax.swing.JLabel();
    roleText = new javax.swing.JLabel();
    statusText = new javax.swing.JLabel();
    scrollPane = new javax.swing.JScrollPane();
    panelItemHeader_1 = new javax.swing.JPanel();
    panelItemHeader_2 = new javax.swing.JPanel();

    table = new javax.swing.JPanel();
    label = new javax.swing.JLabel();

    setLayout(new java.awt.BorderLayout());

    titlePanel.setFont(new java.awt.Font("Segoe UI", 3, 14));
    titlePanel.setText("User List");
    add(titlePanel, java.awt.BorderLayout.NORTH);

    contend.setLayout(new java.awt.BorderLayout(0, 5));

    cartPanel.setLayout(new java.awt.GridLayout(1, 0, 50, 0));

    cartUserAdmin = new CartUserAdmin("/resources/icons/employeeCart.png", "EMPLOYEE", QuantityEmployee, QuantityEmployeeNew);

    cartPanel.add(cartUserAdmin);

    cartUserAdmin = new CartUserAdmin("/resources/icons/adminCart.png", "ADMIN", QuantityAdmin, QuantityAdminNew);

    cartPanel.add(cartUserAdmin);

    cartUserAdmin = new CartUserAdmin("/resources/icons/customerCart.png", "CUSTOMER", QuantityCustomer, QuantityCustomerNew);

    cartPanel.add(cartUserAdmin);

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

    headerTable.setLayout(new java.awt.GridLayout());

    panelItemHeader_1.setLayout(new java.awt.GridLayout(1,2));

    panelItemHeader_1.add(label);

    serialText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    serialText.setText("Serial");

    panelItemHeader_1.add(serialText);

    headerTable.add(panelItemHeader_1);

    nameText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    nameText.setText("Name");
    headerTable.add(nameText);

    phoneText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    phoneText.setText("Phone");
    headerTable.add(phoneText);

    emailText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    emailText.setText("Email");
    headerTable.add(emailText);

    panelItemHeader_2.setLayout(new java.awt.GridLayout(1,2));

    
    roleText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    roleText.setText("Role");
    panelItemHeader_2.add(roleText);

    statusText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    statusText.setText("Status");
    panelItemHeader_2.add(statusText);

    headerTable.add(panelItemHeader_2);
    
    tablePanel.add(headerTable, java.awt.BorderLayout.PAGE_START);

    scrollPane.setViewportView(table);

    tablePanel.add(scrollPane, java.awt.BorderLayout.CENTER);

    contendTable.add(tablePanel, java.awt.BorderLayout.CENTER);

    contend.add(contendTable, java.awt.BorderLayout.CENTER);

    add(contend, java.awt.BorderLayout.CENTER);
  } // </editor-fold>//GEN-END:initComponents

  public void actionCard() {
    QuantityAdmin = 0;
    QuantityCustomer = 0;
    QuantityEmployee = 0;
    QuantityUser = 0;
    List<UserModel> userList = userBUS.getAllModels();
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
  }

  public void addTable() {
    table.removeAll();
    table.setLayout(new GridLayout(0, 1,0,20));
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    int serial = 0;
    for (UserModel user : userList) {
      if (!user.getStatus().toString().equals("BANNED")) {
        UserPanel userForm = new UserPanel(serial,user);
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
        UserPanel userForm = new UserPanel(1,user);
        table.add(userForm);
      }
    }
    table.revalidate();
    table.repaint();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel buttonAction;
  private javax.swing.JButton buttonBanned;
  private javax.swing.JButton buttonCreate;
  private javax.swing.JPanel buttonFilter;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JPanel cartPanel;
  private javax.swing.JPanel contend;
  private javax.swing.JPanel contendTable;
  private javax.swing.JLabel emailText;
  private javax.swing.JButton filterAdmin;
  private javax.swing.JButton filterAll;
  private javax.swing.JButton filterCustomer;
  private javax.swing.JButton filterEmployee;
  private javax.swing.JPanel headerTable;
  private javax.swing.JLabel nameText;
  private javax.swing.JLabel phoneText;
  private javax.swing.JLabel roleText;
  private javax.swing.JScrollPane scrollPane;
  private javax.swing.JLabel serialText;
  private javax.swing.JLabel statusText;
  private javax.swing.JPanel table;
  private javax.swing.JPanel tablePanel;
  private javax.swing.JLabel titlePanel;
  private javax.swing.JPanel panelItemHeader_1;
  private javax.swing.JPanel panelItemHeader_2;
  private javax.swing.JLabel label;

  private int QuantityAdmin = 0;
  private int QuantityCustomer = 0;
  private int QuantityUser = 0;
  private int QuantityEmployee = 0;
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
          UserPanel userForm = new UserPanel(serial,user);
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
          UserPanel userForm = new UserPanel(serial,user);
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
          UserPanel userForm = new UserPanel(serial,user);
          table.add(userForm);
          serial++;
        }
      }
      table.revalidate();
      table.repaint();
    }
  };
}
