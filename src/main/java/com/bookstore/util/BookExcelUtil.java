package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.BookBUS;
import com.bookstore.model.BookModel;

public class BookExcelUtil extends ExcelUtil {

  public static void readBooksFromExcel() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Books", "xls", "xlsx", "xlsm");
    fileChooser.setFileFilter(excelFileName);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();

      try {
        List<List<String>> bookData = ExcelUtil.readExcel(filePath, 1);
        List<BookModel> bookModels = convertToBookModelList(bookData);
        BookBUS bookBusiness = new BookBUS();
        for (BookModel model : bookModels) {
          try {
            // Check for null ISBN before getting book model
            if (model.getIsbn() == null) {
              JOptionPane.showMessageDialog(null, "Error: ISBN cannot be null.", "ISBN Error",
                  JOptionPane.ERROR_MESSAGE);
              continue;
            }

            if (model.getTitle() == null) {
              JOptionPane.showMessageDialog(null, "Error: title cannot be null.", "Title Error",
                  JOptionPane.ERROR_MESSAGE);
              continue;
            }

            BookBUS bookBUS = new BookBUS();
            BookModel bookModel = bookBUS.getBookModel(Integer.parseInt(model.getIsbn()));
            if (bookModel != null) {
              Object[] options = { "Update", "Delete" };
              int choice = JOptionPane.showOptionDialog(
                  null,
                  "Duplicate ISBN Found: do you want to update or delete?",
                  "Duplicate ISBN Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
              if (choice == JOptionPane.YES_OPTION) {
                bookBusiness.deleteModel(Integer.parseInt(model.getIsbn()));
                bookBusiness.insertModel(model);
              } else {
                bookBusiness.updateModel(model);
              }
            } else {
              bookBusiness.insertModel(model);
            }
          } catch (NullPointerException ptr) {
            // Show error message explaining the problem
            JOptionPane.showMessageDialog(null, "Error: Unable to retrieve data from the database.");
          }
        }
        JOptionPane.showMessageDialog(null, "Data from " + file.getName() + " has been inserted successfully.");
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Input Error", JOptionPane.ERROR_MESSAGE);
        // Provide guidance to user on how to fix the problem
        JOptionPane.showMessageDialog(null, "Please ensure that the file format is correct and try again.",
            "File Input Error", JOptionPane.ERROR_MESSAGE);
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

      // Check if file exists and prompt user before overwriting
      if (outputFile.exists()) {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to overwrite the existing file?",
            "Confirm Overwrite", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
          return;
        }
      }

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
}
