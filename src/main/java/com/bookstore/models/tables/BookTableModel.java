package com.bookstore.models.tables;

import com.bookstore.models.BookModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BookTableModel extends AbstractTableModel {

  private static final String[] columnNames = {
    "ISBN",
    "Title",
    "Description",
    "Image",
    "Price",
    "Quantity",
    "Status",
    "Publisher ID",
    "Author ID",
  };

  private List<BookModel> bookList;

  @Override
  public int getRowCount() {
    return bookList == null ? 0 : bookList.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    BookModel bookModel = bookList.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return bookModel.getIsbn();
      case 1:
        return bookModel.getTitle();
      case 2:
        return bookModel.getDescription();
      case 3:
        return bookModel.getImage();
      case 4:
        return bookModel.getPrice();
      case 5:
        return bookModel.getQuantity();
      case 6:
        return bookModel.getStatus();
      case 7:
        return bookModel.getPublisherId();
      case 8:
        return bookModel.getAuthorId();
      default:
        return null;
    }
  }

  public BookModel getBookAt(int rowIndex) {
    return bookList.get(rowIndex);
  }

  public void setBookList(List<BookModel> bookList) {
    this.bookList = bookList;
  }
}
