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

import com.bookstore.bus.AddressBUS;
import com.bookstore.model.AddressModel;

public class AddressExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(AddressExcelUtil.class.getName());

  public static void selectAndProcessAddressesExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Addresses", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> addressData = readExcel(file.getAbsolutePath(), 1);
        if (addressData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<AddressModel> addressModels = convertToAddressModelList(addressData);
        AddressBUS addressBUS = new AddressBUS();
        for (AddressModel model : addressModels) {
          AddressModel existingAddress = addressBUS.getModelById(model.getId());
          if (existingAddress != null) {
            handleDuplicateAddress(existingAddress, model, addressBUS);
          } else {
            addressBUS.addModel(model);
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

  private static void handleDuplicateAddress(AddressModel existingAddress, AddressModel newAddress,
      AddressBUS addressBUS) throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate address with ID: " + existingAddress.getId()
            + " was found. Would you like to update or delete this address?",
        "Duplicate Address Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
    if (choice == JOptionPane.NO_OPTION) {
      addressBUS.deleteModel(existingAddress.getId());
    } else {
      String oldData = "Old Data:\nID: " + existingAddress.getId() + "\nUser ID: " + existingAddress.getUserId()
          + "\nStreet: " + existingAddress.getStreet() + "\nCity: " + existingAddress.getCity() + "\nState: "
          + existingAddress.getState() + "\nZip: " + existingAddress.getZip();
      String newData = "New Data:\nID: " + newAddress.getId() + "\nUser ID: " + newAddress.getUserId()
          + "\nStreet: " + newAddress.getStreet() + "\nCity: " + newAddress.getCity() + "\nState: "
          + newAddress.getState() + "\nZip: " + newAddress.getZip();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(null, message, "Update Address", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate address.",
            "Duplicate Address Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        addressBUS.updateModel(newAddress);
      } else {
        addressBUS.deleteModel(existingAddress.getId());
        addressBUS.addModel(newAddress);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<AddressModel> convertToAddressModelList(List<List<String>> data) throws IllegalArgumentException {
    List<AddressModel> addressModels = new ArrayList<>();
    for (List<String> row : data) {
      int id = Integer.parseInt(row.get(0));
      int userId = Integer.parseInt(row.get(1));
      String street = row.get(2);
      String city = row.get(3);
      String state = row.get(4);
      String zip = row.get(5);
      AddressModel model = new AddressModel(id, userId, street, city, state, zip);
      addressModels.add(model);
    }
    return addressModels;
  }

  public static void writeAddressesToExcel(List<AddressModel> addresses) throws SpreadsheetIOException {
    List<RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("User ID");
    headerValues.add("Street");
    headerValues.add("City");
    headerValues.add("State");
    headerValues.add("Zip");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (AddressModel address : addresses) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(address.getId()));
      values.add(Integer.toString(address.getUserId()));
      values.add(address.getStreet());
      values.add(address.getCity());
      values.add(address.getState());
      values.add(address.getZip());
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
        writeExcel(rowDataList, filePath, "Addresses");
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
