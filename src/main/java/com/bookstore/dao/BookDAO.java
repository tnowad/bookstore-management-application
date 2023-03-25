package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.BookModel;
import com.bookstore.model.BookModel.Status;

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
  public ArrayList<BookModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<BookModel> bookList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM books")) {
      while (rs.next()) {
        BookModel bookModel = createBookModelFromResultSet(rs);
        bookList.add(bookModel);
      }
    }
    return bookList;
  }

  @Override
  public int insert(BookModel book) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO books (isbn, title, description, image, price, quantity, status, publisher_id, author_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { book.getIsbn(), book.getTitle(), book.getDescription(), book.getImage(), book.getPrice(),
        book.getQuantity(), book.getStatus().name(), book.getPublisherId(), book.getAuthorId() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(BookModel book) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE books SET title = ?, description = ?, image = ?, price = ?, quantity = ?, status = ?, publisher_id = ?, author_id = ? WHERE isbn = ?";
    Object[] args = { book.getTitle(), book.getDescription(), book.getImage(), book.getPrice(), book.getQuantity(),
        book.getStatus().name(), book.getPublisherId(), book.getAuthorId(), book.getIsbn() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  public int updateQuantity(String isbn, int quantity) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE books SET quantity = ? WHERE isbn = ?";
    Object[] args = { quantity, isbn };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  public int updateStatus(String isbn, Status status) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE books SET status = ? WHERE isbn = ?";
    Object[] args = { status, isbn };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int ISBN) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM books WHERE isbn = ?";
    Object[] args = { ISBN };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<BookModel> search(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM books WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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
    }
  }
}
