package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConnect {
  private static final Logger LOGGER = LogManager.getLogger(DatabaseConnect.class);
  private static Connection connection = null;
  private static ResourceBundle rb = ResourceBundle.getBundle("resources.config.database");
  private static String driver = rb.getString("driver");
  private static String url = rb.getString("url");
  private static String user = rb.getString("user");
  private static String password = rb.getString("password");

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    if (connection == null) {
      Class.forName(driver);
      connection = DriverManager.getConnection(url, user, password);
    }
    return connection;
  }

  public static PreparedStatement getPreparedStatement(String sql, Object... args)
      throws SQLException, ClassNotFoundException {
    PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
    for (int i = 0; i < args.length; i++) {
      preparedStatement.setObject(i + 1, args[i]);
    }
    return preparedStatement;
  }

  public static ResultSet executeQuery(String sql, Object... args) throws SQLException, ClassNotFoundException {
    PreparedStatement preparedStatement = getPreparedStatement(sql, args);
    return preparedStatement.executeQuery();
  }

  public static int executeUpdate(String sql, Object... args) throws SQLException, ClassNotFoundException {
    PreparedStatement preparedStatement = getPreparedStatement(sql, args);
    return preparedStatement.executeUpdate();
  }

  public static void closeConnection() {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      LOGGER.error("Error closing database connection", e);
    } finally {
      connection = null;
    }
  }
}
