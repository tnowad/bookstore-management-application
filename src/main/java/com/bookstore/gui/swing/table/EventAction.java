package com.bookstore.gui.swing.table;

public interface EventAction<T> {

  public void delete(T student);

  public void update(T student);
}
