package com.bookstore.gui.model;

public class CounterModel {
  private int value;

  public CounterModel() {
  }

  public CounterModel(int value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public void IncreaseValue() {
    this.value++;
  }

  public void DecreaseValue() {
    this.value--;
  }

}
