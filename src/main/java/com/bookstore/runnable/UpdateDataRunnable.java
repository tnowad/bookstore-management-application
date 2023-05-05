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
        e.printStackTrace();
      }
    }
  }

  private void updateData() {
    new Thread(new LoadDataRunnable()).start();
  }
}
