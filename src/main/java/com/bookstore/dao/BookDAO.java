package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.BookModel;

public class BookDAO implements IDAO<BookModel> {
  private static BookDAO instance;

  public static BookDAO getInstance() {
    if (instance == null) {
      instance = new BookDAO();
    }
    return instance;
  }

  private BookModel createBookModelFromResultSet(ResultSet rs) throws SQLException {
    return new BookModel(
        rs.getString("isbn"),
        rs.getString("title"),
        rs.getString("description"),
        rs.getString("image"),
        rs.getInt("price"),
        rs.getInt("quantity"),
        BookModel.Status.valueOf(rs.getString("status").toUpperCase()),
        rs.getInt("publisher_id"),
        rs.getInt("author_id"));
  }

  @Override
  public ArrayList<BookModel> readDatabase() {
    ArrayList<BookModel> bookList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM books")) {
      while (rs.next()) {
        BookModel bookModel = createBookModelFromResultSet(rs);
        bookList.add(bookModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bookList;
  }

  @Override
  public int insert(BookModel book) {
    String insertSql = "INSERT INTO books (isbn, title, description, image, price, quantity, status, publisher_id, author_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { book.getIsbn(), book.getTitle(), book.getDescription(), book.getImage(), book.getPrice(),
        book.getQuantity(), book.getStatus().name(), book.getPublisherId(), book.getAuthorId() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(BookModel book) {
    String updateSql = "UPDATE books SET title = ?, description = ?, image = ?, price = ?, quantity = ?, status = ?, publisher_id = ?, author_id = ? WHERE isbn = ?";
    Object[] args = { book.getTitle(), book.getDescription(), book.getImage(), book.getPrice(), book.getQuantity(),
        book.getStatus().name(), book.getPublisherId(), book.getAuthorId(), book.getIsbn() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateQuantity(String isbn, int quantity) {
    String updateSql = "UPDATE books SET quantity = ? WHERE isbn = ?";
    Object[] args = { quantity, isbn };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateStatus(String isbn, String status) {
    String updateSql = "UPDATE books SET status = ? WHERE isbn = ?";
    Object[] args = { status, isbn };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updatePrice(String isbn, int price) {
    String updateSql = "UPDATE books SET price = ? WHERE isbn = ?";
    Object[] args = { price, isbn };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int ISBN) {
    String deleteSql = "DELETE FROM books WHERE isbn = ?";
    Object[] args = { ISBN };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<BookModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM books WHERE CONCAT(isbn, title, description, image, price, quantity, status, publisher_id, author_id) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in books table
      String column = columnNames[0];
      query = "SELECT * FROM books WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in books table
      query = "SELECT isbn, title, description, image, price, quantity, status, publisher_id, author_id FROM books WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }
    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<BookModel> bookList = new ArrayList<>();
        while (rs.next()) {
          BookModel bookModel = createBookModelFromResultSet(rs);
          bookList.add(bookModel);
        }
        if (bookList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return bookList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
