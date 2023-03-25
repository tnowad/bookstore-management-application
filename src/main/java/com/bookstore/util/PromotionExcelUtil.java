package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bookstore.bus.PromotionBUS;
import com.bookstore.model.PromotionModel;

public class PromotionExcelUtil extends ExcelUtil {
  private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
  private static final Logger LOGGER = Logger.getLogger(PromotionExcelUtil.class.getName());

  public static void selectAndProcessPromotionsExcelFile() throws ParseException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Promotions", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(excelFilter);
    int option = fileChooser.showOpenDialog(null);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        List<List<String>> promotionData = readExcel(file.getAbsolutePath(), 1);
        if (promotionData.isEmpty()) {
          throw new IllegalArgumentException("No data found in file.");
        }
        List<PromotionModel> promotionModels = convertToPromotionModelList(promotionData);
        PromotionBUS promotionBUS = new PromotionBUS();
        for (PromotionModel model : promotionModels) {
          PromotionModel existingPromotion = promotionBUS.getModelById(model.getId());
          if (existingPromotion != null) {
            handleDuplicatePromotion(existingPromotion, model, promotionBUS);
          } else {
            promotionBUS.addModel(model);
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

  private static void handleDuplicatePromotion(PromotionModel existingPromotion, PromotionModel newPromotion,
      PromotionBUS promotionBUS) throws ClassNotFoundException, SQLException {
    Object[] options = { "Update", "Delete" };
    int choice = JOptionPane.showOptionDialog(
        null,
        "A duplicate promotion with ID: " + existingPromotion.getId()
            + " was found. Would you like to update or delete this promotion?",
        "Duplicate Promotion Found",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
    if (choice == JOptionPane.NO_OPTION) {
      promotionBUS.deleteModel(existingPromotion.getId());
    } else {
      String oldData = "Old Data:\nID: " + existingPromotion.getId() + "\nDescription: "
          + existingPromotion.getDescription() + "\nQuantity: " + existingPromotion.getQuantity() + "\nStart Date: "
          + existingPromotion.getStartDate() + "\nEnd Date: " + existingPromotion.getEndDate() + "\nCondition Apply: "
          + existingPromotion.getConditionApply() + "\nDiscount Percent: " + existingPromotion.getDiscountPercent()
          + "\nDiscount Amount: " + existingPromotion.getDiscountAmount();
      String newData = "New Data:\nID: " + newPromotion.getId() + "\nDescription: " + newPromotion.getDescription()
          + "\nQuantity: " + newPromotion.getQuantity() + "\nStart Date: " + newPromotion.getStartDate()
          + "\nEnd Date: " + newPromotion.getEndDate() + "\nCondition Apply: " + newPromotion.getConditionApply()
          + "\nDiscount Percent: " + newPromotion.getDiscountPercent() + "\nDiscount Amount: "
          + newPromotion.getDiscountAmount();
      Object[] message = { oldData, newData };
      int updateChoice = JOptionPane.showOptionDialog(null, message, "Update Promotion", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      while (updateChoice == JOptionPane.CLOSED_OPTION) {
        updateChoice = JOptionPane.showOptionDialog(
            null,
            "Please choose to update or delete the duplicate promotion.",
            "Duplicate Promotion Found",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
      }
      if (updateChoice == JOptionPane.YES_OPTION) {
        promotionBUS.updateModel(newPromotion);
      } else {
        promotionBUS.deleteModel(existingPromotion.getId());
        promotionBUS.addModel(newPromotion);
      }
    }
  }

  private static void showErrorDialog(String message, String title) {
    LOGGER.log(Level.WARNING, "Error occurred: " + message);
    JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
  }

  private static List<PromotionModel> convertToPromotionModelList(List<List<String>> data) throws ParseException {
    List<PromotionModel> promotionModels = new ArrayList<>();
    for (List<String> row : data) {
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

      if (outputFile.exists()) {
        int overwriteOption = JOptionPane.showConfirmDialog(null,
            "The file already exists. Do you want to overwrite it?", "File Exists", JOptionPane.YES_NO_OPTION);
        if (overwriteOption == JOptionPane.NO_OPTION) {
          return;
        }
      }

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