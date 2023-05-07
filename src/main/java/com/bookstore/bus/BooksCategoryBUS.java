package com.bookstore.bus;

import com.bookstore.dao.BooksCategoryDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.BooksCategoryModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooksCategoryBUS implements IBUS<BooksCategoryModel> {

  private final List<BooksCategoryModel> booksCategoryList = new ArrayList<>();
  private static BooksCategoryBUS instance;

  public static BooksCategoryBUS getInstance() {
    if (instance == null) {
      instance = new BooksCategoryBUS();
    }
    return instance;
  }

  private BooksCategoryBUS() {
    this.booksCategoryList.addAll(
        BooksCategoryDAO.getInstance().readDatabase()
      );
  }

  @Override
  public List<BooksCategoryModel> getAllModels() {
    return Collections.unmodifiableList(booksCategoryList);
  }

  public BooksCategoryModel getModel(int categoriesId, String bookIsbn) {
    for (BooksCategoryModel booksCategoryModel : booksCategoryList) {
      if (
        booksCategoryModel.getCategoryId() == categoriesId &&
        booksCategoryModel.getBookIsbn() == bookIsbn
      ) {
        return booksCategoryModel;
      }
    }
    return null;
  }

  private BooksCategoryModel mapToEntity(BooksCategoryModel from) {
    BooksCategoryModel to = new BooksCategoryModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(
    BooksCategoryModel from,
    BooksCategoryModel to
  ) {
    to.setCategoryId(from.getCategoryId());
    to.setBookIsbn(from.getBookIsbn());
  }

  private boolean checkFilter(
    BooksCategoryModel booksCategoryModel,
    String value,
    String[] column
  ) {
    for (String col : column) {
      switch (col.toLowerCase()) {
        case "categories_id" -> {
          if (booksCategoryModel.getCategoryId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "books_isbn" -> {
          if (
            booksCategoryModel
              .getBookIsbn()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(booksCategoryModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(
    BooksCategoryModel booksCategoryModel,
    String value
  ) {
    return (
      booksCategoryModel.getCategoryId() == Integer.parseInt(value) ||
      booksCategoryModel
        .getBookIsbn()
        .toLowerCase()
        .contains(value.toLowerCase())
    );
  }

  @Override
  public int addModel(BooksCategoryModel booksCategoryModel) {
    if (
      booksCategoryModel.getBookIsbn() == null ||
      booksCategoryModel.getBookIsbn().isEmpty()
    ) {
      throw new IllegalArgumentException("BookIsbn cannot be null or empty!");
    }

    int id = BooksCategoryDAO
      .getInstance()
      .insert(mapToEntity(booksCategoryModel));
    booksCategoryModel.setCategoryId(id);
    booksCategoryList.add(booksCategoryModel);
    return id;
  }

  @Override
  public int updateModel(BooksCategoryModel booksCategoryModel) {
    int updatedRows = BooksCategoryDAO.getInstance().update(booksCategoryModel);
    if (updatedRows > 0) {
      for (int i = 0; i < booksCategoryList.size(); i++) {
        if (
          booksCategoryList.get(i).getCategoryId() ==
          booksCategoryModel.getCategoryId()
        ) {
          booksCategoryList.set(i, booksCategoryModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    BooksCategoryModel booksCategoryModel = getModelById(id);
    if (booksCategoryModel == null) {
      throw new IllegalArgumentException(
        "Book category with ID: " + id + " does not exist."
      );
    }
    int deletedRows = BooksCategoryDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      booksCategoryList.remove(booksCategoryModel);
    }
    return deletedRows;
  }

  @Override
  public List<BooksCategoryModel> searchModel(String value, String[] columns) {
    List<BooksCategoryModel> results = new ArrayList<>();
    List<BooksCategoryModel> entities = BooksCategoryDAO
      .getInstance()
      .search(value, columns);
    for (BooksCategoryModel entity : entities) {
      BooksCategoryModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  @Override
  public void refreshData() {
    throw new UnsupportedOperationException(
      "Unimplemented method 'refreshData'"
    );
  }

  @Override
  public BooksCategoryModel getModelById(int id) {
    throw new UnsupportedOperationException(
      "Unimplemented method 'getModelById'"
    );
  }

  public List<BooksCategoryModel> getModelsByBookId(String isbn) {
    List<BooksCategoryModel> results = new ArrayList<>();
    for (BooksCategoryModel booksCategoryModel : booksCategoryList) {
      if (booksCategoryModel.getBookIsbn().equals(isbn)) {
        results.add(booksCategoryModel);
      }
    }
    return results;
  }
}
