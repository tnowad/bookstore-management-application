package com.bookstore.runnable;

public class UpdateDataRunnable implements Runnable {
  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        updateData();
        Thread.sleep(300000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  private void updateData() {
    System.out.println("Updating data...");
    new Thread(new LoadDataRunnable()).start();
    System.out.println("Data updated!");
  }
}
