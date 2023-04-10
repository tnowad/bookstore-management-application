package com.bookstore;

import java.awt.EventQueue;
import javax.swing.UIManager;

import com.bookstore.dao.DatabaseConnection;
import com.bookstore.database.seeders.DatabaseSeeder;
import com.bookstore.gui.main.LoginUI;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
  static {
    DatabaseSeeder.seeder();
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
      UIManager.put("Label.font", new java.awt.Font("Arial", 0, 14));
    } catch (Exception e) {
      System.err.println("Error: Failed to initialize LaF");
    }

    EventQueue.invokeLater(() -> {
      if (DatabaseConnection.getInstance() != null) {
        LoginUI.getInstance().setVisible(true);
      } else {
        System.out.println("Error: Failed to connect to database");
      }
    });
  }
}
