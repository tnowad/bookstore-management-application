
package com.bookstore.gui.components.authors;

import com.bookstore.models.AuthorModel;


public class AuthorPanel extends javax.swing.JPanel {

    public AuthorPanel(int serial,AuthorModel author) {
        initComponents(serial,author);
    }

    
    private void initComponents(int serial,AuthorModel author) {

        panel = new javax.swing.JPanel();
        checkBox = new javax.swing.JCheckBox();
        setSerial = new javax.swing.JLabel();
        setId = new javax.swing.JLabel();
        setName = new javax.swing.JLabel();
        setDescription = new javax.swing.JLabel();

        setLayout(new java.awt.GridLayout(1, 3));

        panel.setLayout(new java.awt.GridLayout(1, 3));
        panel.add(checkBox);

        setSerial.setText(""+serial);
        panel.add(setSerial);

        setId.setText(""+author.getId());
        panel.add(setId);

        add(panel);

        setName.setText(author.getName());
        setName.setPreferredSize(new java.awt.Dimension(100, 16));
        add(setName);

        setDescription.setText(author.getDescription());
        setDescription.setPreferredSize(new java.awt.Dimension(300, 16));
        add(setDescription);
    }


    private javax.swing.JCheckBox checkBox;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel setDescription;
    private javax.swing.JLabel setId;
    private javax.swing.JLabel setName;
    private javax.swing.JLabel setSerial;
}
