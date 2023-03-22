package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.bookstore.bus.BookBUS;
import com.bookstore.dao.BookDAO;
import com.bookstore.model.BookModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BookUtilities {
  public static void readBooksFromExcel() throws ClassNotFoundException, SQLException {
    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();

      try {
        List<List<String>> bookData = ExcelReaderUtil.readExcel(filePath, 1);
        List<BookModel> bookModels = convertToBookModelList(bookData);
        BookBUS bookBus = new BookBUS();
        for (BookModel model : bookModels) {
          bookBus.insertModel(model);
          BookDAO.getInstance().insert(model);
        }
        JOptionPane.showMessageDialog(null, "Data from " + file.getName() + " has been inserted successfully.");
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Input Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Class Not Found Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Number Format Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Illegal Argument Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      }
    }
  }

  private static List<BookModel> convertToBookModelList(List<List<String>> data) {
    List<BookModel> bookModels = new ArrayList<>();
    for (List<String> row : data) {
      String isbn = row.get(0);
      String title = row.get(1);
      String description = row.get(2);
      String image = row.get(3);
      int price = Integer.parseInt(row.get(4));
      int quantity = Integer.parseInt(row.get(5));
      String statusString = row.get(6);
      BookModel.Status status = BookModel.Status.valueOf(statusString.toUpperCase());
      int publisherId = Integer.parseInt(row.get(7));
      int authorId = Integer.parseInt(row.get(8));
      BookModel model = new BookModel(isbn, title, description, image, price, quantity, status, publisherId, authorId);
      bookModels.add(model);
    }
    return bookModels;
  }

  public static void writeBooksToExcel(List<BookModel> books) {
    List<RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ISBN");
    headerValues.add("Title");
    headerValues.add("Description");
    headerValues.add("Image");
    headerValues.add("Price");
    headerValues.add("Quantity");
    headerValues.add("Status");
    headerValues.add("Publisher ID");
    headerValues.add("Author ID");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (BookModel book : books) {
      List<String> values = new ArrayList<>();
      values.add(book.getIsbn());
      values.add(book.getTitle());
      values.add(book.getDescription());
      values.add(book.getImage());
      values.add(Integer.toString(book.getPrice()));
      values.add(Integer.toString(book.getQuantity()));
      values.add(book.getStatus().toString());
      values.add(Integer.toString(book.getPublisherId()));
      values.add(Integer.toString(book.getAuthorId()));
      RowData rowData = new RowData(values);
      rowDataList.add(rowData);
    }

    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showSaveDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File outputFile = fileChooser.getSelectedFile();
      String filePath = outputFile.getAbsolutePath();

      try {
        writeExcel(rowDataList, filePath, "Books");
        JOptionPane.showMessageDialog(null,
            "Data has been written successfully to " + outputFile.getName() + ".");
      } catch (SpreadsheetIOException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Output Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      }
    }
  }

  /**
   * Represents a single row of data in the Excel file.
   */
  public static class RowData {
    private final List<String> values;

    public RowData(List<String> values) {
      this.values = values;
    }

    public List<String> getValues() {
      return values;
    }
  }

  /**
   * Custom exception that wraps an IOException when writing to the spreadsheet
   * fails.
   */
  public static class SpreadsheetIOException extends RuntimeException {
    public SpreadsheetIOException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
