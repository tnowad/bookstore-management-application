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

  /**
   * Returns a connection to the database. This is a lazy operation so it's safe
   * to call multiple times without re - creating the connection every time.
   *
   * @return a connection to the database or null if there was an error connecting
   *         to the database or if the connection could not be
   */
  public static Connection getInstance() {
    try {
      if (connection == null) {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
      }
    } catch (Exception e) {
      System.err.println("Error: " + "Connection failed");
    }
    return connection;
  }

  /**
   * Creates a PreparedStatement with the given SQL. The parameters are set to the
   * values in the array. If there is an error null is returned
   *
   * @param sql - The SQL to be prepared
   *
   * @return The prepared statement or null if the connection could not be
   *         established or the SQL is not valid for the
   */
  public static PreparedStatement getPreparedStatement(String sql, Object... args) {
    Connection connection = getInstance();
    if (connection == null)
      return null;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
      for (int i = 0; i < args.length; i++) {
        preparedStatement.setObject(i + 1, args[i]);
      }
      return preparedStatement;
    } catch (SQLException e) {
      System.err.println("Error: " + "PreparedStatement failed");
    }
    return null;
  }

  /**
   * Executes a query that returns rows. The query is given as a String and can
   * contain placeholder question marks which will be replaced by the given
   * arguments
   *
   * @param sql - The SQL to be executed.
   *
   * @return The result of the query or null if there is an error executing the
   *         query or the query was not
   */
  public static ResultSet executeQuery(String sql, Object... args) {
    PreparedStatement preparedStatement = getPreparedStatement(sql, args);
    if (preparedStatement == null)
      return null;
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
      return resultSet;
    } catch (SQLException e) {
      System.err.println("Error: " + "Query failed");
    }
    return null;
  }

  /**
   * Executes an update statement. This method is useful for updating rows in a
   * table that has been modified or inserted into a table.
   *
   * @param sql - The SQL to execute.
   */
  public static int executeUpdate(String sql, Object... args) {
    PreparedStatement preparedStatement = getPreparedStatement(sql, args);
    if (preparedStatement == null)
      return 0;
    try {
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Error: " + "Update failed");
    }
    return 0;
  }

  /**
   * Closes the connection to the database if it exists. This is useful for unit
   * tests that need to clean up after a test
   */
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
}
