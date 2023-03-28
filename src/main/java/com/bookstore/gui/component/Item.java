package com.bookstore.gui.component;

import com.bookstore.gui.model.BookItem;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;

public class Item extends javax.swing.JPanel {
  public BookItem getbook() {
    return book;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
    repaint();
  }

  private boolean selected;

  public Item() {
    initComponents();
    setOpaque(false);
    setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  private BookItem book;

  public void setbook(BookItem book) {
    this.book = book;
    pic.setImage(book.getImage());
    lbItemName.setText(book.getTitle());
    lbDescription.setText(book.getIsbn());
    DecimalFormat df = new DecimalFormat("$#,##0.00");
    lbPrice.setText(df.format(book.getPrice()));
  }

  @Override
  public void paint(Graphics grphcs) {
    Graphics2D g2 = (Graphics2D) grphcs.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(new Color(242, 242, 242));
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
    if (selected) {
      g2.setColor(new Color(94, 156, 255));
      g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }
    g2.dispose();
    super.paint(grphcs);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    lbItemName = new javax.swing.JLabel();
    lbDescription = new javax.swing.JLabel();
    pic = new com.bookstore.gui.swing.PictureBox();
    lbPrice = new javax.swing.JLabel();
    lbBrand = new javax.swing.JLabel();
    addtoCartButton = new javax.swing.JButton();
    detailsButton = new javax.swing.JButton();

    lbItemName.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
    lbItemName.setForeground(new java.awt.Color(76, 76, 76));
    lbItemName.setText("Title");

    lbDescription.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
    lbDescription.setForeground(new java.awt.Color(178, 178, 178));
    lbDescription.setText("By: Author Name");

    pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/image/book1.jpg"))); // NOI18N
    pic.setInheritsPopupMenu(true);

    lbPrice.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
    lbPrice.setForeground(new java.awt.Color(76, 76, 76));
    lbPrice.setText("$0.00");

    lbBrand.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
    lbBrand.setForeground(new java.awt.Color(76, 76, 76));
    lbBrand.setText("Price");

    addtoCartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/image/cartico.jpg"))); // NOI18N
    addtoCartButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addtoCartButtonActionPerformed(evt);
      }
    });

    detailsButton.setText("Details");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbBrand)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(detailsButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                            126,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addtoCartButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                            45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbPrice))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 180,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout
                        .createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbItemName,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 180,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDescription,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 180,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10)));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPrice)
                    .addComponent(lbBrand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(detailsButton)
                    .addComponent(addtoCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)));
  }// </editor-fold>

  private void addtoCartButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  // Variables declaration - do not modify
  private javax.swing.JButton addtoCartButton;
  private javax.swing.JButton detailsButton;
  private javax.swing.JLabel lbBrand;
  private javax.swing.JLabel lbDescription;
  private javax.swing.JLabel lbItemName;
  private javax.swing.JLabel lbPrice;
  private com.bookstore.gui.swing.PictureBox pic;

  // End of variables declaration
}
