package com.bookstore.gui.form;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainForm extends JPanel {

  public MainForm() {
    initComponents();
    setOpaque(false);
    setLayout(new BorderLayout());
    setBorder(new EmptyBorder(10, 20, 10, 20));
  }

  public void showForm(Component form) {
    removeAll();
    add(form);
    repaint();
    revalidate();
  }

  private void initComponents() {
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE));
  }

}
