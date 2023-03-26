package com.bookstore.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import com.bookstore.dao.UserDAO;
import com.bookstore.model.UserModel;

public class UserUI implements MouseListener {
  static UserDAO od = new UserDAO();

  JFrame frame = new JFrame();
  private JPanel title;
  private JPanel list;
  private JPanel method;
  private JPanel filtering;
  private JScrollPane scrollPane;
  private JLabel title_Now;
  private JButton buttonCreate;
  private JPanel paymentType;

  private JTable table;

  private JLabel icon;

  private JLabel valueAdmin;

  private JLabel contendRole;

  private JLabel filterCus;
  private Object[][] data;

  private JLabel valueCus;

  private JLabel valueEm;

  private JLabel filterALL;

  private JLabel filterAd;

  private JLabel filterEm;

  public UserUI() throws ClassNotFoundException, SQLException {
    frame.setPreferredSize(new Dimension(1060, 550));
    frame.setBackground(Color.BLACK);
    frame.setLayout(new FlowLayout());
    orderUI();
    count();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

  }

  public void orderUI() throws ClassNotFoundException, SQLException {
    list = new JPanel();
    list.setPreferredSize(new Dimension(1060, 550));
    list.setLayout(new FlowLayout(FlowLayout.CENTER));

    title = new JPanel();
    title.setPreferredSize(new Dimension(1030, 40));
    title.setLayout(new BorderLayout());
    // title.setBackground(Color.red);

    title_Now = new JLabel("User List", JLabel.CENTER);
    title_Now.setFont(new Font("sansserif", Font.BOLD, 18));
    title_Now.setPreferredSize(new Dimension(90, 40));
    title.add(title_Now, BorderLayout.WEST);

    buttonCreate = new JButton("Create User");
    buttonCreate.setPreferredSize(new Dimension(100, 20));
    buttonCreate.setBackground(new Color(182238));
    buttonCreate.setForeground(Color.WHITE);
    buttonCreate.setBorder(new RoundedBorder(10)); // set corner radius to 10 pixels
    buttonCreate.setOpaque(true);
    title.add(buttonCreate, BorderLayout.EAST);

    list.add(title);

    method = new JPanel();
    method.setPreferredSize(new Dimension(1060, 90));
    method.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 5));

    paymentType = new JPanel();
    paymentType.setPreferredSize(new Dimension(145, 80));
    paymentType.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
    paymentType.setLayout(new BorderLayout());

    icon = new JLabel();
    icon.setIcon(new ImageIcon(getClass().getResource("/resources/image/admin.png")));
    contendRole = new JLabel();
    contendRole.setLayout(new FlowLayout());
    title_Now = new JLabel("Admin", JLabel.CENTER);
    title_Now.setFont(new Font("sansserif", Font.BOLD, 16));
    title_Now.setPreferredSize(new Dimension(90, 20));
    valueAdmin = new JLabel("100");
    valueAdmin.setFont(new Font("sansserif", Font.BOLD, 16));
    paymentType.add(icon, BorderLayout.WEST);
    contendRole.add(title_Now);
    contendRole.add(valueAdmin);
    paymentType.add(contendRole, BorderLayout.CENTER);
    method.add(paymentType);

    paymentType = new JPanel();
    paymentType.setPreferredSize(new Dimension(145, 80));
    paymentType.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
    paymentType.setLayout(new BorderLayout());
    icon = new JLabel();
    icon.setIcon(new ImageIcon(getClass().getResource("/resources/image/customer.png")));
    contendRole = new JLabel();
    contendRole.setLayout(new FlowLayout());
    title_Now = new JLabel("Customer", JLabel.CENTER);
    title_Now.setFont(new Font("sansserif", Font.BOLD, 16));
    title_Now.setPreferredSize(new Dimension(90, 20));
    valueCus = new JLabel("100");
    valueCus.setFont(new Font("sansserif", Font.BOLD, 16));
    paymentType.add(icon, BorderLayout.WEST);
    contendRole.add(title_Now);
    contendRole.add(valueCus);
    paymentType.add(contendRole, BorderLayout.CENTER);
    method.add(paymentType);

    paymentType = new JPanel();
    paymentType.setPreferredSize(new Dimension(145, 80));
    paymentType.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
    method.add(paymentType);
    paymentType.setLayout(new BorderLayout());
    icon = new JLabel();
    icon.setIcon(new ImageIcon(getClass().getResource("/resources/image/Employee.png")));
    contendRole = new JLabel();
    contendRole.setLayout(new FlowLayout());
    title_Now = new JLabel("Employee", JLabel.CENTER);
    title_Now.setFont(new Font("sansserif", Font.BOLD, 16));
    title_Now.setPreferredSize(new Dimension(90, 20));
    valueEm = new JLabel("100");
    valueEm.setFont(new Font("sansserif", Font.BOLD, 16));
    paymentType.add(icon, BorderLayout.WEST);
    contendRole.add(title_Now);
    contendRole.add(valueEm);
    paymentType.add(contendRole, BorderLayout.CENTER);
    list.add(method);

    filtering = new JPanel();
    filtering.setPreferredSize(new Dimension(900, 40));
    // filtering.setBackground(Color.black);
    filtering.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
    filtering.setLayout(new FlowLayout(FlowLayout.LEFT));

    filterALL = new JLabel("All Users");
    filterALL.setName("All");
    filterALL.setPreferredSize(new Dimension(90, 20));
    filterALL.addMouseListener(this);
    filtering.add(filterALL);

    filterAd = new JLabel("Admin");
    filterAd.setName("Admin");
    filterAd.setPreferredSize(new Dimension(90, 20));
    filterAd.addMouseListener(this);
    filtering.add(filterAd);

    filterCus = new JLabel("Customer");
    filterCus.setName("Customer");
    filterCus.setPreferredSize(new Dimension(90, 20));
    filterCus.addMouseListener(this);
    filtering.add(filterCus);

    filterEm = new JLabel("Employee");
    filterEm.setName("Employee");
    filterEm.setPreferredSize(new Dimension(90, 20));
    filterEm.addMouseListener(this);
    filtering.add(filterEm);
    list.add(filtering, FlowLayout.RIGHT);

    filterALL.setForeground(Color.blue);
    filterAd.setForeground(Color.gray);
    filterCus.setForeground(Color.gray);
    filterEm.setForeground(Color.gray);

    ArrayList<UserModel> results = od.readDatabase();
    data = new Object[results.size()][10];

    for (int i = 0; i < results.size(); i++) {
      UserModel user = results.get(i);
      Object[] row = { user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), user.getName(),
          user.getEmail(), user.getPhone(), user.getCreatedAt(), user.getUpdatedAt(), user.getRole() };
      data[i] = row;
    }

    String[] columnNames = { "Id", "username", "password", "status", "name", "email", "phone", "createdAt",
        "updatedAt", "role" };
    table = new JTable(data, columnNames);
    table.setPreferredSize(new Dimension(900, 300));
    table.setFont(new Font("sansserif", 0, 11));
    table.getColumn("Id").setPreferredWidth(5);
    table.setRowHeight(30);
    table.setBorder(BorderFactory.createLineBorder(Color.black));
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(1070, 300));
    scrollPane.setBackground(Color.green);
    list.add(table);
    frame.add(list);
  }

  public void count() {
    int cusvalue = 0;
    int advalue = 0;
    int emvalue = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i][9] != null && data[i][9].toString().equals("customer")) {
        cusvalue = cusvalue + 1;
      }
      if (data[i][9] != null && data[i][9].toString().equals("admin")) {
        advalue = advalue + 1;
      }
      if (data[i][9] != null && data[i][9].toString().equals("employee")) {
        emvalue = emvalue + 1;
      }
    }
    valueCus.setText(String.valueOf(cusvalue));
    valueAdmin.setText(String.valueOf(advalue));
    valueEm.setText(String.valueOf(emvalue));

  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    new UserUI();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    filterALL.setForeground(Color.gray);
    filterAd.setForeground(Color.gray);
    filterCus.setForeground(Color.gray);
    filterEm.setForeground(Color.gray);
    e.getComponent().setForeground(Color.blue);

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
