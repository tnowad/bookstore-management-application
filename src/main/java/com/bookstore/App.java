package com.bookstore;

import javax.swing.UIManager;
import java.awt.EventQueue;

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

    new Thread(() -> {
      if (DatabaseConnection.getConnection() == null) {
        System.exit(0);
      } else {
        System.out.println("Success: Connected to database");
        EventQueue.invokeLater(() -> LoginUI.getInstance().setVisible(true));
      }
    }).start();
  }
}
