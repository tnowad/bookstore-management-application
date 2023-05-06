package com.bookstore.gui.forms.providers;

import com.bookstore.bus.ProviderBUS;
import com.bookstore.bus.PublisherBUS;
import com.bookstore.models.ProviderModel;
import com.bookstore.models.PublisherModel;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ProviderDetail extends JFrame {

  private JTextArea setDescription;
  private JPanel buttonPanel;
  private JButton buttonBack;
  private JButton buttonSave;
  private JPanel contend;
  private JLabel descriptionText;
  private JLabel idText;
  private JLabel nameText;
  private JScrollPane scrollPane;
  private JTextField setId;
  private JTextField setName;
  private JLabel title; 

  ProviderBUS providerBUS = ProviderBUS.getInstance();
  ProviderModel provider;

  public ProviderDetail(ProviderModel provider) {
    initComponents(provider);
    this.provider = provider;
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents(ProviderModel provider) {
    contend = new JPanel();
    idText = new JLabel();
    setId = new JTextField();
    nameText = new JLabel();
    setName = new JTextField();
    descriptionText = new JLabel();
    scrollPane = new JScrollPane();
    setDescription = new JTextArea();
    buttonPanel = new JPanel();
    buttonBack = new JButton();
    buttonSave = new JButton();
    title = new JLabel();

    contend.setMinimumSize(new Dimension(400, 100));
    contend.setPreferredSize(new Dimension(390, 115));
    contend.setLayout(new FlowLayout(FlowLayout.LEFT));

    title.setFont(new Font("Segoe UI", 1, 18));
    title.setHorizontalAlignment(SwingConstants.LEFT);
    title.setText("Provider ");
    title.setForeground(Color.BLUE);
    title.setPreferredSize(new Dimension(390, 25));
    contend.add(title);

    idText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    idText.setHorizontalAlignment(SwingConstants.RIGHT);
    idText.setText("ID:");
    idText.setPreferredSize(new Dimension(130, 16));
    contend.add(idText);

    setId.setPreferredSize(new Dimension(200, 25));
    setId.setText("" + provider.getId());
    setId.setEditable(false);
    contend.add(setId);

    nameText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    nameText.setHorizontalAlignment(SwingConstants.RIGHT);
    nameText.setText("Name:");
    nameText.setPreferredSize(new Dimension(130, 16));
    contend.add(nameText);

    setName.setPreferredSize(new Dimension(200, 25));
    setName.setText(provider.getName());
    contend.add(setName);

    descriptionText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    descriptionText.setHorizontalAlignment(SwingConstants.LEFT);
    descriptionText.setText("Description:");
    descriptionText.setPreferredSize(new Dimension(130, 16));
    contend.add(descriptionText);

    setDescription.setFont(new Font("Segoe UI", 3, 15));
    setDescription.setLineWrap(true);
    setDescription.setRows(5);
    setDescription.setText(provider.getDescription());
    scrollPane.setViewportView(setDescription);
    scrollPane.getVerticalScrollBar().setValue(0);

    buttonPanel.setPreferredSize(new Dimension(50, 50));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
    buttonBack.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/back.png"))
    );
    buttonBack.setPreferredSize(new Dimension(80, 30));
    buttonBack.addActionListener(actionBack);
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/save.png"))
    );
    buttonSave.setPreferredSize(new Dimension(80, 30));
    buttonSave.addActionListener(actionSave);
    buttonPanel.add(buttonSave);

    getContentPane().add(contend, BorderLayout.PAGE_START);
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    pack();
  }

  public ActionListener actionSave = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (setName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Provider name cannot be empty!");
        return;
      }
      providerBUS.updateModel(
        new ProviderModel(
          provider.getId(),
          setName.getText().trim(),
          setDescription.getText().trim()
        )
      );
      JOptionPane.showMessageDialog(null, "Complete");
    }
  };

  {}

  public ActionListener actionBack = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
      frame.dispose();
    }
  };
}
