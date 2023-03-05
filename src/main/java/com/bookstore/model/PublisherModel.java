package com.bookstore.model;

public class PublisherModel {
    private String location, name, ISBN, publisherID;

    public PublisherModel() {
    }

    public PublisherModel(String location, String name, String ISBN, String publisherID) {
        this.location = location;
        this.name = name;
        this.ISBN = ISBN;
        this.publisherID = publisherID;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisherID() {
        return this.publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public PublisherModel location(String location) {
        setLocation(location);
        return this;
    }

    public PublisherModel name(String name) {
        setName(name);
        return this;
    }

    public PublisherModel ISBN(String ISBN) {
        setISBN(ISBN);
        return this;
    }

    public PublisherModel publisherID(String publisherID) {
        setPublisherID(publisherID);
        return this;
    }
}
