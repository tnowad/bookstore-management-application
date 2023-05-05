package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.BooksCategoryModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooksCategoryDAO implements IDAO<BooksCategoryModel> {

  private static BooksCategoryDAO instance;

  public static BooksCategoryDAO getInstance() {
    if (instance == null) {
      instance = new BooksCategoryDAO();
    }
    return instance;
  }

  private BooksCategoryModel createCategoryModelFromResultSet(ResultSet rs)
    throws SQLException {
    return new BooksCategoryModel(
      rs.getInt("categories_id"),
      rs.getString("book_isbn")
    );
  }

  @Override
  public ArrayList<BooksCategoryModel> readDatabase() {
    ArrayList<BooksCategoryModel> categoryList = new ArrayList<>();
    try (
      ResultSet rs = DatabaseConnection.executeQuery(
        "SELECT * FROM categories_books"
      )
    ) {
      while (rs.next()) {
        BooksCategoryModel booksCategoryModel = createCategoryModelFromResultSet(
          rs
        );
        categoryList.add(booksCategoryModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return categoryList;
  }

  @Override
  public int insert(BooksCategoryModel booksCategory) {
    String insertSql =
      "INSERT INTO categories_books (categories_id,book_isbn) VALUES (?,?)";
    Object[] args = {
      booksCategory.getCategoryId(),
      booksCategory.getBookIsbn(),
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(BooksCategoryModel booksCategory) {
    String updateSql =
      "UPDATE categories_books SET categoies_id = ? WHERE book_isbn = ?";
    Object[] args = {
      booksCategory.getCategoryId(),
      booksCategory.getBookIsbn(),
    };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM categories_books WHERE book_isbn = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<BooksCategoryModel> search(
    String condition,
    String[] columnNames
  ) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "Search condition cannot be empty or null"
      );
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query =
        "SELECT * FROM categories_books WHERE CONCAT(categories_id, book_isbn) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in categories table
      String column = columnNames[0];
      query = "SELECT * FROM categories_books WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in categories table
      query =
        "SELECT id, name FROM categories_books WHERE CONCAT(" +
        String.join(", ", columnNames) +
        ") LIKE ?";
    }

    try (
      PreparedStatement pst = DatabaseConnection.getPreparedStatement(
        query,
        "%" + condition + "%"
      )
    ) {
      try (ResultSet rs = pst.executeQuery()) {
        List<BooksCategoryModel> booksCategoryList = new ArrayList<>();
        while (rs.next()) {
          BooksCategoryModel categoryModel = createCategoryModelFromResultSet(
            rs
          );
          booksCategoryList.add(categoryModel);
        }
        if (booksCategoryList.isEmpty()) {
          throw new SQLException(
            "No records found for the given condition: " + condition
          );
        }
        return booksCategoryList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
