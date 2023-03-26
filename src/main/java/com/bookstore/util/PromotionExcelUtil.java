package com.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

  public static List<PromotionModel> readPromotionsFromExcel()
      throws IOException, ClassNotFoundException, SQLException {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
    fileChooser.setFileFilter(filter);
    int option = fileChooser.showOpenDialog(null);

    if (option == JFileChooser.APPROVE_OPTION) {
      File inputFile = fileChooser.getSelectedFile();
      String filePath = inputFile.getAbsolutePath();

      try {
        List<List<String>> data = ExcelUtil.readExcel(filePath, 0);
        List<PromotionModel> promotions = convertToPromotionModelList(data);

        JOptionPane.showMessageDialog(null,
            "Data has been read successfully from " + inputFile.getName() + ".");
        return promotions;
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while reading data from file: " + inputFile.getName(), e);
        showErrorDialog(e.getMessage(), "File Input Error");
        throw e;
      } catch (IllegalArgumentException e) {
        LOGGER.log(Level.SEVERE, "Error occurred while converting data to PromotionModel: " + e.getMessage());
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

  private static String formatDate(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    return formatter.format(date);
  }

  public static Date parseDate(String dateString) {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      return formatter.parse(dateString);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Invalid date format: " + dateString, e);
    }
  }

  private static List<PromotionModel> convertToPromotionModelList(List<List<String>> data)
      throws IllegalArgumentException, ClassNotFoundException, SQLException {
    List<PromotionModel> promotionModels = new ArrayList<>();
    for (List<String> row : data) {
      int id;
      int quantity;
      int discountPercent;
      int discountAmount;
      Date startDate;
      Date endDate;
      try {
        id = Integer.parseInt(row.get(0));
        quantity = Integer.parseInt(row.get(2));
        discountPercent = Integer.parseInt(row.get(6));
        discountAmount = Integer.parseInt(row.get(7));
        startDate = parseDate(row.get(3));
        endDate = parseDate(row.get(4));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid integer value in input data", e);
      }
      String description = row.get(1);
      String conditionApply = row.get(5);
      PromotionModel model = new PromotionModel(id, description, quantity, startDate, endDate, conditionApply,
          discountPercent, discountAmount);
      promotionModels.add(model);
      PromotionBUS.getInstance().addModel(model);
    }
    return promotionModels;
  }

  public static void writePromotionsToExcel(List<PromotionModel> promotions) throws IOException {
    List<List<String>> data = new ArrayList<>();

    // Create header row
    List<String> headerValues = Arrays.asList("id", "description", "quantity", "start_date", "end_date",
        "condition_apply", "discount_percent", "discount_amount");
    data.add(headerValues);

    // Write data rows
    for (PromotionModel promotion : promotions) {
      List<String> values = Arrays.asList(
          Integer.toString(promotion.getId()),
          promotion.getDescription(),
          Integer.toString(promotion.getQuantity()),
          formatDate(promotion.getStartDate()),
          formatDate(promotion.getEndDate()),
          promotion.getConditionApply(),
          Integer.toString(promotion.getDiscountPercent()),
          Integer.toString(promotion.getDiscountAmount()));
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
        writeExcel(data, filePath, "Promotions");
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
