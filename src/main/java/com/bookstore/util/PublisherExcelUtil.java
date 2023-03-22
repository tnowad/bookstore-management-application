package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.PublisherBUS;
import com.bookstore.dao.PublisherDAO;
import com.bookstore.model.PublisherModel;

public class PublisherExcelUtil extends ExcelUtil {
  public static void readPublishersFromExcel() throws NumberFormatException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Publishers", "xls", "xlsx", "xslm");
    fileChooser.setFileFilter(excelFileName);

    int option = fileChooser.showOpenDialog(null);

    if (option != JFileChooser.APPROVE_OPTION) {
      return;
    }

    File file = fileChooser.getSelectedFile();
    String filePath = file.getAbsolutePath();

    try {
      List<List<String>> providerData = ExcelUtil.readExcel(filePath, 1);
      List<PublisherModel> providerModels = convertToPublisherModelList(providerData);

      for (PublisherModel model : providerModels) {
        PublisherBUS providerBUS = new PublisherBUS();

        try {
          PublisherModel existingPublisher = providerBUS.getPublisherModel(model.getId());

          if (existingPublisher != null) {
            Object[] options = { "Update", "Delete" };

            int choice = JOptionPane.showOptionDialog(
                null,
                "A duplicate provider with ID: " + existingPublisher.getId()
                    + " was found. Would you like to update this provider?",
                "Duplicate Publisher Found",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

            while (choice == JOptionPane.CLOSED_OPTION) {
              choice = JOptionPane.showOptionDialog(
                  null,
                  "Please choose to update or delete the duplicate provider.",
                  "Duplicate Publisher Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
            }

            if (choice == JOptionPane.NO_OPTION) {
              providerBUS.deleteModel(existingPublisher.getId());
              providerBUS.insertModel(model);
            } else if (choice == JOptionPane.YES_OPTION) {
              String oldData = "Old Data:\nID: " + existingPublisher.getId() + "\nName: " + existingPublisher.getName()
                  + "\nDescription: " + existingPublisher.getDescription();

              String newData = "New Data:\nID: " + model.getId() + "\nName: " + model.getName() + "\nDescription: "
                  + model.getDescription();

              Object[] message = { oldData, newData };

              int updateChoice = JOptionPane.showOptionDialog(
                  null,
                  message,
                  "Update Publisher",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);

              if (updateChoice == JOptionPane.YES_OPTION) {
                providerBUS.updateModel(model);
              }
            }
          } else {
            providerBUS.insertModel(model);
            PublisherDAO.getInstance().insert(model);
          }

        } catch (NullPointerException ptr) {
          JOptionPane.showMessageDialog(null, "Error: " + ptr.getMessage(), "Publisher not found.",
              JOptionPane.ERROR_MESSAGE);
        }

      }

      JOptionPane.showMessageDialog(null, "Data from " + file.getName() + " has been inserted successfully.");

    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Input Error", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    } catch (SQLException | ClassNotFoundException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }

  private static List<PublisherModel> convertToPublisherModelList(List<List<String>> data) {
    List<PublisherModel> providerModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int id = Integer.parseInt(row.get(0));
        String name = row.get(1);
        String description = row.get(2);
        PublisherModel model = new PublisherModel(id, name, description);
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

  public static void writePublishersToExcel(List<PublisherModel> providers) {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Name");
    headerValues.add("Description");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (PublisherModel provider : providers) {
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
        writeExcel(rowDataList, filePath, "Publishers");
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
