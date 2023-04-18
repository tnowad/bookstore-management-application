package com.bookstore.gui.form.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import com.bookstore.gui.component.panel.MainPanel;
import com.bookstore.gui.form.admin.component.bookListComponent.BrowseProductPanel;
import com.bookstore.gui.form.admin.component.dashboardComponent.DashboardPanel;
import com.bookstore.gui.form.admin.component.userListComponent.UserComponent;
import com.bookstore.gui.form.admin.menu.HeaderForm;
import com.bookstore.gui.form.admin.menu.MenuForm;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class RunForm extends JFrame {
  private static RunForm instance;
  private static JPanel Construct;

  public static JPanel getConstruct() {
    return Construct;
  }

  public void setConstruct(JPanel construct) {
    Construct = construct;
  }

  public static RunForm getInstance() {
    if (instance == null) {
      instance = new RunForm();
    }
    return instance;
  }

  public RunForm() {
    initComponents();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    setSize(new Dimension(1000, 560));
    setPreferredSize(new Dimension(1000, 560));

    Construct = new JPanel();
    Construct.setLayout(new BorderLayout());

    add(MenuForm.getInstance(), BorderLayout.WEST);
    add(Construct, BorderLayout.CENTER);

    Construct.add(HeaderForm.getInstance(), BorderLayout.NORTH);
    Construct.add(DashboardPanel.getInstance(), BorderLayout.CENTER);

  }

  public void action(String nameButton) {
    switch (nameButton) {
    case "Dashboard":
    Construct.add(DashboardPanel.getInstance(), BorderLayout.CENTER);
    break;
    case "UserList":
    removeAll();
    Construct.add(UserComponent.getInstance(), BorderLayout.CENTER);
    break;
    case "BookList":
    Construct.removeAll();
    Construct.add(BrowseProductPanel.getInstance(), BorderLayout.CENTER);
    break;
    }

  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
        | UnsupportedLookAndFeelException ignored) {
    }
    new RunForm();
  }
}
