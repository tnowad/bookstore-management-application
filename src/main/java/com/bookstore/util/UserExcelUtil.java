package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.UserBUS;
import com.bookstore.dao.UserDAO;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;
import com.bookstore.model.UserModel.Status;

public class UserExcelUtil extends ExcelUtil {

  public static void readUsersFromExcel() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Users", "xls", "xlsx", "xlsm");
    fileChooser.setFileFilter(excelFileName);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();

      try {
        List<List<String>> userData = ExcelUtil.readExcel(filePath, 1);
        List<UserModel> userModels = convertToUserModelList(userData);
        UserBUS userBusiness = new UserBUS();
        for (UserModel model : userModels) {
          try {
            // Fix NullPointerException: add null check before calling getUserByUsername()
            if (model.getUsername() == null) {
              JOptionPane.showMessageDialog(null, "Error: username cannot be null.", "Username Error",
                  JOptionPane.ERROR_MESSAGE);
              continue;
            }
            UserModel userModel = UserDAO.getInstance().getUserByUsername(model.getUsername());
            if (userModel != null) {
              Object[] options = { "Update", "Delete" };
              int choice = JOptionPane.showOptionDialog(
                  null,
                  "Username " + userModel.getUsername() + " already exists. Do you want to update or delete it?",
                  "Duplicate Username Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
              while (choice == JOptionPane.CLOSED_OPTION) {
                choice = JOptionPane.showOptionDialog(
                    null,
                    "Please choose to update or delete the duplicate user.",
                    "Duplicate Username Found",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
              }
              if (choice == JOptionPane.YES_OPTION) {
                userBusiness.updateModel(model);
              } else {
                userBusiness.deleteModel(userModel.getId());
                userBusiness.insertModel(model);
              }
            } else {
              userBusiness.insertModel(model);
            }
          } catch (NullPointerException ptr) {
            JOptionPane.showMessageDialog(null, "User not found.");
          }
        }
        JOptionPane.showMessageDialog(null, "Data from " + file.getName() + " has been inserted successfully.");
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Input Error", JOptionPane.ERROR_MESSAGE);
        // Fix exception handling: provide guidance to user on how to fix the problem
        JOptionPane.showMessageDialog(null, "Please ensure that the file format is correct and try again.",
            "File Input Error", JOptionPane.ERROR_MESSAGE);
      } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Class Not Found Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      }
    }
  }

  private static List<UserModel> convertToUserModelList(List<List<String>> data) {
    List<UserModel> userModels = new ArrayList<>();
    for (List<String> row : data) {

      try {
        int id = Integer.parseInt(row.get(0));
        String username = row.get(1);
        String password = row.get(2);
        Status status = Status.valueOf(row.get(3).toUpperCase());
        String name = row.get(4);
        String email = row.get(5);
        String phone = row.get(6);
        Timestamp createdAt = Timestamp.valueOf(row.get(7));
        Timestamp updatedAt = Timestamp.valueOf(row.get(8));
        Role role = Role.valueOf(row.get(9).toUpperCase());

        UserModel model = new UserModel(id, username, password, status, name, email, phone, createdAt, updatedAt, role);
        userModels.add(model);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Number Format Error",
            JOptionPane.ERROR_MESSAGE);
        // Fix exception handling: provide guidance to user on how to fix the problem
        JOptionPane.showMessageDialog(null,
            "Please ensure that all numeric fields contain valid numerical data and try again.", "Number Format Error",
            JOptionPane.ERROR_MESSAGE);
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Illegal Argument Error",
            JOptionPane.ERROR_MESSAGE);
        // Fix exception handling: provide guidance to user on how to fix the problem
        JOptionPane.showMessageDialog(null, "Please ensure that all enum fields have valid values and try again.",
            "Illegal Argument Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    return userModels;
  }

  public static void writeUsersToExcel(List<UserModel> users) {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

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

      // Check if file exists and prompt user before overwriting
      if (outputFile.exists()) {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to overwrite the existing file?",
            "Confirm Overwrite", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
          return;
        }
      }

      try {
        writeExcel(rowDataList, filePath, "Users");
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
