package com.bookstore.gui.components.users;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.EmployeeType;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.cards.CardPanel;
import com.bookstore.gui.components.carts.CartUserAdmin;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.UserModel;
import com.bookstore.models.gui.StatisticCardModel;
import com.bookstore.services.Authentication;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class UserListPanel extends JPanel implements ISearchable {

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
  private JLabel titleLabel;
  private JPanel panelHeader;
  private JPanel panelEnd;
  private JLabel label;

  private int quantityAdmin = 0;
  private int quantityCustomer = 0;
  private int quantityUser = 0;
  private int quantityEmployee = 0;
  private int quantityAdminNew = 0;
  private int quantityCustomerNew = 0;
  private int quantityEmployeeNew = 0;
  private int quantitySales = 0;
  private int quantitySalesNew = 0;
  private JButton buttonSortAz;
  private JButton buttonSortZa;

  CardPanel cartUserAdmin;

  private UserBUS userBUS = UserBUS.getInstance();
  private EmployeeBUS employeeBUS = EmployeeBUS.getInstance();
  private List<UserModel> listUser = userBUS.getAllModels();
  private List<UserModel> modifiableUserList = new ArrayList<>(listUser);

  UserModel userModel = Authentication.getCurrentUser();

  public UserListPanel() {
    actionCard();
    initComponents();
    addTable(modifiableUserList);
  }

  public static UserListPanel getInstance() {
    if (instance == null) {
      instance = new UserListPanel();
    }
    return instance;
  }

  private void initComponents() {
    setBackground(Color.WHITE);
    titleLabel = new JLabel();
    contend = new JPanel();
    contend.setBackground(Color.WHITE);
    cartPanel = new JPanel();
    cartPanel.setBackground(Color.WHITE);
    contendTable = new JPanel();
    contendTable.setBackground(Color.WHITE);
    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.WHITE);
    buttonFilter = new JPanel();
    buttonFilter.setBackground(Color.WHITE);
    filterAll = new JButton();
    filterAdmin = new JButton();
    filterEmployee = new JButton();
    filterCustomer = new JButton();
    buttonAction = new JPanel();
    buttonAction.setBackground(Color.WHITE);
    buttonCreate = new JButton();
    buttonBanned = new JButton();
    tablePanel = new JPanel();
    tablePanel.setBackground(Color.WHITE);
    headerTable = new JPanel();
    headerTable.setBackground(Color.WHITE);
    serialText = new JLabel();
    nameText = new JLabel();
    phoneText = new JLabel();
    emailText = new JLabel();
    roleText = new JLabel();
    statusText = new JLabel();
    scrollPane = new JScrollPane();
    panelHeader = new JPanel();
    panelHeader.setBackground(Color.WHITE);
    panelEnd = new JPanel();
    panelEnd.setBackground(Color.WHITE);
    buttonSortAz = new JButton();
    buttonSortZa = new JButton();

    table = new JPanel();
    table.setBackground(Color.WHITE);
    label = new JLabel();

    setLayout(new BorderLayout());

    titleLabel.setText("User List");
    titleLabel.setFont(new Font("Segoe UI", 3, 18));
    titleLabel.setForeground(new Color(255, 0, 51));
    add(titleLabel, BorderLayout.NORTH);

    contend.setLayout(new BorderLayout(0, 5));

    cartPanel.setLayout(new GridLayout(1, 0, 50, 0));

    if (userModel.getRole() == UserRole.ADMIN) {
      cartUserAdmin =
        new CardPanel(
          new StatisticCardModel(
            new ImageIcon("src/main/java/resources/icons/employeeCart.png"),
            "EMPLOYEE",
            "" + quantityEmployee,
            "New Employee: " + quantityEmployeeNew
          )
        );
      cartUserAdmin.setColor(Color.decode("#FFC107"));
      cartPanel.add(cartUserAdmin);

      cartUserAdmin =
        new CardPanel(
          new StatisticCardModel(
            new ImageIcon("src/main/java/resources/icons/admin-cart.png"),
            "ADMIN",
            "" + quantityAdmin,
            "New Employee: " + quantityAdminNew
          )
        );
      cartUserAdmin.setColor(Color.decode("#4CAF50"));
      cartPanel.add(cartUserAdmin);

      cartUserAdmin =
        new CardPanel(
          new StatisticCardModel(
            new ImageIcon("src/main/java/resources/icons/customerCart.png"),
            "CUSTOMER",
            "" + quantityCustomer,
            "New Employee: " + quantityCustomerNew
          )
        );
      cartUserAdmin.setColor(Color.decode("#2196F3"));
      cartPanel.add(cartUserAdmin);
    } else {
      cartUserAdmin =
        new CardPanel(
          new StatisticCardModel(
            new ImageIcon("src/main/java/resources/icons/employeeCart.png"),
            "SALESMAN",
            "" + quantitySales,
            "New Employee: " + 0
          )
        );
      cartUserAdmin.setColor(Color.decode("#FFC107"));
      cartPanel.add(cartUserAdmin);

      cartUserAdmin =
        new CardPanel(
          new StatisticCardModel(
            new ImageIcon("src/main/java/resources/icons/customerCart.png"),
            "CUSTOMER",
            "" + quantityCustomer,
            "New Employee: " + quantityCustomerNew
          )
        );
      cartUserAdmin.setColor(Color.decode("#2196F3"));
      cartPanel.add(cartUserAdmin);
    }

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

    if (userModel.getRole() == UserRole.ADMIN) {
      filterAdmin.setText("Admin");
      filterAdmin.setToolTipText("");
      filterAdmin.setBorder(null);
      filterAdmin.addActionListener(findAdmin);
      buttonFilter.add(filterAdmin);
    }

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

    buttonSortAz.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/sortAz.png"))
    );
    buttonSortAz.addActionListener(actionSortAz);
    buttonSortAz.setBorder(null);
    buttonAction.add(buttonSortAz);

    buttonSortZa.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/sortZa.png"))
    );
    buttonSortZa.addActionListener(actionSortZa);
    buttonSortZa.setBorder(null);
    buttonAction.add(buttonSortZa);

    buttonCreate.setText("Create");
    buttonCreate.addActionListener(actionCreate);
    buttonAction.add(buttonCreate);

    buttonBanned.setText("Banned");
    buttonBanned.addActionListener(actionDelete);
    buttonAction.add(buttonBanned);

    buttonPanel.add(buttonAction, BorderLayout.EAST);

    contendTable.add(buttonPanel, BorderLayout.PAGE_START);

    tablePanel.setLayout(new BorderLayout());

    headerTable.setBorder(BorderFactory.createEtchedBorder());

    headerTable.setLayout(new GridLayout());

    panelHeader.setLayout(new GridLayout(1, 2));

    panelHeader.add(label);

    serialText.setFont(new Font("Segoe UI", 0, 16));
    serialText.setText("Serial");

    panelHeader.add(serialText);

    headerTable.add(panelHeader);

    nameText.setFont(new Font("Segoe UI", 0, 16));
    nameText.setText("Name");
    headerTable.add(nameText);

    phoneText.setFont(new Font("Segoe UI", 0, 16));
    phoneText.setText("Phone");
    headerTable.add(phoneText);

    emailText.setFont(new Font("Segoe UI", 0, 16));
    emailText.setText("Email");
    headerTable.add(emailText);

    panelEnd.setLayout(new GridLayout(1, 2));

    roleText.setFont(new Font("Segoe UI", 0, 16));
    roleText.setText("Role");
    panelEnd.add(roleText);

    statusText.setFont(new Font("Segoe UI", 0, 16));
    statusText.setText("Status");
    panelEnd.add(statusText);

    headerTable.add(panelEnd);

    tablePanel.add(headerTable, BorderLayout.PAGE_START);

    scrollPane.setViewportView(table);

    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

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
    quantitySales = 0;
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
    List<EmployeeModel> employeeList = employeeBUS.getAllModels();
    for (EmployeeModel employee : employeeList) {
      if (employee.getEmployeeType() == EmployeeType.EMPLOYEE_SALES) {
        quantitySales++;
      }
    }
  }

  public void addTable(List<UserModel> userList) {
    table.removeAll();
    table.setLayout(new GridLayout(0, 1, 0, 20));
    int serial = 1;
    if (userModel.getRole() == UserRole.ADMIN) {
      for (UserModel user : userList) {
        if (!user.getStatus().toString().equals("BANNED")) {
          UserPanel userForm = new UserPanel(serial, user);
          table.add(userForm);
          serial++;
        }
      }
    } else {
      for (UserModel user : userList) {
        if (user.getRole() != UserRole.ADMIN) {
          UserPanel userForm = new UserPanel(serial, user);
          table.add(userForm);
          serial++;
        }
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
      int deletedRows = 0;
      int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to banned users?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );

      if (choice == JOptionPane.YES_OPTION) {
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
                        UserModel userModel = Authentication.getCurrentUser();
                        if (id == userModel.getId()) {
                          JOptionPane.showMessageDialog(
                            null,
                            "You can't lock yourself here!"
                          );
                          deletedRows = 2;
                        } else {
                          deletedRows = UserBUS.getInstance().deleteModel(id);
                        }
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
        if (deletedRows == 1) {
          JOptionPane.showMessageDialog(null, " Account lock successful!");
        }
        if (deletedRows == 0) {
          JOptionPane.showMessageDialog(null, "no user selected!");
        }
        tablePanel.revalidate();
        tablePanel.repaint();
      }
    }
  };

  public ActionListener findAllUser = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      addTable(listUser);
    }
  };

  public ActionListener findAdmin = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      table.removeAll();
      table.setLayout(new GridLayout(0, 1, 0, 20));
      int serial = 1;
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
      int serial = 1;
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
      int serial = 1;
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
  public ActionListener actionSortAz = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      table.removeAll();
      modifiableUserList.sort(Comparator.comparing(UserModel::getName));
      addTable(modifiableUserList);
      table.revalidate();
      table.repaint();
    }
  };
  public ActionListener actionSortZa = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      table.removeAll();
      modifiableUserList.sort(
        Comparator.comparing(UserModel::getName).reversed()
      );
      addTable(modifiableUserList);
      table.revalidate();
      table.repaint();
    }
  };

  @Override
  public void search(String keyword) {
    table.removeAll();
    if (keyword == null || keyword.isBlank()) {
      JOptionPane.showMessageDialog(
        null,
        "Please enter your search information!"
      );
      addTable(listUser);
      this.revalidate();
      this.repaint();
    } else {
      List<UserModel> newList = UserBUS
        .getInstance()
        .searchModel(keyword, new String[] { "name" });
      if (newList.isEmpty()) {
        JOptionPane.showMessageDialog(
          null,
          "The information you entered could not be found!"
        );
        addTable(listUser);
        this.revalidate();
        this.repaint();
      } else {
        addTable(newList);
        this.revalidate();
        this.repaint();
      }
    }
  }
}
