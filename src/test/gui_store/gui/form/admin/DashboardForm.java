package com.bookstore.gui.form.admin;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.bookstore.gui.component.HeaderDashboard;

public class DashboardForm extends JPanel {
  public DashboardForm() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    FormHome formHome = new FormHome();
    HeaderDashboard headerDashboard = new HeaderDashboard();
    add(headerDashboard);
    add(formHome);
  }

}
