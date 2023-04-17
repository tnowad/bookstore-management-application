package com.bookstore.util.Excel;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.ImportBUS;
import com.bookstore.models.ImportModel;

public class ImportModelExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(ImportModelExcelUtil.class.getName());

  public static List<ImportModel> readImportsFromExcel() throws IOException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(filter);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File inputFile = fileChooser.getSelectedFile();
      String filePath = inputFile.getAbsolutePath();

      try {
        List<List<String>> data = ExcelUtil.readExcel(filePath, 0);
        List<ImportModel> imports = convertToImportModelList(data);

        JOptionPane.showMessageDialog(null,
            "Data has been read successfully from " + inputFile.getName() + ".");
        return imports;
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while reading data from file: " + inputFile.getName(), e);
        showErrorDialog(e.getMessage(), "File Input Error");
        throw e;
      } catch (IllegalArgumentException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while converting data to ImportModel: " + e.getMessage());
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

  private static List<ImportModel> convertToImportModelList(List<List<String>> data) {
    List<ImportModel> importModels = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    for (int i = 1; i < data.size(); i++) {
      List<String> row = data.get(i);
      int id;
      int providerId;
      int employeeId;
      Double totalPrice;
      Timestamp createdAt;
      Timestamp updatedAt;

      if (row.get(0).contains(".")) {
        id = (int) Float.parseFloat(row.get(0)) + 1;
      } else {
        id = Integer.parseInt(row.get(0)) + 1;
      }

      if (row.get(1).contains(".")) {
        providerId = (int) Float.parseFloat(row.get(1));
      } else {
        providerId = Integer.parseInt(row.get(1));
      }

      if (row.get(2).contains(".")) {
        employeeId = (int) Float.parseFloat(row.get(2));
      } else {
        employeeId = Integer.parseInt(row.get(2));
      }

      if (row.get(3).contains(".")) {
        totalPrice = Double.parseDouble(row.get(3));
      } else {
        totalPrice = Double.parseDouble(row.get(3));
      }
      //TODO: Need to fix timestamp format.
      // Parse createdAt and updatedAt
      try {
        createdAt = new Timestamp(dateFormat.parse(row.get(4)).getTime());
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid value in input data for createdAt at line: " + row, e);
      }

      try {
        updatedAt = new Timestamp(dateFormat.parse(row.get(5)).getTime());
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid value in input data for updatedAt at line: " + row, e);
      }

      ImportModel model = new ImportModel(id, providerId, employeeId, totalPrice, createdAt, updatedAt);
      importModels.add(model);
      ImportBUS.getInstance().addModel(model);
    }
    return importModels;
  }

  public static void writeImportsToExcel(List<ImportModel> imports) throws IOException {
    List<List<String>> data = new ArrayList<>();

    // Create header row
    List<String> headerValues = Arrays.asList("id", "providerId", "employeeId", "totalPrice", "createdAt", "updatedAt");
    data.add(headerValues);

    // Write data rows
    for (ImportModel importModel : imports) {
      List<String> values = Arrays.asList(
          Integer.toString(importModel.getId()),
          Integer.toString(importModel.getProviderId()),
          Integer.toString(importModel.getEmployeeId()),
          importModel.getTotalPrice().toString(),
          importModel.getCreatedAt().toString(),
          importModel.getUpdatedAt().toString());
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
        writeExcel(data, filePath, "Imports");
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
