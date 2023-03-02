package com.bookstore.model;

public class WarehouseModel {
    private String ID;

    public WarehouseModel() {
    }

    public WarehouseModel(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public WarehouseModel ID(String ID) {
        setID(ID);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " ID='" + getID() + "'" +
                "}";
    }
}
