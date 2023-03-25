package com.bookstore.bus;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ImportDAO;
import com.bookstore.model.ImportModel;

public class ImportBUS implements BUSInterface<ImportModel> {

  private final List<ImportModel> importList = new ArrayList<>();

  public ImportBUS() throws SQLException, ClassNotFoundException {
    this.importList.addAll(ImportDAO.getInstance().readDatabase());
  }

  @Override
  public List<ImportModel> getAllModels() {
    return Collections.unmodifiableList(importList);
  }

  @Override
  public ImportModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (ImportModel importModel : importList) {
      if (importModel.getId() == id) {
        return importModel;
      }
    }
    return null;
  }

  public List<ImportModel> getImportList() throws NullPointerException {
    return Collections.unmodifiableList(importList);
  }

  private ImportModel mapToEntity(ImportModel from) {
    ImportModel to = new ImportModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(ImportModel from, ImportModel to) {
    to.setProviderId(from.getProviderId());
    to.setEmployeeId(from.getEmployeeId());
    to.setTotalPrice(from.getTotalPrice());
    to.setCreatedAt(from.getCreatedAt());
    to.setUpdatedAt(from.getUpdatedAt());
  }

  private boolean checkFilter(ImportModel importModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> importModel.getId() == Integer.parseInt(value);
      case "provider_id" -> importModel.getProviderId() == Integer.parseInt(value);
      case "employee_id" -> importModel.getEmployeeId() == Integer.parseInt(value);
      case "total_price" -> importModel.getTotalPrice().equals(new BigDecimal(value));
      case "created_at" -> importModel.getCreatedAt().toString().contains(value);
      case "updated_at" -> importModel.getUpdatedAt().toString().contains(value);
      default -> checkAllColumns(importModel, value);
    };
  }

  private boolean checkAllColumns(ImportModel importModel, String value) {
    return importModel.getId() == Integer.parseInt(value)
        || importModel.getProviderId() == Integer.parseInt(value)
        || importModel.getEmployeeId() == Integer.parseInt(value)
        || importModel.getTotalPrice().equals(new BigDecimal(value))
        || importModel.getCreatedAt().toString().contains(value)
        || importModel.getUpdatedAt().toString().contains(value);
  }

  @Override
  public int addModel(ImportModel importModel) throws SQLException, ClassNotFoundException {
    if (importModel.getProviderId() <= 0) {
      throw new IllegalArgumentException("Provider ID cannot be less than or equal to zero!");
    }
    if (importModel.getEmployeeId() <= 0) {
      throw new IllegalArgumentException("Employee ID cannot be less than or equal to zero!");
    }
    if (importModel.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Total price cannot be less than or equal to zero!");
    }

    int id = ImportDAO.getInstance().insert(mapToEntity(importModel));
    importModel.setId(id);
    importList.add(importModel);
    return id;
  }

  @Override
  public int updateModel(ImportModel importModel) throws SQLException, ClassNotFoundException {
    ImportModel existingImport = getModelById(importModel.getId());
    if (existingImport == null) {
      return 0;
    }

    updateEntityFields(importModel, existingImport);
    try {
      ImportDAO.getInstance().update(mapToEntity(existingImport));
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException("Failed to update import: " + e.getMessage());
    }
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
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
  public List<ImportModel> searchModel(String value, String columns) throws SQLException, ClassNotFoundException {
    List<ImportModel> results = new ArrayList<>();
    try {
      List<ImportModel> entities = ImportDAO.getInstance().search(value, columns);
      for (ImportModel entity : entities) {
        ImportModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for import: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for import: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No import found with the specified search criteria.");
    }

    return results;
  }
}
