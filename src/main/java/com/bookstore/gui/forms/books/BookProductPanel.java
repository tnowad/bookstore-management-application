package com.bookstore.gui.forms.books;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookProductPanel extends javax.swing.JPanel {

  public BookProductPanel() {
    initComponents();
  }

  private void initComponents() {
    bookTitleLabel = new JLabel();
    bookImagePanel = new JPanel();
    bookDetailButton = new JButton();

    setLayout(new java.awt.BorderLayout());

    bookTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));
    bookTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    bookTitleLabel.setText("Name book");
    add(bookTitleLabel, java.awt.BorderLayout.PAGE_START);
    bookTitleLabel.getAccessibleContext().setAccessibleDescription("");

    javax.swing.GroupLayout bookImagePanelLayout = new GroupLayout(
      bookImagePanel
    );
    bookImagePanel.setLayout(bookImagePanelLayout);
    bookImagePanelLayout.setHorizontalGroup(
      bookImagePanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 220, Short.MAX_VALUE)
    );
    bookImagePanelLayout.setVerticalGroup(
      bookImagePanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 207, Short.MAX_VALUE)
    );

    add(bookImagePanel, java.awt.BorderLayout.CENTER);

    bookDetailButton.setText("Book Detail");
    add(bookDetailButton, java.awt.BorderLayout.PAGE_END);
  }

  private JButton bookDetailButton;
  private JPanel bookImagePanel;
  private JLabel bookTitleLabel;
}
