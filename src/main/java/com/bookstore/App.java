package com.bookstore;

import javax.swing.UIManager;

import com.bookstore.gui.LoginUI;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
      UIManager.put("Label.font", new java.awt.Font("Arial", 0, 14));
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    new LoginUI();
  }
}
