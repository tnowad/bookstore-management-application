package com.bookstore.model;

import java.sql.Date;

public class GoodNotesReceiptModel {
    private Date importDate;
    private String gnrId;

    public GoodNotesReceiptModel() {
    }

    public GoodNotesReceiptModel(Date importDate, String gnrId) {
        this.importDate = importDate;
        this.gnrId = gnrId;
    }

    public Date getImportDate() {
        return this.importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getGnrId() {
        return this.gnrId;
    }

    public void setGnrId(String gnrId) {
        this.gnrId = gnrId;
    }

    public GoodNotesReceiptModel importDate(Date importDate) {
        setImportDate(importDate);
        return this;
    }

    public GoodNotesReceiptModel gnrId(String gnrId) {
        setGnrId(gnrId);
        return this;
    }
}
