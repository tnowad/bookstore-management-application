package com.bookstore.gui.form.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;

import javax.swing.*;

import com.bookstore.gui.form.admin.component.bookListComponent.BrowseProductPanel;
import com.bookstore.gui.form.admin.component.dashboardComponent.DashboardPanel;
import com.bookstore.gui.form.admin.component.userListComponent.UserComponent;
import com.bookstore.gui.form.admin.menu.MenuForm;

public class RunForm extends JFrame {
  private JPanel MenuAdmin;
  private JPanel Construct;


  public RunForm() throws ClassNotFoundException, SQLException {
    initComponents();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void initComponents() throws ClassNotFoundException, SQLException {

    setLayout(new BorderLayout());
    setSize(new Dimension(1000, 560));
    setPreferredSize(new Dimension(1000, 560));

    MenuForm menuForm = new MenuForm();
    add(menuForm, BorderLayout.WEST);
    revalidate();
    repaint();

    Construct = new JPanel();
    MenuAdmin = new JPanel();

    Construct.setLayout(new BorderLayout());
    add(Construct, BorderLayout.CENTER);

    MenuAdmin.setBackground(Color.red);
    MenuAdmin.setPreferredSize(new Dimension(50, 50));

    DashboardPanel dashboardPanel = new DashboardPanel();
    UserComponent userComponent = new UserComponent();
    BrowseProductPanel browseProductPanel = new BrowseProductPanel();

    Construct.add(MenuAdmin, BorderLayout.NORTH);
    Construct.add(browseProductPanel, BorderLayout.CENTER);
  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      new RunForm();
    }

  }

}
