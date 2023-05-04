
package com.bookstore.gui.components.providers;

import com.bookstore.models.ProviderModel;


public class ProviderPanel extends javax.swing.JPanel {

    public ProviderPanel(int serial, ProviderModel providerModel) {
        initComponents(serial,providerModel);
    }

    
    private void initComponents(int serial,ProviderModel providerModel) {

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

        setId.setText(""+providerModel.getId());
        panel.add(setId);

        add(panel);

        setName.setText(providerModel.getName());
        setName.setPreferredSize(new java.awt.Dimension(100, 16));
        add(setName);

        setDescription.setText(providerModel.getDescription());
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
