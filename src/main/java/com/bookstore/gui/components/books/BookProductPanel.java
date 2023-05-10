package com.bookstore.gui.components.books;

import com.bookstore.models.BookModel;
import com.bookstore.util.image.ImageUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class BookProductPanel extends JPanel {

  private JTextField isbnBook;
  private BookModel bookModel;
  private JButton buttonDetail;
  private JCheckBox checkBox;
  private JPanel contendPanel;
  private JLabel setImage;
  private JTextField setTitle;
  private JPanel titlePanel;

  public BookProductPanel(BookModel book) {
    this.bookModel = book;
    initComponents(book);
    setImage(book.getImage());
  }

  private void initComponents(BookModel book) {
    checkBox = new JCheckBox();
    contendPanel = new JPanel();
    buttonDetail = new JButton();
    setImage = new JLabel();
    titlePanel = new JPanel();
    setTitle = new JTextField();

    isbnBook = new JTextField();
    isbnBook.setText("" + book.getIsbn());
    add(isbnBook);

    setBackground(new Color(255, 255, 255));
    setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
    setPreferredSize(new Dimension(199, 333));
    setLayout(new BorderLayout());

    checkBox.setPreferredSize(new Dimension(50, 19));
    add(checkBox, BorderLayout.PAGE_END);

    contendPanel.setLayout(new BorderLayout());
    buttonDetail.setFont(new Font("Segoe UI", 1, 14));
    buttonDetail.setText("Detail");
    buttonDetail.addActionListener(actionDetail);
    contendPanel.add(buttonDetail, BorderLayout.PAGE_END);

    setImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    contendPanel.add(setImage, BorderLayout.CENTER);

    titlePanel.setMinimumSize(new Dimension(30, 30));

    setTitle.setFont(new Font("Segoe UI", 1, 14));
    setTitle.setText(book.getTitle());
    setTitle.setPreferredSize(new Dimension(220, 22));
    setTitle.setEditable(false);
    titlePanel.add(setTitle);

    contendPanel.add(titlePanel, BorderLayout.PAGE_START);

    add(contendPanel, BorderLayout.CENTER);
  }

  public void actionDetail(BookModel book) {}

  public void setImage(String image) {
    try {
      Image imageBase = ImageUtils.decodeFromBase64(image);
      setImage.setIcon(new ImageIcon(imageBase));
    } catch (Exception ex) {
      setImage.setIcon(
        new ImageIcon("src/main/java/resources/images/product-placeholder.png")
      );
    }
  }

  public ActionListener actionDetail = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      BookDetailFrame bookDetailFrame = new BookDetailFrame(bookModel);
      bookDetailFrame.setVisible(true);
    }
  };
}
