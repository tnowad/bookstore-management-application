package com.bookstore.gui.form;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Form extends JPanel {
  private JLabel label;

  public Form() {
    initComponent();
    handleEvent();
    initFrame();
  }

  private void initComponent() {
    label = new JLabel("Form");
    add(label);
    // Align middle
    label.setVerticalAlignment(JLabel.CENTER);
  }

  private void handleEvent() {
  }

  private void initFrame() {

  }

  public static void main(String[] args) {
    new JFrame() {
      {
        add(new Form());
        setSize(new Dimension(400, 400));
        setVisible(true);
      }
    }.setVisible(true);
  }
}
