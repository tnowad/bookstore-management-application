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

public class UserListPanel extends JPanel {

  private static UserListPanel instance;

  private JPanel buttonAction;
  private JButton buttonBanned;
  private JButton buttonCreate;
  private JPanel buttonFilter;
  private JPanel buttonPanel;
  private JPanel cartPanel;
  private JPanel contend;
  private JPanel contendTable;
  private JLabel emailText;
  private JButton filterAdmin;
  private JButton filterAll;
  private JButton filterCustomer;
  private JButton filterEmployee;
  private JPanel headerTable;
  private JLabel nameText;
  private JLabel phoneText;
  private JLabel roleText;
  private JScrollPane scrollPane;
  private JLabel serialText;
  private JLabel statusText;
  private JPanel table;
  private JPanel tablePanel;
  private JLabel titlePanel;
  private JPanel panelItemHeader_1;
  private JPanel panelItemHeader_2;
  private JLabel label;

  private int quantityAdmin = 0;
  private int quantityCustomer = 0;
  private int quantityUser = 0;
  private int quantityEmployee = 0;
  private int quantityAdminNew = 0;
  private int quantityCustomerNew = 0;
  private int quantityEmployeeNew = 0;
  CartUserAdmin cartUserAdmin;

  UserBUS userBUS = UserBUS.getInstance();

  public UserListPanel() {
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
    titlePanel = new JLabel();
    contend = new JPanel();
    cartPanel = new JPanel();
    contendTable = new JPanel();
    buttonPanel = new JPanel();
    buttonFilter = new JPanel();
    filterAll = new JButton();
    filterAdmin = new JButton();
    filterEmployee = new JButton();
    filterCustomer = new JButton();
    buttonAction = new JPanel();
    buttonCreate = new JButton();
    buttonBanned = new JButton();
    tablePanel = new JPanel();
    headerTable = new JPanel();
    serialText = new JLabel();
    nameText = new JLabel();
    phoneText = new JLabel();
    emailText = new JLabel();
    roleText = new JLabel();
    statusText = new JLabel();
    scrollPane = new JScrollPane();
    panelItemHeader_1 = new JPanel();
    panelItemHeader_2 = new JPanel();

    table = new JPanel();
    label = new JLabel();

    setLayout(new BorderLayout());

    titlePanel.setFont(new Font("Segoe UI", 3, 14));
    titlePanel.setText("User List");
    add(titlePanel, BorderLayout.NORTH);

    contend.setLayout(new BorderLayout(0, 5));

    cartPanel.setLayout(new GridLayout(1, 0, 50, 0));

    cartUserAdmin =
      new CartUserAdmin(
        "/resources/icons/employeeCart.png",
        "EMPLOYEE",
        quantityEmployeeNew,
        quantityEmployee
      );

    cartPanel.add(cartUserAdmin);

    cartUserAdmin =
      new CartUserAdmin(
        "/resources/icons/adminCart.png",
        "ADMIN",
        quantityAdminNew,
        quantityAdmin
      );

    cartPanel.add(cartUserAdmin);

    cartUserAdmin =
      new CartUserAdmin(
        "/resources/icons/customerCart.png",
        "CUSTOMER",
        quantityCustomerNew,
        quantityCustomer
      );

    cartPanel.add(cartUserAdmin);

    contend.add(cartPanel, BorderLayout.PAGE_START);

    contendTable.setLayout(new BorderLayout());

    buttonPanel.setBackground(new Color(255, 255, 255));
    buttonPanel.setPreferredSize(new Dimension(688, 40));
    buttonPanel.setLayout(new BorderLayout());

    buttonFilter.setBackground(new Color(255, 255, 255));
    buttonFilter.setLayout(new GridLayout(1, 0, 10, 10));

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

    buttonPanel.add(buttonFilter, BorderLayout.WEST);

    buttonAction.setLayout(new GridLayout(1, 0, 0, 5));

    buttonCreate.setText("Create");
    buttonCreate.addActionListener(actionCreate);
    buttonAction.add(buttonCreate);

    buttonBanned.setText("Banned");
    buttonBanned.addActionListener(actionDelete);
    buttonAction.add(buttonBanned);

    buttonPanel.add(buttonAction, BorderLayout.EAST);

    contendTable.add(buttonPanel, BorderLayout.PAGE_START);

    tablePanel.setLayout(new BorderLayout());

    headerTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    headerTable.setLayout(new GridLayout());

    panelItemHeader_1.setLayout(new GridLayout(1, 2));

    panelItemHeader_1.add(label);

    serialText.setFont(new Font("Segoe UI", 0, 16));
    serialText.setText("Serial");

    panelItemHeader_1.add(serialText);

    headerTable.add(panelItemHeader_1);

    nameText.setFont(new Font("Segoe UI", 0, 16));
    nameText.setText("Name");
    headerTable.add(nameText);

    phoneText.setFont(new Font("Segoe UI", 0, 16));
    phoneText.setText("Phone");
    headerTable.add(phoneText);

    emailText.setFont(new Font("Segoe UI", 0, 16));
    emailText.setText("Email");
    headerTable.add(emailText);

    panelItemHeader_2.setLayout(new GridLayout(1, 2));

    roleText.setFont(new Font("Segoe UI", 0, 16));
    roleText.setText("Role");
    panelItemHeader_2.add(roleText);

    statusText.setFont(new Font("Segoe UI", 0, 16));
    statusText.setText("Status");
    panelItemHeader_2.add(statusText);

    headerTable.add(panelItemHeader_2);

    tablePanel.add(headerTable, BorderLayout.PAGE_START);

    scrollPane.setViewportView(table);

    tablePanel.add(scrollPane, BorderLayout.CENTER);

    contendTable.add(tablePanel, BorderLayout.CENTER);

    contend.add(contendTable, BorderLayout.CENTER);

    add(contend, BorderLayout.CENTER);
  }

  public void actionCard() {
    quantityAdmin = 0;
    quantityCustomer = 0;
    quantityEmployee = 0;
    quantityUser = 0;
    List<UserModel> userList = userBUS.getAllModels();
    for (UserModel user : userList) {
      LocalDateTime getTime = user.getCreatedAt();
      LocalDateTime timeNow = LocalDateTime.now();
      Duration duration = Duration.between(getTime, timeNow);
      long daysDiff = duration.toDays();

      if (!user.getStatus().toString().equals("BANNED")) {
        quantityUser = quantityUser + 1;

        if (user.getRole().toString().equals("ADMIN")) {
          quantityAdmin = quantityAdmin + 1;

          if (daysDiff <= 7) {
            quantityAdminNew = quantityAdminNew + 1;
          }
        } else if (user.getRole().toString().equals("EMPLOYEE")) {
          quantityEmployee = quantityEmployee + 1;

          if (daysDiff <= 7) {
            quantityEmployeeNew = quantityEmployeeNew + 1;
          }
        } else {
          quantityCustomer = quantityCustomer + 1;

          if (daysDiff <= 7) {
            quantityCustomerNew = quantityCustomerNew + 1;
          }
        }
      }
    }
  }

  public void addTable() {
    table.removeAll();
    table.setLayout(new GridLayout(0, 1, 0, 20));
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    int serial = 0;
    for (UserModel user : userList) {
      if (!user.getStatus().toString().equals("BANNED")) {
        UserPanel userForm = new UserPanel(serial, user);
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
        UserPanel userForm = new UserPanel(1, user);
        table.add(userForm);
      }
    }
    table.revalidate();
    table.repaint();
  }

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
      int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to banned users?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );

      if (choice == JOptionPane.YES_OPTION) {
        int deletedRows = 0;
        for (Component component : table.getComponents()) {
          if (component instanceof JPanel) {
            JPanel subPanel = (JPanel) component;

            for (Component subComponent : subPanel.getComponents()) {
              if (subComponent instanceof JPanel) {
                JPanel subPanelItem = (JPanel) subComponent;

                for (Component subComponentItem : subPanelItem.getComponents()) {
                  if (
                    subComponentItem instanceof JCheckBox &&
                    ((JCheckBox) subComponentItem).isSelected()
                  ) {
                    for (Component c : subPanelItem.getComponents()) {
                      if (c instanceof JTextField) {
                        int id = UserBUS
                          .getInstance()
                          .getModelById(
                            Integer.valueOf(((JTextField) c).getText())
                          )
                          .getId();
                        deletedRows = UserBUS.getInstance().deleteModel(id);
                        break;
                      }
                    }
                    break;
                  }
                }
              }
            }
          }
        }
        if (deletedRows != 0) {
          JOptionPane.showMessageDialog(null, " Account lock successful !");
        }
        tablePanel.revalidate();
        tablePanel.repaint();
      }
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
      table.setLayout(new GridLayout(0, 1, 0, 20));
      int serial = 0;
      List<UserModel> userList = UserBUS.getInstance().getAllModels();
      for (UserModel user : userList) {
        if (
          user.getRole().toString().equals("ADMIN") &&
          !user.getStatus().toString().equals("BANNED")
        ) {
          UserPanel userForm = new UserPanel(serial, user);
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
      table.setLayout(new GridLayout(0, 1, 0, 20));
      int serial = 0;
      List<UserModel> userList = UserBUS.getInstance().getAllModels();
      for (UserModel user : userList) {
        if (
          user.getRole().toString().equals("EMPLOYEE") &&
          !user.getStatus().toString().equals("BANNED")
        ) {
          UserPanel userForm = new UserPanel(serial, user);
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
      table.setLayout(new GridLayout(0, 1, 0, 20));
      int serial = 0;
      List<UserModel> userList = UserBUS.getInstance().getAllModels();
      for (UserModel user : userList) {
        if (
          user.getRole().toString().equals("CUSTOMER") &&
          !user.getStatus().toString().equals("BANNED")
        ) {
          UserPanel userForm = new UserPanel(serial, user);
          table.add(userForm);
          serial++;
        }
      }
      table.revalidate();
      table.repaint();
    }
  };
}
