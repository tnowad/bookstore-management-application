package com.bookstore.model;

public class ProviderModel {
    private String providerID, NameProvider, gnr_id;

    public ProviderModel() {
    }

    public ProviderModel(String providerID, String NameProvider, String gnr_id) {
        this.providerID = providerID;
        this.NameProvider = NameProvider;
        this.gnr_id = gnr_id;
    }

    public String getProviderID() {
        return this.providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getNameProvider() {
        return this.NameProvider;
    }

    public void setNameProvider(String NameProvider) {
        this.NameProvider = NameProvider;
    }

    public String getGnr_id() {
        return this.gnr_id;
    }

    public void setGnr_id(String gnr_id) {
        this.gnr_id = gnr_id;
    }

    public ProviderModel providerID(String providerID) {
        setProviderID(providerID);
        return this;
    }

    public ProviderModel NameProvider(String NameProvider) {
        setNameProvider(NameProvider);
        return this;
    }

    public ProviderModel gnr_id(String gnr_id) {
        setGnr_id(gnr_id);
        return this;
    }
}
