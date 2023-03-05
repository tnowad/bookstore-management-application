package com.bookstore.model;

public class GenreModel {
    private String genreID, nameGenre, ISBN;

    public GenreModel() {
    }

    public GenreModel(String genreID, String nameGenre, String ISBN) {
        this.genreID = genreID;
        this.nameGenre = nameGenre;
        this.ISBN = ISBN;
    }

    public String getGenreID() {
        return this.genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public String getNameGenre() {
        return this.nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public GenreModel genreID(String genreID) {
        setGenreID(genreID);
        return this;
    }

    public GenreModel nameGenre(String nameGenre) {
        setNameGenre(nameGenre);
        return this;
    }

    public GenreModel ISBN(String ISBN) {
        setISBN(ISBN);
        return this;
    }

}
