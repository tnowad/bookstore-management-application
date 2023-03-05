package com.bookstore.model;

public class ProviderModel {
  private String providerId, nameProvider, gnrId;

  public ProviderModel(String providerId, String nameProvider, String gnrId) {
    this.providerId = providerId;
    this.nameProvider = nameProvider;
    this.gnrId = gnrId;
  }

  public ProviderModel() {
  }

  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public String getNameProvider() {
    return nameProvider;
  }

  public void setNameProvider(String nameProvider) {
    this.nameProvider = nameProvider;
  }

  public String getGnrId() {
    return gnrId;
  }

  public void setGnrId(String gnrId) {
    this.gnrId = gnrId;
  }

}
