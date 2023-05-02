package com.bookstore.gui.forms.users;


import com.bookstore.gui.components.dashboards.DashboardPanel;
import com.bookstore.gui.components.headers.HeaderAdmin;
import com.bookstore.gui.components.menus.MenuAdmin;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

public class AdminFrame extends JFrame {

  private static AdminFrame instance;
  private static JPanel Construct;

  public static JPanel getConstruct() {
    return Construct;
  }

  public void setConstruct(JPanel construct) {
    Construct = construct;
  }

  public static AdminFrame getInstance() {
    if (instance == null) {
      instance = new AdminFrame();
    }
    return instance;
  }

  public AdminFrame() {
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

    add(MenuAdmin.getInstance(), BorderLayout.WEST);
    add(Construct, BorderLayout.CENTER);

    Construct.add(HeaderAdmin.getInstance(), BorderLayout.NORTH);
    Construct.add(DashboardPanel.getInstance(), BorderLayout.CENTER);
    HeaderAdmin.getInstance().setPanelNow("DashBoard");
  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    new AdminFrame();
  }
}
