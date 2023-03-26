package com.bookstore.bus;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ImportDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.ImportModel;

public class ImportBUS implements IBUS<ImportModel> {

  private final List<ImportModel> importList = new ArrayList<>();
  private static ImportBUS instance;

  public static ImportBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new ImportBUS();
    }
    return instance;
  }

  private ImportBUS() throws SQLException, ClassNotFoundException {
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

  private boolean checkFilter(ImportModel importModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id":
          if (importModel.getId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "provider_id":
          if (importModel.getProviderId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "employee_id":
          if (importModel.getEmployeeId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "total_price":
          if (importModel.getTotalPrice().equals(new BigDecimal(value))) {
            return true;
          }
          break;
        case "created_at":
          if (importModel.getCreatedAt().toString().contains(value)) {
            return true;
          }
          break;
        case "updated_at":
          if (importModel.getUpdatedAt().toString().contains(value)) {
            return true;
          }
          break;
        default:
          if (checkAllColumns(importModel, value)) {
            return true;
          }
          break;
      }
    }
    return false;
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

  public int updateTotal(int id, BigDecimal total) throws SQLException, ClassNotFoundException {
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
  public List<ImportModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
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
