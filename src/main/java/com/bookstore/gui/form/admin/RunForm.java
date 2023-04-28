package com.bookstore.gui.form.admin;

import com.bookstore.gui.component.header.HeaderAdmin;
import com.bookstore.gui.component.menu.MenuAdmin;
import com.bookstore.gui.form.admin.component.dashboard.DashboardPanel;
import com.bookstore.services.CheckCurrentUser;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

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
    dispose();
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    setSize(new Dimension(1000, 560));
    setPreferredSize(new Dimension(1000, 560));

    Construct = new JPanel();
    Construct.setLayout(new BorderLayout());

    add(MenuAdmin.getInstance(), BorderLayout.WEST);
    add(Construct, BorderLayout.CENTER);

    Construct.add(HeaderAdmin.getInstance(), BorderLayout.NORTH);
    Construct.add(DashboardPanel.getInstance(), BorderLayout.CENTER);
    HeaderAdmin.getInstance().setPanelNow("DashBoard");
  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    new RunForm();
  }
}
