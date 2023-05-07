package com.bookstore.gui.forms.general;

import java.awt.*;
import javax.swing.*;

public class ContactUs extends JPanel {

  private JLabel nameLabel;
  private JLabel emailLabel;
  private JLabel messageLabel;
  private JTextField nameField;
  private JTextField emailField;
  private JTextArea messageArea;
  private JButton submitButton;
  private JScrollPane scrollPane;

  public ContactUs() {
    initComponents();
  }

  private void initComponents() {
    nameLabel = new JLabel("Name");
    emailLabel = new JLabel("Email");
    messageLabel = new JLabel("Message");
    nameField = new JTextField();
    emailField = new JTextField();
    messageArea = new JTextArea();
    submitButton = new JButton("Submit");
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(0, 2, 10, 10));
    formPanel.add(nameLabel);
    formPanel.add(nameField);
    formPanel.add(emailLabel);
    formPanel.add(emailField);
    formPanel.add(messageLabel);
    formPanel.add(messageArea);
    formPanel.add(submitButton);
    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel formWrapper = new JPanel(new BorderLayout());
    formWrapper.setLayout(new BorderLayout());
    formWrapper.add(formPanel, BorderLayout.CENTER);
    scrollPane =
      new JScrollPane(
        formWrapper,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
      );

    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    add(scrollPane);
  }
}
