package com.bookstore.gui.form.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.*;

import com.bookstore.gui.form.admin.component.UserComponent;

public class RunForm extends JFrame {
  private JPanel MenuAdmin;
  private JPanel Construct;
  private JPanel HeaderAdmin;
  private JPanel Contend;

  public RunForm() throws ClassNotFoundException, SQLException {
    initComponents();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void initComponents() throws ClassNotFoundException, SQLException {

    setLayout(new FlowLayout());
    setSize(new Dimension(920, 560));
    MenuAdmin = new JPanel();
    MenuAdmin.setPreferredSize(new Dimension(180, 500));
    MenuAdmin.setBackground(Color.BLUE);
    add(MenuAdmin);

    Construct = new JPanel();
    Construct.setPreferredSize(new Dimension(700, 500));
    Construct.setLayout(new FlowLayout());
    HeaderAdmin = new JPanel();
    Contend = new JPanel();

    HeaderAdmin.setBackground(Color.red);
    HeaderAdmin.setPreferredSize(new Dimension(680, 40));

    Contend.setPreferredSize(new Dimension(700, 460));
    UserComponent userComponent = new UserComponent();
    Contend.add(userComponent);

    Construct.add(HeaderAdmin);
    Construct.add(Contend);
    add(Construct);
    revalidate();
    repaint();

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
