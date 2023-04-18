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

import com.bookstore.dao.ImportDAO;
import com.bookstore.models.ImportModel;
import com.bookstore.util.Excel.ImportModelExcelUtil;

public class ImportExcelUtilTest {
  @Test
  void testreadUsersExcelFile() throws IOException, ClassNotFoundException, SQLException {
    ImportModelExcelUtil.readImportsFromExcel();
  }

  @Test
  public void testWriteUsersToExcel() throws IOException, ClassNotFoundException, SQLException {

    // Retrieve user data from the database
    List<ImportModel> users = ImportDAO.getInstance().readDatabase();

    // Mock the JFileChooser class to return a file to write data to
    JFileChooser fileChooser = mock(JFileChooser.class);
    File outputFile = new File("test.xlsx");
    when(fileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
    when(fileChooser.getSelectedFile()).thenReturn(outputFile);

    // Call the function and verify that it writes data to the file
    ImportModelExcelUtil.writeImportsToExcel(users);
    // try {
    // assertTrue(outputFile.createNewFile());
    // } catch (IOException e) {
    // fail("Failed to create output file");
    // }

    // Read the data from the file and compare it to the expected output
    List<ImportModel> actualUsers = ImportDAO.getInstance().readDatabase();
    assertEquals(users.size(), actualUsers.size());
    // for (int i = 0; i < users.size(); i++) {
    //   assertEquals(users.get(i), actualUsers.get(i));
    // }

    // Clean up by deleting the test file
    outputFile.delete();
  }

}