package com.bookstore.model;

public class ProviderModel {
  private String providerId, nameProvider;

  public ProviderModel() {
  }

  public ProviderModel(String providerId, String nameProvider) {
    this.providerId = providerId;
    this.nameProvider = nameProvider;
  }

  public String getProviderId() {
    return this.providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public String nameProvider() {
    return this.nameProvider;
  }

  public void setNameProvider(String nameProvider) {
    this.nameProvider = nameProvider;
  }

  public ProviderModel getProviderId(String providerId) {
    setProviderId(providerId);
    return this;
  }

  public ProviderModel getNameProvider(String nameProvider) {
    setNameProvider(nameProvider);
    return this;
  }

  @Override
  public String toString() {
    return "{" +
        " providerId='" + getProviderId() + "'" +
        ", nameProvider='" + nameProvider() + "'" +
        "}";
  }

}
