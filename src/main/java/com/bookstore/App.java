package com.bookstore;

import com.bookstore.bus.*;
import com.bookstore.dao.DatabaseConnection;
import com.bookstore.gui.main.LoginUI;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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

  static class LoadDataRunnable implements Runnable {
    @Override
    public void run() {
      DatabaseConnection.getInstance().getConnection();
      System.out.println("Loading data...");
      AddressBUS.getInstance().getAllModels();
      AuthorBUS.getInstance().getAllModels();
      BookBUS.getInstance().getAllModels();
      CartBUS.getInstance().getAllModels();
      CartItemsBUS.getInstance().getAllModels();
      CategoryBUS.getInstance().getAllModels();
      EmployeeBUS.getInstance().getAllModels();
      ImportBUS.getInstance().getAllModels();
      OrderBUS.getInstance().getAllModels();
      PaymentBUS.getInstance().getAllModels();
      PaymentMethodBUS.getInstance().getAllModels();
      PromotionBUS.getInstance().getAllModels();
      ProviderBUS.getInstance().getAllModels();
      PublisherBUS.getInstance().getAllModels();
      UserBUS.getInstance().getAllModels();
      System.out.println("Data loaded!");
    }
  }

  static class CheckConnectionRunnable implements Runnable {
    @Override
    public void run() {
      while (DatabaseConnection.getInstance().checkConnection()) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      System.out.println("Connection failed!");
      System.exit(0);
    }
  }

  static class LoadGuiRunnable implements Runnable {
    @Override
    public void run() {
      LoginUI loginUI = new LoginUI();
      loginUI.setVisible(true);
    }
  }
}
