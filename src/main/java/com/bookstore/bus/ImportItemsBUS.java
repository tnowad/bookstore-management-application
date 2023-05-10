package com.bookstore.bus;

import com.bookstore.dao.ImportItemsDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.ImportItemsModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImportItemsBUS implements IBUS<ImportItemsModel> {

  private final List<ImportItemsModel> importItemsList = new ArrayList<>();
  private static ImportItemsBUS instance;

  public static ImportItemsBUS getInstance() {
    if (instance == null) {
      instance = new ImportItemsBUS();
    }
    return instance;
  }

  private ImportItemsBUS() {
    this.importItemsList.addAll(ImportItemsDAO.getInstance().readDatabase());
  }

  @Override
  public List<ImportItemsModel> getAllModels() {
    return Collections.unmodifiableList(importItemsList);
  }

  @Override
  public ImportItemsModel getModelById(int id) {
    for (ImportItemsModel ImportItemsModel : importItemsList) {
      if (ImportItemsModel.getImportId() == id) {
        return ImportItemsModel;
      }
    }
    return null;
  }

  private ImportItemsModel mapToEntity(ImportItemsModel from) {
    ImportItemsModel to = new ImportItemsModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(ImportItemsModel from, ImportItemsModel to) {
    to.setImportId(from.getImportId());
    to.setBookIsbn(from.getBookIsbn());
    to.setPrice(from.getPrice());
    to.setQuantity(from.getQuantity());
  }

  private boolean checkFilter(
    ImportItemsModel ImportItemsModel,
    String value,
    String[] columns
  ) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "import_id" -> {
          if (ImportItemsModel.getImportId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "book_isbn" -> {
          if (ImportItemsModel.getBookIsbn().equals(value)) {
            return true;
          }
        }
        case "price" -> {
          if (ImportItemsModel.getPrice() == Double.parseDouble(value)) {
            return true;
          }
        }
        case "quantity" -> {
          if (ImportItemsModel.getQuantity() == Integer.parseInt(value)) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(ImportItemsModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(
    ImportItemsModel ImportItemsModel,
    String value
  ) {
    return (
      ImportItemsModel.getImportId() == Integer.parseInt(value) ||
      ImportItemsModel.getBookIsbn().contains(value) ||
      ImportItemsModel.getPrice() == Double.parseDouble(value) ||
      ImportItemsModel.getQuantity() == Integer.parseInt(value)
    );
  }

  @Override
  public int addModel(ImportItemsModel importItemsModel) {
    int importId = importItemsModel.getImportId();
    String bookIsbn = importItemsModel.getBookIsbn();
    double price = importItemsModel.getPrice();
    int quantity = importItemsModel.getQuantity();

    // if (importId == 0) {
    //   throw new IllegalArgumentException("Import ID cannot be zero!");
    // }
    if (bookIsbn == null || bookIsbn.isEmpty()) {
      throw new IllegalArgumentException("Book ISBN cannot be null or empty!");
    }
    if (price <= 0) {
      throw new IllegalArgumentException("Price must be greater than zero!");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero!");
    }

    int id = ImportItemsDAO.getInstance().insert(mapToEntity(importItemsModel));
    importItemsModel.setImportId(importId);
    importItemsList.add(importItemsModel);
    return id;
  }

  @Override
  public int updateModel(ImportItemsModel ImportItemsModel) {
    int updatedRows = ImportItemsDAO.getInstance().update(ImportItemsModel);
    if (updatedRows > 0) {
      for (int i = 0; i < importItemsList.size(); i++) {
        if (
          importItemsList.get(i).getImportId() == ImportItemsModel.getImportId()
        ) {
          importItemsList.set(i, ImportItemsModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    ImportItemsModel ImportItemsModel = getModelById(id);
    if (ImportItemsModel == null) {
      throw new IllegalArgumentException(
        "import_items with ID " + id + " does not exist."
      );
    }
    int deletedRows = ImportItemsDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      importItemsList.remove(ImportItemsModel);
    }
    return deletedRows;
  }

  @Override
  public List<ImportItemsModel> searchModel(String value, String[] columns) {
    List<ImportItemsModel> results = new ArrayList<>();
    List<ImportItemsModel> entities = ImportItemsDAO
      .getInstance()
      .search(value, columns);
    for (ImportItemsModel entity : entities) {
      ImportItemsModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  @Override
  public void refreshData() {
    throw new UnsupportedOperationException(
      "Unimplemented method 'refreshData'"
    );
  }
}
