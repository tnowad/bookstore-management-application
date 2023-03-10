package com.bookstore.model;

public class CategoryModel {
    private int id;
    private String name;

    public CategoryModel() {

    }

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
