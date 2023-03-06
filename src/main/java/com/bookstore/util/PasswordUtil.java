package com.bookstore.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
  private static final int BCRYPT_WORKLOAD = 12;

  public static String hashPassword(String plainTextPassword) {
    String salt = BCrypt.gensalt(BCRYPT_WORKLOAD);
    String hashedPassword = BCrypt.hashpw(plainTextPassword, salt);
    return hashedPassword;
  }

  public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
    boolean passwordMatch = BCrypt.checkpw(plainTextPassword, hashedPassword);
    return passwordMatch;
  }
}