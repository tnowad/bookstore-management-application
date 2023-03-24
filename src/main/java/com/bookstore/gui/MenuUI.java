package com.bookstore.gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class MenuUI extends JFrame implements MouseListener {

  Dashboard db = new Dashboard();
  UserUI us = new UserUI();
  JFrame frame = new JFrame();
  private JPanel homeMenu;
  private JLabel jButton_Menu;

  private JLabel jButton_MenuItem;
  private JLabel jButton_MenuTable;
  private JLabel jLogo;

  public JPanel initMenu() {
    homeMenu = new JPanel();
    homeMenu.setPreferredSize(new Dimension(150, 1000));
    homeMenu.setLayout(new GridLayout(10, 1));

    jLogo = new JLabel("Book Store");
    jLogo.setFont(new Font("sansserif", Font.BOLD, 20));
    jLogo.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/logo.png")));
    homeMenu.add(jLogo);

    jButton_Menu = new JLabel("Dashboard");
    jButton_Menu.setName("Dashboard");
    jButton_Menu.setFont(new Font("sansserif", 0, 12));
    jButton_Menu.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/dashboard.png")));
    jButton_Menu.addMouseListener(this);
    homeMenu.add(jButton_Menu);

    jButton_Menu = new JLabel("Table");
    jButton_Menu.setName("Table");
    jButton_Menu.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/table.png")));
    jButton_Menu.setFont(new Font("sansserif", 0, 12));
    jButton_Menu.addMouseListener(this);
    homeMenu.add(jButton_Menu);

    jButton_MenuTable = new JLabel();
    jButton_MenuTable.setLayout(new GridLayout(4, 1));

    jButton_MenuItem = new JLabel("Product");
    jButton_MenuItem.setName("Product");
    jButton_MenuItem.setFont(new Font("sansserif", 0, 11));
    jButton_MenuItem.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/dot.png")));
    jButton_MenuItem.addMouseListener(this);
    jButton_MenuTable.add(jButton_MenuItem);

    jButton_MenuItem = new JLabel("User");
    jButton_MenuItem.setName("User");
    jButton_MenuItem.setFont(new Font("sansserif", 0, 11));
    jButton_MenuItem.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/dot.png")));
    jButton_MenuTable.add(jButton_MenuItem);

    jButton_MenuItem = new JLabel("Order");
    jButton_MenuItem.setName("Order");
    jButton_MenuItem.setFont(new Font("sansserif", 0, 11));
    jButton_MenuItem.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/dot.png")));
    jButton_MenuTable.add(jButton_MenuItem);

    jButton_MenuItem = new JLabel("Import");
    jButton_MenuItem.setName("Import");
    jButton_MenuItem.setFont(new Font("sansserif", 0, 11));
    jButton_MenuItem.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/dot.png")));
    jButton_MenuTable.add(jButton_MenuItem);

    jButton_MenuTable.setBorder(new EmptyBorder(0, 14, 0, 0));
    jButton_MenuTable.setName("MenuTable");
    // homeMenu.add(jButton_MenuTable);

    jButton_Menu = new JLabel("Messenger");
    jButton_Menu.setName("Messenger");
    jButton_Menu.setFont(new Font("sansserif", 0, 12));
    jButton_Menu.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/messenger.png")));
    homeMenu.add(jButton_Menu);

    jButton_Menu = new JLabel("Report");
    jButton_Menu.setName("Report");
    jButton_Menu.setFont(new Font("sansserif", 0, 12));
    jButton_Menu.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/report.png")));
    homeMenu.add(jButton_Menu);

    jButton_Menu = new JLabel("Contact");
    jButton_Menu.setName("Contact");
    jButton_Menu.setFont(new Font("sansserif", 0, 12));
    jButton_Menu.setIcon(
        new ImageIcon(getClass().getResource("/resources/image/contact.png")));
    homeMenu.add(jButton_Menu);

    homeMenu.setBackground(new Color(182238));
    homeMenu.setBorder(new EmptyBorder(0, 7, 0, 0));
    return homeMenu;
  }

  public void actionMenu(MouseEvent e) {
    Component component = (Component) e.getSource();
    String name = component.getName();

    Container contentPane = frame.getContentPane(); // frame là một đối tượng JFrame
    Component[] components = contentPane.getComponents(); // Lấy tất cả các thành phần con
    if (name.equals("User")) {
      frame.getContentPane().remove(components[2]);
      frame.getContentPane().add(us.User(), BorderLayout.CENTER);
      System.out.println(components[2].getName());
      frame.revalidate();
      frame.repaint();
    }
    if (name.equals("Dashboard")) {
      frame.getContentPane().remove(components[2]);
      frame.getContentPane().add(db.initDashboard(), BorderLayout.CENTER);
      System.out.println(components[2].getName());
      frame.revalidate();
      frame.repaint();
    }
  }

  public void setMenuItem(MouseEvent e) {
    Component component = (Component) e.getSource();
    String name = component.getName();

    Component[] components = homeMenu.getComponents();
    String name2 = components[3].getName();

    if (name.equals("Table")) {
      if (name2.equals("Messenger")) {
        homeMenu.add(jButton_MenuTable, 3);
      } else {
        homeMenu.remove(jButton_MenuTable);
      }
      homeMenu.revalidate();
      homeMenu.repaint();
    }
  }

  public static void main(String[] args) {
    new MenuUI().setVisible(true);
    ;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    setMenuItem(e);
    actionMenu(e);
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

}
