package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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

  public static List<UserModel> readUsersFromExcel() throws IOException, ClassNotFoundException, SQLException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(filter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File inputFile = fileChooser.getSelectedFile();
      String filePath = inputFile.getAbsolutePath();
      try {
        List<List<String>> data = ExcelUtil.readExcel(filePath, 0);
        List<UserModel> users = convertToUserModelList(data);
        JOptionPane.showMessageDialog(null,
            "Data has been read successfully from " + inputFile.getName() + ".");
        return users;
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while reading data from file: " + inputFile.getName(), e);
        showErrorDialog(e.getMessage(), "File Input Error");
        throw e;
      } catch (IllegalArgumentException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while converting data to UserModel: " + e.getMessage());
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

  private static List<UserModel> convertToUserModelList(List<List<String>> data)
      throws IllegalArgumentException, ClassNotFoundException, SQLException {
    List<UserModel> userModels = new ArrayList<>();
    for (int i = 1; i < data.size(); i++) {
      List<String> row = data.get(i);
      String id = String.valueOf(row.get(0)) + 1;
      String username = row.get(1);
      String password = row.get(2);
      Status status;
      try {
        status = Status.valueOf(row.get(3));
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid status value in row: " + row);
      }
      String name = row.get(4);
      String email = row.get(5);
      String phone = row.get(6);
      Timestamp createdAt;
      Timestamp updatedAt;
      try {
        createdAt = Timestamp.valueOf(row.get(7));
        updatedAt = Timestamp.valueOf(row.get(8));
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid timestamp format in row: " + row);
      }
      String roleStr = row.get(9);
      Role role;
      try {
        role = Role.valueOf(roleStr);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid role value in row: " + row);
      }

      UserModel model = new UserModel(Integer.parseInt(id), username, password, status, name, email, phone, createdAt,
          updatedAt, role);
      userModels.add(model);
      UserBUS.getInstance().addModel(model);
    }
    return userModels;
  }

  public static void writeUsersToExcel(List<UserModel> users) throws IOException {
    List<List<String>> data = new ArrayList<>();

    // Create header row
    List<String> headerValues = Arrays.asList("id", "username", "password", "status", "name", "email", "phone",
        "created_at", "updated_at", "role");
    data.add(headerValues);

    // Write data rows
    for (UserModel user : users) {
      List<String> values = Arrays.asList(
          Integer.toString(user.getId()),
          user.getUsername(),
          user.getPassword(),
          user.getStatus().toString(),
          user.getName(),
          user.getEmail(),
          user.getPhone(),
          user.getCreatedAt().toString(),
          user.getUpdatedAt().toString(),
          user.getRole().toString());
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
        writeExcel(data, filePath, "Users");
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
