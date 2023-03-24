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

import com.bookstore.bus.PublisherBUS;
import com.bookstore.model.PublisherModel;

public class PublisherExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(PublisherExcelUtil.class.getName());

  public static void selectAndProcessPublishersExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Publishers", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> publisherData = readExcel(file.getAbsolutePath(), 1);
        if (publisherData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<PublisherModel> publisherModels = convertToPublisherModelList(publisherData);
        PublisherBUS publisherBUS = new PublisherBUS();
        for (PublisherModel model : publisherModels) {
          PublisherModel existingPublisher = publisherBUS.getPublisherModel(model.getId());
          if (existingPublisher != null) {
            handleDuplicatePublisher(existingPublisher, model, publisherBUS);
          } else {
            publisherBUS.insertModel(model);
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

  private static void handleDuplicatePublisher(PublisherModel existingPublisher, PublisherModel newPublisher,
      PublisherBUS publisherBUS) throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate publisher with ID: " + existingPublisher.getId()
            + " was found. Would you like to update or delete this publisher?",
        "Duplicate Publisher Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
    if (choice == JOptionPane.NO_OPTION) {
      publisherBUS.deleteModel(existingPublisher.getId());
    } else {
      String oldData = "Old Data:\nID: " + existingPublisher.getId() + "\nName: " + existingPublisher.getName()
          + "\nDescription: " + existingPublisher.getDescription();
      String newData = "New Data:\nID: " + newPublisher.getId() + "\nName: " + newPublisher.getName()
          + "\nDescription: " + newPublisher.getDescription();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(null, message, "Update Publisher", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate publisher.",
            "Duplicate Publisher Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        publisherBUS.updateModel(newPublisher);
      } else {
        publisherBUS.deleteModel(existingPublisher.getId());
        publisherBUS.insertModel(newPublisher);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<PublisherModel> convertToPublisherModelList(List<List<String>> data)
      throws IllegalArgumentException {
    List<PublisherModel> publisherModels = new ArrayList<>();
    for (List<String> row : data) {
      int id = Integer.parseInt(row.get(0));
      String name = row.get(1);
      String description = row.get(2);
      PublisherModel model = new PublisherModel(id, name, description);
      publisherModels.add(model);
    }
    return publisherModels;
  }

  public static void writePublishersToExcel(List<PublisherModel> publishers) throws SpreadsheetIOException {
    List<RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Name");
    headerValues.add("Description");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (PublisherModel publisher : publishers) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(publisher.getId()));
      values.add(publisher.getName());
      values.add(publisher.getDescription());
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
        writeExcel(rowDataList, filePath, "Publishers");
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
