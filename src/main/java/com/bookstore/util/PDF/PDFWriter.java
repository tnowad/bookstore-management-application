package com.bookstore.util.PDF;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.EmployeeBUS;
import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ImportItemsBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.PaymentBUS;
import com.bookstore.bus.PaymentMethodBUS;
import com.bookstore.bus.PromotionBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.ImportItemsModel;
import com.bookstore.models.ImportModel;
import com.bookstore.models.OrderModel;
import com.bookstore.models.PaymentMethodModel;
import com.bookstore.models.PaymentModel;
import com.bookstore.models.PromotionModel;
import com.bookstore.models.ProviderModel;
import com.bookstore.models.UserModel;
import com.bookstore.models.PaymentModel.PaymentStatus;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
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

  // TODO: THE FUNCTION IS FINISHED BUT IT HASN'T BEEN TESTED YET.
  public void exportReceiptToPDF(int orderId, String url) {
    try {
      OrderModel order = getOrderById(orderId);
      CartModel cart = getCartById(order.getCartId());
      UserModel customer = getUserById(order.getCustomerId());
      EmployeeModel employee = getEmployeeById(order.getEmployeeId());
      UserModel employeeUser = getUserById(employee.getUserId());
      PromotionModel promotion = getPromotionById(cart.getPromotionId());

      PaymentModel payment = getPaymentByOrderId(orderId, customer.getId());
      PaymentMethodModel paymentMethod = null;
      if (payment != null) {
        paymentMethod = getPaymentMethodById(payment.getPaymentMethodId());
      }

      Document document = createDocument();
      addMetaData(document);
      addTitle(document, "Purchase Receipt");
      addHeader(document, order, customer, employeeUser, promotion, payment);
      addTable(document, cart);
      addTotal(document, cart, promotion);

      writeDocumentToFile(document, url);
      showSuccessMessage(url);
    } catch (IOException | DocumentException ex) {
      throw new RuntimeException("Failed to export receipt to PDF", ex);
    }
  }

  private OrderModel getOrderById(int orderId) {
    return OrderBUS.getInstance().getModelById(orderId);
  }

  private CartModel getCartById(int cartId) {
    return CartBUS.getInstance().getModelById(cartId);
  }

  private UserModel getUserById(int userId) {
    return UserBUS.getInstance().getModelById(userId);
  }

  private EmployeeModel getEmployeeById(int employeeId) {
    return EmployeeBUS.getInstance().getModelById(employeeId);
  }

  private PromotionModel getPromotionById(int promotionId) {
    return PromotionBUS.getInstance().getModelById(promotionId);
  }

  private PaymentModel getPaymentByOrderId(int orderId, int customerId) {
    List<PaymentModel> payments = PaymentBUS.getInstance().getAllModels();
    payments.removeIf(payment -> payment.getUserId() != customerId
        || payment.getStatus() == PaymentStatus.FAILED
        || payment.getStatus() == PaymentStatus.SUCCESS);
    return payments.size() == 1 ? payments.get(0) : null;
  }

  private PaymentMethodModel getPaymentMethodById(int paymentMethodId) {
    return PaymentMethodBUS.getInstance().getModelById(paymentMethodId);
  }

  private Document createDocument() {
    return new Document();
  }

  private void addMetaData(Document document) {
    document.addTitle("Purchase Receipt");
    document.addAuthor("Your Name");
  }

  private void addTitle(Document document, String title) throws DocumentException {
    Paragraph paragraph = new Paragraph(title);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    document.add(paragraph);
  }

  private void addHeader(Document document, OrderModel order, UserModel customer,
      UserModel employee, PromotionModel promotion, PaymentModel payment)
      throws DocumentException {
    Paragraph header = new Paragraph();
    header.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD));
    header.add(String.format("Order ID: %d\n", order.getId()));
    header.add(String.format("Date: %s\n", order.getCreatedAt().toString()));
    header.add(String.format("Customer Name: %s\n", customer.getName()));
    header.add(String.format("Employee Name: %s\n", employee.getName()));
    header.add(String.format("Promotion: %s\n", promotion.getDescription()));
    if (payment != null) {
      header.add(String.format("Payment Method: %s\n", payment.getStatus()));
    }
    document.add(header);
    document.add(Chunk.NEWLINE);
  }

  private void addTable(Document document, CartModel cart) throws DocumentException {
    PdfPTable table = new PdfPTable(4);
    table.addCell(new PdfPCell(new Phrase("Product Name", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
    table.addCell(new PdfPCell(new Phrase("Quantity", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
    table.addCell(new PdfPCell(new Phrase("Price", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
    table.addCell(new PdfPCell(new Phrase("Total", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));

    double subTotalPrice = 0;
    List<CartItemsModel> cartItems = getCartItemsByCartId(cart.getId());
    for (CartItemsModel item : cartItems) {
      BookModel product = getBookByIsbn(item.getBookIsbn());
      table.addCell(new PdfPCell(new Phrase(product.getTitle())));
      table.addCell(new PdfPCell(new Phrase(String.valueOf(item.getQuantity()))));
      table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getPrice()))));
      table.addCell(new PdfPCell(new Phrase(String.valueOf(item.getQuantity() * product.getPrice()))));
      subTotalPrice += item.getQuantity() * product.getPrice();
    }

    document.add(table);
    document.add(Chunk.NEWLINE);
  }

  private void addTotal(Document document, CartModel cart, PromotionModel promotion)
      throws DocumentException {
    double subTotalPrice = calculateSubtotal(cart);
    double discountPercent = promotion.getDiscountPercent();
    double total = subTotalPrice - (discountPercent / 100.0) * subTotalPrice;

    Paragraph totalParagraph = new Paragraph();
    totalParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA));
    totalParagraph.add(String.format("Subtotal: %.2f\n", subTotalPrice));
    totalParagraph.add(String.format("Discount: %.2f%%\n", discountPercent));
    totalParagraph.add(String.format("Total: %.2f\n", total));
    document.add(totalParagraph);
  }

  private List<CartItemsModel> getCartItemsByCartId(int cartId) {
    List<CartItemsModel> cartItems = CartItemsBUS.getInstance().getAllModels();
    cartItems.removeIf(cartItem -> cartItem.getCartId() != cartId);
    return cartItems;
  }

  private BookModel getBookByIsbn(String isbn) {
    return BookBUS.getInstance().getBookByIsbn(isbn);
  }

  private double calculateSubtotal(CartModel cart) {
    List<CartItemsModel> cartItems = getCartItemsByCartId(cart.getId());
    return cartItems.stream()
        .mapToDouble(item -> item.getQuantity() * getBookByIsbn(item.getBookIsbn()).getPrice())
        .sum();
  }

  private void writeDocumentToFile(Document document, String url) throws IOException, DocumentException {
    PdfWriter.getInstance(document, new FileOutputStream(url));
    document.open();
  }

  private void showSuccessMessage(String url) {
    JOptionPane.showMessageDialog(null, "Exported receipt to PDF successfully at: " + url);
  }

}
