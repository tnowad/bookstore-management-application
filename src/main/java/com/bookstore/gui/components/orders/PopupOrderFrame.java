/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bookstore.gui.components.orders;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.models.CartItemsModel;
import com.bookstore.util.PDF.PDFWriter;
import com.itextpdf.text.DocumentException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.WindowConstants;

/**
 *
 * @author yanti
 */
public class PopupOrderFrame extends javax.swing.JFrame {

  /**
   * Creates new form PopupOrderFrame
   */
  public PopupOrderFrame(
    int serial,
    int idOrder,
    int idCart,
    int idCustomer,
    int idEmployee,
    int total,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    OrderStatus status
  ) {
    initComponents();
    setTitle("Order");
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    addTable(idCart);
    setStatus(status);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    getNameCustomer = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    getNameEmployee = new javax.swing.JTextField();
    getIdOrder = new javax.swing.JTextField();
    getIdEmployee = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    getTotal = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    getDateCreated = new javax.swing.JTextField();
    getDateUpdated = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    getQuantityProduct = new javax.swing.JTextField();
    jLabel7 = new javax.swing.JLabel();
    getStatus = new javax.swing.JComboBox<>();
    jScrollPane1 = new javax.swing.JScrollPane();
    table = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    getSerial1 = new javax.swing.JLabel();
    getIsbn1 = new javax.swing.JLabel();
    getTitle1 = new javax.swing.JLabel();
    getQuantity = new javax.swing.JLabel();
    getPrice2 = new javax.swing.JLabel();
    getTotal1 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    buttonBack = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();
    ButtonExportPDF = new javax.swing.JButton();
    ButtonExportExcel = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel1.setForeground(new java.awt.Color(255, 0, 0));
    jLabel1.setText("Employee Name");

    getNameCustomer.setEditable(false);
    getNameCustomer.setText("ok");

    jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel2.setForeground(new java.awt.Color(255, 0, 0));
    jLabel2.setText("Customer Name");

    getNameEmployee.setEditable(false);
    getNameEmployee.setText("ok");

    getIdOrder.setEditable(false);
    getIdOrder.setText("ok");

    getIdEmployee.setEditable(false);
    getIdEmployee.setText("ok");

    jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel3.setForeground(new java.awt.Color(255, 0, 0));
    jLabel3.setText("Total");

    getTotal.setEditable(false);
    getTotal.setText("ok");

    jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel4.setForeground(new java.awt.Color(255, 0, 255));
    jLabel4.setText("Date Created");

    jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel5.setForeground(new java.awt.Color(255, 0, 255));
    jLabel5.setText("Date Updated");

    getDateCreated.setEditable(false);
    getDateCreated.setText("ok");

    getDateUpdated.setEditable(false);
    getDateUpdated.setText("ok");

    jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel6.setForeground(new java.awt.Color(255, 0, 0));
    jLabel6.setText("Product Quantity");

    getQuantityProduct.setEditable(false);
    getQuantityProduct.setText("ok");

    jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel7.setForeground(new java.awt.Color(255, 0, 0));
    jLabel7.setText("Status");

    getStatus.setModel(
      new javax.swing.DefaultComboBoxModel<>(
        new String[] { "Solve", "Pending", " " }
      )
    );

    javax.swing.GroupLayout tableLayout = new javax.swing.GroupLayout(table);
    table.setLayout(tableLayout);
    tableLayout.setHorizontalGroup(
      tableLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 767, Short.MAX_VALUE)
    );
    tableLayout.setVerticalGroup(
      tableLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 238, Short.MAX_VALUE)
    );

    jScrollPane1.setViewportView(table);

    getSerial1.setFont(new java.awt.Font("Segoe UI", 3, 14));
    getSerial1.setText("Serial");

    getIsbn1.setFont(new java.awt.Font("Segoe UI", 3, 14));
    getIsbn1.setText("Title");

    getTitle1.setFont(new java.awt.Font("Segoe UI", 3, 14));
    getTitle1.setText("Book Isbn");

    getQuantity.setFont(new java.awt.Font("Segoe UI", 3, 14));
    getQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    getQuantity.setText("Quantity");

    getPrice2.setFont(new java.awt.Font("Segoe UI", 3, 14));
    getPrice2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    getPrice2.setText("Price");

    getTotal1.setFont(new java.awt.Font("Segoe UI", 3, 14));
    getTotal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    getTotal1.setText("Total");

    jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14));
    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel8.setText("Status");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
      jPanel1
    );
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          jPanel1Layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(getSerial1)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getIsbn1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              57,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getTitle1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              102,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getPrice2,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              56,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getQuantity,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              94,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getTotal1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              67,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              jLabel8,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addGap(22, 22, 22)
        )
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          javax.swing.GroupLayout.Alignment.TRAILING,
          jPanel1Layout
            .createSequentialGroup()
            .addGap(8, 8, 8)
            .addGroup(
              jPanel1Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  getSerial1,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getIsbn1,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getTitle1,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getQuantity,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getPrice2,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getTotal1,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(jLabel8)
            )
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
    );

    buttonBack.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/back.png")
      )
    );

    buttonSave.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/save.png")
      )
    );

    ButtonExportPDF.setText("Export PDF");
    ButtonExportPDF.setToolTipText("");

    ButtonExportExcel.setText("Export Excel");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
      getContentPane()
    );
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addGroup(
                          layout
                            .createSequentialGroup()
                            .addComponent(
                              jLabel2,
                              javax.swing.GroupLayout.PREFERRED_SIZE,
                              135,
                              javax.swing.GroupLayout.PREFERRED_SIZE
                            )
                            .addPreferredGap(
                              javax.swing.LayoutStyle.ComponentPlacement.RELATED
                            )
                            .addComponent(
                              getNameCustomer,
                              javax.swing.GroupLayout.PREFERRED_SIZE,
                              287,
                              javax.swing.GroupLayout.PREFERRED_SIZE
                            )
                            .addPreferredGap(
                              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                              javax.swing.GroupLayout.DEFAULT_SIZE,
                              Short.MAX_VALUE
                            )
                            .addComponent(
                              getIdOrder,
                              javax.swing.GroupLayout.PREFERRED_SIZE,
                              83,
                              javax.swing.GroupLayout.PREFERRED_SIZE
                            )
                        )
                        .addGroup(
                          layout
                            .createSequentialGroup()
                            .addGroup(
                              layout
                                .createParallelGroup(
                                  javax.swing.GroupLayout.Alignment.LEADING,
                                  false
                                )
                                .addGroup(
                                  layout
                                    .createSequentialGroup()
                                    .addComponent(
                                      jLabel1,
                                      javax.swing.GroupLayout.PREFERRED_SIZE,
                                      135,
                                      javax.swing.GroupLayout.PREFERRED_SIZE
                                    )
                                    .addPreferredGap(
                                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                                    )
                                    .addComponent(
                                      getNameEmployee,
                                      javax.swing.GroupLayout.PREFERRED_SIZE,
                                      249,
                                      javax.swing.GroupLayout.PREFERRED_SIZE
                                    )
                                    .addPreferredGap(
                                      javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                                    )
                                    .addComponent(
                                      getIdEmployee,
                                      javax.swing.GroupLayout.PREFERRED_SIZE,
                                      187,
                                      javax.swing.GroupLayout.PREFERRED_SIZE
                                    )
                                )
                                .addGroup(
                                  layout
                                    .createSequentialGroup()
                                    .addGroup(
                                      layout
                                        .createParallelGroup(
                                          javax.swing.GroupLayout.Alignment.LEADING,
                                          false
                                        )
                                        .addComponent(
                                          jLabel3,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          Short.MAX_VALUE
                                        )
                                        .addComponent(
                                          jLabel6,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          Short.MAX_VALUE
                                        )
                                    )
                                    .addPreferredGap(
                                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                                    )
                                    .addGroup(
                                      layout
                                        .createParallelGroup(
                                          javax.swing.GroupLayout.Alignment.LEADING
                                        )
                                        .addComponent(
                                          getTotal,
                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                          71,
                                          javax.swing.GroupLayout.PREFERRED_SIZE
                                        )
                                        .addComponent(
                                          getQuantityProduct,
                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                          71,
                                          javax.swing.GroupLayout.PREFERRED_SIZE
                                        )
                                    )
                                    .addGap(164, 164, 164)
                                    .addGroup(
                                      layout
                                        .createParallelGroup(
                                          javax.swing.GroupLayout.Alignment.LEADING,
                                          false
                                        )
                                        .addComponent(
                                          jLabel5,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          97,
                                          Short.MAX_VALUE
                                        )
                                        .addComponent(
                                          jLabel4,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          Short.MAX_VALUE
                                        )
                                    )
                                    .addPreferredGap(
                                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                                    )
                                    .addGroup(
                                      layout
                                        .createParallelGroup(
                                          javax.swing.GroupLayout.Alignment.LEADING,
                                          false
                                        )
                                        .addComponent(
                                          getDateUpdated,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          187,
                                          Short.MAX_VALUE
                                        )
                                        .addComponent(getDateCreated)
                                    )
                                )
                                .addGroup(
                                  layout
                                    .createSequentialGroup()
                                    .addComponent(
                                      jLabel7,
                                      javax.swing.GroupLayout.PREFERRED_SIZE,
                                      51,
                                      javax.swing.GroupLayout.PREFERRED_SIZE
                                    )
                                    .addPreferredGap(
                                      javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                      72,
                                      Short.MAX_VALUE
                                    )
                                    .addComponent(
                                      getStatus,
                                      javax.swing.GroupLayout.PREFERRED_SIZE,
                                      109,
                                      javax.swing.GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(417, 417, 417)
                                )
                            )
                            .addGap(0, 0, Short.MAX_VALUE)
                        )
                    )
                )
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1)
                )
            )
            .addContainerGap()
        )
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(
              jPanel1,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
        .addGroup(
          layout
            .createSequentialGroup()
            .addGap(249, 249, 249)
            .addComponent(
              buttonBack,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              83,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(66, 66, 66)
            .addComponent(
              buttonSave,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              83,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(ButtonExportPDF)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addComponent(ButtonExportExcel)
            .addGap(13, 13, 13)
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(
                  layout
                    .createParallelGroup(
                      javax.swing.GroupLayout.Alignment.BASELINE
                    )
                    .addComponent(
                      jLabel2,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      28,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                    .addComponent(
                      getNameCustomer,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      28,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
                .addComponent(
                  getIdOrder,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  31,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  jLabel1,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  28,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getNameEmployee,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  28,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getIdEmployee,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  28,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(
                          jLabel4,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          26,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          getDateCreated,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          26,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(
                          jLabel5,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          24,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          getDateUpdated,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          24,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                )
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING,
                          false
                        )
                        .addComponent(
                          jLabel6,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          24,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addGroup(
                          layout
                            .createSequentialGroup()
                            .addComponent(getQuantityProduct)
                            .addGap(1, 1, 1)
                        )
                    )
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(
                          jLabel3,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          25,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          getTotal,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          javax.swing.GroupLayout.DEFAULT_SIZE,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                )
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7)
                .addComponent(
                  getStatus,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  30,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addGap(18, 18, 18)
            .addComponent(
              jPanel1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              36,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              jScrollPane1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              240,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buttonBack)
                .addComponent(buttonSave)
                .addGroup(
                  layout
                    .createParallelGroup(
                      javax.swing.GroupLayout.Alignment.BASELINE
                    )
                    .addComponent(ButtonExportPDF)
                    .addComponent(ButtonExportExcel)
                )
            )
            .addContainerGap(13, Short.MAX_VALUE)
        )
    );

    pack();
  } // </editor-fold>//GEN-END:initComponents

  /**
   * @param args the command line arguments
   */
  public void addTable(int idCart) {
    table.setLayout(new GridLayout(0, 1, 0, 5));
    List<CartItemsModel> listCartItems = CartItemsBUS
      .getInstance()
      .searchModel(String.valueOf(idCart), new String[] { "cart_id" });
    int Serial = 0;
    for (CartItemsModel cartItems : listCartItems) {
      String nameTitle = BookBUS
        .getInstance()
        .getBookByIsbn(cartItems.getBookIsbn())
        .getTitle();
      String status = String.valueOf(
        BookBUS.getInstance().getBookByIsbn(cartItems.getBookIsbn()).getStatus()
      );
      BookOrderPanel bookOrderPanel = new BookOrderPanel(
        Serial,
        nameTitle,
        cartItems.getBookIsbn(),
        cartItems.getPrice(),
        cartItems.getQuantity(),
        1000,
        status
      );
      table.add(bookOrderPanel);
      Serial = Serial + 1;
    }
    getQuantityProduct.setText("" + listCartItems.size());
  }

  public void setStatus(OrderStatus status) {
    int index = -1;
    switch (status.toString()) {
      case "SOLVED" -> {
        index = 0;
      }
      case "PENDING" -> {
        index = 1;
      }
    }
    getStatus.setSelectedIndex(index);
  }

  public void importPDF(int idOrder) {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();
      PDFWriter.getInstance().exportReceiptToPDF(idOrder, filePath);
    }
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton ButtonExportExcel;
  private javax.swing.JButton ButtonExportPDF;
  private javax.swing.JButton buttonBack;
  private javax.swing.JButton buttonSave;
  private javax.swing.JTextField getDateCreated;
  private javax.swing.JTextField getDateUpdated;
  private javax.swing.JTextField getIdEmployee;
  private javax.swing.JTextField getIdOrder;
  private javax.swing.JLabel getIsbn1;
  private javax.swing.JTextField getNameCustomer;
  private javax.swing.JTextField getNameEmployee;
  private javax.swing.JLabel getPrice2;
  private javax.swing.JLabel getQuantity;
  private javax.swing.JTextField getQuantityProduct;
  private javax.swing.JLabel getSerial1;
  private javax.swing.JComboBox<String> getStatus;
  private javax.swing.JLabel getTitle1;
  private javax.swing.JTextField getTotal;
  private javax.swing.JLabel getTotal1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JPanel table;
  // End of variables declaration//GEN-END:variables
}
