package com.bookstore.gui.factories;

import javax.swing.JFrame;

import com.bookstore.gui.form.customer.view.MainCustomerFrame;
import com.bookstore.interfaces.FactoryFrame;

public class CustomerFrameFactory implements FactoryFrame {
  @Override
  public JFrame createFrame() {
    return new MainCustomerFrame();
  }
}
