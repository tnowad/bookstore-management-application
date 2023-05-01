package com.bookstore.gui.components.book;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author yanti
 */
public class BookProductPanel extends javax.swing.JPanel {

  private JTextField isbnBook;

  public BookProductPanel(
    String isbn,
    String title,
    String description,
    String image,
    int price,
    int quantity,
    Enum status,
    int publisher_id,
    int author_id
  ) {
    initComponents(
      isbn,
      title,
      description,
      image,
      price,
      quantity,
      status,
      publisher_id,
      author_id
    );
    setImage(image);
  }

  @SuppressWarnings("unchecked")
  private void initComponents(
    String isbn,
    String title,
    String description,
    String image,
    int price,
    int quantity,
    Enum status,
    int publisher_id,
    int author_id
  ) {
    checkBox = new javax.swing.JCheckBox();
    contendPanel = new javax.swing.JPanel();
    buttonDetail = new javax.swing.JButton();
    setImage = new javax.swing.JLabel();
    titlePanel = new javax.swing.JPanel();
    setTitle = new javax.swing.JTextField();

    isbnBook = new javax.swing.JTextField();
    isbnBook.setText("" + isbn);
    add(isbnBook);

    setBackground(new java.awt.Color(255, 255, 255));
    setBorder(
      javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))
    );
    setMaximumSize(new java.awt.Dimension(199, 333));
    setMinimumSize(new java.awt.Dimension(199, 333));
    setPreferredSize(new java.awt.Dimension(199, 333));
    setLayout(new java.awt.BorderLayout());

    checkBox.setPreferredSize(new java.awt.Dimension(50, 19));
    add(checkBox, java.awt.BorderLayout.PAGE_END);

    contendPanel.setLayout(new java.awt.BorderLayout());

    buttonDetail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    buttonDetail.setText("Detail");
    buttonDetail.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          actionDetail(
            isbn,
            title,
            description,
            image,
            price,
            quantity,
            status,
            publisher_id,
            author_id
          );
        }
      }
    );
    contendPanel.add(buttonDetail, java.awt.BorderLayout.PAGE_END);

    setImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setImage.setPreferredSize(new java.awt.Dimension(399, 260));
    contendPanel.add(setImage, java.awt.BorderLayout.CENTER);

    titlePanel.setMinimumSize(new java.awt.Dimension(30, 30));

    setTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    setTitle.setText(title);
    setTitle.setPreferredSize(new java.awt.Dimension(220, 22));
    titlePanel.add(setTitle);

    contendPanel.add(titlePanel, java.awt.BorderLayout.PAGE_START);

    add(contendPanel, java.awt.BorderLayout.CENTER);
  }

  public void actionDetail(
    String isbn,
    String title,
    String description,
    String image,
    int price,
    int quantity,
    Enum status,
    int publisher_id,
    int author_id
  ) {
    BookDetailFrame bookDetailFrame = new BookDetailFrame(
      isbn,
      title,
      description,
      image,
      price,
      quantity,
      status,
      publisher_id,
      author_id
    );
    bookDetailFrame.setVisible(true);
  }

  public void setImage(String image) {
    setImage.setIcon(new javax.swing.ImageIcon(image));
    ImageIcon icon = (ImageIcon) setImage.getIcon();
    int imageLoadStatus = icon.getImageLoadStatus();
    if (imageLoadStatus != 8) {
      setImage.setIcon(
        new javax.swing.ImageIcon(
          getClass().getResource("/resources/images/product-placeholder.png")
        )
      );
    }
  }

  private javax.swing.JButton buttonDetail;
  private javax.swing.JCheckBox checkBox;
  private javax.swing.JPanel contendPanel;
  private javax.swing.JLabel setImage;
  private javax.swing.JTextField setTitle;
  private javax.swing.JPanel titlePanel;
}
