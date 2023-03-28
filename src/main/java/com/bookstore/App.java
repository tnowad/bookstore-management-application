package com.bookstore;

import java.awt.EventQueue;
import javax.swing.UIManager;

import com.bookstore.dao.DatabaseConnection;
import com.bookstore.gui.main.LoginUI;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
      UIManager.put("Label.font", new java.awt.Font("Arial", 0, 14));
    } catch (Exception e) {
      System.err.println("Error: Failed to initialize LaF");
    }

    EventQueue.invokeLater(() -> {
      if (DatabaseConnection.getInstance() != null)
        LoginUI.getInstance().setVisible(true);
    });
  }
}
