package com.bookstore.gui.component;

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
    setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
    setBackground(Color.WHITE);
    label = new JLabel(name);
    label.setFont(new Font("sansserif", 0, 24));
    label.setPreferredSize(new Dimension(150, 50));
    add(label);

    if (typeField == "show") {
      textField = new JTextField();
      textField.setPreferredSize(new Dimension(300, 50));
      Border borderUsernameTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
      textField.setBorder(borderUsernameTextField);
      add(textField);
    } else if (typeField == "hide") {
      passwordField = new JPasswordField();
      passwordField.setPreferredSize(new Dimension(300, 50));
      Border borderPasswordField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
      passwordField.setBorder(borderPasswordField);
      add(passwordField);
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
