package com.bookstore.bus;

import com.bookstore.interfaces.IBUS;
import com.bookstore.models.BooksCategoryModel;
import java.util.List;

public class BooksCategoryBUS implements IBUS<BooksCategoryModel> {

  @Override
  public List<BooksCategoryModel> getAllModels() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'getAllModels'"
    );
  }

  @Override
  public void refreshData() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'refreshData'"
    );
  }

  @Override
  public BooksCategoryModel getModelById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'getModelById'"
    );
  }

  @Override
  public int addModel(BooksCategoryModel model) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addModel'");
  }

  @Override
  public int updateModel(BooksCategoryModel model) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'updateModel'"
    );
  }

  @Override
  public int deleteModel(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'deleteModel'"
    );
  }

  @Override
  public List<BooksCategoryModel> searchModel(String value, String[] columns) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'searchModel'"
    );
  }
}
