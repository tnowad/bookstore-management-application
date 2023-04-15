package com.bookstore.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.swing.JTable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.bookstore.util.PDF.PDFWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFUtilTest {

  @TempDir
  File tempDir;

  @Test
  public void testGetInstance() {
    PDFWriter pdfWriter = PDFWriter.getInstance();
    assertNotNull(pdfWriter);
  }

  @Test
  public void testChooseURL() {
    // Create a new instance of the PDFWriter class
    PDFWriter pdfWriter = PDFWriter.getInstance();

    // Call the chooseURL() method with a file path parameter
    String filePath = "C:\\Users\\Danh\\Desktop\\test.pdf";
    pdfWriter.chooseURL(filePath);

    // Check that the file was created at the specified path
    File outputFile = new File(filePath);
    assertTrue(outputFile.exists());
  }

  @Test
  public void testSetTitle() throws IOException {
    PDFWriter pdfWriter = PDFWriter.getInstance();
    String url = "C:\\Users\\Danh\\Desktop\\test.pdf"; // Replace with actual file path
    pdfWriter.chooseURL(url);
    pdfWriter.setTitle("Test Title");
    pdfWriter.close();
    PdfReader reader = new PdfReader(url);
    String text = PdfTextExtractor.getTextFromPage(reader, 1);
    assertEquals(text.trim(), "Test Title");
    reader.close();
  }

  @Test
  public void testWriteObject() throws IOException {
    PDFWriter pdfWriter = PDFWriter.getInstance();
    String url = "C:\\Users\\Danh\\Desktop\\test.pdf";
    pdfWriter.chooseURL(url);
    String[] data = { "Test", "Data", "12345", "678910" };
    pdfWriter.writeObject(data);
    pdfWriter.close();
    PdfReader reader = new PdfReader(url);
    String text = PdfTextExtractor.getTextFromPage(reader, 1);
    assertEquals(text.trim(), "Test Data");
    reader.close();
  }

  @Test
  public void testWriteTable() throws IOException {
    PDFWriter pdfWriter = PDFWriter.getInstance();
    String url = "C:\\Users\\Danh\\Desktop\\test.pdf";
    pdfWriter.chooseURL(url);
    JTable table = new JTable(new Object[][] { { "Test", "Data" }, { "More", "Data" } },
        new Object[] { "Column 1", "Column 2" });
    pdfWriter.writeTable(table);
    pdfWriter.close();
    PdfReader reader = new PdfReader(url);
    String text = PdfTextExtractor.getTextFromPage(reader, 1);
    assertEquals(text.trim(), "Column 1 Column 2 Test Data More Data");
    reader.close();
  }

  @Test
  public void testExportImportsToPDF_FailedToWriteImportData() {
    // Arrange
    int id = 1;
    PDFWriter pdfWriter = PDFWriter.getInstance();

    // Act & Assert
    assertThrows(RuntimeException.class, () -> pdfWriter.exportImportsToPDF(id));
  }

  @Test
  public void testExportImportsToPDF_FailedToWriteImportItems() {
    // Arrange
    int id = 2;
    PDFWriter pdfWriter = PDFWriter.getInstance();

    // Act & Assert
    assertThrows(RuntimeException.class, () -> pdfWriter.exportImportsToPDF(id));
  }

  @Test
  public void testExportImportsToPDF_WithImportData() {
    // Arrange
    int id = 3;
    PDFWriter pdfWriter = PDFWriter.getInstance();

    // Act
    pdfWriter.exportImportsToPDF(id);

    // Assert
    // Check that the PDF document was created with the correct title and import
    // data
    // ...
  }

  @Test
  public void testExportImportsToPDF_WithImportItems() {
    // Arrange
    int id = 4;
    PDFWriter pdfWriter = PDFWriter.getInstance();

    // Act
    pdfWriter.exportImportsToPDF(id);

    // Assert
    // Check that the PDF document was created with the correct title and import
    // items
    // ...
  }

}
