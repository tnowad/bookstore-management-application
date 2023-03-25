package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.CategoryModel;

public class CategoryBUS implements IBUS<CategoryModel> {

  private final List<CategoryModel> categoryList = new ArrayList<>();

  public CategoryBUS() throws SQLException, ClassNotFoundException {
    this.categoryList.addAll(CategoryDAO.getInstance().readDatabase());
  }

  @Override
  public List<CategoryModel> getAllModels() {
    return Collections.unmodifiableList(categoryList);
  }

  @Override
  public CategoryModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (CategoryModel categoryModel : categoryList) {
      if (categoryModel.getId() == id) {
        return categoryModel;
      }
    }
    return null;
  }

  public List<CategoryModel> getCategoryList() throws NullPointerException {
    return Collections.unmodifiableList(categoryList);
  }

  private CategoryModel mapToEntity(CategoryModel from) {
    CategoryModel to = new CategoryModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(CategoryModel from, CategoryModel to) {
    to.setName(from.getName());
  }

  private boolean checkFilter(CategoryModel categoryModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> categoryModel.getId() == Integer.parseInt(value);
      case "name" -> categoryModel.getName().toLowerCase().contains(value.toLowerCase());
      default -> checkAllColumns(categoryModel, value);
    };
  }

  private boolean checkAllColumns(CategoryModel categoryModel, String value) {
    return categoryModel.getId() == Integer.parseInt(value)
        || categoryModel.getName().toLowerCase().contains(value.toLowerCase());
  }

  @Override
  public int addModel(CategoryModel categoryModel) throws SQLException, ClassNotFoundException {
    if (categoryModel.getName() == null || categoryModel.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }

    int id = CategoryDAO.getInstance().insert(mapToEntity(categoryModel));
    categoryModel.setId(id);
    categoryList.add(categoryModel);
    return id;
  }

  @Override
  public int updateModel(CategoryModel categoryModel) throws SQLException, ClassNotFoundException {
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
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    CategoryModel categoryModel = getModelById(id);
    if (categoryModel == null) {
      throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
    }
    int deletedRows = CategoryDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      categoryList.remove(categoryModel);
    }
    return deletedRows;
  }

  @Override
  public List<CategoryModel> searchModel(String value, String columns) throws SQLException, ClassNotFoundException {
    List<CategoryModel> results = new ArrayList<>();
    try {
      List<CategoryModel> entities = CategoryDAO.getInstance().search(value, columns);
      for (CategoryModel entity : entities) {
        CategoryModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for category: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for category: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No category found with the specified search criteria.");
    }

    return results;
  }
}
