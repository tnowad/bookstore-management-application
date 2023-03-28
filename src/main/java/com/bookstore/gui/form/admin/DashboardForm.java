package com.bookstore.gui.form.admin;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.bookstore.gui.component.HeaderDashboard;

public class DashboardForm extends JFrame {
  public DashboardForm() {
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

    FormHome formHome = new FormHome();
    HeaderDashboard headerDashboard = new HeaderDashboard();
    container.add(headerDashboard);
    container.add(formHome);
    add(container);
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
