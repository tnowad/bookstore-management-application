package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.BookModel;
import com.bookstore.model.BookModel.Status;

public class BookBUS implements BUSInterface<BookModel> {

  private final List<BookModel> bookList = new ArrayList<>();

  public BookBUS() throws SQLException, ClassNotFoundException {
    this.bookList.addAll(BookDAO.getInstance().readDatabase());
  }

  @Override
  public List<BookModel> getAllModels() {
    return Collections.unmodifiableList(bookList);
  }

  @Override
  public BookModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (BookModel bookModel : bookList) {
      if (bookModel.getIsbn() == String.valueOf(id)) {
        return bookModel;
      }
    }
    return null;
  }

  public List<BookModel> getBookList() throws NullPointerException {
    return Collections.unmodifiableList(bookList);
  }

  private BookModel mapToEntity(BookModel from) {
    BookModel to = new BookModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(BookModel from, BookModel to) {
    to.setIsbn(from.getIsbn());
    to.setTitle(from.getTitle());
    to.setDescription(from.getDescription());
    to.setImage(from.getImage());
    to.setPrice(from.getPrice());
    to.setQuantity(from.getQuantity());
    to.setStatus(from.getStatus());
    to.setPublisherId(from.getPublisherId());
    to.setAuthorId(from.getAuthorId());
  }

  private boolean checkFilter(BookModel bookModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "isbn" -> bookModel.getIsbn().toLowerCase().contains(value.toLowerCase());
      case "title" -> bookModel.getTitle().toLowerCase().contains(value.toLowerCase());
      case "description" -> bookModel.getDescription().toLowerCase().contains(value.toLowerCase());
      case "image" -> bookModel.getImage().toLowerCase().contains(value.toLowerCase());
      case "quantity" -> bookModel.getQuantity() > 0;
      case "price" -> bookModel.getPrice() > 0;
      case "status" -> bookModel.getStatus().toString().equalsIgnoreCase(value);
      case "publisher_id" -> Integer.parseInt(value) == bookModel.getPublisherId();
      case "author_id" -> Integer.parseInt(value) == bookModel.getAuthorId();
      default -> checkAllColumns(bookModel, value);
    };
  }

  private boolean checkAllColumns(BookModel bookModel, String value) {
    return bookModel.getIsbn().toLowerCase().contains(value.toLowerCase())
        || bookModel.getTitle().toLowerCase().contains(value.toLowerCase())
        || bookModel.getDescription().toLowerCase().contains(value.toLowerCase())
        || bookModel.getImage().toLowerCase().contains(value.toLowerCase())
        || bookModel.getQuantity() == Integer.parseInt(value)
        || bookModel.getPrice() == Integer.parseInt(value)
        || bookModel.getStatus().toString().toLowerCase().equals(value.toLowerCase())
        || bookModel.getPublisherId() == Integer.parseInt(value)
        || bookModel.getAuthorId() == Integer.parseInt(value);
  }

  @Override
  public int addModel(BookModel bookModel) throws SQLException, ClassNotFoundException {
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
      throw new IllegalArgumentException("Price must be greater than 0!");
    }
    if (bookModel.getQuantity() <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than 0!");
    }
    if (bookModel.getStatus() == null || bookModel.getStatus().toString().isEmpty()) {
      bookModel.setStatus(Status.AVAILABLE);
    }
    if (bookModel.getPublisherId() <= 0) {
      throw new IllegalArgumentException("Publisher ID must be greater than 0!");
    }
    if (bookModel.getAuthorId() <= 0) {
      throw new IllegalArgumentException("Author ID must be greater than 0!");
    }

    int id = BookDAO.getInstance().insert(mapToEntity(bookModel));
    bookModel.setIsbn(String.valueOf(id));
    bookList.add(bookModel);
    return id;
  }

  @Override
  public int updateModel(BookModel bookModel) throws SQLException, ClassNotFoundException {
    BookModel existingBook = getModelById(Integer.parseInt(bookModel.getIsbn()));
    if (existingBook == null) {
      return 0;
    }

    updateEntityFields(bookModel, existingBook);
    try {
      BookDAO.getInstance().update(mapToEntity(existingBook));
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException("Failed to update book: " + e.getMessage());
    }
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    BookModel bookModel = getModelById(id);
    if (bookModel == null) {
      throw new IllegalArgumentException("Book with ID " + id + " does not exist.");
    }
    int deletedRows = BookDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      bookList.remove(bookModel);
    }
    return deletedRows;
  }

  @Override
  public List<BookModel> searchModel(String value, String columns) throws SQLException, ClassNotFoundException {
    List<BookModel> results = new ArrayList<>();
    try {
      List<BookModel> entities = BookDAO.getInstance().search(value, columns);
      for (BookModel entity : entities) {
        BookModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for book: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for book: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No book found with the specified search criteria.");
    }

    return results;
  }

}
