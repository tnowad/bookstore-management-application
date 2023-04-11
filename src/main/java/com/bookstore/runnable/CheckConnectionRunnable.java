package com.bookstore.runnable;

import com.bookstore.dao.DatabaseConnection;

public class CheckConnectionRunnable implements Runnable {
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
