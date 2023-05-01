package com.bookstore.gui.form.user;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.form.book.BookList;
import com.bookstore.gui.form.imports.ImportList;
import com.bookstore.gui.form.order.OrderList;
import com.bookstore.gui.main.RegisterUI;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.EmployeeModel.EmployeeType;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SalesmanFrame extends JFrame {

  private Button cartButton;
  private Button customerListButton;
  private Button bookListButton;
  private Button pendingOrderButton;
  private Button employeeListButton;
  private Button importListButton;
  private Button accountButton;
  private Button aboutUsButton;
  private Button contactButton;
  private Button logoutButton;
  private JPanel contentPanel;
  private JPanel headerPanel;
  private JPanel menuBottomPanel;
  private JPanel menuPanel;
  private JPanel menuTopPanel;
  private JLabel statusLabel;
  private JLabel welcomeLabel;

  UserBUS userBus = UserBUS.getInstance();
  UserModel currentUser = Authentication.getCurrentUser();
  EmployeeBUS employeeBUS = EmployeeBUS.getInstance();
  EmployeeModel employeeModel = employeeBUS.getModelById(currentUser.getId());

  public SalesmanFrame() {
    initFrame();
    initComponents();
    handleEvent();
  }

  private void initFrame() {
    setPreferredSize(new Dimension(1200, 700));
    setVisible(true);
    pack();
    setLocationRelativeTo(null);
  }

  private void initComponents() {
    headerPanel = new JPanel();
    welcomeLabel = new JLabel();
    statusLabel = new JLabel();
    cartButton = new Button("Cart");
    menuPanel = new JPanel();
    menuTopPanel = new JPanel();
    employeeListButton = new Button("Employee List");
    customerListButton = new Button("Customer List");
    pendingOrderButton = new Button("Pending Order");
    bookListButton = new Button("Book List");
    importListButton = new Button("Import List");
    menuBottomPanel = new JPanel();
    accountButton = new Button("Account settings");
    contactButton = new Button("Contact us");
    aboutUsButton = new Button("About us");
    logoutButton = new Button("Logout");
    contentPanel = new JPanel();

    contentPanel.add(new CustomerList());

    headerPanel.setAlignmentX(1.0F);
    headerPanel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
    headerPanel.setDoubleBuffered(false);
    headerPanel.setMaximumSize(new Dimension(832, 80));
    headerPanel.setMinimumSize(new Dimension(413, 40));
    headerPanel.setPreferredSize(new Dimension(672, 80));

    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    welcomeLabel.setText("Welcome, ");
    welcomeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    welcomeLabel.setMaximumSize(new Dimension(380, 16));
    welcomeLabel.setMinimumSize(new Dimension(300, 16));
    welcomeLabel.setPreferredSize(new Dimension(300, 30));

    headerPanel.add(welcomeLabel);

    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    statusLabel.setText("Status : ");
    statusLabel.setMaximumSize(new Dimension(500, 16));
    statusLabel.setPreferredSize(new Dimension(300, 16));
    headerPanel.add(statusLabel);

    headerPanel.add(cartButton);

    getContentPane().add(headerPanel, BorderLayout.PAGE_START);

    menuPanel.setPreferredSize(new Dimension(200, 399));
    menuPanel.setLayout(new GridLayout(2, 1, 0, 150));

    if (employeeModel.getEmployeeType() == EmployeeType.EMPLOYEE_MANAGER) {
      menuTopPanel.setLayout(new GridLayout(5, 1, 0, 5));
      menuTopPanel.add(employeeListButton);
    } else {
      menuTopPanel.setLayout(new GridLayout(4, 1, 0, 5));
    }

    menuTopPanel.add(customerListButton);

    menuTopPanel.add(pendingOrderButton);

    menuTopPanel.add(bookListButton);

    menuTopPanel.add(importListButton);

    menuPanel.add(menuTopPanel);

    menuBottomPanel.setLayout(new GridLayout(4, 1, 0, 5));

    menuBottomPanel.add(accountButton);

    menuBottomPanel.add(contactButton);

    menuBottomPanel.add(aboutUsButton);

    menuBottomPanel.add(logoutButton);

    menuPanel.add(menuBottomPanel);

    getContentPane().add(menuPanel, BorderLayout.LINE_START);

    contentPanel.setLayout(new CardLayout());
    getContentPane().add(contentPanel, BorderLayout.CENTER);
  }

  private void handleEvent() {
    employeeListButton.addActionListener(e -> {
      contentPanel.removeAll();
      contentPanel.add(new EmployeeList());
      contentPanel.revalidate();
      contentPanel.repaint();
    });

    customerListButton.addActionListener(e -> {
      contentPanel.removeAll();
      contentPanel.add(new CustomerList());
      contentPanel.revalidate();
      contentPanel.repaint();
    });

    pendingOrderButton.addActionListener(e -> {
      contentPanel.removeAll();
      contentPanel.add(new OrderList());
      contentPanel.revalidate();
      contentPanel.repaint();
    });

    bookListButton.addActionListener(e -> {
      contentPanel.removeAll();
      contentPanel.add(new BookList());
      contentPanel.revalidate();
      contentPanel.repaint();
    });

    importListButton.addActionListener(e -> {
      contentPanel.removeAll();
      contentPanel.add(new ImportList());
      contentPanel.revalidate();
      contentPanel.repaint();
    });

    accountButton.addActionListener(e -> {
      contentPanel.removeAll();
      contentPanel.add(new com.bookstore.gui.form.account.AccountPanel());
      contentPanel.revalidate();
      contentPanel.repaint();
    });

    logoutButton.addActionListener(e -> {
      int option = JOptionPane.showConfirmDialog(
        null,
        "Bạn chắc chắn muốn đăng xuất?",
        "Đăng xuất",
        JOptionPane.OK_OPTION
      );
      if (option == 0) {
        dispose();
        RegisterUI loginFrame = new RegisterUI();
        loginFrame.setVisible(true);
      }
    });
  }

  public static void main(String args[]) {
    try {
      UIManager.setLookAndFeel(
        "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
      );
      UIManager.put("Panel.background", new Color(250, 250, 250));
    } catch (
      ClassNotFoundException
      | IllegalAccessException
      | InstantiationException
      | UnsupportedLookAndFeelException ignored
    ) {}
    FlatMacLightLaf.setup();
    EventQueue.invokeLater(
      new Runnable() {
        public void run() {
          new SalesmanFrame();
        }
      }
    );
  }
}
