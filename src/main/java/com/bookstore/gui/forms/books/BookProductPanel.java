package com.bookstore.gui.forms.books;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BookProductPanel extends JPanel {

  private JButton bookDetailButton;
  private JPanel bookImagePanel;
  private JLabel bookTitleLabel;

  public BookProductPanel() {
    initComponents();
  }

  private void initComponents() {
    bookTitleLabel = new JLabel();
    bookImagePanel = new JPanel();
    bookDetailButton = new JButton();

    setLayout(new BorderLayout());

    bookTitleLabel.setFont(new Font("Segoe UI", 0, 14));
    bookTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    bookTitleLabel.setText("Name book");
    add(bookTitleLabel, BorderLayout.PAGE_START);
    bookTitleLabel.getAccessibleContext().setAccessibleDescription("");

    GroupLayout bookImagePanelLayout = new GroupLayout(bookImagePanel);
    bookImagePanel.setLayout(bookImagePanelLayout);
    bookImagePanelLayout.setHorizontalGroup(
      bookImagePanelLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 220, Short.MAX_VALUE)
    );
    bookImagePanelLayout.setVerticalGroup(
      bookImagePanelLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 207, Short.MAX_VALUE)
    );

    add(bookImagePanel, BorderLayout.CENTER);

    bookDetailButton.setText("Book Detail");
    add(bookDetailButton, BorderLayout.PAGE_END);
  }
}
