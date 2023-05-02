package com.bookstore.gui.components.carts;

public class CartItemPanel extends javax.swing.JPanel {

  public CartItemPanel() {
    initComponents();
  }

  private void initComponents() {
    lblBookName = new javax.swing.JLabel();
    lbPrice = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    DescriptionTextArea = new javax.swing.JTextArea();
    checkBoxChooseBookButton = new javax.swing.JCheckBox();
    quantitySpinner = new javax.swing.JSpinner();
    bookImagePanel = new javax.swing.JPanel();
    deleteItemBtn = new javax.swing.JButton();

    setPreferredSize(new java.awt.Dimension(500, 215));

    lblBookName.setFont(new java.awt.Font("sansserif", 1, 18));
    lblBookName.setForeground(new java.awt.Color(76, 76, 76));
    lblBookName.setText("Book Title");

    lbPrice.setFont(new java.awt.Font("sansserif", 1, 18));
    lbPrice.setForeground(new java.awt.Color(76, 76, 76));
    lbPrice.setText("$0.00");

    DescriptionTextArea.setColumns(20);
    DescriptionTextArea.setRows(5);
    jScrollPane1.setViewportView(DescriptionTextArea);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
      jPanel1
    );
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(
          jScrollPane1,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          302,
          Short.MAX_VALUE
        )
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          jPanel1Layout
            .createSequentialGroup()
            .addComponent(
              jScrollPane1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              125,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(0, 0, Short.MAX_VALUE)
        )
    );

    checkBoxChooseBookButton.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          checkBoxChooseBookButtonActionPerformed(evt);
        }
      }
    );

    javax.swing.GroupLayout bookImagePanelLayout = new javax.swing.GroupLayout(
      bookImagePanel
    );
    bookImagePanel.setLayout(bookImagePanelLayout);
    bookImagePanelLayout.setHorizontalGroup(
      bookImagePanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 155, Short.MAX_VALUE)
    );
    bookImagePanelLayout.setVerticalGroup(
      bookImagePanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );

    deleteItemBtn.setIcon(
      new javax.swing.ImageIcon(getClass().getResource("/icon/x (Custom).png"))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          javax.swing.GroupLayout.Alignment.TRAILING,
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(
              checkBoxChooseBookButton,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              19,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              bookImagePanel,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(
                  jPanel1,
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  Short.MAX_VALUE
                )
                .addGroup(
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  layout
                    .createSequentialGroup()
                    .addComponent(
                      lblBookName,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addComponent(
                      deleteItemBtn,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      30,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
                .addGroup(
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  layout
                    .createSequentialGroup()
                    .addComponent(
                      lbPrice,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addGap(18, 18, 18)
                    .addComponent(
                      quantitySpinner,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      76,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
            )
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addContainerGap()
                )
                .addGroup(
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  layout
                    .createSequentialGroup()
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(
                          lblBookName,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          37,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          deleteItemBtn,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          25,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addComponent(
                      jPanel1,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(
                          quantitySpinner,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          25,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(lbPrice)
                    )
                    .addGap(6, 6, 6)
                )
            )
        )
    );
  }

  private void checkBoxChooseBookButtonActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private javax.swing.JTextArea DescriptionTextArea;
  private javax.swing.JPanel bookImagePanel;
  private javax.swing.JCheckBox checkBoxChooseBookButton;
  private javax.swing.JButton deleteItemBtn;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lbPrice;
  private javax.swing.JLabel lblBookName;
  private javax.swing.JSpinner quantitySpinner;
}
