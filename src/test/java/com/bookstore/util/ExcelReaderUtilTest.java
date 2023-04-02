package com.bookstore.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ExcelReaderUtilTest {

  @Test
  void testReadExcel() throws IOException {
    String filePath = "resources/ExcelTest.xlsx";
    int sheetIndex = 0;
    List<List<String>> data = ExcelUtil.readExcel(filePath, sheetIndex);

    assertNotNull(data);
    assertEquals(3, data.size());

    List<String> row1 = data.get(0);
    assertEquals("Name", row1.get(0));
    assertEquals("Age", row1.get(1));
    assertEquals("Email", row1.get(2));

    List<String> row2 = data.get(1);
    assertEquals("John Doe", row2.get(0));
    assertEquals("35.0", row2.get(1));
    assertEquals("johndoe@example.com", row2.get(2));

    List<String> row3 = data.get(2);
    assertEquals("Jane Smith", row3.get(0));
    assertEquals("28.0", row3.get(1));
    assertEquals("janesmith@example.com", row3.get(2));
  }

}