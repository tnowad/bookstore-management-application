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

      Thread updateDataThread = new Thread(UpdateDataRunnable::new);
      Thread checkConnectionThread = new Thread(CheckConnectionRunnable::new);
      Thread loadGuiThread = new Thread(LoadGuiRunnable::new);

      checkConnectionThread.start();
      updateDataThread.start();
      loadGuiThread.start();
    });
  }
}
