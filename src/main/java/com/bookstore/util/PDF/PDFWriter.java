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
  // TODO: Change the font location
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
    for (String datum : data) {
      Paragraph pdfData = new Paragraph(datum, fontData);
      pdfData.setSpacingAfter(10f); // Add some spacing after each line
      try {
        document.add(pdfData);
      } catch (DocumentException ex) {
        Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
        throw new RuntimeException("Failed to write object to PDF", ex);
      }
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
  public void exportImportsToPDF(int importId, String url) {
    ImportModel importData = ImportBUS.getInstance().getModelById(importId);
    List<ImportItemsModel> importItemsList = ImportItemsBUS.getInstance().getAllModels();
    List<ImportItemsModel> modifiableItemsList = new ArrayList<>(importItemsList);
    modifiableItemsList.removeIf(items -> items.getImportId() != importData.getId());

    List<BookModel> booksList = BookBUS.getInstance().getAllModels();
    List<BookModel> modifiableBooksList = new ArrayList<>(booksList);
    for (int i = modifiableItemsList.size() - 1; i >= 0; i--) {
      boolean found = false;
      for (BookModel book : modifiableBooksList) {
        if (modifiableItemsList.get(i).getBookIsbn().equals(book.getIsbn())) {
          found = true;
          break;
        }
      }
      if (!found) {
        modifiableItemsList.remove(i);
      }
    }

    ProviderModel provider = ProviderBUS.getInstance().getModelById(importData.getProviderId());
    UserModel employee = UserBUS.getInstance().getModelById(importData.getEmployeeId());

    // Calculate Total Price
    double totalPrice = 0;
    for (ImportItemsModel items : modifiableItemsList) {
      BookModel book = BookBUS.getInstance().getBookByIsbn(items.getBookIsbn());
      double itemTotalPrice = (items.getQuantity() * book.getPrice());
      totalPrice += itemTotalPrice;
    }

    // Format Total Price as Currency
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    String formattedTotalPrice = currencyFormatter.format(totalPrice);

    chooseURL(url);
    try {

      setTitle("Import Receipt");

      // Add header information
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String headerInfoString = "ID: " + importData.getId() + "\n"
          + "Provider: " + provider.getName() + "\n" + "Employee: " + employee.getName() + "\n"
          + "Total Price: " + /* importData.getTotalPrice() */ formattedTotalPrice + "\n"
          + "Created At: " + dateFormat.format(importData.getCreatedAt());
      writeObject(headerInfoString.split("\n"));

      // Add Book Information
      String[] columnNames = { "ISBN", "Title", "Price", "Quantity", "Total Price" };
      Object[][] data = new Object[modifiableItemsList.size()][5];
      for (int i = 0; i < modifiableItemsList.size(); i++) {
        ImportItemsModel item = modifiableItemsList.get(i);
        BookModel book = BookBUS.getInstance().getBookByIsbn(item.getBookIsbn());
        double itemTotalPrice = item.getQuantity() * book.getPrice();
        data[i][0] = book.getIsbn();
        data[i][1] = book.getTitle();
        data[i][2] = "$" + book.getPrice();
        data[i][3] = item.getQuantity();
        data[i][4] = currencyFormatter.format(itemTotalPrice);
      }
      JTable table = new JTable(data, columnNames);
      writeTable(table);

      // Close the document
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // TODO: NEED A PROPER CHECK FOR THIS FUNCTION (IT WORKS NORMALLY).
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
    try {
      setTitle("Purchase Receipt");

      // Add Order Information
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String orderInfoString = "Order Date: " + dateFormat.format(orderData.getCreatedAt()) + "\n"
          + "Order ID: " + orderData.getId() + "\n" + "Customer Name: " + customer.getName() + "\n"
          + "Employee Name: " + employee.getName() + "\n" + "Payment Method: "
          + (paymentMethod != null ? payment.getPaymentMethod() : "") + "\n" + "Total Amount: "
          + formattedTotalPrice;
      writeObject(orderInfoString.split("\n"));

      // Add Book Information
      String[] columnNames = { "ISBN", "Title", "Price", "Quantity", "Discount", "Total Price" };
      Object[][] data = new Object[modifiableCartItemsList.size()][6];
      for (int i = 0; i < modifiableCartItemsList.size(); i++) {
        CartItemsModel cartItem = modifiableCartItemsList.get(i);
        BookModel book = BookBUS.getInstance().getBookByIsbn(cartItem.getBookIsbn());
        double itemTotalPrice = cartItem.getQuantity() * book.getPrice();
        data[i][0] = book.getIsbn();
        data[i][1] = book.getTitle();
        data[i][2] = "$" + book.getPrice();
        data[i][3] = cartItem.getQuantity();
        data[i][4] = "$" + cartItem.getDiscount();
        data[i][5] = currencyFormatter.format(itemTotalPrice);
      }
      JTable table = new JTable(data, columnNames);
      writeTable(table);

      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
