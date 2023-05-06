package com.bookstore.gui.forms.authors;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.models.AuthorModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddAuthor extends JFrame {

  private JLabel title;
  private JPanel contend;
  private JLabel descriptionText;
  private JLabel nameText;
  private JScrollPane scrollPane;
  private JTextField setName;
  private JTextArea setDescription;
  private JPanel buttonPanel;
  private JButton buttonBack;
  private JButton buttonSave;

  private AuthorBUS authorBUS = AuthorBUS.getInstance();

  public AddAuthor() {
    initComponents();
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents() {
    title = new JLabel();
    contend = new JPanel();
    nameText = new JLabel();
    setName = new JTextField();
    descriptionText = new JLabel();
    scrollPane = new JScrollPane();
    setDescription = new JTextArea();
    buttonPanel = new JPanel();
    buttonBack = new JButton();
    buttonSave = new JButton();

    contend.setMinimumSize(new Dimension(400, 100));
    contend.setPreferredSize(new Dimension(390, 90));
    contend.setLayout(new FlowLayout(FlowLayout.LEFT));

    title.setFont(new Font("Segoe UI", 1, 18));
    title.setHorizontalAlignment(SwingConstants.LEFT);
    title.setText("New Author ");
    title.setForeground(Color.BLUE);
    title.setPreferredSize(new Dimension(390, 25));
    contend.add(title);

    nameText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    nameText.setHorizontalAlignment(SwingConstants.RIGHT);
    nameText.setText("Name:");
    nameText.setPreferredSize(new Dimension(130, 16));
    contend.add(nameText);

    setName.setPreferredSize(new Dimension(200, 25));
    contend.add(setName);

    descriptionText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    descriptionText.setHorizontalAlignment(SwingConstants.LEFT);
    descriptionText.setText("Description:");
    descriptionText.setPreferredSize(new Dimension(130, 16));
    contend.add(descriptionText);

    setDescription.setFont(new Font("Segoe UI", 3, 15));
    setDescription.setLineWrap(true);
    setDescription.setRows(5);
    setDescription.setText("Description here!");
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
      int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to add author?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );
      if (choice == JOptionPane.YES_OPTION) {
        if (setName.getText().trim().isEmpty()) {
          JOptionPane.showMessageDialog(null, "Author name cannot be empty!");
          return;
        }

        if (authorBUS.getModelByName(setName.getText().trim())!=null) {
          JOptionPane.showMessageDialog(null, "Author already exists!");
          return;
        }
        authorBUS.addModel(new AuthorModel(9, setName.getText().trim(), setDescription.getText().trim()));
        JOptionPane.showMessageDialog(null,"Complete");
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
        frame.dispose();
      }
    }
  };

  public ActionListener actionBack = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
      frame.dispose();
    }
  };
}
