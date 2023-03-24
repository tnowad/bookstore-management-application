package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.BookBUS;
import com.bookstore.model.BookModel;
import com.bookstore.model.BookModel.Status;

public class BookExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(BookExcelUtil.class.getName());

  public static void selectAndProcessBooksExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Books", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> bookData = ExcelUtil.readExcel(file.getAbsolutePath(), 1);
        if (bookData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<BookModel> bookModels = convertToBookModelList(bookData);
        BookBUS bookBUS = new BookBUS();
        for (BookModel model : bookModels) {
          BookModel existingBook = bookBUS.getBookModel(Integer.parseInt(model.getIsbn()));
          if (existingBook != null) {
            handleDuplicateBook(existingBook, model, bookBUS);
          } else {
            bookBUS.insertModel(model);
          }
        }
        JOptionPane.showMessageDialog(null, "Data from " + file.getName() + " has been inserted successfully.");
      } catch (IOException | SQLException | ClassNotFoundException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while processing file: " + file.getName(), e);
        showErrorDialog("An error occurred while processing the file.", "Error");
      } catch (IllegalArgumentException e) {
        LOGGER.log(Level.WARNING, "Invalid data found in file: " + file.getName(), e);
        showErrorDialog(e.getMessage(), "Invalid Data");
      }
    } else if (option == JFileChooser.CANCEL_OPTION) {
      LOGGER.log(Level.INFO, "User cancelled file selection dialog.");
    }
  }

  private static void handleDuplicateBook(BookModel existingBook, BookModel newBook, BookBUS bookBUS)
      throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate book with ISBN: " + existingBook.getIsbn()
            + " was found. Would you like to update or delete this book?",
        "Duplicate Book Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);

    if (choice == JOptionPane.NO_OPTION) {
      bookBUS.deleteModel(Integer.parseInt(existingBook.getIsbn()));
    } else {
      String oldData = "Old Data:\nISBN: " + existingBook.getIsbn() + "\nTitle: " + existingBook.getTitle()
          + "\nDescription: " + existingBook.getDescription() + "\nImage: " + existingBook.getImage()
          + "\nPrice: " + existingBook.getPrice() + "\nQuantity: " + existingBook.getQuantity() + "\nStatus: "
          + existingBook.getStatus().toString() + "\nPublisher ID: " + existingBook.getPublisherId()
          + "\nAuthor ID: " + existingBook.getAuthorId();
      String newData = "New Data:\nISBN: " + newBook.getIsbn() + "\nTitle: " + newBook.getTitle()
          + "\nDescription: " + newBook.getDescription() + "\nImage: " + newBook.getImage() + "\nPrice: "
          + newBook.getPrice() + "\nQuantity: " + newBook.getQuantity() + "\nStatus: "
          + newBook.getStatus().toString() + "\nPublisher ID: " + newBook.getPublisherId() + "\nAuthor ID: "
          + newBook.getAuthorId();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(
          null,
          message,
          "Update Book",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate book.",
            "Duplicate Book Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        bookBUS.updateModel(newBook);
      } else {
        bookBUS.deleteModel(Integer.parseInt(existingBook.getIsbn()));
        bookBUS.insertModel(newBook);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<BookModel> convertToBookModelList(List<List<String>> data) throws IllegalArgumentException {
    List<BookModel> bookModels = new ArrayList<>();
    for (List<String> row : data) {
      String isbn = row.get(0);
      String title = row.get(1);
      String description = row.get(2);
      String image = row.get(3);
      int price = Integer.parseInt(row.get(4));
      int quantity = Integer.parseInt(row.get(5));
      Status status = Status.valueOf(row.get(6).toUpperCase());
      int publisherId = Integer.parseInt(row.get(7));
      int authorId = Integer.parseInt(row.get(8));
      BookModel model = new BookModel(isbn, title, description, image, price, quantity, status, publisherId,
          authorId);
      bookModels.add(model);
    }
    return bookModels;
  }

  public static void writeBooksToExcel(List<BookModel> books) {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

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

      if (outputFile.exists()) {
        int overwriteOption = JOptionPane.showConfirmDialog(null,
            "The file already exists. Do you want to overwrite it?", "File Exists", JOptionPane.YES_NO_OPTION);
        if (overwriteOption == JOptionPane.NO_OPTION) {
          return;
        }
      }

      try {
        writeExcel(rowDataList, filePath, "Books");
        JOptionPane.showMessageDialog(null,
            "Data has been written successfully to " + outputFile.getName() + ".");
      } catch (SpreadsheetIOException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while writing data to file: " + outputFile.getName(), e);
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Output Error",
            JOptionPane.ERROR_MESSAGE);
        throw e;
      }
    }
  }

}
