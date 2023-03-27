package com.bookstore.gui.form.admin;

import javax.swing.JFrame;

public class DashboardForm extends JFrame {
  public DashboardForm() {
    initComponent();
  }

  private void initComponent() {
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Dashboard");
    setResizable(false);
    setSize(new java.awt.Dimension(800, 600));
    setLocationRelativeTo(null);
  }
}
