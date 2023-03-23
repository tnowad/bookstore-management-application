package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.CategoryBUS;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.model.CategoryModel;

public class CategoryExcelReader extends ExcelUtil {

  public static void readCategoriesFromExcel() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Categories", "xls", "xlsx", "xslm");
    fileChooser.setFileFilter(excelFileName);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();

      try {
        List<List<String>> categoryData = ExcelUtil.readExcel(filePath, 1);
        List<CategoryModel> categoryModels = convertToCategoryModelList(categoryData);
        CategoryBUS categoryBus = new CategoryBUS();
        for (CategoryModel model : categoryModels) {
          try {
            CategoryModel categoryModel = categoryBus.getCategoryModel(model.getId());
            if (categoryModel != null) {
              Object[] options = { "Update", "Delete" };
              int choice = JOptionPane.showOptionDialog(
                  null,
                  "A duplicate category with ID: " + categoryModel.getId()
                      + " was found. Would you like to update or delete this category?",
                  "Duplicate Category Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
              if (choice == JOptionPane.NO_OPTION) {
                categoryBus.deleteModel(categoryModel.getId());
                categoryBus.insertModel(model);
              } else {
                String oldData = "Old Data:\nID: " + categoryModel.getId() + "\nName: " + categoryModel.getName();
                String newData = "New Data:\nID: " + model.getId() + "\nName: " + model.getName();
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
                  categoryBus.updateModel(model);
                } else {
                  categoryBus.deleteModel(categoryModel.getId());
                }
              }
            } else {
              categoryBus.insertModel(model);
              CategoryDAO.getInstance().insert(model);
            }
          } catch (NullPointerException ptr) {
            JOptionPane.showMessageDialog(null, "Error: " + ptr.getMessage(), "Category not found.",
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

  private static List<CategoryModel> convertToCategoryModelList(List<List<String>> data) {
    List<CategoryModel> CategoryModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int id = Integer.parseInt(row.get(0));
        String name = row.get(1);
        CategoryModel model = new CategoryModel(id, name);
        CategoryModels.add(model);
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
    return CategoryModels;
  }
}
