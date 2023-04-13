package com.bookstore.util.Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.BookBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.BookModel.Status;

public class BookExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(BookExcelUtil.class.getName());

  public static List<BookModel> readBooksFromExcel() throws IOException {
    // BookBUS bookBUS = BookBUS.getInstance();
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(filter);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File inputFile = fileChooser.getSelectedFile();
      String filePath = inputFile.getAbsolutePath();

      try {
        List<List<String>> data = ExcelUtil.readExcel(filePath, 0);
        List<BookModel> books = convertToBookModelList(data);

        JOptionPane.showMessageDialog(null,
            "Data has been read successfully from " + inputFile.getName() + ".");
        return books;
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while reading data from file: " + inputFile.getName(), e);
        showErrorDialog(e.getMessage(), "File Input Error");
        throw e;
      } catch (IllegalArgumentException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while converting data to BookModel: " + e.getMessage());
        showErrorDialog(e.getMessage(), "Data Conversion Error");
        throw e;
      }
    }

    return null;
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<BookModel> convertToBookModelList(List<List<String>> data) {
    List<BookModel> bookModels = new ArrayList<>();
    for (int i = 1; i < data.size(); i++) {
      List<String> row = data.get(i);
      String isbn = row.get(0);
      String title = row.get(1);
      String description = row.get(2);
      String image = row.get(3);
      int price;
      int quantity;
      try {
        price = Integer.parseInt(row.get(4));
        quantity = Integer.parseInt(row.get(5));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid integer value in input data", e);
      }
      String statusStr = row.get(6);
      Status status;
      try {
        status = Status.valueOf(statusStr);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid status value in row: " + row);
      }
      int publisherId;
      int authorId;
      try {
        publisherId = Integer.parseInt(row.get(7));
        authorId = Integer.parseInt(row.get(8));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid integer value in input data", e);
      }
      BookModel model = new BookModel(isbn, title, description, image, price, quantity, status, publisherId, authorId);
      bookModels.add(model);
      BookBUS.getInstance().addModel(model);
    }
    return bookModels;
  }

  public static void writeBooksToExcel(List<BookModel> books) throws IOException {
    List<List<String>> data = new ArrayList<>();

    // Create header row
    List<String> headerValues = Arrays.asList("isbn", "title", "description", "image", "price", "quantity", "status",
        "publisherId", "authorId");
    data.add(headerValues);

    // Write data rows
    for (BookModel book : books) {
      List<String> values = Arrays.asList(
          book.getIsbn(),
          book.getTitle(),
          book.getDescription(),
          book.getImage(),
          Integer.toString(book.getPrice()),
          Integer.toString(book.getQuantity()),
          book.getStatus().toString(),
          Integer.toString(book.getPublisherId()),
          Integer.toString(book.getAuthorId()));
      data.add(values);
    }

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(filter);
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
        writeExcel(data, filePath, "Books");
        JOptionPane.showMessageDialog(null,
            "Data has been written successfully to " + outputFile.getName() + ".");
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while writing data to file: " + outputFile.getName(), e);
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Output Error",
            JOptionPane.ERROR_MESSAGE);
        throw e;
      }
    }
  }

}
