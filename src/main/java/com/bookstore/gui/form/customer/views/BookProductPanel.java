package com.bookstore.gui.form.customer;

public class BookProductPanel extends javax.swing.JPanel {

 
    public BookProductPanel() {
        initComponents();
    }

    private void initComponents() {

        bookTitleLabel = new javax.swing.JLabel();
        bookImagePanel = new javax.swing.JPanel();
        bookDetailButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        bookTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        bookTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookTitleLabel.setText("Name book");
        add(bookTitleLabel, java.awt.BorderLayout.PAGE_START);
        bookTitleLabel.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout bookImagePanelLayout = new javax.swing.GroupLayout(bookImagePanel);
        bookImagePanel.setLayout(bookImagePanelLayout);
        bookImagePanelLayout.setHorizontalGroup(
                bookImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 220, Short.MAX_VALUE));
        bookImagePanelLayout.setVerticalGroup(
                bookImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 207, Short.MAX_VALUE));

        add(bookImagePanel, java.awt.BorderLayout.CENTER);

        bookDetailButton.setText("Book Detail");
        add(bookDetailButton, java.awt.BorderLayout.PAGE_END);
    }

    private javax.swing.JButton bookDetailButton;
    private javax.swing.JPanel bookImagePanel;
    private javax.swing.JLabel bookTitleLabel;
}
