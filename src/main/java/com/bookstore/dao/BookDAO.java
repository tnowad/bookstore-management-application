package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.BookModel;

public class BookDAO implements DAOInterface<BookModel> {
  public static BookDAO getInstance() {
    return new BookDAO();
  }

  private BookModel createBookModelFromResultSet(ResultSet rs) throws SQLException {
    return new BookModel(
        rs.getString("title"),
        rs.getString("ISBN"),
        rs.getString("description"),
        rs.getBoolean("Status"),
        rs.getDate("PublicationDate"),
        rs.getInt("Quantity"),
        rs.getInt("Quantity_in_stock"),
        rs.getFloat("Price"));
  }

  @Override
  public ArrayList<BookModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<BookModel> bookList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM book")) {
      while (rs.next()) {
        BookModel bookModel = createBookModelFromResultSet(rs);
        bookList.add(bookModel);
      }
    }
    return bookList;
  }

  @Override
  public int insert(BookModel book) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO book (title, description, status, publication_date, quantity, Quantity_in_stock, price, ISBN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { book.getTitle(), book.getDescription(), book.isStatus(), book.getPublicationDate(),
        book.getQuantity(), book.getQuantityInStock(), book.getPrice(), book.getISBN() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(BookModel book) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE book SET title = ?, description =? ,status =?, publication_date =?, quantity =?, Quantity_in_stock =?, price =? WHERE ISBN =?";
    Object[] args = { book.getTitle(), book.getDescription(), book.isStatus(), book.getPublicationDate(),
        book.getQuantity(),
        book.getQuantityInStock(), book.getPrice(), book.getISBN() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String isbn) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM book WHERE ISBN = ?";
    Object[] args = { isbn };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<BookModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM book";
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
        throw new SQLException("No records found for the given condition: " + condition);
      }
      return bookList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<BookModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM book WHERE " + columnName + " LIKE ?";
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