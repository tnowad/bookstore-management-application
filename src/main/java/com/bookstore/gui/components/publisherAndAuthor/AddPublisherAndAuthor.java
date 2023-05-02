
package com.bookstore.gui.components.publisherAndAuthor;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.*;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.bus.PublisherBUS;
import com.bookstore.gui.components.books.AddProductFrame;
import com.bookstore.gui.forms.users.AdminFrame;
import com.bookstore.models.AuthorModel;
import com.bookstore.models.PublisherModel;

/**
 *
 * @author yanti
 */
public class AddPublisherAndAuthor extends javax.swing.JFrame {

    public AddPublisherAndAuthor() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    private void initComponents() {

        titlePanel = new javax.swing.JLabel();
        contend = new javax.swing.JPanel();
        publisherText = new javax.swing.JLabel();
        setPublisherName = new javax.swing.JTextField();
        setPublisherId = new javax.swing.JTextField();
        authorText = new javax.swing.JLabel();
        setAuthorName = new javax.swing.JTextField();
        setAuthorId = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        buttonBack = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(188, 105));
        setPreferredSize(new java.awt.Dimension(440, 160));
        setResizable(false);

        titlePanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titlePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlePanel.setText("Publisher and Author");
        getContentPane().add(titlePanel, java.awt.BorderLayout.PAGE_START);

        publisherText.setText("Publisher :");
        publisherText.setPreferredSize(new java.awt.Dimension(80, 16));
        contend.add(publisherText);

        setPublisherName.setPreferredSize(new java.awt.Dimension(200, 22));
        setPublisherName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                checkPublisherName();
            }
        });
        contend.add(setPublisherName);

        setPublisherId.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        setPublisherId.setText("id");
        setPublisherId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                checkPublisherId();
            }
        });
        contend.add(setPublisherId);

        authorText.setText("Author :");
        authorText.setPreferredSize(new java.awt.Dimension(80, 16));
        contend.add(authorText);

        setAuthorName.setPreferredSize(new java.awt.Dimension(200, 22));
        setAuthorName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                checkAuthorName();
            }
        });
        contend.add(setAuthorName);

        setAuthorId.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        setAuthorId.setText("id");
        setAuthorId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                checkAuthorId();
            }
        });
        contend.add(setAuthorId);

        buttonPanel.setPreferredSize(new java.awt.Dimension(430, 110));
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        buttonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back.png"))); // NOI18N
        buttonBack.setPreferredSize(new java.awt.Dimension(70, 23));
        buttonBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
                frame.dispose();
            }

        });
        buttonPanel.add(buttonBack);

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/save.png"))); // NOI18N
        buttonSave.setPreferredSize(new java.awt.Dimension(70, 23));
        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionSave();
            }

        });
        buttonPanel.add(buttonSave);

        contend.add(buttonPanel);

        getContentPane().add(contend, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void checkPublisherId() {
        if (PublisherBUS
                .getInstance()
                .getModelById(Integer.parseInt(setPublisherId.getText().trim())) != null) {
            setPublisherName.setText(
                    PublisherBUS
                            .getInstance()
                            .getModelById(Integer.parseInt(setPublisherId.getText().trim()))
                            .getName());
        } else {
            setPublisherName.setText(null);
        }
    }

    public void checkAuthorId() {
        if (AuthorBUS
                .getInstance()
                .getModelById(Integer.parseInt(setAuthorId.getText().trim())) != null) {
            setAuthorName.setText(
                    AuthorBUS
                            .getInstance()
                            .getModelById(Integer.parseInt(setAuthorId.getText().trim()))
                            .getName());
        } else {
            setAuthorName.setText(null);
        }
    }

    public Boolean checkPublisherName() {
        if (PublisherBUS
                .getInstance()
                .getModelByPublisherName(setPublisherName.getText().trim()) != null) {
            setPublisherId.setText(
                    String.valueOf(
                            PublisherBUS
                                    .getInstance()
                                    .getModelByPublisherName(setPublisherName.getText().trim())
                                    .getId()));
            return true;
        } else {
            setPublisherId.setText(null);
            return false;
        }
    }

    public Boolean checkAuthorName() {
        if (AuthorBUS
                .getInstance()
                .getModelByAuthorName(setAuthorName.getText().trim()) != null) {
            setAuthorId.setText(
                    String.valueOf(
                            AuthorBUS
                                    .getInstance()
                                    .getModelByAuthorName(setAuthorName.getText().trim())
                                    .getId()));
            return true;
        } else {
            setAuthorId.setText(null);
            return false;
        }
    }

    public void actionSave() {

        final String EMPTY_FIELD_ERROR = " cannot be empty!";

        if (setPublisherName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Publisher Name" + EMPTY_FIELD_ERROR);
            return;
        }

        if (setAuthorName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Author Name" + EMPTY_FIELD_ERROR);
            return;
        }

        if (!checkPublisherName()) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to add new publisher?");
            if (choice == JOptionPane.YES_OPTION) {
                PublisherModel publisherModel = new PublisherModel(
                        0,
                        setPublisherName.getText().trim(),
                        "description ?");
                PublisherBUS.getInstance().addModel(publisherModel);
            } else {
                return;
            }
        }
        if (!checkAuthorName()) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to add new author?");
            if (choice == JOptionPane.YES_OPTION) {
                AuthorModel authorModel = new AuthorModel(
                        0,
                        setAuthorName.getText().trim(),
                        "description ?");
                AuthorBUS.getInstance().addModel(authorModel);
            } else {
                return;
            }
        }
        JFrame thisFrame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
        thisFrame.dispose();
        // for (Frame window : JFrame.getFrames()) {
        //     if (window instanceof JFrame) {
        //       JFrame frame = (JFrame) window;
        //       frame.setVisible(false);
        //       frame.dispose();
        //       frame.setVisible(true);
        //  }
        // }
        
        AddProductFrame addProductFrame = new AddProductFrame(setPublisherName.getText().trim(), setAuthorName.getText().trim());
        addProductFrame.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorText;
    private javax.swing.JButton buttonBack;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton buttonSave;
    private javax.swing.JPanel contend;
    private javax.swing.JLabel publisherText;
    private javax.swing.JTextField setAuthorId;
    private javax.swing.JTextField setAuthorName;
    private javax.swing.JTextField setPublisherId;
    private javax.swing.JTextField setPublisherName;
    private javax.swing.JLabel titlePanel;
    // End of variables declaration//GEN-END:variables
}
