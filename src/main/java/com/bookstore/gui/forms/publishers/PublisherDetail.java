package com.bookstore.gui.forms.publishers;

import java.awt.*;
import javax.swing.*;

import com.bookstore.models.PublisherModel;

public class PublisherDetail extends JFrame {

  private JTextArea setDescription;
private JPanel buttonPanel;
private JButton buttonBack;
private JButton buttonSave;

  public PublisherDetail(PublisherModel publisher) {
    initComponents(publisher);
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents(PublisherModel publisher) {
    contend = new JPanel();
    idText = new JLabel();
    setId = new JTextField();
    nameText = new JLabel();
    setName = new JTextField();
    productsText = new JLabel();
    setProducts = new JTextField();
    descriptionText = new JLabel();
    scrollPane = new JScrollPane();
    setDescription = new JTextArea();
    buttonPanel = new JPanel();
    buttonBack = new JButton();
    buttonSave = new JButton();

    contend.setMinimumSize(new Dimension(400, 100));
    contend.setPreferredSize(new Dimension(390, 115));
    contend.setLayout(new FlowLayout(FlowLayout.LEFT));

    idText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    idText.setHorizontalAlignment(SwingConstants.RIGHT);
    idText.setText("ID:");
    idText.setPreferredSize(new Dimension(130, 16));
    contend.add(idText);

    setId.setPreferredSize(new Dimension(200, 25));
    setId.setText(""+publisher.getId());
    contend.add(setId);

    nameText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    nameText.setHorizontalAlignment(SwingConstants.RIGHT);
    nameText.setText("Name:");
    nameText.setPreferredSize(new Dimension(130, 16));
    contend.add(nameText);

    setName.setPreferredSize(new Dimension(200, 25));
    setName.setText(publisher.getName());
    contend.add(setName);

    productsText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    productsText.setHorizontalAlignment(SwingConstants.RIGHT);
    productsText.setText("Product Of This");
    productsText.setPreferredSize(new Dimension(130, 16));
    contend.add(productsText);

    setProducts.setPreferredSize(new Dimension(130, 25));
    contend.add(setProducts);

    descriptionText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    descriptionText.setHorizontalAlignment(SwingConstants.LEFT);
    descriptionText.setText("Description:");
    descriptionText.setPreferredSize(new Dimension(130, 16));
    contend.add(descriptionText);

    setDescription.setFont(new Font("Segoe UI", 3, 15));
    setDescription.setLineWrap(true);
    setDescription.setRows(5);
    setDescription.setText(publisher.getDescription());
    scrollPane.setViewportView(setDescription);
    scrollPane.getVerticalScrollBar().setValue(0);

    buttonPanel.setPreferredSize(new Dimension(50, 50));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
    buttonBack.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/back.png"))
    );
    buttonBack.setPreferredSize(new Dimension(80, 30));
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/save.png"))
    );
    buttonSave.setPreferredSize(new Dimension(80, 30));
    buttonPanel.add(buttonSave);


    getContentPane().add(contend, BorderLayout.PAGE_START);
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    pack();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JPanel contend;
  private JLabel descriptionText;
  private JLabel idText;
  private JLabel nameText;
  private JLabel productsText;
  private JScrollPane scrollPane;
  private JTextField setId;
  private JTextField setName;
  private JTextField setProducts;
  // End of variables declaration//GEN-END:variables
}
