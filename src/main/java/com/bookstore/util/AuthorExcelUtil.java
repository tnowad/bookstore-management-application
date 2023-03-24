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

import com.bookstore.bus.AuthorBUS;
import com.bookstore.model.AuthorModel;

public class AuthorExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(AuthorExcelUtil.class.getName());

  public static void selectAndProcessAuthorsExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Authors", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> authorData = ExcelUtil.readExcel(file.getAbsolutePath(), 1);
        if (authorData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<AuthorModel> authorModels = convertToAuthorModelList(authorData);
        AuthorBUS authorBUS = new AuthorBUS();
        for (AuthorModel model : authorModels) {
          AuthorModel existingAuthor = authorBUS.getAuthorModel(model.getId());
          if (existingAuthor != null) {
            handleDuplicateAuthor(existingAuthor, model, authorBUS);
          } else {
            authorBUS.insertModel(model);
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

  private static void handleDuplicateAuthor(AuthorModel existingAuthor, AuthorModel newAuthor, AuthorBUS authorBUS)
      throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate author with ID: " + existingAuthor.getId()
            + " was found. Would you like to update or delete this author?",
        "Duplicate Author Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);

    if (choice == JOptionPane.NO_OPTION) {
      authorBUS.deleteModel(existingAuthor.getId());
    } else {
      String oldData = "Old Data:\nID: " + existingAuthor.getId() + "\nName: " + existingAuthor.getName()
          + "\nDescription: " + existingAuthor.getDescription();
      String newData = "New Data:\nID: " + newAuthor.getId() + "\nName: " + newAuthor.getName() + "\nDescription: "
          + newAuthor.getDescription();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(
          null,
          message,
          "Update Author",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate author.",
            "Duplicate Author Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        authorBUS.updateModel(newAuthor);
      } else {
        authorBUS.deleteModel(existingAuthor.getId());
        authorBUS.insertModel(newAuthor);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<AuthorModel> convertToAuthorModelList(List<List<String>> data) throws IllegalArgumentException {
    List<AuthorModel> AuthorModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int id = Integer.parseInt(row.get(0));
        String name = row.get(1);
        String description = row.get(2);
        AuthorModel model = new AuthorModel(id, name, description);
        AuthorModels.add(model);
      } catch (NumberFormatException e) {
        LOGGER.log(Level.WARNING, "Invalid number format found in data: " + row, e);
        throw new IllegalArgumentException("Invalid number format found in data.");
      } catch (IndexOutOfBoundsException e) {
        LOGGER.log(Level.WARNING, "Missing data found in row: " + row, e);
        throw new IllegalArgumentException("Missing data found in row.");
      }
    }
    return AuthorModels;
  }

  public static void writeAuthorsToExcel(List<AuthorModel> Authors) throws SpreadsheetIOException {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Name");
    headerValues.add("Description");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (AuthorModel Author : Authors) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(Author.getId()));
      values.add(Author.getName());
      values.add(Author.getDescription());
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
        writeExcel(rowDataList, filePath, "Authors");
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
