package com.bookstore.models;

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

  public void increaseValue() {
    this.value++;
  }

  public void decreaseValue() {
    this.value--;
  }

}
