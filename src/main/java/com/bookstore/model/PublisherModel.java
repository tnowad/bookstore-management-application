package com.bookstore.model;

public class PublisherModel {
    private String location, name;

    public PublisherModel() {
    }

    public PublisherModel(String location, String name) {
        this.location = location;
        this.name = name;
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

    public PublisherModel location(String location) {
        setLocation(location);
        return this;
    }

    public PublisherModel name(String name) {
        setName(name);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " location='" + getLocation() + "'" +
                ", name='" + getName() + "'" +
                "}";
    }

}
