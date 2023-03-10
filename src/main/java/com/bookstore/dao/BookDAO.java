package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.BookModel;

public class BookDAO implements DAOInterface<BookModel> {

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

  @Override
  public int delete(String ISBN) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM books WHERE isbn = ?";
    Object[] args = { ISBN };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<BookModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM books";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<BookModel> bookList = new ArrayList<>();
      while (rs.next()) {
        BookModel bookModel = createBookModelFromResultSet(rs);
        bookList.add(bookModel);
      }
      if (bookList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return bookList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<BookModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
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
