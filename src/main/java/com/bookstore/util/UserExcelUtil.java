package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.UserBUS;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;
import com.bookstore.model.UserModel.Status;

public class UserExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(UserExcelUtil.class.getName());

  static void selectAndProcessUsersExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Users", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> userData = readExcel(file.getAbsolutePath(), 1);
        if (userData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<UserModel> userModels = convertToUserModelList(userData);
        UserBUS userBUS = new UserBUS();
        for (UserModel model : userModels) {
          UserModel existingUser = userBUS.getModelById(model.getId());
          if (existingUser != null) {
            handleDuplicateUser(existingUser, model, userBUS);
          } else {
            userBUS.addModel(model);
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

  static void handleDuplicateUser(UserModel existingUser, UserModel newUser,
      UserBUS userBUS) throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate user with ID: " + existingUser.getId()
            + " was found. Would you like to update or delete this user?",
        "Duplicate User Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
    if (choice == JOptionPane.NO_OPTION) {
      userBUS.deleteModel(existingUser.getId());
    } else {
      String oldData = "Old Data:\nID: " + existingUser.getId() + "\nUsername: " + existingUser.getUsername()
          + "\nPassword: " + existingUser.getPassword() + "\nStatus: " + existingUser.getStatus().toString()
          + "\nName: " + existingUser.getName() + "\nEmail: " + existingUser.getEmail() + "\nPhone: "
          + existingUser.getPhone() + "\nCreated At: " + existingUser.getCreatedAt().toString() + "\nUpdated At: "
          + existingUser.getUpdatedAt().toString() + "\nRole: " + existingUser.getRole().toString();
      String newData = "New Data:\nID: " + newUser.getId() + "\nUsername: " + newUser.getUsername()
          + "\nPassword: " + newUser.getPassword() + "\nStatus: " + newUser.getStatus().toString()
          + "\nName: " + newUser.getName() + "\nEmail: " + newUser.getEmail() + "\nPhone: "
          + newUser.getPhone() + "\nCreated At: " + newUser.getCreatedAt().toString() + "\nUpdated At: "
          + newUser.getUpdatedAt().toString() + "\nRole: " + newUser.getRole().toString();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(null, message, "Update User", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate user.",
            "Duplicate User Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        userBUS.updateModel(newUser);
      } else {
        userBUS.deleteModel(existingUser.getId());
        userBUS.addModel(newUser);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  public static List<UserModel> convertToUserModelList(List<List<String>> data) throws IllegalArgumentException {
    List<UserModel> userModels = new ArrayList<>();
    for (List<String> row : data) {
      int id = Integer.parseInt(row.get(0));
      String username = row.get(1);
      String password = row.get(2);
      Status status = Status.valueOf(row.get(3));
      String name = row.get(4);
      String email = row.get(5);
      String phone = row.get(6);
      Timestamp createdAt = Timestamp.valueOf(row.get(7));
      Timestamp updatedAt = Timestamp.valueOf(row.get(8));
      Role role = Role.valueOf(row.get(9));
      UserModel model = new UserModel(id, username, password, status, name, email, phone, createdAt, updatedAt, role);
      userModels.add(model);
    }
    return userModels;
  }

  public static void writeUsersToExcel(List<UserModel> users) throws SpreadsheetIOException {
    List<RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Username");
    headerValues.add("Password");
    headerValues.add("Status");
    headerValues.add("Name");
    headerValues.add("Email");
    headerValues.add("Phone");
    headerValues.add("Created At");
    headerValues.add("Updated At");
    headerValues.add("Role");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (UserModel user : users) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(user.getId()));
      values.add(user.getUsername());
      values.add(user.getPassword());
      values.add(user.getStatus().toString());
      values.add(user.getName());
      values.add(user.getEmail());
      values.add(user.getPhone());
      values.add(user.getCreatedAt().toString());
      values.add(user.getUpdatedAt().toString());
      values.add(user.getRole().toString());
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
        writeExcel(rowDataList, filePath, "Users");
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
