package com.bookstore.gui;

import java.awt.*;
import javax.swing.*;

public class CartUI extends JFrame {

  private JLabel jLabelCartTitle, jLabelBookTitle, jLabelQuantity, jLabelPrice, jLabelTotalPrice;
  private JTextField jTextFieldSearch;
  private JButton jButtonAdd, jButtonRemove, jButtonCheckout;

  private JTable jTableCart;
  private JScrollPane jScrollPane;

  public CartUI() {
    setPreferredSize(new Dimension(1160, 550));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("Giỏ Hàng");
    setBackground(Color.WHITE);
    this.setVisible(true);
    init();
    pack();
  }

  public void init() {
    // Title Label
    jLabelCartTitle = new JLabel("Giỏ Hàng");
    jLabelCartTitle.setFont(new Font("sansserif", Font.BOLD, 25));

    // Search TextField
    jTextFieldSearch = new JTextField(20);

    // Add Button
    jButtonAdd = new JButton("Thêm sách");

    // Remove Button
    jButtonRemove = new JButton("Xóa");

    // Checkout Button
    jButtonCheckout = new JButton("Thanh toán");

    // Table and ScrollPane
    Object[][] data = {
        { "Sách 1", "2", "50,000", "100,000" },
        { "Sách 2", "1", "75,000", "75,000" } };
    String[] columnNames = { "Tên Sách", "Số Lượng", "Đơn Giá", "Thành Tiền" };
    jTableCart = new JTable(data, columnNames);
    jTableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jScrollPane = new JScrollPane(jTableCart);

    // Quantity Label
    jLabelQuantity = new JLabel("Tổng số lượng: 3");

    // Total Price Label
    jLabelTotalPrice = new JLabel("Tổng tiền: 175,000 đ");

    // Layout Settings
    setLayout(new BorderLayout());
    JPanel contentTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel contentCenter = new JPanel(new BorderLayout());
    JPanel contentBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));

    contentTop.add(jLabelCartTitle);
    contentTop.add(Box.createHorizontalStrut(10));
    contentTop.add(jTextFieldSearch);
    contentTop.add(jButtonAdd);

    contentCenter.add(jScrollPane, BorderLayout.CENTER);
    contentCenter.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

    contentBottom.add(jButtonRemove);
    contentBottom.add(Box.createHorizontalStrut(20));
    contentBottom.add(jButtonCheckout);
    contentBottom.add(Box.createHorizontalStrut(180));
    contentBottom.add(jLabelQuantity);
    contentBottom.add(Box.createHorizontalStrut(20));
    contentBottom.add(jLabelTotalPrice);

    add(contentTop, BorderLayout.NORTH);
    add(contentCenter, BorderLayout.CENTER);
    add(contentBottom, BorderLayout.SOUTH);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new CartUI());
  }
}
