import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashPassword {
  private byte[] salt = null;
  private String password = null;
  private String hashedPassword = null;

  public HashPassword(String password) {
    this.salt = generateSalt();

    this.password = password;
    this.hashedPassword = hashPassword(this.password, this.salt);
  }

  static String hashPassword(String password, byte[] salt) {
    String hashedPassword = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(salt);
      byte[] bytes = md.digest(password.getBytes());
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      hashedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hashedPassword;
  }

  public static boolean checkPassword(String password, byte[] salt, String hashedPassword) {
    String hashedPasswordToCheck = hashPassword(password, salt);
    return hashedPasswordToCheck.equals(hashedPassword);
  }

  public static byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  public byte[] getSalt() {
    return salt;
  }

  public void setSalt(byte[] salt) {
    this.salt = salt;
    this.hashedPassword = hashPassword(this.password, this.salt);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
    this.hashedPassword = hashPassword(this.password, this.salt);
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

}
