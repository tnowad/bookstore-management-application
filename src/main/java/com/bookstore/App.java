package com.bookstore;

import javax.swing.UIManager;
import java.awt.EventQueue;

import com.bookstore.dao.DatabaseConnection;
import com.bookstore.gui.LoginUI;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
  private static boolean checkConnection() {
    try {
      DatabaseConnection.getInstance();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
      UIManager.put("Label.font", new java.awt.Font("Arial", 0, 14));
    } catch (Exception e) {
      e.printStackTrace();
    }

    new Thread(() -> {
      if (!checkConnection()) {
        System.out.println("Connection failed");
        System.exit(0);
      } else {
        EventQueue.invokeLater(() -> LoginUI.getInstance().setVisible(true));
      }
    }).start();
  }
}
