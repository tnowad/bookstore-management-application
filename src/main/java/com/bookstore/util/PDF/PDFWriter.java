package com.bookstore.util.PDF;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ImportItemsBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.PaymentBUS;
import com.bookstore.bus.PaymentMethodBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.ImportItemsModel;
import com.bookstore.models.ImportModel;
import com.bookstore.models.OrderModel;
import com.bookstore.models.PaymentMethodModel;
import com.bookstore.models.PaymentModel;
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

  private static final String FONT_FILE_PATH = "C:\\Windows\\Fonts\\Arial.ttf";
  private static PDFWriter instance;

  public Document document;
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

  public static synchronized PDFWriter getInstance() {
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

  public void chooseURL(String filePath) {
    try {
      document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(filePath));
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

  // TODO: THE FUNCTION WORKS AS INTENTED TO BE BUT IT NEEDS A PROPER CHECK
  public void exportImportsToPDF(int id, String url) {
    // Get the import data from the database
    ImportModel importData = ImportBUS.getInstance().getModelById(id);

    // Get the provider data from the database
    ProviderModel providerData = ProviderBUS.getInstance().getModelById(importData.getProviderId());

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

      List<ImportItemsModel> importItemsData = new ArrayList<>(ImportItemsBUS.getInstance().getAllModels());
      importItemsData.removeIf(importItem -> importItem.getImportId() != id);

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

  // TODO: NEED A PROPER CHECK FOR THIS FUNCTION:
  public void exportReceiptToPDF(int orderId, String url) {
    OrderModel orderData = OrderBUS.getInstance().getModelById(orderId);
    CartModel cartData = CartBUS.getInstance().getModelById(orderData.getCartId());

    // Get List Books in cart items:
    List<CartItemsModel> cartItemsList = CartItemsBUS.getInstance().getAllModels();
    List<CartItemsModel> modifiableCartItemsList = new ArrayList<>(cartItemsList);
    modifiableCartItemsList.removeIf(cartItem -> cartItem.getCartId() != cartData.getId());

    // Get Book information:
    List<BookModel> books = BookBUS.getInstance().getAllModels();
    List<BookModel> modifiableBookList = new ArrayList<>(books);
    for (int i = modifiableCartItemsList.size() - 1; i >= 0; i--) {
      boolean found = false;
      for (BookModel book : modifiableBookList) {
        if (modifiableCartItemsList.get(i).getBookIsbn().equals(book.getIsbn())) {
          found = true;
          break;
        }
      }
      if (!found) {
        modifiableCartItemsList.remove(i);
      }
    }

    // Get Customer Data
    UserModel customer = UserBUS.getInstance().getModelById(orderData.getCustomerId());

    // Get Employee Information
    UserModel employee = UserBUS.getInstance().getModelById(orderData.getEmployeeId());

    // Get Payment & payment method
    List<PaymentModel> paymentData = PaymentBUS.getInstance().searchModel(String.valueOf(orderId),
        new String[] { "order_id" });
    PaymentMethodModel paymentMethod = null;
    PaymentModel payment = null;
    if (paymentData.size() == 1) {
      payment = paymentData.get(0);
      paymentMethod = PaymentMethodBUS.getInstance().getModelById(payment.getId());
    }

    // Calculate Total Price
    double totalPrice = 0;
    for (CartItemsModel cartItem : modifiableCartItemsList) {
      BookModel book = BookBUS.getInstance().getBookByIsbn(cartItem.getBookIsbn());
      double itemTotalPrice = (cartItem.getQuantity() * book.getPrice()) - cartItem.getDiscount();
      totalPrice += itemTotalPrice;
    }

    // Format Total Price as Currency
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    String formattedTotalPrice = currencyFormatter.format(totalPrice);

    // Add receipt information into PDF File.
    chooseURL(url);
    setTitle("Purchase Receipt");
    try {
      Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(url));
      document.open();

      // Add Title
      Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
      Paragraph title = new Paragraph("Purchase Receipt", titleFont);
      title.setAlignment(Element.ALIGN_CENTER);
      document.add(title);

      // Add Order Information
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      Paragraph orderInfo = new Paragraph("Order Date: " + dateFormat.format(orderData.getCreatedAt()) + "\n"
          + "Order ID: " + orderData.getId() + "\n" + "Customer Name: " + customer.getName() + "\n"
          + "Employee Name: " + employee.getName() + "\n" + "Payment Method: "
          + (paymentMethod != null ? payment.getPaymentMethod() : "") + "\n" + "Total Amount: "
          + formattedTotalPrice);
      orderInfo.setSpacingBefore(20f);
      orderInfo.setSpacingAfter(20f);
      document.add(orderInfo);

      // Add Book Information
      PdfPTable table = new PdfPTable(6);
      PdfPCell cell1 = new PdfPCell(new Paragraph("ISBN"));
      PdfPCell cell2 = new PdfPCell(new Paragraph("Title"));
      PdfPCell cell3 = new PdfPCell(new Paragraph("Price"));
      PdfPCell cell4 = new PdfPCell(new Paragraph("Quantity"));
      PdfPCell cell5 = new PdfPCell(new Paragraph("Discount"));
      PdfPCell cell6 = new PdfPCell(new Paragraph("Total Price"));
      table.addCell(cell1);
      table.addCell(cell2);
      table.addCell(cell3);
      table.addCell(cell4);
      table.addCell(cell5);
      table.addCell(cell6);
      for (CartItemsModel cartItem : modifiableCartItemsList) {
        BookModel book = BookBUS.getInstance().getBookByIsbn(cartItem.getBookIsbn());
        double itemTotalPrice = cartItem.getQuantity() * book.getPrice();
        table.addCell(book.getIsbn());
        table.addCell(book.getTitle());
        table.addCell("$" + book.getPrice());
        table.addCell("" + cartItem.getQuantity());
        table.addCell("$" + cartItem.getDiscount());
        table.addCell(currencyFormatter.format(itemTotalPrice));
      }
      document.add(table);

      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
