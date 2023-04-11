package com.bookstore.runnable;

import com.bookstore.bus.*;

public class LoadDataRunnable implements Runnable {

  @Override
  public void run() {
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