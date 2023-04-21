package com.bookstore.gui.form.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


import com.bookstore.gui.form.admin.component.dashboardComponent.DashboardPanel;
import com.bookstore.gui.form.admin.menu.HeaderForm;
import com.bookstore.gui.form.admin.menu.MenuForm;
import com.formdev.flatlaf.themes.FlatMacLightLaf;


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
    HeaderForm.getInstance().setPanelNow("DashBoard");

  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    new RunForm();
  }
}
