package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {
  private static Connection connection = null;
  private static ResourceBundle rb = ResourceBundle.getBundle("resources.config.database");
  private static String driver = rb.getString("driver");
  private static String url = rb.getString("url");
  private static String user = rb.getString("user");
  private static String password = rb.getString("password");

  private DatabaseConnection() {
  }

  public static Connection getInstance() {
    if (connection == null) {
      try {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
      } catch (ClassNotFoundException e) {
        System.err.println("Error: driver not found");
      } catch (SQLException e) {
        System.err.println("Error: connection failed");
      }
    }
    return connection;
  }

  public static ResultSet executeQuery(String sql, Object... args) {
    Connection connection = getInstance();
    if (connection == null) {
      System.err.println("Error: " + "Connection failed");
      return null;
    }
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement(sql);
      for (int i = 0; i < args.length; i++) {
        preparedStatement.setObject(i + 1, args[i]);
      }
      return preparedStatement.executeQuery();
    } catch (SQLException e) {
      System.err.println("Error: " + "Query failed");
    }
    return null;
  }

  public static int executeUpdate(String sql, Object... args) {
    Connection connection = getInstance();
    if (connection == null) {
      System.err.println("Error: " + "Connection failed");
      return -1;
    }
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      for (int i = 0; i < args.length; i++) {
        preparedStatement.setObject(i + 1, args[i]);
      }
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Error: " + "Update failed");
    }
    return -1;
  }

  public static void closeConnection() {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connection = null;
    }
  }

  public static void main(String[] args) {
    ResultSet resultSet = DatabaseConnection.executeQuery("show tables");
    try {
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static PreparedStatement getPreparedStatement(String query, Object... args) {
    Connection connection = getInstance();
    if (connection == null) {
      System.err.println("Error: " + "Connection failed");
      return null;
    }
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement(query);
      for (int i = 0; i < args.length; i++) {
        preparedStatement.setObject(i + 1, args[i]);
      }
    } catch (SQLException e) {
      System.err.println("Error: " + "Query failed");
    }
    return preparedStatement;
  }
}