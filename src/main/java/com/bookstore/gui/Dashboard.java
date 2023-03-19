package com.bookstore.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Dashboard extends JFrame {
  private JPanel list;
  private JLabel title_Now;
  private JPanel button;
  private JButton button_New;
  private JProgressBar progressBar;
  private JTable jTableCart;
  private JScrollPane scrollPane;
  private JLabel endDashboard;

  public JPanel initDashboard() {
    list = new JPanel(); // tổng thể dashboard
    list.setLayout(new FlowLayout());

    title_Now = new JLabel("Dashboard"); // cái chữ dashboard
    title_Now.setFont(new Font("sansserif", 0, 18));
    title_Now.setPreferredSize(new Dimension(1000, 30));
    title_Now.setBorder(new EmptyBorder(0, 10, 0, 0));
    list.add(title_Now);

    button = new JPanel(); // button này là chứa mấy cái new oder new user ....
    button.setPreferredSize(new Dimension(1000, 100));
    button.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    button_New = new JButton(); // tạo cái new user
    button_New.setPreferredSize(new Dimension(300, 90));
    button_New.setLayout(new GridLayout(3, 1));
    title_Now = new JLabel("New User");
    title_Now.setFont(new Font("sansserif", 0, 17));
    button_New.add(title_Now);

    title_Now = new JLabel("17743"); // show user mới
    button_New.add(title_Now);
    button.add(button_New);

    title_Now = new JLabel(); // thanh %
    title_Now.setPreferredSize(new Dimension(250, 3));
    progressBar = new JProgressBar();
    progressBar.setMinimum(0);
    progressBar.setMaximum(100);
    progressBar.setStringPainted(true);
    progressBar.setValue(40);
    progressBar.setPreferredSize(new Dimension(250, 10));
    button_New.add(progressBar);

    button_New = new JButton(); // tạo cái new oder
    button_New.setPreferredSize(new Dimension(300, 90));
    button_New.setLayout(new GridLayout(3, 1));
    title_Now = new JLabel("New Order");
    title_Now.setFont(new Font("sansserif", 0, 17));
    button_New.add(title_Now);

    title_Now = new JLabel("17743"); // set giá trị
    button_New.add(title_Now);
    button.add(button_New);

    title_Now = new JLabel();// thanh %
    title_Now.setPreferredSize(new Dimension(250, 3));
    progressBar = new JProgressBar();
    progressBar.setMinimum(0);
    progressBar.setMaximum(100);
    progressBar.setStringPainted(true);
    progressBar.setValue(40);
    progressBar.setPreferredSize(new Dimension(250, 10));
    button_New.add(progressBar);

    button_New = new JButton(); // tạo cái income
    button_New.setPreferredSize(new Dimension(300, 90));
    button_New.setLayout(new GridLayout(3, 1));
    title_Now = new JLabel("Income");
    title_Now.setFont(new Font("sansserif", 0, 17));
    button_New.add(title_Now);

    title_Now = new JLabel("17743"); // giá trị income
    button_New.add(title_Now);
    button.add(button_New);

    title_Now = new JLabel(); // thanh %
    title_Now.setPreferredSize(new Dimension(250, 3));
    progressBar = new JProgressBar();
    progressBar.setMinimum(0);
    progressBar.setMaximum(100);
    progressBar.setStringPainted(true);
    progressBar.setValue(40);
    progressBar.setPreferredSize(new Dimension(250, 10));
    button_New.add(progressBar);

    list.add(button); // thêm nó vô

    title_Now = new JLabel("Data user"); // chữ data user
    title_Now.setFont(new Font("sansserif", 0, 18));
    title_Now.setBorder(new EmptyBorder(0, 10, 0, 0));
    title_Now.setPreferredSize(new Dimension(1000, 50));
    list.add(title_Now);

    endDashboard = new JLabel();
    endDashboard.setLayout(new FlowLayout());
    endDashboard.setPreferredSize(new Dimension(1000, 253));

    // set data
    Object[][] data = {
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },
        { "005", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002", "employee" },

        { "001", "admin", "12345", "Online", "admin", "admin@gmail.com", "113", "31-2-2001", "31-2-2002",
            "employee" } };
    String[] columnNames = { "Id", "username", "password", "status", "name", "email", "phone", "createdAt", "updatedAt",
        "role" };
    jTableCart = new JTable(data, columnNames);
    jTableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    scrollPane = new JScrollPane(jTableCart);
    scrollPane.setPreferredSize(new Dimension(900, 253));
    endDashboard.add(scrollPane);

    list.add(endDashboard);
    list.setName("dashboard");

    return list;
  }

}
