package com.bookstore.gui.form;

import java.awt.*;
import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CartUI extends JFrame {

  public CartUI() {
    setPreferredSize(new Dimension(1280, 720));
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Giỏ Hàng");
    getContentPane().setBackground(Color.WHITE);

    init();
    pack();
    setLocationRelativeTo(null); // center on screen
    setVisible(true);
  }

  public void init() {
    // Title Label
    JLabel jLabelCartTitle = new JLabel("Shopping List", SwingConstants.CENTER);
    jLabelCartTitle.setFont(new Font("Segoe Script", Font.BOLD, 32));
    jLabelCartTitle.setForeground(new Color(94, 63, 43));

    JPanel titleContainer = new JPanel();
    titleContainer.add(jLabelCartTitle);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;

    JPanel contentTop = new JPanel(new GridBagLayout());
    contentTop.add(titleContainer, gbc);

    // Remove Button
    JButton jButtonRemove = new JButton(new ImageIcon("remove.png"));
    jButtonRemove.setToolTipText("Delete");
    jButtonRemove.setBackground(new Color(222, 66, 66));
    jButtonRemove.setBorder(BorderFactory.createEmptyBorder());
    jButtonRemove.setPreferredSize(new Dimension(50, 33));

    jButtonRemove.addActionListener(e -> {
      /* Handle click on Remove button */
    });

    // Checkout Button
    JButton jButtonCheckout = new JButton("Proceed to checkout");
    jButtonCheckout.setFont(new Font("sansserif", Font.PLAIN, 18));
    jButtonCheckout.setForeground(new Color(255, 255, 255));
    jButtonCheckout.setBackground(new Color(94, 63, 43));

    jButtonCheckout.addActionListener(e -> {
      /* Handle click on Checkout button */
    });

    // Table and ScrollPane
    DefaultTableModel model = new DefaultTableModel(new Object[][] {
        { false, "Book 1", "1", "10", "10" },
        { false, "Book 2", "2", "20", "40" },
        { false, "Book 3", "3", "15", "45" }
    }, new String[] { "", "Tên Sách", "Số Lượng", "Đơn Giá", "Thành Tiền" }) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == 2; // enable editing on column quantity
      }

      @Override
      public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
          return Boolean.class; // set column 1 to be rendered as checkbox
        } else {
          return super.getColumnClass(columnIndex);
        }
      }
    };

    JTable jTableCart = new JTable(model);
    jTableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jTableCart.setFont(new Font("sansserif", Font.PLAIN, 16));
    jTableCart.setRowHeight(30);

    // make table header not movable and resizable
    jTableCart.getTableHeader().setReorderingAllowed(false);
    jTableCart.getTableHeader().setResizingAllowed(false);

    JScrollPane jScrollPane = new JScrollPane(jTableCart);
    jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane.setBorder(BorderFactory.createEmptyBorder());
    jTableCart.getTableHeader().setFont(new Font("sansserif", Font.PLAIN, 18));

    // Column Widths
    TableColumnModel tcm = jTableCart.getColumnModel();
    tcm.getColumn(0).setPreferredWidth(16);
    tcm.getColumn(1).setPreferredWidth(700);
    tcm.getColumn(2).setPreferredWidth(120);
    tcm.getColumn(3).setPreferredWidth(120);
    tcm.getColumn(4).setPreferredWidth(120);

    // Layout Settings
    setLayout(new BorderLayout());
    JPanel contentCenter = new JPanel(new BorderLayout());
    JPanel contentBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 40, 15));

    contentCenter.add(jScrollPane, BorderLayout.CENTER);
    contentCenter.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

    // Bottom Panel
    contentBottom.add(jButtonRemove);
    contentBottom.add(jButtonCheckout);

    // Set Borders
    jScrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0),
        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)));
    contentBottom.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Add Content
    add(contentTop, BorderLayout.NORTH);
    add(contentCenter, BorderLayout.CENTER);
    add(contentBottom, BorderLayout.SOUTH);

    // render the checkbox in column 1
    jTableCart.getColumnModel().getColumn(0).setCellRenderer((table, value, isSelected, hasFocus, row, col) -> {
      var c = new JCheckBox("");
      c.setSelected((Boolean) value);
      return c;
    });

    // align left for left columns and right for right columns
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
    jTableCart.getColumnModel().getColumn(1).setCellRenderer(renderer);
    jTableCart.getColumnModel().getColumn(2).setCellRenderer(renderer);
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
    jTableCart.getColumnModel().getColumn(3).setCellRenderer(renderer);
    jTableCart.getColumnModel().getColumn(4).setCellRenderer(renderer);

    // add +/- buttons to quantity column
    TableColumn quantityColumn = jTableCart.getColumn("Số Lượng");
    quantityColumn.setCellEditor(new QuantityEditor());
  }

  static class QuantityEditor extends AbstractCellEditor implements TableCellEditor {
    private final JTextField field;

    public QuantityEditor() {
      JButton plusButton = new JButton("+");
      JButton minusButton = new JButton("-");
      field = new JTextField();
      JPanel panel = new JPanel(new BorderLayout());

      panel.add(plusButton, BorderLayout.WEST);
      panel.add(field, BorderLayout.CENTER);
      panel.add(minusButton, BorderLayout.EAST);

      plusButton.addActionListener(e -> incrementValue()); // attach listener to btn +
      minusButton.addActionListener(e -> decrementValue()); // attach listener to btn -
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
      field.setText(value.toString());
      return field;
    }

    @Override
    public Object getCellEditorValue() {
      return field.getText();
    }

    public void incrementValue() {
      int num = Integer.parseInt(field.getText());
      num++;
      field.setText(Integer.toString(num));
    }

    public void decrementValue() {
      int num = Integer.parseInt(field.getText());
      if (num > 0) {
        num--;
        field.setText(Integer.toString(num));
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(CartUI::new);
  }
}
