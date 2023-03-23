package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.ImportBUS;
import com.bookstore.dao.ImportDAO;
import com.bookstore.model.ImportModel;
import com.bookstore.util.ExcelUtil.RowData;
import com.bookstore.util.ExcelUtil.SpreadsheetIOException;

public class ImportModelExcelUtil {

  public static void readModelsFromExcel() throws NumberFormatException, ClassNotFoundException, SQLException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Imports", "xls", "xlsx", "xlsm");
    fileChooser.setFileFilter(excelFileName);
    int option = fileChooser.showOpenDialog(null);
    if (option != JFileChooser.APPROVE_OPTION) {
      return;
    }
    File file = fileChooser.getSelectedFile();
    String filePath = file.getAbsolutePath();
    try {
      List<List<String>> modelData = ExcelUtil.readExcel(filePath, 0);
      List<ImportModel> importModels = convertToImportModelList(modelData);
      ImportBUS importModelBUS = new ImportBUS();

      for (ImportModel model : importModels) {
        try {

          ImportModel existingModel = importModelBUS.getModel(model.getId());

          if (existingModel != null) {
            Object[] options = { "Update", "Delete" };

            int choice = JOptionPane.showOptionDialog(
                null,
                "A duplicate model with ID: " + existingModel.getId()
                    + " was found. Would you like to update this model?",
                "Duplicate Model Found",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

            while (choice == JOptionPane.CLOSED_OPTION) {
              choice = JOptionPane.showOptionDialog(
                  null,
                  "Please choose to update or delete the duplicate model.",
                  "Duplicate Model Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
            }

            if (choice == JOptionPane.NO_OPTION) {
              ImportDAO.getInstance().delete(existingModel.getId());
              ImportDAO.getInstance().insert(model);
            } else if (choice == JOptionPane.YES_OPTION) {
              String oldData = "Old Data:\nID: " + existingModel.getId() + "\nProvider ID: "
                  + existingModel.getProviderId() + "\nEmployee ID: " + existingModel.getEmployeeId()
                  + "\nTotal Price: "
                  + existingModel.getTotalPrice() + "\nCreated At: " + existingModel.getCreatedAt() + "\nUpdated At:"
                  + existingModel.getUpdatedAt();

              String newData = "New Data:\nID: " + model.getId() + "\nProvider ID: " + model.getProviderId()
                  + "\nEmployee ID: " + model.getEmployeeId() + "\nTotal Price: " + model.getTotalPrice()
                  + "\nCreated At: " + model.getCreatedAt() + "\nUpdated At:" + model.getUpdatedAt();

              Object[] message = { oldData, newData };

              int updateChoice = JOptionPane.showOptionDialog(
                  null,
                  message,
                  "Update Model",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);

              if (updateChoice == JOptionPane.YES_OPTION) {
                ImportDAO.getInstance().update(model);
              }
            }
          } else {
            ImportDAO.getInstance().insert(model);

          }

        } catch (NullPointerException ptr) {
          JOptionPane.showMessageDialog(null, "Error: " + ptr.getMessage(), "Model not found.",
              JOptionPane.ERROR_MESSAGE);
        }
      }
      JOptionPane.showMessageDialog(null, "Data from " + file.getName() + " has been inserted successfully.");
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Input Error", JOptionPane.ERROR_MESSAGE);
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

  private static List<ImportModel> convertToImportModelList(List<List<String>> data) {
    List<ImportModel> importModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int id = Integer.parseInt(row.get(0));
        int providerId = Integer.parseInt(row.get(1));
        int employeeId = Integer.parseInt(row.get(2));
        BigDecimal totalPrice = new BigDecimal(row.get(3));
        Timestamp createdAt = Timestamp.valueOf(row.get(4));
        Timestamp updatedAt = Timestamp.valueOf(row.get(5));
        ImportModel model = new ImportModel(id, providerId, employeeId, totalPrice, createdAt, updatedAt);
        importModels.add(model);
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
    return importModels;
  }

  public static void writeModelsToExcel(List<List<String>> imports) {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Provider ID");
    headerValues.add("Employee ID");
    headerValues.add("Total Price");
    headerValues.add("Created At");
    headerValues.add("Updated At");
    RowData headerRow = new RowData(headerValues);
    rowDataList.add(headerRow);

    // Write data rows
    for (List<String> importModel : imports) {
      List<String> values = new ArrayList<>();
      values.add(importModel.get(0));
      values.add(importModel.get(1));
      values.add(importModel.get(2));
      values.add(importModel.get(3));
      values.add(importModel.get(4));
      values.add(importModel.get(5));
      RowData rowData = new RowData(values);
      rowDataList.add(rowData);
    }

    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showSaveDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File outputFile = fileChooser.getSelectedFile();
      String filePath = outputFile.getAbsolutePath();

      try {
        ExcelUtil.writeExcel(rowDataList, filePath, "Imports");
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
