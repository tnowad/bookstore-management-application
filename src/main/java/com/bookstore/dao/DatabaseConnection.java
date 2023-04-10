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

  /**
   * Get connection to database
   *
   * @return Connection object to database
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public Connection getConnection() throws SQLException, ClassNotFoundException {
    if (connection == null || connection.isClosed()) {
      Class.forName(driver);
      connection = DriverManager.getConnection(url, user, password);
    }
    return connection;
  }

  /**
   * Get PreparedStatement
   *
   * @param sql
   * @param args
   * @return PreparedStatement after setting arguments
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static PreparedStatement getPreparedStatement(String sql, Object... args)
      throws SQLException, ClassNotFoundException {
    PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement(sql);
    for (int i = 0; i < args.length; i++) {
      preparedStatement.setObject(i + 1, args[i]);
    }
    return preparedStatement;
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
  public static ResultSet executeQuery(String sql, Object... args) throws SQLException, ClassNotFoundException {
    PreparedStatement preparedStatement = getPreparedStatement(sql, args);
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
  public static int executeUpdate(String sql, Object... args) throws SQLException, ClassNotFoundException {
    PreparedStatement preparedStatement = getPreparedStatement(sql, args);
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

  public static void main(String[] args) {
    // try connect database and close connection
    try {
      DatabaseConnection.getInstance().getConnection();
      System.out.println("Connected to database");
      DatabaseConnection.closeConnection();
      System.out.println("Closed connection");
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
