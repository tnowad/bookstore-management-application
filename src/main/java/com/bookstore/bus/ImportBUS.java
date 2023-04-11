package com.bookstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ImportDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.ImportModel;

public class ImportBUS implements IBUS<ImportModel> {

  private final List<ImportModel> importList = new ArrayList<>();
  private static ImportBUS instance;

  public static ImportBUS getInstance() {
    if (instance == null) {
      instance = new ImportBUS();
    }
    return instance;
  }

  private ImportBUS() {
    this.importList.addAll(ImportDAO.getInstance().readDatabase());
  }

  @Override
  public List<ImportModel> getAllModels() {
    return Collections.unmodifiableList(importList);
  }

  @Override
  public ImportModel getModelById(int id) {
    for (ImportModel importModel : importList) {
      if (importModel.getId() == id) {
        return importModel;
      }
    }
    return null;
  }

  private ImportModel mapToEntity(ImportModel from) {
    ImportModel to = new ImportModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(ImportModel from, ImportModel to) {
    to.setId(from.getId());
    to.setProviderId(from.getProviderId());
    to.setEmployeeId(from.getEmployeeId());
    to.setTotalPrice(from.getTotalPrice());
    to.setCreatedAt(from.getCreatedAt());
    to.setUpdatedAt(from.getUpdatedAt());
  }

  private boolean checkFilter(ImportModel importModel, String value, String[] columns) {
    for (String column : columns) {
      if (checkColumn(importModel, value, column)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkColumn(ImportModel importModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        return checkId(importModel, value);
      case "provider_id":
        return checkProviderId(importModel, value);
      case "employee_id":
        return checkEmployeeId(importModel, value);
      case "total_price":
        return checkTotalPrice(importModel, value);
      case "created_at":
        return checkCreatedAt(importModel, value);
      case "updated_at":
        return checkUpdatedAt(importModel, value);
      default:
        return checkAllColumns(importModel, value);
    }
  }

  private boolean checkId(ImportModel importModel, String value) {
    return importModel.getId() == Integer.parseInt(value);
  }

  private boolean checkProviderId(ImportModel importModel, String value) {
    return importModel.getProviderId() == Integer.parseInt(value);
  }

  private boolean checkEmployeeId(ImportModel importModel, String value) {
    return importModel.getEmployeeId() == Integer.parseInt(value);
  }

  private boolean checkTotalPrice(ImportModel importModel, String value) {
    return importModel.getTotalPrice() == Double.parseDouble(value);
  }

  private boolean checkCreatedAt(ImportModel importModel, String value) {
    return importModel.getCreatedAt().toString().contains(value);
  }

  private boolean checkUpdatedAt(ImportModel importModel, String value) {
    return importModel.getUpdatedAt().toString().contains(value);
  }

  private boolean checkAllColumns(ImportModel importModel, String value) {
    return importModel.getId() == Integer.parseInt(value)
        || importModel.getProviderId() == Integer.parseInt(value)
        || importModel.getEmployeeId() == Integer.parseInt(value)
        || importModel.getTotalPrice() == Double.parseDouble(value)
        || importModel.getCreatedAt().toString().contains(value)
        || importModel.getUpdatedAt().toString().contains(value);
  }

  @Override
  public int addModel(ImportModel importModel) {
    if (importModel.getProviderId() <= 0) {
      throw new IllegalArgumentException("Provider ID cannot be less than or equal to zero!");
    }
    if (importModel.getEmployeeId() <= 0) {
      throw new IllegalArgumentException("Employee ID cannot be less than or equal to zero!");
    }
    if (importModel.getTotalPrice() <= 0) {
      throw new IllegalArgumentException("Total price cannot be less than or equal to zero!");
    }

    int id = ImportDAO.getInstance().insert(mapToEntity(importModel));
    importModel.setId(id);
    importList.add(importModel);
    return id;
  }

  @Override
  public int updateModel(ImportModel importModel) {
    int updatedRows = ImportDAO.getInstance().update(importModel);
    if (updatedRows > 0) {
      for (int i = 0; i < importList.size(); i++) {
        if (importList.get(i).getId() == importModel.getId()) {
          importList.set(i, importModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  public int updateTotal(int id, Double total) {
    ImportModel importModel = getModelById(id);
    if (importModel == null) {
      throw new IllegalArgumentException("Import with ID " + id + " does not exist.");
    }
    importModel.setTotalPrice(total);
    int updatedRows = ImportDAO.getInstance().update(importModel);
    if (updatedRows > 0) {
      for (int i = 0; i < importList.size(); i++) {
        if (importList.get(i).getId() == importModel.getId()) {
          importList.set(i, importModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    ImportModel importModel = getModelById(id);
    if (importModel == null) {
      throw new IllegalArgumentException("Import with ID " + id + " does not exist.");
    }
    int deletedRows = ImportDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      importList.remove(importModel);
    }
    return deletedRows;
  }

  @Override
  public List<ImportModel> searchModel(String value, String[] columns) {
    List<ImportModel> results = new ArrayList<>();
    List<ImportModel> entities = ImportDAO.getInstance().search(value, columns);
    for (ImportModel entity : entities) {
      ImportModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No import found with the specified search criteria.");
    }

    return results;
  }
}
