package com.bookstore.dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DatabaseConnect {
  private static Connection connection = null;
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
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      statement = getConnection().createStatement();
      resultSet = statement.executeQuery(sql);
    } catch (SQLException e) {
      System.out.println(e);
    }
    return resultSet;
  }
}
