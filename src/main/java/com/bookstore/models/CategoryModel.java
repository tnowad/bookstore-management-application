package com.bookstore.models;

public class CategoryModel {
  private int id;
  private String name;

  public CategoryModel() {
  }

  public CategoryModel(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CategoryModel id(int id) {
    setId(id);
    return this;
  }

  public CategoryModel name(String name) {
    setName(name);
    return this;
  }

}
