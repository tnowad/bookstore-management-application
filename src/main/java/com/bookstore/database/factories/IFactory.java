package com.bookstore.database.factories;

public interface IFactory<T> {
  public abstract T create();
}
