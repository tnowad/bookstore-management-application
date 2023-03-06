package com.bookstore.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordUtilTest {

  @Test
  void testHashPassword() {
    String password = "MySecurePassword";
    String hashedPassword = PasswordUtil.hashPassword(password);
    assertNotNull(hashedPassword);
    assertNotEquals(password, hashedPassword);
  }

  @Test
  void testCheckPassword() {
    String password = "MySecurePassword";
    String hashedPassword = PasswordUtil.hashPassword(password);
    assertTrue(PasswordUtil.checkPassword(password, hashedPassword));
    assertFalse(PasswordUtil.checkPassword("IncorrectPassword", hashedPassword));
  }

}