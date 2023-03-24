package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.ImportBUS;
import com.bookstore.model.ImportModel;

public class ImportModelExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(ImportModelExcelUtil.class.getName());

  public static void selectAndProcessImportModelExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Import Models", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> importModelData = ExcelUtil.readExcel(file.getAbsolutePath(), 1);
        if (importModelData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<ImportModel> importModels = convertToImportModelList(importModelData);
        ImportBUS importModelBUS = new ImportBUS();
        for (ImportModel model : importModels) {
          ImportModel existingImportModel = importModelBUS.getImportModel(model.getId());
          if (existingImportModel != null) {
            handleDuplicateImportModel(existingImportModel, model, importModelBUS);
          } else {
            importModelBUS.insertModel(model);
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
    }
  }

  private static void handleDuplicateImportModel(ImportModel existingImportModel, ImportModel newImportModel,
      ImportBUS importModelBUS) throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate import model with ID: " + existingImportModel.getId()
            + " was found. Would you like to update or delete this import model?",
        "Duplicate Import Model Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
    if (choice == JOptionPane.NO_OPTION) {
      importModelBUS.deleteModel(existingImportModel.getId());
    } else {
      String oldData = "Old Data:\nID: " + existingImportModel.getId() + "\nProvider ID: "
          + existingImportModel.getProviderId() + "\nEmployee ID: " + existingImportModel.getEmployeeId()
          + "\nTotal Price: " + existingImportModel.getTotalPrice() + "\nCreated At: "
          + existingImportModel.getCreatedAt() + "\nUpdated At: " + existingImportModel.getUpdatedAt();
      String newData = "New Data:\nID: " + newImportModel.getId() + "\nProvider ID: " + newImportModel.getProviderId()
          + "\nEmployee ID: " + newImportModel.getEmployeeId() + "\nTotal Price: " + newImportModel.getTotalPrice()
          + "\nCreated At: " + newImportModel.getCreatedAt() + "\nUpdated At: " + newImportModel.getUpdatedAt();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(
          null,
          message,
          "Update Import Model",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate import model.",
            "Duplicate Import Model Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        importModelBUS.updateModel(newImportModel);
      } else {
        importModelBUS.deleteModel(existingImportModel.getId());
        importModelBUS.insertModel(newImportModel);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<ImportModel> convertToImportModelList(List<List<String>> data) throws IllegalArgumentException {
    List<ImportModel> importModels = new ArrayList<>();
    for (List<String> row : data) {
      int id = Integer.parseInt(row.get(0));
      int providerId = Integer.parseInt(row.get(1));
      int employeeId = Integer.parseInt(row.get(2));
      BigDecimal totalPrice = new BigDecimal(row.get(3));
      Timestamp createdAt = Timestamp.valueOf(row.get(4));
      Timestamp updatedAt = Timestamp.valueOf(row.get(5));
      ImportModel model = new ImportModel(id, providerId, employeeId, totalPrice, createdAt, updatedAt);
      importModels.add(model);
    }
    return importModels;
  }

  public static void writeImportModelToExcel(List<ImportModel> imports) throws SpreadsheetIOException {
    List<RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Provider ID");
    headerValues.add("Employee ID");
    headerValues.add("Total Price");
    headerValues.add("Created At");
    headerValues.add("Updated At");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (ImportModel importModel : imports) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(importModel.getId()));
      values.add(Integer.toString(importModel.getProviderId()));
      values.add(Integer.toString(importModel.getEmployeeId()));
      values.add(importModel.getTotalPrice().toString());
      values.add(importModel.getCreatedAt().toString());
      values.add(importModel.getUpdatedAt().toString());
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
        writeExcel(rowDataList, filePath, "Imports");
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
