package com.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ConnectDatabase {
  private static Connection connection = null;
  // get from .properties file
  private static ResourceBundle rb = ResourceBundle.getBundle("com.bookstore.database");
  private static String driver = rb.getString("driver");
  private static String url = rb.getString("url");
  private static String user = rb.getString("user");
  private static String password = rb.getString("password");

  public static Connection getConnection() {
    if (connection == null) {
      try {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return connection;
  }

  public static ResultSet getResultSet(String sql) {
    ResultSet rs = null;
    try {
      rs = getConnection().createStatement().executeQuery(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public static void main(String[] args) {
    ResultSet rs = getResultSet("select * from books");
    try {
      while (rs.next()) {
        System.out.println(rs.getString("book_name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
