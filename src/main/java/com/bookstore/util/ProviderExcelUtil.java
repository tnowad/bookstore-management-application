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

import com.bookstore.bus.ProviderBUS;
import com.bookstore.model.ProviderModel;

public class ProviderExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(ProviderExcelUtil.class.getName());

  public static void selectAndProcessProvidersExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Providers", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> providerData = readExcel(file.getAbsolutePath(), 1);
        if (providerData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<ProviderModel> providerModels = convertToProviderModelList(providerData);
        ProviderBUS providerBUS = new ProviderBUS();
        for (ProviderModel model : providerModels) {
          ProviderModel existingProvider = providerBUS.getProviderModel(model.getId());
          if (existingProvider != null) {
            handleDuplicateProvider(existingProvider, model, providerBUS);
          } else {
            providerBUS.insertModel(model);
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

  private static void handleDuplicateProvider(ProviderModel existingProvider, ProviderModel newProvider,
      ProviderBUS providerBUS) throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate provider with ID: " + existingProvider.getId()
            + " was found. Would you like to update or delete this provider?",
        "Duplicate Provider Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
    if (choice == JOptionPane.NO_OPTION) {
      providerBUS.deleteModel(existingProvider.getId());
    } else {
      String oldData = "Old Data:\nID: " + existingProvider.getId() + "\nName: " + existingProvider.getName()
          + "\nDescription: " + existingProvider.getDescription();
      String newData = "New Data:\nID: " + newProvider.getId() + "\nName: " + newProvider.getName()
          + "\nDescription: " + newProvider.getDescription();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(null, message, "Update Provider", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate provider.",
            "Duplicate Provider Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        providerBUS.updateModel(newProvider);
      } else {
        providerBUS.deleteModel(existingProvider.getId());
        providerBUS.insertModel(newProvider);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<ProviderModel> convertToProviderModelList(List<List<String>> data)
      throws IllegalArgumentException {
    List<ProviderModel> providerModels = new ArrayList<>();
    for (List<String> row : data) {
      int id = Integer.parseInt(row.get(0));
      String name = row.get(1);
      String description = row.get(2);
      ProviderModel model = new ProviderModel(id, name, description);
      providerModels.add(model);
    }
    return providerModels;
  }

  public static void writeProvidersToExcel(List<ProviderModel> providers) throws SpreadsheetIOException {
    List<RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Name");
    headerValues.add("Description");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (ProviderModel provider : providers) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(provider.getId()));
      values.add(provider.getName());
      values.add(provider.getDescription());
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
        writeExcel(rowDataList, filePath, "Providers");
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
