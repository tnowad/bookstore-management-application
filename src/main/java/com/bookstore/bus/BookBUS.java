package com.bookstore.bus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.bookstore.dao.BookDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.BookModel.Status;

public class BookBUS implements IBUS<BookModel> {

  private final List<BookModel> bookList = new ArrayList<>();
  private static BookBUS instance;

  public static BookBUS getInstance() {
    if (instance == null) {
      instance = new BookBUS();
    }
    return instance;
  }

  private BookBUS() {
    this.bookList.addAll(BookDAO.getInstance().readDatabase());
  }

  @Override
  public List<BookModel> getAllModels() {
    return Collections.unmodifiableList(bookList);
  }

  @Override
  public BookModel getModelById(int id) {
    for (BookModel bookModel : bookList) {
      if (bookModel.getIsbn() == String.valueOf(id)) {
        return bookModel;
      }
    }
    return null;
  }

  public BookModel getBookByIsbn(String isbn) {
    for (BookModel bookModel : bookList) {
      if (bookModel.getIsbn().equals(isbn)) {
        return bookModel;
      }
    }
    return null;
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

  private boolean checkFilter(BookModel bookModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "isbn" -> {
          if (bookModel.getIsbn().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "title" -> {
          if (bookModel.getTitle().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "description" -> {
          if (bookModel.getDescription().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "image" -> {
          if (bookModel.getImage().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
        }
        case "quantity" -> {
          if (bookModel.getQuantity() > 0) {
            return true;
          }
        }
        case "price" -> {
          if (bookModel.getPrice() > 0) {
            return true;
          }
        }
        case "status" -> {
          if (bookModel.getStatus().toString().equalsIgnoreCase(value)) {
            return true;
          }
        }
        case "publisher_id" -> {
          if (Integer.parseInt(value) == bookModel.getPublisherId()) {
            return true;
          }
        }
        case "author_id" -> {
          if (Integer.parseInt(value) == bookModel.getAuthorId()) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(bookModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(BookModel bookModel, String value) {
    return bookModel.getIsbn().toLowerCase().contains(value.toLowerCase())
        || bookModel.getTitle().toLowerCase().contains(value.toLowerCase())
        || bookModel.getDescription().toLowerCase().contains(value.toLowerCase())
        || bookModel.getImage().toLowerCase().contains(value.toLowerCase())
        || bookModel.getQuantity() == Integer.parseInt(value)
        || bookModel.getPrice() == Integer.parseInt(value)
        || bookModel.getStatus().toString().equalsIgnoreCase(value)
        || bookModel.getPublisherId() == Integer.parseInt(value)
        || bookModel.getAuthorId() == Integer.parseInt(value);
  }

  @Override
  public int addModel(BookModel bookModel) {
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
  public int updateModel(BookModel bookModel) {
    int updatedRows = BookDAO.getInstance().update(bookModel);
    if (updatedRows > 0) {
      for (int i = 0; i < bookList.size(); i++) {
        if (bookList.get(i).getIsbn() == bookModel.getIsbn()) {
          bookList.set(i, bookModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  public int updateQuantity(String isbn, int quantity) {
    int success = BookDAO.getInstance().updateQuantity(isbn, quantity);
    if (success == 1) {
      for (BookModel book : bookList) {
        if (book.getIsbn().equals(isbn)) {
          book.setQuantity(quantity);
          return 1;
        }
      }
    }
    return 0;
  }

  public int updateStatus(String isbn, String status) {
    int success = BookDAO.getInstance().updateStatus(isbn, status);
    if (success == 1) {
      for (BookModel book : bookList) {
        if (book.getIsbn().equals(isbn)) {
          Status roleEnum = Status.valueOf(status.toUpperCase());
          book.setStatus(roleEnum);
          return 1;
        }
      }
    }
    return 0;
  }

  public int updatePrice(String isbn, int price) {
    int success = BookDAO.getInstance().updatePrice(isbn, price);
    if (success == 1) {
      for (BookModel book : bookList) {
        if (book.getIsbn().equals(isbn)) {
          book.setPrice(price);
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) {
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
  public List<BookModel> searchModel(String value, String[] columns) {
    List<BookModel> results = new ArrayList<>();
    List<BookModel> entities = BookDAO.getInstance().search(value, columns);
    for (BookModel entity : entities) {
      BookModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }
  public boolean checkForDuplicate(List<String> values, String[] columns) {
    Optional<BookModel> optionalUser = BookBUS
      .getInstance()
      .getAllModels()
      .stream()
      .filter(user -> {
        for (String value : values) {
          if (
            Arrays.asList(columns).contains("title") &&
            !value.isEmpty() &&
            user.getTitle().equals(value)
          ) {
            return true;
          }
          if (
            Arrays.asList(columns).contains("isbn") &&
            user.getIsbn().equals(value)
          ) {
            return true;
          }
        }
        return false;
      })
      .findFirst();
    return optionalUser.isPresent();
  }
}
