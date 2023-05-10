package com.bookstore.gui.components.carts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

public class CartItemPanel extends JPanel {

  private JTextArea DescriptionTextArea;
  private JPanel bookImagePanel;
  private JCheckBox checkBoxChooseBookButton;
  private JButton deleteItemBtn;
  private JPanel jPanel1;
  private JScrollPane jScrollPane1;
  private JLabel lbPrice;
  private JLabel lblBookName;
  private JSpinner quantitySpinner;

  public CartItemPanel() {
    initComponents();
  }

  private void initComponents() {
    lblBookName = new JLabel();
    lbPrice = new JLabel();
    jPanel1 = new JPanel();
    jScrollPane1 = new JScrollPane();
    DescriptionTextArea = new JTextArea();
    checkBoxChooseBookButton = new JCheckBox();
    quantitySpinner = new JSpinner();
    bookImagePanel = new JPanel();
    deleteItemBtn = new JButton();

    setPreferredSize(new Dimension(500, 215));

    lblBookName.setFont(new Font("sansserif", 1, 18));
    lblBookName.setForeground(new Color(76, 76, 76));
    lblBookName.setText("Book Title");

    lbPrice.setFont(new Font("sansserif", 1, 18));
    lbPrice.setForeground(new Color(76, 76, 76));
    lbPrice.setText("$0.00");

    DescriptionTextArea.setColumns(20);
    DescriptionTextArea.setRows(5);
    jScrollPane1.setViewportView(DescriptionTextArea);

    GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(
          jScrollPane1,
          GroupLayout.DEFAULT_SIZE,
          302,
          Short.MAX_VALUE
        )
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          jPanel1Layout
            .createSequentialGroup()
            .addComponent(
              jScrollPane1,
              GroupLayout.PREFERRED_SIZE,
              125,
              GroupLayout.PREFERRED_SIZE
            )
            .addGap(0, 0, Short.MAX_VALUE)
        )
    );

    checkBoxChooseBookButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          checkBoxChooseBookButtonActionPerformed(evt);
        }
      }
    );

    GroupLayout bookImagePanelLayout = new GroupLayout(bookImagePanel);
    bookImagePanel.setLayout(bookImagePanelLayout);
    bookImagePanelLayout.setHorizontalGroup(
      bookImagePanelLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 155, Short.MAX_VALUE)
    );
    bookImagePanelLayout.setVerticalGroup(
      bookImagePanelLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );

    deleteItemBtn.setIcon(
      new ImageIcon(getClass().getResource("/icon/x (Custom).png"))
    );

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          GroupLayout.Alignment.TRAILING,
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(
              checkBoxChooseBookButton,
              GroupLayout.PREFERRED_SIZE,
              19,
              GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              bookImagePanel,
              GroupLayout.PREFERRED_SIZE,
              GroupLayout.DEFAULT_SIZE,
              GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(
                  jPanel1,
                  GroupLayout.Alignment.TRAILING,
                  GroupLayout.DEFAULT_SIZE,
                  GroupLayout.DEFAULT_SIZE,
                  Short.MAX_VALUE
                )
                .addGroup(
                  GroupLayout.Alignment.TRAILING,
                  layout
                    .createSequentialGroup()
                    .addComponent(
                      lblBookName,
                      GroupLayout.DEFAULT_SIZE,
                      GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(
                      deleteItemBtn,
                      GroupLayout.PREFERRED_SIZE,
                      30,
                      GroupLayout.PREFERRED_SIZE
                    )
                )
                .addGroup(
                  GroupLayout.Alignment.TRAILING,
                  layout
                    .createSequentialGroup()
                    .addComponent(
                      lbPrice,
                      GroupLayout.DEFAULT_SIZE,
                      GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addGap(18, 18, 18)
                    .addComponent(
                      quantitySpinner,
                      GroupLayout.PREFERRED_SIZE,
                      76,
                      GroupLayout.PREFERRED_SIZE
                    )
                )
            )
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addComponent(checkBoxChooseBookButton)
                    .addGap(0, 0, Short.MAX_VALUE)
                )
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addComponent(
                      bookImagePanel,
                      GroupLayout.DEFAULT_SIZE,
                      GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addContainerGap()
                )
                .addGroup(
                  GroupLayout.Alignment.TRAILING,
                  layout
                    .createSequentialGroup()
                    .addGroup(
                      layout
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(
                          lblBookName,
                          GroupLayout.PREFERRED_SIZE,
                          37,
                          GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          deleteItemBtn,
                          GroupLayout.PREFERRED_SIZE,
                          25,
                          GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(
                      jPanel1,
                      GroupLayout.DEFAULT_SIZE,
                      GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                      layout
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(
                          quantitySpinner,
                          GroupLayout.PREFERRED_SIZE,
                          25,
                          GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(lbPrice)
                    )
                    .addGap(6, 6, 6)
                )
            )
        )
    );
  }

  private void checkBoxChooseBookButtonActionPerformed(ActionEvent evt) {}
}
