package com.bookstore.model;

public class WarehouseModel {
    private String warehouseID, ISBN;

    public WarehouseModel() {
    }

    public WarehouseModel(String warehouseID, String ISBN) {
        this.warehouseID = warehouseID;
        this.ISBN = ISBN;
    }

    public String getWarehouseID() {
        return this.warehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public WarehouseModel warehouseID(String warehouseID) {
        setWarehouseID(warehouseID);
        return this;
    }

    public WarehouseModel ISBN(String ISBN) {
        setISBN(ISBN);
        return this;
    }
}
