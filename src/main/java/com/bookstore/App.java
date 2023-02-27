package com.bookstore;

import java.util.ResourceBundle;

public class App {
    public static void main(String[] args) {
      ResourceBundle rb = ResourceBundle.getBundle("resources.config.database");
      System.out.println(rb.getString("driver"));
    }
}
