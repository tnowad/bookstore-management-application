package com.bookstore.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
  private PasswordUtil() {
    throw new IllegalStateException("Utility class");
  }

  private static final int BCRYPT_WORKLOAD = 12;

  public static String hashPassword(String plainTextPassword) {
    String salt = BCrypt.gensalt(BCRYPT_WORKLOAD);
    return BCrypt.hashpw(plainTextPassword, salt);
  }

  public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
    return BCrypt.checkpw(plainTextPassword, hashedPassword);
  }
}