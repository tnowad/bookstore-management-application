package com.bookstore;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDatabase {
  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "admin123");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
      while (resultSet.next()) {
        System.out.println(resultSet.getString("name"));
      }
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
