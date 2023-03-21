package com.bookstore.gui.form;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bookstore.gui.MenuUI;
import com.bookstore.gui.component.Menu;

public class Form extends JPanel {

  public Form() {
    initComponent();
    handleEvent();
    initFrame();
  }

  private void initComponent() {
    JLabel label = new JLabel("Form");
    add(label);
    // Align middle
    label.setVerticalAlignment(JLabel.CENTER);
    MenuUI menu = new MenuUI(); // này trieuden them vo de test thử
    add(menu.initMenu());

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
