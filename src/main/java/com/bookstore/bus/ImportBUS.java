package com.bookstore.bus;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.ImportDAO;
import com.bookstore.model.ImportModel;

public class ImportBUS extends BUSInterface<ImportModel> {

  private final List<ImportModel> importList = new ArrayList<>();
  private final ImportDAO importDAO = ImportDAO.getInstance();

  public ImportBUS() throws SQLException, ClassNotFoundException {
    this.importList.addAll(importDAO.readDatabase());
  }

  @Override
  protected ArrayList<ImportModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return importDAO.readDatabase();
  }

  @Override
  public int getId(ImportModel importModel) {
    return importModel.getId();
  }

  public ImportModel getImportModel(int id) {
    return getModel(id);
  }

  public List<ImportModel> getImportList() {
    return Collections.unmodifiableList(importList);
  }

  @Override
  protected ImportModel mapToEntity(ImportModel from) {
    ImportModel to = new ImportModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(ImportModel from, ImportModel to) {
    to.setProviderId(from.getProviderId());
    to.setEmployeeId(from.getEmployeeId());
    to.setTotalPrice(from.getTotalPrice());
    to.setCreatedAt(from.getCreatedAt());
    to.setUpdatedAt(from.getUpdatedAt());
  }

  @Override
  protected boolean checkFilter(ImportModel importModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id" -> {
        if (Integer.parseInt(value) <= 0) {
          throw new IllegalArgumentException("Id must be greater than 0!");
        }
        return importModel.getId() == Integer.parseInt(value);
      }
      case "provider_id" -> {
        return importModel.getProviderId() == Integer.parseInt(value);
      }
      case "employee_id" -> {
        return importModel.getEmployeeId() == Integer.parseInt(value);
      }
      case "total_price" -> {
        BigDecimal price = new BigDecimal(value);
        return importModel.getTotalPrice().compareTo(price) == 0;
      }
      case "created_at" -> {
        long createdAtTimestamp = importModel.getCreatedAt().getTime() / 1000;
        return createdAtTimestamp == Long.parseLong(value);
      }
      case "updated_at" -> {
        long updatedAtTimestamp = importModel.getUpdatedAt().getTime() / 1000;
        return updatedAtTimestamp == Long.parseLong(value);
      }
      default -> {
        return checkAllColumns(importModel, value);
      }
    }
  }

  private boolean checkAllColumns(ImportModel importModel, String value) {
    return importModel.getId() == Integer.parseInt(value)
        || importModel.getProviderId() == Integer.parseInt(value)
        || importModel.getEmployeeId() == Integer.parseInt(value)
        || importModel.getTotalPrice().compareTo(new BigDecimal(value)) == 0;
  }

  @Override
  public int insertModel(ImportModel importModel) throws SQLException, ClassNotFoundException {
    if (importModel.getEmployeeId() <= 0) {
      throw new IllegalArgumentException("Employee id must be greater than 0!");
    }
    if (importModel.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Total price must be greater than 0!");
    }
    Date now = new Date();
    importModel.setCreatedAt(new Timestamp(now.getTime()));
    importModel.setUpdatedAt(new Timestamp(now.getTime()));
    return add(importModel);
  }

  @Override
  public int updateModel(ImportModel importModel) throws SQLException, ClassNotFoundException {
    if (importModel.getEmployeeId() < 0) {
      throw new IllegalArgumentException("Employee id must be greater than 0!");
    }
    if (importModel.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Total price must be greater than 0!");
    }
    importModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    return update(importModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<ImportModel> searchModel(String value, String columns) {
    return search(value, columns);
  }
}