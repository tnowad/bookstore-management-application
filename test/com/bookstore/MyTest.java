package com.bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyTest {
  @Test
  public void testMyMethod() {
    // Arrange
    int a = 5;
    int b = 3;

    // Act
    int result = a + b;

    // Assert
    Assertions.assertEquals(8, result);
  }
}
