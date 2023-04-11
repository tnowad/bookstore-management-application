package com.bookstore.util;

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

import com.bookstore.bus.PublisherBUS;
import com.bookstore.models.PublisherModel;

public class PublisherExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(PublisherExcelUtil.class.getName());

  public static List<PublisherModel> readPublishersFromExcel() throws IOException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(filter);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File inputFile = fileChooser.getSelectedFile();
      String filePath = inputFile.getAbsolutePath();

      try {
        List<List<String>> data = ExcelUtil.readExcel(filePath, 0);
        List<PublisherModel> publishers = convertToPublisherModelList(data);

        JOptionPane.showMessageDialog(null,
            "Data has been read successfully from " + inputFile.getName() + ".");
        return publishers;
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while reading data from file: " + inputFile.getName(), e);
        showErrorDialog(e.getMessage(), "File Input Error");
        throw e;
      } catch (IllegalArgumentException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while converting data to PublisherModel: " + e.getMessage());
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

  private static List<PublisherModel> convertToPublisherModelList(List<List<String>> data) {
    List<PublisherModel> publisherModels = new ArrayList<>();
    for (int i = 1; i < data.size(); i++) {
      List<String> row = data.get(i);
      int id;
      try {
        id = Integer.parseInt(row.get(0)) + 1;
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid integer value in input data", e);
      }
      String name = row.get(1);
      String description = row.get(2);
      PublisherModel model = new PublisherModel(id, name, description);
      publisherModels.add(model);
      PublisherBUS.getInstance().addModel(model);
    }
    return publisherModels;
  }

  public static void writePublishersToExcel(List<PublisherModel> publishers) throws IOException {
    List<List<String>> data = new ArrayList<>();

    // Create header row
    List<String> headerValues = Arrays.asList("id", "name", "description");
    data.add(headerValues);

    // Write data rows
    for (PublisherModel publisher : publishers) {
      List<String> values = Arrays.asList(
          Integer.toString(publisher.getId()),
          publisher.getName(),
          publisher.getDescription());
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
        writeExcel(data, filePath, "Publishers");
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
