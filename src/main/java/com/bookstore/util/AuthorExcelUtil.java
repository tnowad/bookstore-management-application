package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.dao.AuthorDAO;
import com.bookstore.model.AuthorModel;

public class AuthorExcelUtil extends ExcelUtil {

  public static void readAuthorsFromExcel() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Authors", "xls", "xlsx", "xslm");
    fileChooser.setFileFilter(excelFileName);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();

      try {
        List<List<String>> authorData = ExcelUtil.readExcel(filePath, 1);
        List<AuthorModel> authorModels = convertToAuthorModelList(authorData);
        AuthorBUS authorBus = new AuthorBUS();
        for (AuthorModel model : authorModels) {
          try {
            AuthorModel authorModel = authorBus.getAuthorModel(model.getId());
            if (authorModel != null) {
              Object[] options = { "Update", "Delete" };
              int choice = JOptionPane.showOptionDialog(
                  null,
                  "A duplicate author with ID: " + authorModel.getId()
                      + " was found. Would you like to update or delete this author?",
                  "Duplicate Author Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
              if (choice == JOptionPane.NO_OPTION) {
                authorBus.deleteModel(authorModel.getId());
                authorBus.insertModel(model);
              } else {
                String oldData = "Old Data:\nID: " + authorModel.getId() + "\nName: " + authorModel.getName()
                    + "\nDescription: " + authorModel.getDescription();
                String newData = "New Data:\nID: " + model.getId() + "\nName: " + model.getName() + "\nDescription: "
                    + model.getDescription();
                Object[] message = { oldData, newData };
                int updateChoice = JOptionPane.showOptionDialog(
                    null,
                    message,
                    "Update Author",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                while (updateChoice == JOptionPane.CLOSED_OPTION) {
                  updateChoice = JOptionPane.showOptionDialog(
                      null,
                      "Please choose to update or delete the duplicate author.",
                      "Duplicate Author Found",
                      JOptionPane.YES_NO_OPTION,
                      JOptionPane.QUESTION_MESSAGE,
                      null,
                      options,
                      options[0]);
                }
                if (updateChoice == JOptionPane.YES_OPTION) {
                  authorBus.updateModel(model);
                } else {
                  authorBus.deleteModel(authorModel.getId());
                }
              }
            } else {
              authorBus.insertModel(model);
              AuthorDAO.getInstance().insert(model);
            }
          } catch (NullPointerException ptr) {
            JOptionPane.showMessageDialog(null, "Error: " + ptr.getMessage(), "Author not found.",
                JOptionPane.ERROR_MESSAGE);
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

  private static List<AuthorModel> convertToAuthorModelList(List<List<String>> data) {
    List<AuthorModel> AuthorModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int id = Integer.parseInt(row.get(0));
        String name = row.get(1);
        String description = row.get(2);
        AuthorModel model = new AuthorModel(id, name, description);
        AuthorModels.add(model);
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
    return AuthorModels;
  }

  public static void writeAuthorsToExcel(List<AuthorModel> Authors) {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Name");
    headerValues.add("Description");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (AuthorModel Author : Authors) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(Author.getId()));
      values.add(Author.getName());
      values.add(Author.getDescription());
      RowData rowData = new RowData(values);
      rowDataList.add(rowData);
    }

    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showSaveDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File outputFile = fileChooser.getSelectedFile();
      String filePath = outputFile.getAbsolutePath();

      try {
        writeExcel(rowDataList, filePath, "Authors");
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
