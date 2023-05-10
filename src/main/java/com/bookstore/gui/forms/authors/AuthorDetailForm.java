package com.bookstore.gui.forms.authors;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.gui.components.authors.AuthorListPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.AuthorModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AuthorDetailForm extends JFrame {

  private JTextArea setDescription;
  private JPanel buttonPanel;
  private JButton buttonBack;
  private JButton buttonSave;
  private JButton buttonDelete;
  private JPanel contend;
  private JLabel descriptionText;
  private JLabel idText;
  private JLabel nameText;
  private JScrollPane scrollPane;
  private JTextField setId;
  private JTextField setName;
  private JLabel productsText;
  private JTextField setProducts;
  private JLabel title;

  private AuthorBUS authorBUS = AuthorBUS.getInstance();
  AuthorModel author;

  public AuthorDetailForm(AuthorModel author) {
    initComponents(author);
    this.author = author;
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents(AuthorModel author) {
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
    buttonDelete = new JButton();
    productsText = new JLabel();
    setProducts = new JTextField();
    title = new JLabel();

    contend.setMinimumSize(new Dimension(400, 100));
    contend.setPreferredSize(new Dimension(390, 123));
    contend.setLayout(new FlowLayout(FlowLayout.LEFT));

    title.setFont(new Font("Segoe UI", 1, 18));
    title.setHorizontalAlignment(SwingConstants.LEFT);
    title.setText("Author ");
    title.setForeground(Color.BLUE);
    title.setPreferredSize(new Dimension(390, 25));
    contend.add(title);

    idText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    idText.setHorizontalAlignment(SwingConstants.RIGHT);
    idText.setText("ID:");
    idText.setPreferredSize(new Dimension(130, 16));
    contend.add(idText);

    setId.setPreferredSize(new Dimension(200, 25));
    setId.setText("" + author.getId());
    setId.setEditable(false);
    contend.add(setId);

    nameText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    nameText.setHorizontalAlignment(SwingConstants.RIGHT);
    nameText.setText("Name:");
    nameText.setPreferredSize(new Dimension(130, 16));
    contend.add(nameText);

    setName.setPreferredSize(new Dimension(200, 25));
    setName.setText(author.getName());
    contend.add(setName);

    productsText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    productsText.setHorizontalAlignment(SwingConstants.RIGHT);
    productsText.setText("Product Of This");
    productsText.setPreferredSize(new Dimension(130, 16));
    contend.add(productsText);

    setProducts.setPreferredSize(new Dimension(130, 25));
    setProducts.setEditable(false);
    setProducts.setText(
      "" +
      BookBUS
        .getInstance()
        .searchModel(
          String.valueOf(author.getId()),
          new String[] { "author_id" }
        )
        .size()
    );
    contend.add(setProducts);

    descriptionText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    descriptionText.setHorizontalAlignment(SwingConstants.LEFT);
    descriptionText.setText("Description:");
    descriptionText.setPreferredSize(new Dimension(130, 16));
    contend.add(descriptionText);

    setDescription.setFont(new Font("Segoe UI", 3, 15));
    setDescription.setLineWrap(true);
    setDescription.setRows(5);
    setDescription.setText(author.getDescription());
    scrollPane.setViewportView(setDescription);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
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

    buttonDelete.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/delete.png"))
    );
    buttonDelete.setPreferredSize(new Dimension(80, 30));
    buttonDelete.addActionListener(actionDelete);
    buttonPanel.add(buttonDelete);

    getContentPane().add(contend, BorderLayout.PAGE_START);
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    pack();
  }

  public ActionListener actionSave = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (setName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Author name cannot be empty!");
        return;
      }
      if (setDescription.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Description cannot be empty!");
        return;
      }
      int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to add author?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );
      if (choice == JOptionPane.YES_OPTION) {
        authorBUS.updateModel(
          new AuthorModel(
            author.getId(),
            setName.getText().trim(),
            setDescription.getText().trim()
          )
        );
        JOptionPane.showMessageDialog(null, "Complete");
        AuthorBUS.getInstance().refreshData();
        MainPanel.getInstance().showForm(AuthorListPanel.getInstance());
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

  public ActionListener actionDelete = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to delete author?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );
      if (choice == JOptionPane.YES_OPTION) {
        if (
          BookBUS
            .getInstance()
            .searchModel(
              String.valueOf(author.getId()),
              new String[] { "author_id" }
            )
            .size() !=
          0
        ) {
          JOptionPane.showMessageDialog(
            null,
            "You cannot delete this author because of product constraints!"
          );
          return;
        } else {
          AuthorBUS.getInstance().deleteModel(author.getId());
          JOptionPane.showMessageDialog(null, "Completed");
        }
      }
    }
  };
}
