package com.bookstore;

import com.bookstore.runnable.CheckConnectionRunnable;
import com.bookstore.runnable.LoadGuiRunnable;
import com.bookstore.runnable.UpdateDataRunnable;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.SwingUtilities;

public class Application {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      FlatMacLightLaf.setup();

      Thread updateDataThread = new Thread(new UpdateDataRunnable());
      Thread checkConnectionThread = new Thread(new CheckConnectionRunnable());
      Thread loadGuiThread = new Thread(new LoadGuiRunnable());

      loadGuiThread.start();
      checkConnectionThread.start();
      updateDataThread.start();
    });
  }
}
