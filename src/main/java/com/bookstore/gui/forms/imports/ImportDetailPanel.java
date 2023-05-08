package com.bookstore.gui.forms.imports;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ImportItemsBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.dialogs.Dialog;
import com.bookstore.gui.forms.providers.FindProvider;
import com.bookstore.models.BookModel;
import com.bookstore.models.ImportItemsModel;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ProviderModel;
import com.bookstore.models.UserModel;
import com.bookstore.util.PDF.PDFWriter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
  private double totalPrice = 0;

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
        double itemsPrice =
          importItemsModel.getQuantity() * bookModel.getPrice();
        totalPrice += itemsPrice;
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
    // TODO: Finish adding new Book
    JButton addButton = new JButton("Add Book");
    addButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // TODO: Popup bookdetail Panel/Frame or something. Fill in information and
          // store data into row
        }
      }
    );
    add(addButton, BorderLayout.SOUTH);
  }

  private void initComponents() {
    headerPanel = new JPanel();
    jPanel1 = new JPanel();
    importDataLabel = new JLabel();
    contentHeaderPanel = new JPanel();

    idLabel = new JLabel();
    idTextField = new JTextField();
    idTextField.setText("" + importModel.getId());
    idTextField.setEditable(false);

    providerLabel = new JLabel();
    providerTextField = new JTextField();
    ProviderModel provider = ProviderBUS
      .getInstance()
      .getModelById(importModel.getProviderId());
    providerTextField.setText("" + provider.getName());

    employeeLabel = new JLabel();
    UserModel user = UserBUS
      .getInstance()
      .getModelById(importModel.getEmployeeId());
    employeeTextField = new JTextField();
    employeeTextField.setText("" + user.getName());

    totalPriceLabel = new JLabel();
    totalPriceTextField = new JTextField();
    totalPriceTextField.setText("" + totalPrice);
    totalPriceTextField.setEditable(false);

    createdAtLabel = new JLabel();
    createdAtTextField = new JTextField();
    createdAtTextField.setText("" + importModel.getCreatedAt());
    createdAtTextField.setEditable(false);

    tablePanel = new JPanel();
    groupButtonPanel = new JPanel();

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

    exportToPDFButton.setText("Export (PDF)");
    exportToPDFButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          exportToPDFButtonActionPerformed(evt);
        }
      }
    );
    groupButtonPanel.add(exportToPDFButton);

    // TODO: the update function works but needs logical error check!!! also check
    // TODO: for new books if there are any.
    updateButton.setText("Update");
    updateButton.addActionListener(e -> {
      String providerName = providerTextField.getText().trim();
      String employeeName = employeeTextField.getText().trim();

      if (employeeName.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter an employee name.");
        return;
      }

      List<UserModel> userModel = UserBUS
        .getInstance()
        .searchModel(employeeName, new String[] { "name" });
      // Remove if a user is customer:
      userModel.removeIf(user1 -> user1.getRole().toString().equals("customer")
      );

      if (userModel.size() == 0) {
        JOptionPane.showMessageDialog(
          null,
          "Employee not found! Please try again."
        );
        return;
      } else if (userModel.size() > 1) {
        // Show a list of users with employee role with same name and ask them to
        // choose.
        Object[] options = new Object[userModel.size()];
        for (int i = 0; i < userModel.size(); i++) {
          UserModel user2 = userModel.get(i);
          String option = String.format(
            "%s - %s - %s - %s",
            user2.getName(),
            user2.getEmail(),
            user2.getPhone(),
            user2.getStatus()
          );
          options[i] = option;
        }
        int selectedOption = JOptionPane.showOptionDialog(
          null,
          "Multiple employees found with the same name. Please select one:",
          "Select Employee",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.PLAIN_MESSAGE,
          null,
          options,
          options[0]
        );
        if (selectedOption == -1) {
          return;
        }
        userModel = Collections.singletonList(userModel.get(selectedOption));
      }

      UserModel employee = userModel.get(0);

      List<ProviderModel> providers;
      try {
        providers =
          ProviderBUS
            .getInstance()
            .searchModel(providerName, new String[] { "name" });
      } catch (Exception ex) {
        providers = new ArrayList<>();
      }
      ProviderModel providerModel = null;

      if (providers.size() == 0) {
        int choose = JOptionPane.showConfirmDialog(
          null,
          "Provider is not found! Do you want to create new Provider?"
        );
        if (choose == JOptionPane.YES_OPTION) {
          // Create new Provider:
          ProviderModel newProvider = new ProviderModel();
          newProvider.setName(providerName);
          try {
            ProviderBUS.getInstance().addModel(newProvider);
            providerModel = newProvider;
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(
              null,
              "Failed to create new provider: " + ex.getMessage()
            );
            return;
          }
        } else {
          JOptionPane.showMessageDialog(null, "Update failed!");
          return;
        }
      } else if (providers.size() == 1) {
        providerModel = providers.get(0);
      } else {
        JOptionPane.showMessageDialog(
          null,
          "Multiple providers found with the same name. Please enter a more specific name."
        );
        return;
      }

      ImportModel importModel = new ImportModel();
      importModel.setEmployeeId(employee.getId());
      importModel.setProviderId(providerModel.getId());

      try {
        ImportBUS.getInstance().updateModel(importModel);
        JOptionPane.showMessageDialog(null, "Updated successfully!");
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(
          null,
          "Failed to update import receipt: " + ex.getMessage()
        );
        return;
      }
    });

    groupButtonPanel.add(updateButton);
    // TODO: Reset book row back to default as well.
    resetButton.setText("Reset");
    resetButton.addActionListener(e -> {
      int choice = JOptionPane.showConfirmDialog(null, "Do you want to reset?");
      if (choice == JOptionPane.YES_OPTION) {
        providerTextField.setText("" + provider.getName());
        employeeTextField.setText("" + user.getName());
      } else if (choice == JOptionPane.NO_OPTION) {} else {
        return;
      }
    });
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
