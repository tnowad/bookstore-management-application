package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.PromotionBUS;
import com.bookstore.dao.PromotionDAO;
import com.bookstore.model.PromotionModel;

public class PromotionExcelUtil extends ExcelUtil {

  public static void readPromotionsFromExcel() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFileName = new FileNameExtensionFilter("Promotions", "xls", "xlsx", "xslm");
    fileChooser.setFileFilter(excelFileName);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();

      try {
        List<List<String>> promotionData = ExcelUtil.readExcel(filePath, 1);
        List<PromotionModel> promotionModels = convertToPromotionModelList(promotionData);
        PromotionBUS promotionBus = new PromotionBUS();
        for (PromotionModel model : promotionModels) {
          try {
            PromotionModel promotionModel = promotionBus.getPromotionModel(model.getId());
            if (promotionModel != null) {
              Object[] options = { "Update", "Delete" };
              int choice = JOptionPane.showOptionDialog(
                  null,
                  "A duplicate promotion with ID: " + promotionModel.getId()
                      + " was found.\nWould you like to update or delete this promotion?",
                  "Duplicate Promotion Found",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
              if (choice == JOptionPane.NO_OPTION) {
                promotionBus.deleteModel(promotionModel.getId());
                continue;
              } else {
                String oldData = "Old Data:\nID: " + promotionModel.getId()
                    + "\nDescription: " + promotionModel.getDescription()
                    + "\nQuantity: " + promotionModel.getQuantity()
                    + "\nStart Date: " + promotionModel.getStartDate()
                    + "\nEnd Date: " + promotionModel.getEndDate()
                    + "\nCondition Apply: " + promotionModel.getConditionApply()
                    + "\nDiscount Percent: " + promotionModel.getDiscountPercent()
                    + "\nDiscount Amount: " + promotionModel.getDiscountAmount();
                String newData = "New Data:\nID: " + model.getId()
                    + "\nDescription: " + model.getDescription()
                    + "\nQuantity: " + model.getQuantity()
                    + "\nStart Date: " + model.getStartDate()
                    + "\nEnd Date: " + model.getEndDate()
                    + "\nCondition Apply: " + model.getConditionApply()
                    + "\nDiscount Percent: " + model.getDiscountPercent()
                    + "\nDiscount Amount: " + model.getDiscountAmount();
                Object[] message = { oldData, newData };
                int updateChoice = JOptionPane.showOptionDialog(
                    null,
                    message,
                    "Update Promotion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                if (updateChoice == JOptionPane.YES_OPTION) {
                  promotionBus.updateModel(model);
                  PromotionDAO.getInstance().update(model);
                } else {
                  promotionBus.deleteModel(model.getId());
                }
              }
            } else {
              promotionBus.insertModel(model);
              PromotionDAO.getInstance().insert(model);
            }
          } catch (NullPointerException ptr) {
            JOptionPane.showMessageDialog(null, "Error: " + ptr.getMessage(), "Promotion not found.",
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

  private static List<PromotionModel> convertToPromotionModelList(List<List<String>> data) {
    List<PromotionModel> promotionModels = new ArrayList<>();
    for (List<String> row : data) {
      try {
        int id = Integer.parseInt(row.get(0));
        String description = row.get(1);
        int quantity = Integer.parseInt(row.get(2));
        Date startDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(row.get(3));
        Date endDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(row.get(4));
        String conditionApply = row.get(5);
        int discountPercent = Integer.parseInt(row.get(6));
        int discountAmount = Integer.parseInt(row.get(7));
        PromotionModel model = new PromotionModel(id, description, quantity, startDate, endDate, conditionApply,
            discountPercent, discountAmount);
        promotionModels.add(model);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Number Format Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Illegal Argument Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Parse Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      }
    }
    return promotionModels;
  }

  public static void writePromotionsToExcel(List<PromotionModel> promotions) {
    List<ExcelUtil.RowData> rowDataList = new ArrayList<>();

    // Create header row
    List<String> headerValues = new ArrayList<>();
    headerValues.add("ID");
    headerValues.add("Description");
    headerValues.add("Quantity");
    headerValues.add("Start Date");
    headerValues.add("End Date");
    headerValues.add("Condition Apply");
    headerValues.add("Discount Percent");
    headerValues.add("Discount Amount");
    RowData header = new RowData(headerValues);
    rowDataList.add(header);

    // Write data rows
    for (PromotionModel promotion : promotions) {
      List<String> values = new ArrayList<>();
      values.add(Integer.toString(promotion.getId()));
      values.add(promotion.getDescription());
      values.add(Integer.toString(promotion.getQuantity()));
      values.add(promotion.getStartDate().toString());
      values.add(promotion.getEndDate().toString());
      values.add(promotion.getConditionApply());
      values.add(Integer.toString(promotion.getDiscountPercent()));
      values.add(Integer.toString(promotion.getDiscountAmount()));
      RowData rowData = new RowData(values);
      rowDataList.add(rowData);
    }

    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showSaveDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File outputFile = fileChooser.getSelectedFile();
      String filePath = outputFile.getAbsolutePath();

      try {
        writeExcel(rowDataList, filePath, "Promotions");
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