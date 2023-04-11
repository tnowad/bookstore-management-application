package com.bookstore;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.bookstore.runnable.LoadGuiRunnable;
import com.bookstore.runnable.UpdateDataRunnable;
import com.bookstore.runnable.CheckConnectionRunnable;

public class App {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        UIManager.setLookAndFeel(new FlatLightLaf());
      } catch (Exception e) {
        e.printStackTrace();
      }

      Thread updateDataThread = new Thread(new UpdateDataRunnable());
      Thread checkConnectionThread = new Thread(new CheckConnectionRunnable());
      Thread loadGuiThread = new Thread(new LoadGuiRunnable());

      loadGuiThread.start();
      checkConnectionThread.start();
      updateDataThread.start();
    });
  }
}
