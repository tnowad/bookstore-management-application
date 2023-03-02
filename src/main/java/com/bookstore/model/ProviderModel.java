package com.bookstore.model;

public class ProviderModel {
    private String IDprovider, NameProvider;

    public ProviderModel() {
    }

    public ProviderModel(String IDprovider, String NameProvider) {
        this.IDprovider = IDprovider;
        this.NameProvider = NameProvider;
    }

    public String getIDprovider() {
        return this.IDprovider;
    }

    public void setIDprovider(String IDprovider) {
        this.IDprovider = IDprovider;
    }

    public String getNameProvider() {
        return this.NameProvider;
    }

    public void setNameProvider(String NameProvider) {
        this.NameProvider = NameProvider;
    }

    public ProviderModel IDprovider(String IDprovider) {
        setIDprovider(IDprovider);
        return this;
    }

    public ProviderModel NameProvider(String NameProvider) {
        setNameProvider(NameProvider);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " IDprovider='" + getIDprovider() + "'" +
                ", NameProvider='" + getNameProvider() + "'" +
                "}";
    }

}
