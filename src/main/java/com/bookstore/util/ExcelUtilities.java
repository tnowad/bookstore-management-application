package com.bookstore.util;

import org.apache.poi.ss.usermodel.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class ExcelUtilities {

  /**
   * Reads an Excel file at a given path and returns its data
   *
   * @param filePath   - The path to the Excel file
   * @param sheetIndex - The index of the sheet to read data from (starting from
   *                   0)
   * @return A List of Lists containing the data from the Excel file
   * @throws IOException if the file is not found or cannot be opened
   */
  public static List<List<String>> readExcel(String filePath, int sheetIndex) throws IOException {
    List<List<String>> data = new ArrayList<>();
    File file = new File(filePath);
    if (!file.exists()) {
      throw new IOException("File not found: " + filePath);
    }
    FileInputStream fileInputStream = new FileInputStream(file);
    Workbook workbook = WorkbookFactory.create(fileInputStream);
    Sheet sheet = workbook.getSheetAt(sheetIndex);
    for (Row row : sheet) {
      Iterator<Cell> cellIterator = row.cellIterator();
      List<String> rowData = new ArrayList<>();
      while (cellIterator.hasNext()) {
        Cell cell = cellIterator.next();
        switch (cell.getCellType()) {
          case STRING -> rowData.add(cell.getStringCellValue());
          case NUMERIC -> rowData.add(String.valueOf(cell.getNumericCellValue()));
          case BOOLEAN -> rowData.add(String.valueOf(cell.getBooleanCellValue()));
          default -> rowData.add("");
        }
      }
      data.add(rowData);
    }
    workbook.close();
    fileInputStream.close();
    return data;
  }

  /**
   * Writes the given data to an Excel file.
   *
   * @param data      The data to write.
   * @param filePath  The path where the file should be saved.
   * @param sheetName The name of the sheet to create.
   * @throws SpreadsheetIOException If an error occurs while creating the file or
   *                                writing to it.
   */
  public static void writeExcel(List<RowData> data, String filePath, String sheetName) throws SpreadsheetIOException {
    Objects.requireNonNull(data, "Data cannot be null");
    Objects.requireNonNull(filePath, "File path cannot be null");
    Objects.requireNonNull(sheetName, "Sheet name cannot be null");

    Path parentDirectory = Paths.get(filePath).getParent();
    try {
      if (parentDirectory != null && !Files.exists(parentDirectory)) {
        Files.createDirectories(parentDirectory);
      }

      try (Workbook workbook = WorkbookFactory.create(true);
          BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
        Sheet sheet = workbook.createSheet(sheetName);
        for (RowData rowData : data) {
          Row row = sheet.createRow(sheet.getLastRowNum() + 1);

          int columnIndex = 0;
          for (String cellData : rowData.getValues()) {
            Cell cell = row.createCell(columnIndex++);
            Optional.ofNullable(cellData).ifPresent(cell::setCellValue);
          }
        }

        workbook.write(bos);
      }
    } catch (IOException e) {
      throw new SpreadsheetIOException("Error writing to spreadsheet", e);
    }
  }

  public static class RowData {
    /**
     * Getter method for retrieving the row values
     */
    private final List<String> values;

    public RowData(List<String> values) {
      this.values = values;
    }

    public List<String> getValues() {
      return values;
    }
  }

  public static class SpreadsheetIOException extends RuntimeException {
    public SpreadsheetIOException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}