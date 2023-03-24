package com.bookstore.gui.form;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Form extends JPanel {

  public Form() {
    initComponents();
  }

  public Form(String string) {
    setName(string);
    initComponents();
  }

  private void initComponents() {
    JLabel jLabel;
    jLabel = new JLabel();
    jLabel.setText(getName());
    add(jLabel);
  }
}
