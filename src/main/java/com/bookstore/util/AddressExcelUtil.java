package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.AddressBUS;
import com.bookstore.dao.AddressDAO;
import com.bookstore.model.AddressModel;

public class AddressExcelUtil extends ExcelUtil {

  public static void readAddressesFromExcel() throws NumberFormatException, ClassNotFoundException, SQLException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Addresses", "xls", "xlsx", "xslm");
    fileChooser.setFileFilter(excelFileName);

    int option = fileChooser.showOpenDialog(null);

    if (option != JFileChooser.APPROVE_OPTION) {
      return;
    }

    File file = fileChooser.getSelectedFile();
    String filePath = file.getAbsolutePath();

    try {
      List<List<String>> addressData = ExcelUtil.readExcel(filePath, 1);
      List<AddressModel> addressModels = convertToAddressModelList(addressData);
      AddressBUS addressBUS = new AddressBUS();
      for (AddressModel model : addressModels) {
        try {

          AddressModel existingAddress = addressBUS.getModel(model.getId());

          if (existingAddress != null) {
            Object[] options = { "Update", "Delete" };

            int choice = JOptionPane.showOptionDialog(
                null,
                "A duplicate address with ID: " + existingAddress.getId()
                    + " was found. Would you like to update this address?",
                "Duplicate Address Found",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

            while (choice == JOptionPane.CLOSED_OPTION) {
              choice = JOptionPane.showOptionDialog(
                  null,
                  "Please choose to update or delete the duplicate address.",
                  "Duplicate Address Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
            }

            if (choice == JOptionPane.NO_OPTION) {
              AddressDAO.getInstance().delete(existingAddress.getId());
              AddressDAO.getInstance().insert(model);
            } else if (choice == JOptionPane.YES_OPTION) {
              String oldData = "Old Data:\nID: " + existingAddress.getId() + "\nUserID: "
                  + existingAddress.getUserId() + "\nStreet: " + existingAddress.getStreet() + "\nCity: "
                  + existingAddress.getCity() + "\nState: " + existingAddress.getState() + "\nZip Code: "
                  + existingAddress.getZip();

              String newData = "New Data:\nID: " + model.getId() + "\nUserID: " + model.getUserId() + "\nStreet: "
                  + model.getStreet() + "\nCity: " + model.getCity() + "\nState: " + model.getState() + "\nZip Code: "
                  + model.getZip();

              Object[] message = { oldData, newData };

              int updateChoice = JOptionPane.showOptionDialog(
                  null,
                  message,
                  "Update Address",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);

              if (updateChoice == JOptionPane.YES_OPTION) {
                AddressDAO.getInstance().update(model);
              }
            }
          } else {
            AddressDAO.getInstance().insert(model);
          }

        } catch (NullPointerException ptr) {
          JOptionPane.showMessageDialog(null, "Error: " + ptr.getMessage(), "Address not found.",
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

  private static List<AddressModel> convertToAddressModelList(List<List<String>> data) {
    List<AddressModel> addressModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int id = Integer.parseInt(row.get(0));
        int userId = Integer.parseInt(row.get(1));
        String street = row.get(2);
        String city = row.get(3);
        String state = row.get(4);
        String zip = row.get(5);
        AddressModel model = new AddressModel(id, userId, street, city, state, zip);
        addressModels.add(model);
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
    return addressModels;
  }

  public static void writeAddressesToExcel(List<AddressModel> addresses) {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

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

      try {
        writeExcel(rowDataList, filePath, "Addresses");
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