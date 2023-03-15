package com.bookstore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReaderUtil {

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

}
