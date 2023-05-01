package com.bookstore.bus;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.CategoryModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryBUS implements IBUS<CategoryModel> {

  private final List<CategoryModel> categoryList = new ArrayList<>();
  private static CategoryBUS instance;

  public static CategoryBUS getInstance() {
    if (instance == null) {
      instance = new CategoryBUS();
    }
    return instance;
  }

  private CategoryBUS() {
    this.categoryList.addAll(CategoryDAO.getInstance().readDatabase());
  }

  @Override
  public List<CategoryModel> getAllModels() {
    return Collections.unmodifiableList(categoryList);
  }

  @Override
  public CategoryModel getModelById(int id) {
    for (CategoryModel categoryModel : categoryList) {
      if (categoryModel.getId() == id) {
        return categoryModel;
      }
    }
    return null;
  }

  private CategoryModel mapToEntity(CategoryModel from) {
    CategoryModel to = new CategoryModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(CategoryModel from, CategoryModel to) {
    to.setId(from.getId());
    to.setName(from.getName());
  }

  private boolean checkFilter(
    CategoryModel categoryModel,
    String value,
    String[] column
  ) {
    for (String col : column) {
      switch (col.toLowerCase()) {
        case "id" -> {
          if (categoryModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "name" -> {
          if (
            categoryModel.getName().toLowerCase().contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(categoryModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(CategoryModel categoryModel, String value) {
    return (
      categoryModel.getId() == Integer.parseInt(value) ||
      categoryModel.getName().toLowerCase().contains(value.toLowerCase())
    );
  }

  @Override
  public int addModel(CategoryModel categoryModel) {
    if (categoryModel.getName() == null || categoryModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }

    int id = CategoryDAO.getInstance().insert(mapToEntity(categoryModel));
    categoryModel.setId(id);
    categoryList.add(categoryModel);
    return id;
  }

  @Override
  public int updateModel(CategoryModel categoryModel) {
    int updatedRows = CategoryDAO.getInstance().update(categoryModel);
    if (updatedRows > 0) {
      for (int i = 0; i < categoryList.size(); i++) {
        if (categoryList.get(i).getId() == categoryModel.getId()) {
          categoryList.set(i, categoryModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    CategoryModel categoryModel = getModelById(id);
    if (categoryModel == null) {
      throw new IllegalArgumentException(
        "Category with ID " + id + " does not exist."
      );
    }
    int deletedRows = CategoryDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      categoryList.remove(categoryModel);
    }
    return deletedRows;
  }

  @Override
  public List<CategoryModel> searchModel(String value, String[] columns) {
    List<CategoryModel> results = new ArrayList<>();
    List<CategoryModel> entities = CategoryDAO
      .getInstance()
      .search(value, columns);
    for (CategoryModel entity : entities) {
      CategoryModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }
}
