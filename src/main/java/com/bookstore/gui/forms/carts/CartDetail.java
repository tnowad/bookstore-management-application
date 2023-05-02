package com.bookstore.gui.forms.carts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CartDetail extends JPanel {

    private static CartDetail instance;


  private CartDetail() {
    initComponents();
  }

  public static CartDetail getInstance() {
    if (instance == null) {
      instance = new CartDetail();
    }
    return instance; 
  }

  private void initComponents() {
    cententProductPanel = new JPanel();
    checkboxPanel = new JPanel();
    chooseBookCheckBox = new JCheckBox();
    bookImagePanel = new JPanel();
    contentCartPanel = new JPanel();
    groupHeaderPanel = new JPanel();
    bookTitleTextField = new JTextField();
    deleteButton = new JButton();
    infoDetailPanel = new JPanel();
    groupBottomPanel = new JPanel();
    bookPriceLabel = new JLabel();
    quantitySpinner = new JSpinner();
    descriptionScrollPane = new JScrollPane();
    descriptionPanel = new JPanel();
    descriptionTextArea = new JTextArea();

    setFont(new Font("Arial", 0, 14));
    setMinimumSize(new Dimension(500, 100));
    setPreferredSize(new Dimension(500, 250));
    setLayout(new BorderLayout());

    cententProductPanel.setMaximumSize(new Dimension(155, 100));
    cententProductPanel.setMinimumSize(new Dimension(155, 100));
    cententProductPanel.setPreferredSize(new Dimension(155, 100));
    cententProductPanel.setLayout(new BorderLayout());

    checkboxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    chooseBookCheckBox.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          chooseBookCheckBoxActionPerformed(evt);
        }
      }
    );
    checkboxPanel.add(chooseBookCheckBox);

    cententProductPanel.add(checkboxPanel, BorderLayout.PAGE_START);

    bookImagePanel.setLayout(new BorderLayout());
    cententProductPanel.add(bookImagePanel, BorderLayout.CENTER);

    add(cententProductPanel, BorderLayout.LINE_START);

    contentCartPanel.setLayout(new BorderLayout());

    bookTitleTextField.setEditable(false);
    bookTitleTextField.setFont(new Font("Arial", 0, 14));
    bookTitleTextField.setHorizontalAlignment(JTextField.LEFT);
    bookTitleTextField.setPreferredSize(new Dimension(300, 30));
    bookTitleTextField.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
          bookTitleTextFieldActionPerformed(evt);
        }
      }
    );
    groupHeaderPanel.add(bookTitleTextField);

    deleteButton.setFont(new Font("Arial", 0, 14));
    deleteButton.setPreferredSize(new Dimension(30, 30));
    deleteButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
          deleteButtonActionPerformed(evt);
        }
      }
    );
    groupHeaderPanel.add(deleteButton);

    contentCartPanel.add(groupHeaderPanel, BorderLayout.PAGE_START);

    infoDetailPanel.setLayout(new BorderLayout());

    groupBottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    bookPriceLabel.setFont(new Font("Arial", 0, 18));
    bookPriceLabel.setText("$");
    bookPriceLabel.setMaximumSize(new Dimension(200, 50));
    bookPriceLabel.setMinimumSize(new Dimension(200, 22));
    bookPriceLabel.setPreferredSize(new Dimension(230, 30));
    groupBottomPanel.add(bookPriceLabel);

    quantitySpinner.setFont(new Font("Arial", 0, 18));
    quantitySpinner.setPreferredSize(new Dimension(100, 30));
    groupBottomPanel.add(quantitySpinner);

    infoDetailPanel.add(groupBottomPanel, BorderLayout.PAGE_END);

    descriptionPanel.setLayout(new GridLayout(1, 0));

    descriptionTextArea.setEditable(false);
    descriptionTextArea.setColumns(20);
    descriptionTextArea.setRows(5);
    descriptionTextArea.setMaximumSize(new Dimension(232, 160));
    descriptionTextArea.setMinimumSize(new Dimension(232, 160));
    descriptionTextArea.setPreferredSize(new Dimension(232, 160));
    descriptionPanel.add(descriptionTextArea);

    descriptionScrollPane.setViewportView(descriptionPanel);

    infoDetailPanel.add(descriptionScrollPane, BorderLayout.CENTER);

    contentCartPanel.add(infoDetailPanel, BorderLayout.CENTER);

    add(contentCartPanel, BorderLayout.CENTER);
  }

  private void chooseBookCheckBoxActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void bookTitleTextFieldActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void deleteButtonActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
  }

  private JPanel bookImagePanel;
  private JLabel bookPriceLabel;
  private JTextField bookTitleTextField;
  private JPanel cententProductPanel;
  private JPanel checkboxPanel;
  private JCheckBox chooseBookCheckBox;
  private JPanel contentCartPanel;
  private JButton deleteButton;
  private JPanel descriptionPanel;
  private JScrollPane descriptionScrollPane;
  private JTextArea descriptionTextArea;
  private JPanel groupBottomPanel;
  private JPanel groupHeaderPanel;
  private JPanel infoDetailPanel;
  private JSpinner quantitySpinner;
}
