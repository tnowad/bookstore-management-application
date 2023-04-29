package com.bookstore.gui.form.admin.component.book;

import java.awt.event.*;
import java.io.File;
import java.util.Arrays;

import javax.swing.*;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.PublisherBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.BookModel.Status;

/**
 *
 * @author yanti
 */
public class AddProductFrame extends javax.swing.JFrame {


    public AddProductFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        titleFrame = new javax.swing.JLabel();
        isbnText = new javax.swing.JLabel();
        setIsbn = new javax.swing.JTextField();
        titleText = new javax.swing.JLabel();
        setTitle = new javax.swing.JTextField();
        priceText = new javax.swing.JLabel();
        setPrice = new javax.swing.JTextField();
        quantityText = new javax.swing.JLabel();
        setQuantity = new javax.swing.JTextField();
        publisherText = new javax.swing.JLabel();
        setPublisherId = new javax.swing.JTextField();
        setPublisherName = new javax.swing.JTextField();
        authorText = new javax.swing.JLabel();
        setAuthorId = new javax.swing.JTextField();
        setAuthorName = new javax.swing.JTextField();
        imageText = new javax.swing.JLabel();
        setImageLink = new javax.swing.JTextField();
        chooseLink = new javax.swing.JButton();
        statusText = new javax.swing.JLabel();
        setStatus = new javax.swing.JComboBox<>();
        scrollPane = new javax.swing.JScrollPane();
        setDescription = new javax.swing.JTextArea();
        buttonPanel = new javax.swing.JPanel();
        buttonBack = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setMaximumSize(new java.awt.Dimension(566, 400));
        setMinimumSize(new java.awt.Dimension(566, 400));
        setPreferredSize(new java.awt.Dimension(566, 450));
        getContentPane().setLayout(new java.awt.FlowLayout());

        titleFrame.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titleFrame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleFrame.setText("Add New Book ");
        titleFrame.setPreferredSize(new java.awt.Dimension(600, 25));
        getContentPane().add(titleFrame);

        isbnText.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        isbnText.setText("Isbn");
        isbnText.setPreferredSize(new java.awt.Dimension(200, 16));
        getContentPane().add(isbnText);

        setIsbn.setPreferredSize(new java.awt.Dimension(200, 22));
        getContentPane().add(setIsbn);

        titleText.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        titleText.setText("Book Title");
        titleText.setPreferredSize(new java.awt.Dimension(200, 16));
        getContentPane().add(titleText);

        setTitle.setPreferredSize(new java.awt.Dimension(200, 22));
        getContentPane().add(setTitle);

        priceText.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        priceText.setText("Price");
        priceText.setPreferredSize(new java.awt.Dimension(200, 16));
        getContentPane().add(priceText);

        setPrice.setPreferredSize(new java.awt.Dimension(200, 22));
        getContentPane().add(setPrice);

        quantityText.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        quantityText.setText("Quantity");
        quantityText.setPreferredSize(new java.awt.Dimension(200, 16));
        getContentPane().add(quantityText);

        setQuantity.setPreferredSize(new java.awt.Dimension(200, 22));
        getContentPane().add(setQuantity);

        publisherText.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        publisherText.setText("Publisher id");
        publisherText.setPreferredSize(new java.awt.Dimension(160, 16));
        getContentPane().add(publisherText);

        setPublisherId.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(setPublisherId);

        setPublisherName.setPreferredSize(new java.awt.Dimension(200, 22));
        getContentPane().add(setPublisherName);

        authorText.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        authorText.setText("Author id");
        authorText.setPreferredSize(new java.awt.Dimension(160, 16));
        getContentPane().add(authorText);

        setAuthorId.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(setAuthorId);

        setAuthorName.setPreferredSize(new java.awt.Dimension(200, 22));
        getContentPane().add(setAuthorName);

        imageText.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        imageText.setText("Image Link");
        imageText.setPreferredSize(new java.awt.Dimension(140, 16));
        getContentPane().add(imageText);

        setImageLink.setPreferredSize(new java.awt.Dimension(220, 22));
        getContentPane().add(setImageLink);

        chooseLink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/categories.png"))); // NOI18N
        chooseLink.setActionCommand("+");
        chooseLink.setPreferredSize(new java.awt.Dimension(50, 23));
        chooseLink.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionAddLinkImage();
            }
            
        });
        getContentPane().add(chooseLink);

        statusText.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        statusText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusText.setText("Status");
        statusText.setPreferredSize(new java.awt.Dimension(180, 16));
        getContentPane().add(statusText);

        setStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AVAILABLE", "INVAILABLE", "DELETED" }));
        getContentPane().add(setStatus);

        scrollPane.setPreferredSize(new java.awt.Dimension(550, 100));

        setDescription.setColumns(20);
        setDescription.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        setDescription.setRows(5);
        setDescription.setText("description ?");
        scrollPane.setViewportView(setDescription);

        getContentPane().add(scrollPane);

        buttonPanel.setPreferredSize(new java.awt.Dimension(300, 50));
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 20));

        buttonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/back.png"))); // NOI18N
        buttonBack.setPreferredSize(new java.awt.Dimension(72, 23));
        buttonBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
                frame.dispose();
            }
            
        });
        buttonPanel.add(buttonBack);

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/save.png"))); // NOI18N
        buttonSave.setPreferredSize(new java.awt.Dimension(72, 23));
        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionAdd();
            }
            
        });
        buttonPanel.add(buttonSave);

        getContentPane().add(buttonPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void checkPublisher(){
        if(PublisherBUS.getInstance().getModelById(Integer.parseInt(setPublisherId.getText().trim()))!=null){
            setPublisherName.setText(PublisherBUS.getInstance().getModelById(Integer.parseInt(setPublisherId.getText().trim())).getName());
        }
        else{
            setPublisherName.setText("");
        }
    }
    public void checkAuthor(){
        if(AuthorBUS.getInstance().getModelById(Integer.parseInt(setAuthorId.getText().trim()))!=null){
            setAuthorName.setText(AuthorBUS.getInstance().getModelById(Integer.parseInt(setAuthorId.getText().trim())).getName());
        }
        else{
            setAuthorName.setText("");
        }
    }
    public String actionAddLinkImage(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            setImageLink.setText(filePath);
            return filePath;
        }
        return null;
    }
    
    public void actionAdd(){
        Boolean checkForDuplicate = true;
        if(setIsbn.getText().trim().isEmpty())
        {
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "Isbn cannot be empty!");
            return;
        }
        if(setTitle.getText().trim().isEmpty())
        {
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "Title cannot be empty!");
            return;
        }
        if(setPrice.getText().trim().isEmpty() || !setPrice.getText().trim().matches("\\d+"))
        {
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "Prince is not valid!");
            return;
        }
        if(setQuantity.getText().trim().isEmpty()|| !setQuantity.getText().trim().matches("\\d+"))
        {
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "Quantity is not valid!");
            return;
        }
        if(setPublisherId.getText().trim().isEmpty() || !setPublisherId.getText().trim().matches("\\d+"))
        {
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "Publisher Id is not valid!");
            return;
        }
        if(setAuthorId.getText().trim().isEmpty() || !setAuthorId.getText().trim().matches("\\d+"))
        {
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "Author Id is not valid!");
            return;
        }
        if(setAuthorName.getText().trim().isEmpty() )
        {
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "This author's name can't be found, please enter the author's name to add it!");
            return;
        }
        if(setPublisherName.getText().trim().isEmpty() )
        {
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "This publisher's name could not be found, please enter the publisher's name to add it!");
            return;
        }


        if(BookBUS.getInstance().checkForDuplicate(Arrays.asList(setIsbn.getText()), new String[] {"isbn"})){
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "Isbn already exists!");
            return;
        }
        if(BookBUS.getInstance().checkForDuplicate(Arrays.asList(setTitle.getText()), new String[] {"title"})){
            checkForDuplicate = false;
            JOptionPane.showMessageDialog(null, "Title already exists!");
            return;
        }


        if(checkForDuplicate) {
            Status newStatus = Status.valueOf(setStatus.getSelectedItem().toString().toUpperCase());
            BookBUS.getInstance().addModel(new BookModel(setIsbn.getText(), setTitle.getText(), setDescription.getText(), setImageLink.getText(), Integer.parseInt(setPrice.getText()), Integer.parseInt(setQuantity.getText().trim()), newStatus, Integer.parseInt(setPublisherId.getText().trim()), Integer.parseInt(setAuthorId.getText().trim())));
            JOptionPane.showMessageDialog(null, "Completed!");
        }
    }
    

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorText;
    private javax.swing.JButton buttonBack;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton chooseLink;
    private javax.swing.JLabel imageText;
    private javax.swing.JLabel isbnText;
    private javax.swing.JLabel priceText;
    private javax.swing.JLabel publisherText;
    private javax.swing.JLabel quantityText;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextField setAuthorId;
    private javax.swing.JTextField setAuthorName;
    private javax.swing.JTextArea setDescription;
    private javax.swing.JTextField setImageLink;
    private javax.swing.JTextField setIsbn;
    private javax.swing.JTextField setPrice;
    private javax.swing.JTextField setPublisherId;
    private javax.swing.JTextField setPublisherName;
    private javax.swing.JTextField setQuantity;
    private javax.swing.JComboBox<String> setStatus;
    private javax.swing.JTextField setTitle;
    private javax.swing.JLabel statusText;
    private javax.swing.JLabel titleFrame;
    private javax.swing.JLabel titleText;
    // End of variables declaration//GEN-END:variables
}
