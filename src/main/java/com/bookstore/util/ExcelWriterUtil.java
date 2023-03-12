package com.bookstore.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelWriterUtil {

  public static void writeExcel(List<List<String>> data, String filePath, String sheetName) throws IOException {
    Objects.requireNonNull(data, "Data cannot be null");
    Objects.requireNonNull(filePath, "File path cannot be null");
    Objects.requireNonNull(sheetName, "Sheet name cannot be null");
    Files.createDirectories(Paths.get(filePath).getParent());
    SXSSFWorkbook workbook = new SXSSFWorkbook();
    Sheet sheet = workbook.createSheet(sheetName);
    for (List<String> rowData : data) {
      Row row = sheet.createRow(sheet.getLastRowNum() + 1);
      int columnIndex = 0;
      for (String cellData : rowData) {
        Cell cell = row.createCell(columnIndex++);
        Optional.ofNullable(cellData).ifPresent(cell::setCellValue);
      }
    }
    try (FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {
      workbook.write(outputStream);
    }
    workbook.close();
  }
}
