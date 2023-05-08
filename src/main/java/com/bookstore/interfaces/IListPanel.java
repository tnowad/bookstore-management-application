package com.bookstore.interfaces;

import java.util.List;

public interface IListPanel <T> {
  public void activateFilterPanel();
  public void updateList(List<T> filteredList);
}
