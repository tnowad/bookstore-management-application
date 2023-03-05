package com.bookstore.model;

public class AuthorModel {
    private String name, nationality, authorID, ISBN;

    public AuthorModel() {
    }

    public AuthorModel(String name, String nationality, String authorID, String ISBN) {
        this.name = name;
        this.nationality = nationality;
        this.authorID = authorID;
        this.ISBN = ISBN;
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

    public String getAuthorID() {
        return this.authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public AuthorModel name(String name) {
        setName(name);
        return this;
    }

    public AuthorModel nationality(String nationality) {
        setNationality(nationality);
        return this;
    }

    public AuthorModel authorID(String authorID) {
        setAuthorID(authorID);
        return this;
    }

    public AuthorModel ISBN(String ISBN) {
        setISBN(ISBN);
        return this;
    }

}
