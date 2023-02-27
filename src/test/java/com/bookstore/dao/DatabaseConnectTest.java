package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DatabaseConnect tests")
public class DatabaseConnectTest {

  private static Connection connection;

  @BeforeAll
  static void setUp() {
    connection = DatabaseConnect.getConnection();
  }

  @Test
  @DisplayName("Test database connection")
  void testConnection() {
    assertNotNull(connection, "Connection should not be null");
  }

  @Test
  @DisplayName("Test getResultSet method")
  void testGetResultSet() {
    String sql = "SELECT * FROM books";
    ResultSet resultSet = DatabaseConnect.getResultSet(sql);
    assertNotNull(resultSet, "ResultSet should not be null");
  }

  @AfterAll
  static void tearDown() {
    if (connection != null) {
      try {
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}