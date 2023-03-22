package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.dao.EmployeeDAO;
import com.bookstore.model.EmployeeModel;
import com.bookstore.model.EmployeeModel.EmployeeType;;

public class EmployeeExcelUtil {

  public static void readEmployeeFromExcel() throws NumberFormatException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Employees", "xls", "xlsx", "xslm");
    fileChooser.setFileFilter(excelFileName);

    int option = fileChooser.showOpenDialog(null);

    if (option != JFileChooser.APPROVE_OPTION) {
      return;
    }

    File file = fileChooser.getSelectedFile();
    String filePath = file.getAbsolutePath();

    try {
      List<List<String>> employeeData = ExcelUtil.readExcel(filePath, 1);
      List<EmployeeModel> employeeModels = convertToEmployeeModelList(employeeData);

      for (EmployeeModel model : employeeModels) {
        EmployeeBUS employeeBUS = new EmployeeBUS();

        try {
          EmployeeModel existingEmployee = EmployeeDAO.getInstance().getEmployeeById(model.getUserId());

          if (existingEmployee != null) {
            Object[] options = { "Update", "Delete" };

            int choice = JOptionPane.showOptionDialog(
                null,
                "A duplicate employee with ID: " + existingEmployee.getUserId()
                    + " was found. Would you like to update this employee?",
                "Duplicate Employee Found",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

            while (choice == JOptionPane.CLOSED_OPTION) {
              choice = JOptionPane.showOptionDialog(
                  null,
                  "Please choose to update or delete the duplicate employee.",
                  "Duplicate Employee Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
            }

            if (choice == JOptionPane.NO_OPTION) {
              employeeBUS.deleteModel(existingEmployee.getUserId());
              employeeBUS.insertModel(model);
            } else if (choice == JOptionPane.YES_OPTION) {
              String oldData = "Old Data:\nID: " + existingEmployee.getUserId()
                  + "\nSalary: " + existingEmployee.getSalary() + "\nType: " + existingEmployee.getEmployeeType()
                  + "\nContact Information: " + existingEmployee.getContactInformation();

              String newData = "New Data:\nID: " + model.getUserId() + "\nSalary: "
                  + model.getSalary() + "\nType: " + model.getEmployeeType() + "\nContact Information: "
                  + model.getContactInformation();

              Object[] message = { oldData, newData };

              int updateChoice = JOptionPane.showOptionDialog(
                  null,
                  message,
                  "Update Employee",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);

              if (updateChoice == JOptionPane.YES_OPTION) {
                employeeBUS.updateModel(model);
              }
            }
          } else {
            employeeBUS.insertModel(model);
            EmployeeDAO.getInstance().insert(model);
          }

        } catch (NullPointerException ptr) {
          JOptionPane.showMessageDialog(null, "Error: " + ptr.getMessage(), "Employee not found.",
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

  private static List<EmployeeModel> convertToEmployeeModelList(List<List<String>> data) {
    List<EmployeeModel> employeeModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int userId = Integer.parseInt(row.get(0));
        int salary = Integer.parseInt(row.get(1));
        EmployeeType type = EmployeeType.valueOf(row.get(2).toUpperCase());
        String contactInformation = row.get(3);
        EmployeeModel model = new EmployeeModel(userId, salary, type, contactInformation);
        employeeModels.add(model);
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
    return employeeModels;
  }
}
