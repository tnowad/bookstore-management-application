package com.bookstore.model;

public class GenreModel {
    private String ID, nameGenre;

    public GenreModel() {
    }

    public GenreModel(String ID, String nameGenre) {
        this.ID = ID;
        this.nameGenre = nameGenre;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNameGenre() {
        return this.nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public GenreModel ID(String ID) {
        setID(ID);
        return this;
    }

    public GenreModel nameGenre(String nameGenre) {
        setNameGenre(nameGenre);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " ID='" + getID() + "'" +
                ", nameGenre='" + getNameGenre() + "'" +
                "}";
    }

}
