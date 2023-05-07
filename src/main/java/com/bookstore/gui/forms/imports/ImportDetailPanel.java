package com.bookstore.gui.forms.imports;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ImportItemsBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.models.BookModel;
import com.bookstore.models.ImportItemsModel;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ProviderModel;
import com.bookstore.util.PDF.PDFWriter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.Provider;
import java.util.List;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class ImportDetailPanel extends JPanel {

  private JPanel contentHeaderPanel;
  private JLabel createdAtLabel;
  private JTextField createdAtTextField;
  private JLabel employeeLabel;
  private JTextField employeeTextField;
  private Button exportToPDFButton;
  private JPanel headerPanel;
  private JLabel idLabel;
  private JTextField idTextField;
  private JLabel importDataLabel;
  private Button importItemsLabel;
  private JPanel jPanel1;
  private JPanel tablePanel;
  private JPanel groupButtonPanel;
  // private JPanel jPanel5;
  // private JScrollPane jScrollPane1;
  private JScrollPane tableScrollPane;
  private JTable bookListTable;
  private JLabel providerLabel;
  private JTextField providerTextField;
  private Button resetButton;
  private JLabel totalPriceLabel;
  private JTextField totalPriceTextField;
  private Button updateButton;
  private ImportModel importModel;

  public ImportDetailPanel(ImportModel importModel) {
    this.importModel = importModel;
    initComponents();
    bookListTable();
  }

  private void bookListTable() {
    DefaultTableModel model = new DefaultTableModel(
      new String[] { "Book ISBN", "Title", "Quantity", "Price" },
      0
    );

    for (ImportItemsModel importItemsModel : ImportItemsBUS
      .getInstance()
      .getAllModels()) {
      if (importItemsModel.getImportId() == importModel.getId()) {
        BookModel bookModel = BookBUS
          .getInstance()
          .getBookByIsbn(importItemsModel.getBookIsbn());
        model.addRow(
          new Object[] {
            bookModel.getIsbn(),
            bookModel.getTitle(),
            importItemsModel.getQuantity(),
            importItemsModel.getPrice(),
          }
        );
        bookListTable.setModel(model);
      }
    }
    // add(new JScrollPane(bookListTable), BorderLayout.CENTER);
  }

  private void initComponents() {
    headerPanel = new JPanel();
    jPanel1 = new JPanel();
    importDataLabel = new JLabel();
    contentHeaderPanel = new JPanel();
    idLabel = new JLabel();
    idTextField = new JTextField();
    providerLabel = new JLabel();
    providerTextField = new JTextField();
    employeeLabel = new JLabel();
    employeeTextField = new JTextField();
    totalPriceLabel = new JLabel();
    totalPriceTextField = new JTextField();
    createdAtLabel = new JLabel();
    createdAtTextField = new JTextField();
    tablePanel = new JPanel();
    groupButtonPanel = new JPanel();
    importItemsLabel = new Button("Import");
    exportToPDFButton = new Button();
    updateButton = new Button();
    resetButton = new Button();
    tableScrollPane = new JScrollPane();
    // jPanel5 = new JPanel();
    // jScrollPane1 = new JScrollPane();
    bookListTable = new JTable();

    setPreferredSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    headerPanel.setLayout(new BorderLayout());

    jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));

    importDataLabel.setText("Import");
    importDataLabel.setPreferredSize(new Dimension(95, 30));
    jPanel1.add(importDataLabel);

    headerPanel.add(jPanel1, BorderLayout.NORTH);

    contentHeaderPanel.setLayout(new GridLayout(5, 2, 5, 5));

    idLabel.setHorizontalAlignment(SwingConstants.CENTER);
    idLabel.setText("ID");
    idLabel.setMinimumSize(new Dimension(17, 30));
    idLabel.setPreferredSize(new Dimension(20, 30));
    contentHeaderPanel.add(idLabel);

    idTextField.setPreferredSize(new Dimension(85, 30));

    contentHeaderPanel.add(idTextField);

    providerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    providerLabel.setText("Provider");
    contentHeaderPanel.add(providerLabel);

    providerTextField.setPreferredSize(new Dimension(84, 30));

    contentHeaderPanel.add(providerTextField);

    employeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    employeeLabel.setText("Employee");
    contentHeaderPanel.add(employeeLabel);

    contentHeaderPanel.add(employeeTextField);

    totalPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
    totalPriceLabel.setText("Total Price");
    contentHeaderPanel.add(totalPriceLabel);

    contentHeaderPanel.add(totalPriceTextField);

    createdAtLabel.setHorizontalAlignment(SwingConstants.CENTER);
    createdAtLabel.setText("Created At");
    contentHeaderPanel.add(createdAtLabel);

    contentHeaderPanel.add(createdAtTextField);

    headerPanel.add(contentHeaderPanel, BorderLayout.CENTER);

    add(headerPanel, BorderLayout.PAGE_START);

    tablePanel.setLayout(new BorderLayout());

    groupButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    groupButtonPanel.add(importItemsLabel);

    exportToPDFButton.setText("Export (PDF)");
    exportToPDFButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          exportToPDFButtonActionPerformed(evt);
        }
      }
    );
    groupButtonPanel.add(exportToPDFButton);

    updateButton.setText("Update");
    updateButton.addActionListener(e -> {
      int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to update?"
      );
      if (choice == JOptionPane.YES_OPTION) {
        ImportBUS.getInstance().getModelById(importModel.getId());
        ProviderModel providerModel = ProviderBUS
          .getInstance()
          .getModelById(importModel.getProviderId());
        providerModel.setName(providerTextField.getText());
        ProviderBUS.getInstance().updateModel(providerModel);
        ImportBUS.getInstance().updateModel(importModel);
        JOptionPane.showMessageDialog(null, "Updated successfully!");
      } else if (choice == JOptionPane.NO_OPTION) {
        return;
      }
    });
    groupButtonPanel.add(updateButton);

    resetButton.setText("Reset");

    groupButtonPanel.add(resetButton);

    tablePanel.add(groupButtonPanel, BorderLayout.PAGE_START);

    bookListTable.getTableHeader().setReorderingAllowed(false);

    tableScrollPane.setViewportView(bookListTable);

    tablePanel.add(tableScrollPane, BorderLayout.CENTER);

    add(tablePanel, BorderLayout.CENTER);
  }

  private void exportToPDFButtonActionPerformed(ActionEvent evt) {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String url = selectedFile.toURI().toString();
      PDFWriter.getInstance().exportImportsToPDF(importModel.getId(), url);
    }
  }
}
