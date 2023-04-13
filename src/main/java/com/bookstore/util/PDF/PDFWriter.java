package com.bookstore.util.PDF;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.EmployeeBUS;
import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ImportItemsBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.ImportItemsModel;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ProviderModel;
import com.bookstore.models.UserModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFWriter {

  private static final String FONT_FILE_PATH = "../../../resources/fonts/SansSerif.ttf";
  private static PDFWriter instance;

  private Document document;
  private Font fontData;
  private Font fontTitle;
  private Font fontHeader;

  private PDFWriter() {
    try {
      initializeFonts();
    } catch (IOException | DocumentException e) {
      throw new RuntimeException("Failed to initialize fonts", e);
    }
  }

  public static PDFWriter getInstance() {
    if (instance == null) {
      instance = new PDFWriter();
    }
    return instance;
  }

  private void initializeFonts() throws IOException, DocumentException {
    BaseFont baseFont = BaseFont.createFont(FONT_FILE_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    fontData = new Font(baseFont, 11, Font.NORMAL);
    fontTitle = new Font(baseFont, 25, Font.NORMAL);
    fontHeader = new Font(baseFont, 13, Font.NORMAL);
  }

  public void chooseURL(String url) {
    try {
      document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(url));
      document.open();
    } catch (IOException | DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to open PDF file", ex);
    }
  }

  public void setTitle(String title) {
    Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
    pdfTitle.setAlignment(Element.ALIGN_CENTER);
    try {
      document.add(pdfTitle);
      document.add(new Paragraph(Chunk.NEWLINE));
    } catch (DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to write title to PDF", ex);
    }
  }

  public void writeObject(String[] data) {
    StringBuilder sb = new StringBuilder();
    for (String datum : data) {
      sb.append(datum).append("  ");
    }
    Paragraph pdfData = new Paragraph(sb.toString(), fontData);
    try {
      document.add(pdfData);
    } catch (DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to write object to PDF", ex);
    }
  }

  public void writeTable(JTable table) {
    PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
    pdfTable.setHeaderRows(1);
    for (int i = 0; i < table.getColumnCount(); i++) {
      String header = table.getColumnName(i);
      PdfPCell cell = new PdfPCell(new Phrase(header, fontHeader));
      pdfTable.addCell(cell);
    }
    for (int i = 0; i < table.getRowCount(); i++) {
      for (int j = 0; j < table.getColumnCount(); j++) {
        String data = String.valueOf(table.getValueAt(i, j));
        PdfPCell cell = new PdfPCell(new Phrase(data, fontData));
        pdfTable.addCell(cell);
      }
    }
    try {
      document.add(pdfTable);
    } catch (DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to write table to PDF", ex);
    }
  }

  public void close() {
    if (document != null) {
      document.close();
    }
  }

  // TODO: NEED A TEST FOR THIS FUNCTION BELOW!
  public void exportImportsToPDF(int id) {
    String url = ""; // Set the URL where the PDF file will be saved

    // Get the import data from the database
    ImportModel importData = ImportBUS.getInstance().getModelById(id);

    // Get the provider data from the database
    ProviderModel providerData = ProviderBUS.getInstance().getModelById(importData.getProviderId());

    // Get the employee data from the database
    EmployeeModel employeeData = EmployeeBUS.getInstance().getModelById(importData.getEmployeeId());

    // Get the user data from employee
    UserModel userData = UserBUS.getInstance().getModelById(importData.getEmployeeId());

    // Create a new PDF document
    chooseURL(url);

    // Add the title to the PDF document
    setTitle("Import Receipt");

    // Add the import data to the PDF document
    Paragraph importTitle = new Paragraph(new Phrase("Import Data", fontHeader));
    importTitle.setAlignment(Element.ALIGN_LEFT);
    try {
      document.add(importTitle);
      document.add(new Paragraph(Chunk.NEWLINE));

      PdfPTable importTable = new PdfPTable(2);
      importTable.setWidthPercentage(100);
      importTable.setSpacingBefore(10f);
      importTable.setSpacingAfter(10f);

      PdfPCell cell1 = new PdfPCell(new Phrase("ID", fontHeader));
      cell1.setBorderColor(BaseColor.BLACK);
      cell1.setPaddingLeft(10);
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

      PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(importData.getId()), fontData));
      cell2.setBorderColor(BaseColor.BLACK);
      cell2.setPaddingLeft(10);
      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

      importTable.addCell(cell1);
      importTable.addCell(cell2);

      cell1 = new PdfPCell(new Phrase("Provider", fontHeader));
      cell1.setBorderColor(BaseColor.BLACK);
      cell1.setPaddingLeft(10);
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

      cell2 = new PdfPCell(new Phrase(providerData.getName(), fontData));
      cell2.setBorderColor(BaseColor.BLACK);
      cell2.setPaddingLeft(10);
      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

      importTable.addCell(cell1);
      importTable.addCell(cell2);

      cell1 = new PdfPCell(new Phrase("Employee", fontHeader));
      cell1.setBorderColor(BaseColor.BLACK);
      cell1.setPaddingLeft(10);
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

      cell2 = new PdfPCell(new Phrase(userData.getName(), fontData));
      cell2.setBorderColor(BaseColor.BLACK);
      cell2.setPaddingLeft(10);
      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

      importTable.addCell(cell1);
      importTable.addCell(cell2);

      cell1 = new PdfPCell(new Phrase("Total Price", fontHeader));
      cell1.setBorderColor(BaseColor.BLACK);
      cell1.setPaddingLeft(10);
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

      cell2 = new PdfPCell(new Phrase(String.valueOf(importData.getTotalPrice()), fontData));
      cell2.setBorderColor(BaseColor.BLACK);
      cell2.setPaddingLeft(10);
      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

      importTable.addCell(cell1);
      importTable.addCell(cell2);

      cell1 = new PdfPCell(new Phrase("Created At", fontHeader));
      cell1.setBorderColor(BaseColor.BLACK);
      cell1.setPaddingLeft(10);
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

      cell2 = new PdfPCell(new Phrase(importData.getCreatedAt().toString(), fontData));
      cell2.setBorderColor(BaseColor.BLACK);
      cell2.setPaddingLeft(10);
      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

      importTable.addCell(cell1);
      importTable.addCell(cell2);

      document.add(importTable);
      document.add(new Paragraph(Chunk.NEWLINE));
    } catch (DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to write import data to PDF", ex);
    }

    // Add the import items to the PDF document
    Paragraph importItemsTitle = new Paragraph(new Phrase("Import Items", fontHeader));
    importItemsTitle.setAlignment(Element.ALIGN_LEFT);
    try {
      document.add(importItemsTitle);
      document.add(new Paragraph(Chunk.NEWLINE));

      PdfPTable importItemsTable = new PdfPTable(4);
      importItemsTable.setWidthPercentage(100);
      importItemsTable.setSpacingBefore(10f);
      importItemsTable.setSpacingAfter(10f);

      PdfPCell cell1 = new PdfPCell(new Phrase("Book ISBN", fontHeader));
      cell1.setBorderColor(BaseColor.BLACK);
      cell1.setPaddingLeft(10);
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

      PdfPCell cell2 = new PdfPCell(new Phrase("Book Title", fontHeader));
      cell2.setBorderColor(BaseColor.BLACK);
      cell2.setPaddingLeft(10);
      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

      PdfPCell cell3 = new PdfPCell(new Phrase("Quantity", fontHeader));
      cell3.setBorderColor(BaseColor.BLACK);
      cell3.setPaddingLeft(10);
      cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

      PdfPCell cell4 = new PdfPCell(new Phrase("Price", fontHeader));
      cell4.setBorderColor(BaseColor.BLACK);
      cell4.setPaddingLeft(10);
      cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

      importItemsTable.addCell(cell1);
      importItemsTable.addCell(cell2);
      importItemsTable.addCell(cell3);
      importItemsTable.addCell(cell4);

      List<ImportItemsModel> importItemsData = ImportItemsBUS.getInstance().getAllModels();

      for (ImportItemsModel importItem : importItemsData) {
        BookModel bookData = BookBUS.getInstance().getBookByIsbn(importItem.getBookIsbn());

        cell1 = new PdfPCell(new Phrase(bookData.getIsbn(), fontData));
        cell1.setBorderColor(BaseColor.BLACK);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell2 = new PdfPCell(new Phrase(bookData.getTitle(), fontData));
        cell2.setBorderColor(BaseColor.BLACK);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell3 = new PdfPCell(new Phrase(String.valueOf(importItem.getQuantity()), fontData));
        cell3.setBorderColor(BaseColor.BLACK);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell4 = new PdfPCell(new Phrase(String.valueOf(importItem.getPrice()), fontData));
        cell4.setBorderColor(BaseColor.BLACK);
        cell4.setPaddingLeft(10);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

        importItemsTable.addCell(cell1);
        importItemsTable.addCell(cell2);
        importItemsTable.addCell(cell3);
        importItemsTable.addCell(cell4);
      }

      document.add(importItemsTable);
      document.add(new Paragraph(Chunk.NEWLINE));
    } catch (DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to write import items to PDF", ex);
    }

    // Close the PDF document
    close();
  }

}
