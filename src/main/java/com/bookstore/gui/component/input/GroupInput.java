package com.bookstore.gui.component.input;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;

public class GroupInput extends JPanel {

  private JLabel label;
  private JTextField textField;
  private JPasswordField passwordField;

  public GroupInput(String name, String typeField) {
    initComponents(name, typeField);
    setBackground();
    this.setBackground(Color.white);
  }

  private void setBackground() {
  }

  private void initComponents(String name, String typeField) {
    this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
    this.setBackground(Color.WHITE);
    label = new JLabel(name);
    label.setFont(new Font("Arial", 0, 16));
    label.setPreferredSize(new Dimension(150, 50));
    this.add(label);

    if ("show".equals(typeField)) {
      textField = new JTextField();
      textField.setPreferredSize(new Dimension(250, 50));
      Border borderUsernameTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
      textField.setBorder(borderUsernameTextField);
      this.add(textField);
    } else if ("hide".equals(typeField)) {
      passwordField = new JPasswordField();
      passwordField.setPreferredSize(new Dimension(250, 50));
      Border borderPasswordField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
      passwordField.setBorder(borderPasswordField);
      this.add(passwordField);
    }
  }

  public JLabel getLabel() {
    return label;
  }

  public void setLabel(JLabel label) {
    this.label = label;
  }

  public JTextField getTextField() {
    return textField;
  }

  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

  public JPasswordField getPasswordField() {
    return passwordField;
  }

  public void setPasswordField(JPasswordField passwordField) {
    this.passwordField = passwordField;
  }

}
