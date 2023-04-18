
package com.bookstore.gui.form.salesman.view;

import javax.swing.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.bookstore.models.BookModel;
import com.bookstore.bus.BookBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;

public class BookListPanel extends javax.swing.JPanel {
        BookBUS bookBus = BookBUS.getInstance();
        List<BookModel> bookList = bookBus.getAllModels();

        public BookListPanel() {
                initComponents();
                listBooks();
        }

        private void listBooks() {
                DefaultTableModel model = new DefaultTableModel();
                // "ISBN", "Title", "Quantity", "Price", "Status"
                model.addColumn("ISBN");
                model.addColumn("Title");
                model.addColumn("Quantity");
                model.addColumn("Price");
                model.addColumn("Status");
                for (BookModel book : bookList) {
                        model.addRow(new Object[] {
                                        book.getIsbn(), book.getTitle(), book.getDescription(), book.getImage(),
                                        book.getPrice(), book.getQuantity(), book.getStatus(), book.getPublisherId(),
                                        book.getAuthorId()
                        });
                        bookTableList.setModel(model);
                }
        }

        private void initComponents() {

                CustomerListUtility = new JPanel();
                exportCustomerBtn = new Button("Export Customer");
                importCustomerListBtn = new Button("Import Customer");
                searchBtn = new Button("Search Customer");
                searchCustomerTxtFld = new JTextField();
                jLabel1 = new JLabel();
                addCustomerBtn = new Button("Add Cusomer");
                jSeparator1 = new JSeparator();
                jScrollPane2 = new JScrollPane();
                bookTableList = new JTable();
                jSeparator2 = new JSeparator();

                exportCustomerBtn.setFont(new ThemeFont().getSmallFont());
                exportCustomerBtn.setIcon(
                                new ImageIcon(getClass().getResource("../../../../../../resources/images/export.png")));

                importCustomerListBtn.setFont(new ThemeFont().getSmallFont());
                importCustomerListBtn.setIcon(new ImageIcon(getClass()
                                .getResource("../../../../../../resources/images/import.png")));

                importCustomerListBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                importCustomerListBtnActionPerformed(evt);
                        }
                });

                searchBtn.setFont(new ThemeFont().getSmallFont()); // NOI18N
                searchBtn.setIcon(new ImageIcon(getClass().getResource(
                                "../../../../../../resources/images/search.png"))); // NOI18N

                searchCustomerTxtFld.setFont(new ThemeFont().getSmallFont()); // NOI18N

                jLabel1.setFont(new ThemeFont().getMediumFont()); // NOI18N
                jLabel1.setText("Books");

                addCustomerBtn.setFont(new ThemeFont().getSmallFont()); // NOI18N
                addCustomerBtn
                                .setIcon(new ImageIcon(getClass().getResource(
                                                "../../../../../../resources/images/addBook.png"))); // NOI18N
                addCustomerBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                addCustomerBtnActionPerformed(evt);
                        }
                });

                GroupLayout CustomerListUtilityLayout = new GroupLayout(CustomerListUtility);
                CustomerListUtility.setLayout(CustomerListUtilityLayout);
                CustomerListUtilityLayout.setHorizontalGroup(
                                CustomerListUtilityLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(searchCustomerTxtFld,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                430,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(searchBtn)))
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(importCustomerListBtn)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(exportCustomerBtn))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                CustomerListUtilityLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                .addComponent(addCustomerBtn)))
                                                                .addContainerGap())
                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                CustomerListUtilityLayout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(jSeparator1,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                911,
                                                                                                GroupLayout.PREFERRED_SIZE)));
                CustomerListUtilityLayout.setVerticalGroup(
                                CustomerListUtilityLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(addCustomerBtn))
                                                                .addGap(23, 23, 23)
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(
                                                                                                                CustomerListUtilityLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(importCustomerListBtn)
                                                                                                                                .addComponent(exportCustomerBtn)
                                                                                                                                .addComponent(searchBtn))
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addComponent(searchCustomerTxtFld))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jSeparator1,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                10,
                                                                                GroupLayout.PREFERRED_SIZE)));

                bookTableList.getTableHeader().setReorderingAllowed(false);
                jScrollPane2.setViewportView(bookTableList);

                GroupLayout layout = new GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2)
                                                .addComponent(CustomerListUtility, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jSeparator2));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addComponent(CustomerListUtility,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                100,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jSeparator2,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                10,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                442,
                                                                                GroupLayout.PREFERRED_SIZE)));
        }

        private void importCustomerListBtnActionPerformed(ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void addCustomerBtnActionPerformed(ActionEvent evt) {
                // TODO add your handling code here:
        }

        // Variables declaration - do not modify
        private JPanel CustomerListUtility;
        private JTable bookTableList;
        private JLabel jLabel1;
        private Button addCustomerBtn;
        private Button exportCustomerBtn;
        private Button importCustomerListBtn;
        private Button searchBtn;
        private JScrollPane jScrollPane2;
        private JSeparator jSeparator1;
        private JSeparator jSeparator2;
        private JTextField searchCustomerTxtFld;

        // End of variables declaration
        public static void main(String[] args) {
                JFrame jFrame = new JFrame();
                jFrame.add(new BookListPanel());
                jFrame.setVisible(true);
        }
}
