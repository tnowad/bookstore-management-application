package com.bookstore.gui.forms.orders;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.PDF.PDFWriter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class OrderDetail extends JPanel {

  private Button acceptButton;
  private JPanel container;
  private JPanel groupBottomPanel;
  private JTable productListTable;
  private Button rejectButton;
  private JScrollPane tableListScrollPane;
  private Label titleLabel;
  private Label totalPriceLabel;
  private JPanel groupHeaderPanel;
  private JLabel nameCustomerLabel;
  private JLabel emailCustomerLabel;
  private JLabel phoneCustomerLabel;
  private JButton exportPdfButton;

  private java.util.List<CartModel> cartList;
  private java.util.List<CartItemsModel> cartItemList;
  private List<BookModel> bookList;
  private UserModel userModel;
  private JPanel backToPreviousPanel;
  private JButton backToPreviousButton;
  private OrderModel orderModel;

  public OrderDetail(OrderModel orderModel) {
    this.orderModel = orderModel;
    updateData();
    initComponents();
    listOrder();
    handleEvent();
  }

  private void handleEvent() {
    acceptButton.addActionListener(acceptButtonActionListener);
    rejectButton.addActionListener(rejectButtonActionListener);
    exportPdfButton.addActionListener(e -> {
      if (orderModel.getStatus().equals(OrderStatus.SOLVED)) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
          java.io.File selectedFile = fileChooser.getSelectedFile();
          String filePath = selectedFile.getAbsolutePath();
          PDFWriter.getInstance().exportReceiptToPDF(orderModel, filePath);
        }
      } else if (orderModel.getStatus().equals(OrderStatus.PENDING)) {
        JOptionPane.showMessageDialog(
          null,
          "This order is pending so you can't print to PDF! You must accept in order to print to PDF!"
        );
      } else {
        JOptionPane.showMessageDialog(
          null,
          "This order is rejected so you can't print to PDF!"
        );
      }
    });

    backToPreviousButton.addActionListener(e -> {
      MainPanel.getInstance().backToPreviousForm();
    });
  }

  private ActionListener acceptButtonActionListener = e -> {
    if (orderModel.getStatus().equals(OrderStatus.PENDING)) {
      int answer = JOptionPane.showConfirmDialog(
        this,
        "Do you want to accept this order?",
        "Confirm",
        JOptionPane.YES_NO_OPTION
      );
      if (answer == JOptionPane.YES_OPTION) {
        orderModel.setStatus(OrderStatus.SOLVED);
        OrderBUS.getInstance().updateModel(orderModel);
        JOptionPane.showMessageDialog(
          this,
          "Order Accepted",
          "Success",
          JOptionPane.INFORMATION_MESSAGE
        );

        int printAnswer = JOptionPane.showConfirmDialog(
          null,
          "Do you want to print this order to PDF?",
          "Print Order",
          JOptionPane.YES_NO_OPTION
        );
        if (printAnswer == JOptionPane.YES_OPTION) {
          JFileChooser fileChooser = new JFileChooser();
          int result = fileChooser.showSaveDialog(null);
          if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            PDFWriter.getInstance().exportReceiptToPDF(orderModel, filePath);
          }
        }
      }
    } else if (orderModel.getStatus().equals(OrderStatus.REJECTED)) {
      JOptionPane.showMessageDialog(
        null,
        "This order is rejected so you can't accept"
      );
    } else {
      JOptionPane.showMessageDialog(
        null,
        "This order is accepted , you can export to pdf"
      );
    }
  };

  private ActionListener rejectButtonActionListener = e -> {
    if (orderModel.getStatus().equals(OrderStatus.PENDING)) {
      int answer = JOptionPane.showConfirmDialog(
        this,
        "Do you want to reject this order?",
        "Confirm",
        JOptionPane.YES_NO_OPTION
      );
      if (answer == JOptionPane.YES_OPTION) {
        orderModel.setStatus(OrderStatus.REJECTED);
        OrderBUS.getInstance().updateModel(orderModel);
        JOptionPane.showMessageDialog(
          this,
          "Order Rejected",
          "Success",
          JOptionPane.INFORMATION_MESSAGE
        );
      }
    } else if (orderModel.getStatus().equals(OrderStatus.SOLVED)) {
      JOptionPane.showMessageDialog(
        null,
        "This order is accepted, you can't reject this order."
      );
    } else {
      JOptionPane.showMessageDialog(
        null,
        "This order is rejected, you can't export to pdf"
      );
    }
  };

  private void updateData() {
    if (orderModel.getCustomerId() == 0) {
      userModel = new UserModel();
      userModel.setName("Guest");
      userModel.setEmail("Guest");
      userModel.setPhone("Guest");
    } else {
      userModel =
        UserBUS.getInstance().getModelById(orderModel.getCustomerId());
    }
    cartList = CartBUS.getInstance().getAllModels();
    cartItemList = CartItemsBUS.getInstance().getAllModels();
    bookList = BookBUS.getInstance().getAllModels();
  }

  private void listOrder() {
    DefaultTableModel model = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    model.addColumn("ISBN");
    model.addColumn("Name");
    model.addColumn("Price");
    model.addColumn("Quantity");
    model.addColumn("Status");
    for (CartModel cartModel : cartList) {
      if (cartModel.getId() == orderModel.getCartId()) {
        for (CartItemsModel cartItemModel : cartItemList) {
          if (cartItemModel.getCartId() == cartModel.getId()) {
            BookModel bookModel = bookList
              .stream()
              .filter(book -> book.getIsbn().equals(cartItemModel.getBookIsbn())
              )
              .findFirst()
              .orElse(null);

            model.addRow(
              new Object[] {
                cartItemModel.getBookIsbn(),
                bookModel.getTitle(),
                bookModel.getPrice(),
                cartItemModel.getQuantity(),
                bookModel.getStatus(),
              }
            );
            productListTable.setModel(model);
          }
        }
      }
    }
    productListTable.getTableHeader().setReorderingAllowed(false);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    for (int i = 0; i < productListTable.getColumnCount(); i++) {
      productListTable
        .getColumnModel()
        .getColumn(i)
        .setCellRenderer(centerRenderer);
    }
    tableListScrollPane.setViewportView(productListTable);

    container.add(tableListScrollPane, BorderLayout.CENTER);
    tableListScrollPane.setMaximumSize(new Dimension(500, 200));
  }

  private void initComponents() {
    container = new JPanel();
    titleLabel = new Label("Order Details");
    tableListScrollPane = new JScrollPane();
    tableListScrollPane.getVerticalScrollBar().setUnitIncrement(16);

    productListTable = new JTable();
    groupHeaderPanel = new JPanel();
    groupBottomPanel = new JPanel();
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(
      Locale.of("vi", "VN")
    );
    totalPriceLabel =
      new Label(
        "Total Price : " + currencyFormatter.format(orderModel.getTotal())
      );
    totalPriceLabel.setPreferredSize(new Dimension(200, 20));
    nameCustomerLabel = new Label("Name : " + userModel.getName());
    emailCustomerLabel = new Label("Email : " + userModel.getEmail());
    phoneCustomerLabel = new Label("Phone : " + userModel.getPhone());
    acceptButton = new Button();
    rejectButton = new Button();
    backToPreviousPanel = new JPanel();
    backToPreviousButton = new JButton("Back to Previous");
    exportPdfButton = new JButton("Export PDF");

    backToPreviousPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    backToPreviousPanel.add(backToPreviousButton);
    backToPreviousPanel.add(exportPdfButton);

    container.setLayout(new BorderLayout());
    groupHeaderPanel.setLayout(
      new BoxLayout(groupHeaderPanel, BoxLayout.Y_AXIS)
    );

    groupHeaderPanel.add(backToPreviousPanel);
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setText("Order detail");
    groupHeaderPanel.add(titleLabel);

    groupHeaderPanel.setPreferredSize(new Dimension(500, 100));
    groupHeaderPanel.add(nameCustomerLabel);
    groupHeaderPanel.add(emailCustomerLabel);
    groupHeaderPanel.add(phoneCustomerLabel);

    container.add(groupHeaderPanel, BorderLayout.PAGE_START);

    groupBottomPanel.add(totalPriceLabel);

    if (Authentication.getCurrentUser().getRole() != UserRole.CUSTOMER) {
      acceptButton.setText("Accept");
      groupBottomPanel.add(acceptButton);
      rejectButton.setText("Reject");
      groupBottomPanel.add(rejectButton);
    }

    container.add(groupBottomPanel, BorderLayout.PAGE_END);

    add(container);
  }
}
