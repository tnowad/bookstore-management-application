package com.bookstore;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.bookstore.runnable.LoadDataRunnable;
import com.bookstore.runnable.LoadGuiRunnable;
import com.bookstore.runnable.CheckConnectionRunnable;

public class App {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        UIManager.setLookAndFeel(new FlatLightLaf());
      } catch (Exception e) {
        e.printStackTrace();
      }

      Thread loadDataThread = new Thread(new LoadDataRunnable());
      Thread checkConnectionThread = new Thread(new CheckConnectionRunnable());
      Thread loadGuiThread = new Thread(new LoadGuiRunnable());

      checkConnectionThread.start();
      loadDataThread.start();
      loadGuiThread.start();
    });
  }
}
