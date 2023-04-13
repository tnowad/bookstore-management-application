package com.bookstore.gui;

import java.util.HashMap;

import java.awt.Component;

public class UIManagement {
  private UIManagement() {
  }

  private static HashMap<String, Component> uiMap = new HashMap<>();

  public static void setUI(String key, Component value) {
    uiMap.put(key, value);
  }

  public static Component getUI(String key) {
    return uiMap.get(key);
  }
}
