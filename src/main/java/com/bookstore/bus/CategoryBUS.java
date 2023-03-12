package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.model.CategoryModel;

public class CategoryBUS extends BUSAbstract<CategoryModel> {

  private final List<CategoryModel> categoryList = new ArrayList<>();
  private final CategoryDAO categoryDAO;

  protected CategoryBUS(CategoryDAO categoryDAO) throws SQLException, ClassNotFoundException {
    this.categoryDAO = categoryDAO;
    this.categoryList.addAll(categoryDAO.readDatabase());
  }

  @Override
  protected ArrayList<CategoryModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return categoryDAO.readDatabase();
  }

  @Override
  protected int getId(CategoryModel categoryModel) {
    return categoryModel.getId();
  }

  @Override
  protected CategoryModel mapToEntity(CategoryModel from) {
    CategoryModel to = new CategoryModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(CategoryModel from, CategoryModel to) {
    to.setName(from.getName());
  }

  @Override
  protected boolean checkFilter(CategoryModel categoryModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        if (Integer.parseInt(value) <= 0) {
          throw new IllegalArgumentException("Id must be greater than 0!");
        }
        return categoryModel.getId() == Integer.parseInt(value);
      case "name":
        return categoryModel.getName().toLowerCase().contains(value.toLowerCase());
      default:
        return checkAllColumns(categoryModel, value);
    }
  }

  protected boolean checkAllColumns(CategoryModel categoryModel, String value) {
    return categoryModel.getId() == Integer.parseInt(value)
        || categoryModel.getName().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  protected int insertModel(CategoryModel categoryModel) throws SQLException, ClassNotFoundException {
    if (categoryModel.getName() == null || categoryModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Category name cannot be null or empty!");
    }
    return add(categoryModel);
  }

  @Override
  protected int updateModel(CategoryModel categoryModel) throws SQLException, ClassNotFoundException {
    return update(categoryModel);
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<CategoryModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public CategoryModel getCategoryModel(int id) {
    return getModel(id);
  }

  public List<CategoryModel> getCategoryList() {
    return Collections.unmodifiableList(categoryList);
  }
}
