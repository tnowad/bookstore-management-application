package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {
  private Connection connection = null;
  private static ResourceBundle rb = ResourceBundle.getBundle("config.database");
  private static String driver = rb.getString("driver");
  private static String url = rb.getString("url");
  private static String user = rb.getString("user");
  private static String password = rb.getString("password");
  private static DatabaseConnection instance;

  private DatabaseConnection() {
  }

  public static DatabaseConnection getInstance() {
    if (instance == null) {
      instance = new DatabaseConnection();
    }
    return instance;
  }

  public Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
      }
      return connection;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static PreparedStatement getPreparedStatement(String sql, Object... args) {
    try {
      PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement(sql);
      for (int i = 0; i < args.length; i++) {
        preparedStatement.setObject(i + 1, args[i]);
      }
      return preparedStatement;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Execute query
   *
   * @param sql
   * @param args
   * @return ResultSet after executing query
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ResultSet executeQuery(String sql, Object... args) throws SQLException {
    PreparedStatement preparedStatement = getPreparedStatement(sql, args);
    if (preparedStatement == null)
      throw new SQLException("Prepared statement is null");
    return preparedStatement.executeQuery();
  }

  /**
   * Execute update
   *
   * @param sql
   * @param args
   * @return number of rows affected by the update
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static int executeUpdate(String sql, Object... args) throws SQLException {
    PreparedStatement preparedStatement = getPreparedStatement(sql, args);
    if (preparedStatement == null)
      throw new SQLException("Prepared statement is null");
    return preparedStatement.executeUpdate();
  }

  /**
   * Close connection
   *
   * @throws SQLException
   */
  public static void closeConnection() {
    try {
      if (getInstance().connection != null && !getInstance().connection.isClosed()) {
        getInstance().connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
