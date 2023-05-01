package com.bookstore.gui.main;

import com.bookstore.util.image.ImageUtils;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.Base64;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UploadImage extends JFrame {

  private JButton uploadButton;
  private JTextField filePathTextField;
  private JLabel imageLabel;

  public UploadImage() {
    initComponents();
    setPreferredSize(new Dimension(500, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
  }

  private void initComponents() {
    setLayout(new java.awt.GridLayout(3, 1));

    uploadButton = new JButton("Upload");
    uploadButton.addActionListener(uploadButtonActionListener);
    filePathTextField = new JTextField();
    imageLabel = new JLabel();
    add(uploadButton);
    add(filePathTextField);
    add(imageLabel);
  }

  private ActionListener uploadButtonActionListener = e -> {
    // show file chooser and get file path
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose an image");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      String filePath = fileChooser.getSelectedFile().getAbsolutePath();
      filePathTextField.setText(filePath);
      // encode image to base64
      String base64 = null;
      try {
        base64 = ImageUtils.toBase64(ImageUtils.loadImage(filePath));
        // clipboard

        // move to clipboard
        Toolkit
          .getDefaultToolkit()
          .getSystemClipboard()
          .setContents(new StringSelection(base64), null);

        // show message
        JOptionPane.showMessageDialog(
          null,
          "Image has been copied to clipboard"
        );
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      // decode base64 to image
      Image image = null;
      try {
        image = ImageUtils.decodeFromBase64(base64);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      // show image
      imageLabel.setIcon(new javax.swing.ImageIcon(image));
      imageLabel.repaint();
      imageLabel.revalidate();
    }
  };

  public static void main(String[] args) {
    UploadImage uploadImage = new UploadImage();
    uploadImage.setVisible(true);
  }
}
