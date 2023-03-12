package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.BookModel;

public class BookBUS extends BUSAbstract<BookModel> {

  private final List<BookModel> bookList = new ArrayList<>();
  private final BookDAO bookDAO;

  protected BookBUS(BookDAO bookDAO) throws SQLException, ClassNotFoundException {
    this.bookDAO = bookDAO;
    this.bookList.addAll(bookDAO.readDatabase());
  }

  @Override
  protected ArrayList<BookModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return bookDAO.readDatabase();
  }

  @Override
  protected int getId(BookModel bookModel) {
    return Integer.parseInt(bookModel.getIsbn());
  }

  @Override
  protected BookModel mapToEntity(BookModel from) {
    BookModel to = new BookModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(BookModel from, BookModel to) {
    to.setTitle(from.getTitle());
    to.setDescription(from.getDescription());
    to.setImage(from.getImage());
    to.setPrice(from.getPrice());
    to.setQuantity(from.getQuantity());
    to.setStatus(from.getStatus());
    to.setPublisherId(from.getPublisherId());
    to.setAuthorId(from.getAuthorId());
  }

  @Override
  protected boolean checkFilter(BookModel bookModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "isbn":
        return bookModel.getIsbn().equals(value);
      case "title":
        return bookModel.getTitle().toLowerCase().contains(value.toLowerCase());
      case "description":
        return bookModel.getDescription().toLowerCase().contains(value.toLowerCase());
      case "image":
        return bookModel.getImage().toLowerCase().contains(value.toLowerCase());
      case "price":
        try {
          return Integer.parseInt(value) > 0 && bookModel.getPrice() == Integer.parseInt(value);
        } catch (NumberFormatException e) {
          return false;
        }
      case "quantity":
        try {
          return Integer.parseInt(value) > 0 && bookModel.getQuantity() == Integer.parseInt(value);
        } catch (NumberFormatException e) {
          return false;
        }
      case "status":
        return bookModel.getStatus().equals(value.toUpperCase());
      case "author_id":
        try {
          return Integer.parseInt(value) > 0 && bookModel.getAuthorId() == Integer.parseInt(value);
        } catch (NumberFormatException e) {
          return false;
        }
      case "publisher_id":
        try {
          return Integer.parseInt(value) > 0 && bookModel.getPublisherId() == Integer.parseInt(value);
        } catch (NumberFormatException e) {
          return false;
        }
      default:
        return checkAllColumns(bookModel, value);
    }
  }

  protected boolean checkAllColumns(BookModel bookModel, String value) {
    return bookModel.getIsbn().equals(value)
        || bookModel.getTitle().toLowerCase().contains(value.toLowerCase())
        || bookModel.getDescription().toLowerCase().contains(value.toLowerCase())
        || bookModel.getImage().toLowerCase().contains(value.toLowerCase())
        || bookModel.getPrice() > 0 && ("" + bookModel.getPrice()).equals(value)
        || bookModel.getQuantity() > 0 && ("" + bookModel.getQuantity()).equals(value)
        || bookModel.getStatus().equals(value.toUpperCase())
        || bookModel.getAuthorId() > 0 && ("" + bookModel.getAuthorId()).equals(value)
        || bookModel.getPublisherId() > 0 && ("" + bookModel.getPublisherId()).equals(value);
  }

  @Override
  protected int insertModel(BookModel bookModel) throws SQLException, ClassNotFoundException {
    if (bookModel.getIsbn() == null || bookModel.getIsbn().isEmpty()) {
      throw new IllegalArgumentException("ISBN cannot be null or empty!");
    }
    if (bookModel.getTitle() == null || bookModel.getTitle().isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or empty!");
    }
    if (bookModel.getDescription() == null || bookModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }
    if (bookModel.getImage() == null || bookModel.getImage().isEmpty()) {
      throw new IllegalArgumentException("Image cannot be null or empty!");
    }
    if (bookModel.getPrice() <= 0) {
      throw new IllegalArgumentException("Price must be greater than zero!");
    }
    if (bookModel.getQuantity() < 0) {
      throw new IllegalArgumentException("Quantity cannot be less than zero!");
    }
    if (bookModel.getStatus() == null) {
      throw new IllegalArgumentException("Status cannot be null!");
    }
    return add(bookModel);
  }

  @Override
  protected int updateModel(BookModel bookModel) throws SQLException, ClassNotFoundException {
    return update(bookModel);
  }

  @Override
  protected int deleteModel(int isbn) throws SQLException, ClassNotFoundException {
    return delete(isbn);
  }

  public List<BookModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public BookModel getBookModel(int isbn) {
    return getModel(isbn);
  }

  public List<BookModel> getBookList() {
    return Collections.unmodifiableList(bookList);
  }

}
