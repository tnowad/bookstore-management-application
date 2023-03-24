package com.bookstore;

import javax.swing.UIManager;
import java.awt.EventQueue;
import com.bookstore.gui.LoginUI;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
      UIManager.put("Label.font", new java.awt.Font("Arial", 0, 14));
    } catch (Exception e) {
      e.printStackTrace();
    }
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          new LoginUI();
        } catch (Exception e) {
          System.exit(0);
        }
      }
    });
  }
}
