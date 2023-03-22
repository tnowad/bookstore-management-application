package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.ProviderBUS;
import com.bookstore.dao.ProviderDAO;
import com.bookstore.model.ProviderModel;

public class ProviderExcelUtil extends ExcelUtil {

  public static void readProvidersFromExcel() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Providers", "xls", "xlsx", "xslm");
    fileChooser.setFileFilter(excelFileName);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();

      try {
        List<List<String>> providerData = ExcelUtil.readExcel(filePath, 1);
        List<ProviderModel> providerModels = convertToProviderModelList(providerData);
        ProviderBUS providerBus = new ProviderBUS();
        for (ProviderModel model : providerModels) {
          try {
            ProviderModel providerModel = ProviderDAO.getInstance().getProviderById(model.getId());
            if (providerModel != null) {
              Object[] options = { "Skip", "Update" };
              int choice = JOptionPane.showOptionDialog(
                  null,
                  "Do you want to update the provider with ID: " + providerModel.getId(),
                  "Duplicate Provider Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
              if (choice == JOptionPane.YES_OPTION) {
                continue;
              } else {
                providerBus.updateModel(model);
              }
            } else {
              providerBus.insertModel(model);
              ProviderDAO.getInstance().insert(model);
            }
          } catch (NullPointerException ptr) {
            JOptionPane.showMessageDialog(null, "Provider not found.");
          }
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
    } else {
      return;
    }
  }

  private static List<ProviderModel> convertToProviderModelList(List<List<String>> data) {
    List<ProviderModel> providerModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int id = Integer.parseInt(row.get(0));
        String name = row.get(1);
        String description = row.get(2);
        ProviderModel model = new ProviderModel(id, name, description);
        providerModels.add(model);
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
    return providerModels;
  }

  public static void writeProvidersToExcel(List<ProviderModel> providers) {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

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

      try {
        writeExcel(rowDataList, filePath, "Providers");
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
