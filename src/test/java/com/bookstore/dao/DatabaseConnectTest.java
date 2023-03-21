package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DatabaseConnectTest {

  @BeforeAll
  public static void setUp() throws Exception {
    DatabaseConnect.getConnection();
    createTable();
  }

  @AfterAll
  public static void tearDown() throws Exception {
    dropTable();
    DatabaseConnect.closeConnection();
  }

  private static void createTable() throws SQLException, ClassNotFoundException {
    String createTableQuery = "CREATE TABLE test (id INT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), price DOUBLE)";
    DatabaseConnect.executeUpdate(createTableQuery);
  }

  private static void dropTable() throws SQLException, ClassNotFoundException {
    String dropTableQuery = "DROP TABLE test";
    DatabaseConnect.executeUpdate(dropTableQuery);
  }

  @Test
  public void testAddBook() throws SQLException, ClassNotFoundException {
    String insertQuery = "INSERT INTO test (id, title, author, price) VALUES (?, ?, ?, ?)";
    int rowsUpdated = DatabaseConnect.executeUpdate(insertQuery, 1, "The Great Gatsby", "F. Scott Fitzgerald", 12.99);
    assertEquals(1, rowsUpdated);
  }

  @Test
  public void testGetBook() throws SQLException, ClassNotFoundException {
    String selectQuery = "SELECT * FROM test WHERE id = ?";
    PreparedStatement statement = DatabaseConnect.getPreparedStatement(selectQuery, 1);
    ResultSet resultSet = statement.executeQuery();
    assertTrue(resultSet.next());
    assertEquals("The Great Gatsby", resultSet.getString("title"));
    assertEquals("F. Scott Fitzgerald", resultSet.getString("author"));
    resultSet.close();
    statement.close();
  }

  @Test
  public void testUpdateBook() throws SQLException, ClassNotFoundException {
    String updateQuery = "UPDATE test SET price = ? WHERE id = ?";
    int rowsUpdated = DatabaseConnect.executeUpdate(updateQuery, 20.0, 1);
    assertEquals(1, rowsUpdated);

    String selectQuery = "SELECT price FROM test WHERE id = ?";
    PreparedStatement statement = DatabaseConnect.getPreparedStatement(selectQuery, 1);
    ResultSet resultSet = statement.executeQuery();
    assertTrue(resultSet.next());
    assertEquals(20.0, resultSet.getDouble("price"));
    resultSet.close();
    statement.close();
  }

  @Test
  public void testRemoveBook() throws SQLException, ClassNotFoundException {
    String deleteQuery = "DELETE FROM test WHERE id = ?";
    int rowsUpdated = DatabaseConnect.executeUpdate(deleteQuery, 1);
    assertEquals(1, rowsUpdated);

    String selectQuery = "SELECT * FROM test WHERE id = ?";
    PreparedStatement statement = DatabaseConnect.getPreparedStatement(selectQuery, 1);
    ResultSet resultSet = statement.executeQuery();
    assertFalse(resultSet.next());
    resultSet.close();
    statement.close();
  }
}
