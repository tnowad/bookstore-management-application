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

import com.bookstore.bus.CategoryBUS;
import com.bookstore.model.CategoryModel;

public class CategoryExcelReader extends ExcelUtil {

  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(CategoryExcelReader.class.getName());

  public static void selectAndProcessCategoriesExcelFile() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Categories", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> categoryData = ExcelUtil.readExcel(file.getAbsolutePath(), 1);
        if (categoryData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<CategoryModel> categoryModels = convertToCategoryModelList(categoryData);
        CategoryBUS categoryBUS = new CategoryBUS();
        for (CategoryModel model : categoryModels) {
          CategoryModel existingCategory = categoryBUS.getCategoryModel(model.getId());
          if (existingCategory != null) {
            handleDuplicateCategory(existingCategory, model, categoryBUS);
          } else {
            categoryBUS.insertModel(model);
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

  private static void handleDuplicateCategory(CategoryModel existingCategory, CategoryModel newCategory,
      CategoryBUS categoryBUS)
      throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate category with ID: " + existingCategory.getId()
            + " was found. Would you like to update or delete this category?",
        "Duplicate Category Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
    if (choice == JOptionPane.NO_OPTION) {
      categoryBUS.deleteModel(existingCategory.getId());
    } else {
      String oldData = "Old Data:\nID: " + existingCategory.getId() + "\nName: " + existingCategory.getName();
      String newData = "New Data:\nID: " + newCategory.getId() + "\nName: " + newCategory.getName();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(
          null,
          message,
          "Update Category",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate category.",
            "Duplicate Category Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        categoryBUS.updateModel(newCategory);
      } else {
        categoryBUS.deleteModel(existingCategory.getId());
        categoryBUS.insertModel(newCategory);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<CategoryModel> convertToCategoryModelList(List<List<String>> data)
      throws IllegalArgumentException {
    List<CategoryModel> categoryModels = new ArrayList<>();
    for (List<String> row : data) {
      int id = Integer.parseInt(row.get(0));
      String name = row.get(1);
      CategoryModel model = new CategoryModel(id, name);
      categoryModels.add(model);
    }
    return categoryModels;
  }

}
