package com.bookstore.util.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

  public static String encodeToBase64(Image image, String formatName) throws IOException {
    BufferedImage bufferedImage = toBufferedImage(image);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, formatName, byteArrayOutputStream);
    byte[] bytes = byteArrayOutputStream.toByteArray();
    return Base64.getEncoder().encodeToString(bytes);
  }

  public static Image decodeFromBase64(String base64) throws IOException {
    byte[] bytes = java.util.Base64.getDecoder().decode(base64);
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
    BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
    return toImage(bufferedImage);
  }

  private static BufferedImage toBufferedImage(Image image) {
    if (image instanceof BufferedImage) {
      return (BufferedImage) image;
    }
    BufferedImage bufferedImage = new BufferedImage(
        image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    bufferedImage.getGraphics().drawImage(image, 0, 0, null);
    return bufferedImage;
  }

  private static Image toImage(BufferedImage bufferedImage) {
    return new ImageIcon(bufferedImage).getImage();
  }
}
