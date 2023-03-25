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

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.model.EmployeeModel;
import com.bookstore.model.EmployeeModel.EmployeeType;

public class EmployeeExcelUtil extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(EmployeeExcelUtil.class.getName());

  public static void selectAndProcessEmployeesExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Employees", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> employeeData = readExcel(file.getAbsolutePath(), 1);
        if (employeeData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<EmployeeModel> employeeModels = convertToEmployeeModelList(employeeData);
        EmployeeBUS employeeBUS = new EmployeeBUS();
        for (EmployeeModel model : employeeModels) {
          EmployeeModel existingEmployee = employeeBUS.getModelById(model.getUserId());
          if (existingEmployee != null) {
            handleDuplicateEmployee(existingEmployee, model, employeeBUS);
          } else {
            employeeBUS.addModel(model);
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

  private static void handleDuplicateEmployee(EmployeeModel existingEmployee, EmployeeModel newEmployee,
      EmployeeBUS employeeBUS) throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate employee with ID: " + existingEmployee.getUserId()
            + " was found. Would you like to update or delete this employee?",
        "Duplicate Employee Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
    if (choice == JOptionPane.NO_OPTION) {
      employeeBUS.deleteModel(existingEmployee.getUserId());
    } else {
      String oldData = "Old Data:\nID: " + existingEmployee.getUserId() + "\nSalary: " + existingEmployee.getSalary()
          + "\nEmployee Type: " + existingEmployee.getEmployeeType() + "\nContact Information: "
          + existingEmployee.getContactInformation();
      String newData = "New Data:\nID: " + newEmployee.getUserId() + "\nSalary: " + newEmployee.getSalary()
          + "\nEmployee Type: " + newEmployee.getEmployeeType() + "\nContact Information: "
          + newEmployee.getContactInformation();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(null, message, "Update Employee", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate employee.",
            "Duplicate Employee Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        employeeBUS.updateModel(newEmployee);
      } else {
        employeeBUS.deleteModel(existingEmployee.getUserId());
        employeeBUS.addModel(newEmployee);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<EmployeeModel> convertToEmployeeModelList(List<List<String>> data)
      throws IllegalArgumentException {
    List<EmployeeModel> employeeModels = new ArrayList<>();
    for (List<String> row : data) {
      int userId = Integer.parseInt(row.get(0));
      int salary = Integer.parseInt(row.get(1));
      EmployeeType employeeType = EmployeeType.valueOf(row.get(2));
      String contactInformation = row.get(3);
      EmployeeModel model = new EmployeeModel(userId, salary, employeeType, contactInformation);
      employeeModels.add(model);
    }
    return employeeModels;
  }

  public static void writeEmployeesToExcel(List<EmployeeModel> employees) throws SpreadsheetIOException {
    List<RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Salary");
    headerValues.add("Employee Type");
    headerValues.add("Contact Information");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (EmployeeModel employee : employees) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(employee.getUserId()));
      values.add(Integer.toString(employee.getSalary()));
      values.add(employee.getEmployeeType().toString());
      values.add(employee.getContactInformation());
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
        writeExcel(rowDataList, filePath, "Employees");
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
