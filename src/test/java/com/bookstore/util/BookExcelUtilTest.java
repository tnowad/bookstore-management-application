package com.bookstore.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFileChooser;

import org.junit.jupiter.api.Test;

import com.bookstore.bus.BookBUS;
import com.bookstore.models.BookModel;
import com.bookstore.util.Excel.BookExcelUtil;

public class BookExcelUtilTest {
  @Test
  void testreadUsersExcelFile() throws IOException, ClassNotFoundException, SQLException {
    BookExcelUtil.readBooksFromExcel();
  }

  @Test
  public void testWriteUsersToExcel() throws IOException, ClassNotFoundException, SQLException {

    // Retrieve user data from the database
    List<BookModel> users = BookBUS.getInstance().getAllModels();

    // Mock the JFileChooser class to return a file to write data to
    JFileChooser fileChooser = mock(JFileChooser.class);
    File outputFile = new File("test.xlsx");
    when(fileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
    when(fileChooser.getSelectedFile()).thenReturn(outputFile);

    // Call the function and verify that it writes data to the file
    BookExcelUtil.writeBooksToExcel(users);
    // try {
    // assertTrue(outputFile.createNewFile());
    // } catch (IOException e) {
    // fail("Failed to create output file");
    // }

    // Read the data from the file and compare it to the expected output
    List<BookModel> actualUsers = BookBUS.getInstance().getAllModels();
    assertEquals(users.size(), actualUsers.size());
    for (int i = 0; i < users.size(); i++) {
      assertEquals(users.get(i), actualUsers.get(i));
    }

    // Clean up by deleting the test file
    outputFile.delete();
  }

}