
package com.bookstore.gui.components.publishers;

import com.bookstore.models.PublisherModel;


public class PublisherPanel extends javax.swing.JPanel {

    public PublisherPanel(int serial,PublisherModel publisher) {
        initComponents(serial,publisher);
    }

    
    private void initComponents(int serial,PublisherModel publisher) {

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

        setId.setText(""+publisher.getId());
        panel.add(setId);

        add(panel);

        setName.setText(publisher.getName());
        setName.setPreferredSize(new java.awt.Dimension(100, 16));
        add(setName);

        setDescription.setText(publisher.getDescription());
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
