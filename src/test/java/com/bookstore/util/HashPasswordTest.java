package com.bookstore.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashPasswordTest {

  private HashPassword hashPassword;

  @BeforeEach
  void setUp() {
    hashPassword = new HashPassword("myPassword123");
  }

  @Test
  void testGenerateSalt() {
    byte[] salt1 = HashPassword.generateSalt();
    byte[] salt2 = HashPassword.generateSalt();
    Assertions.assertNotNull(salt1);
    Assertions.assertNotNull(salt2);
    Assertions.assertNotEquals(salt1, salt2);
  }

  @Test
  void testHashPassword() {
    String password = "myPassword123";
    byte[] salt = HashPassword.generateSalt();
    String hashedPassword = HashPassword.hashPassword(password, salt);
    Assertions.assertNotNull(hashedPassword);
    Assertions.assertNotEquals(password, hashedPassword);
  }

  @Test
  void testCheckPassword() {
    String password = "myPassword123";
    byte[] salt = HashPassword.generateSalt();
    String hashedPassword = HashPassword.hashPassword(password, salt);
    Assertions.assertTrue(HashPassword.checkPassword(password, salt, hashedPassword));
    Assertions.assertFalse(HashPassword.checkPassword("wrongPassword", salt, hashedPassword));
  }

  @Test
  void testSetSalt() {
    byte[] newSalt = HashPassword.generateSalt();
    hashPassword.setSalt(newSalt);
    Assertions.assertArrayEquals(newSalt, hashPassword.getSalt());
  }

  @Test
  void testGetHashedPassword() {
    Assertions.assertEquals(hashPassword.getHashedPassword(),
        HashPassword.hashPassword("myPassword123", hashPassword.getSalt()));
  }
}
