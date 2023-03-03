package com.bookstore.model;

public class AuthorModel {
    private String name, nationality;

    public AuthorModel() {
    }

    public AuthorModel(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public AuthorModel name(String name) {
        setName(name);
        return this;
    }

    public AuthorModel nationality(String nationality) {
        setNationality(nationality);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", nationality='" + getNationality() + "'" +
                "}";
    }

}