package com.bookstore.gui.factories;

import javax.swing.JFrame;

import com.bookstore.gui.salesman.view.SalesmanFrame;
import com.bookstore.interfaces.FactoryFrame;

public class EmployeeFrameFactory implements FactoryFrame {
  @Override
  public JFrame createFrame() {
    return new SalesmanFrame();
  }
}
