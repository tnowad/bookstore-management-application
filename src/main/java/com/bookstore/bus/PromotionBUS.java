package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.PromotionDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.PromotionModel;

public class PromotionBUS implements IBUS<PromotionModel> {

  private final List<PromotionModel> promotionList = new ArrayList<>();
  private static PromotionBUS instance;

  public static PromotionBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new PromotionBUS();
    }
    return instance;
  }

  private PromotionBUS() throws SQLException, ClassNotFoundException {
    this.promotionList.addAll(PromotionDAO.getInstance().readDatabase());
  }

  @Override
  public List<PromotionModel> getAllModels() {
    return Collections.unmodifiableList(promotionList);
  }

  @Override
  public PromotionModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (PromotionModel promotionModel : promotionList) {
      if (promotionModel.getId() == id) {
        return promotionModel;
      }
    }
    return null;
  }

  public List<PromotionModel> getPromotionList() throws NullPointerException {
    return Collections.unmodifiableList(promotionList);
  }

  private PromotionModel mapToEntity(PromotionModel from) {
    PromotionModel to = new PromotionModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PromotionModel from, PromotionModel to) {
    to.setDescription(from.getDescription());
    to.setQuantity(from.getQuantity());
    to.setStartDate(from.getStartDate());
    to.setEndDate(from.getEndDate());
    to.setConditionApply(from.getConditionApply());
    to.setDiscountPercent(from.getDiscountPercent());
    to.setDiscountAmount(from.getDiscountAmount());
  }

  private boolean checkFilter(PromotionModel promotionModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id":
          if (promotionModel.getId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "description":
          if (promotionModel.getDescription().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "quantity":
          if (promotionModel.getQuantity() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "start_date":
          if (promotionModel.getStartDate().toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "end_date":
          if (promotionModel.getEndDate().toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "condition_apply":
          if (promotionModel.getConditionApply().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "discount_percent":
          if (promotionModel.getDiscountPercent() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "discount_amount":
          if (promotionModel.getDiscountAmount() == Integer.parseInt(value)) {
            return true;
          }
          break;
        default:
          if (checkAllColumns(promotionModel, value)) {
            return true;
          }
          break;
      }
    }
    return false;
  }

  private boolean checkAllColumns(PromotionModel promotionModel, String value) {
    return promotionModel.getId() == Integer.parseInt(value)
        || promotionModel.getDescription().toLowerCase().contains(value.toLowerCase())
        || promotionModel.getQuantity() == Integer.parseInt(value)
        || promotionModel.getStartDate().toString().toLowerCase().contains(value.toLowerCase())
        || promotionModel.getEndDate().toString().toLowerCase().contains(value.toLowerCase())
        || promotionModel.getConditionApply().toLowerCase().contains(value.toLowerCase())
        || promotionModel.getDiscountPercent() == Integer.parseInt(value)
        || promotionModel.getDiscountAmount() == Integer.parseInt(value);
  }

  @Override
  public int addModel(PromotionModel promotionModel) throws SQLException, ClassNotFoundException {
    if (promotionModel.getDescription() == null || promotionModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }
    if (promotionModel.getQuantity() <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than 0!");
    }
    if (promotionModel.getStartDate() == null) {
      throw new IllegalArgumentException("Start date cannot be null!");
    }
    if (promotionModel.getEndDate() == null) {
      throw new IllegalArgumentException("End date cannot be null!");
    }
    if (promotionModel.getDiscountPercent() < 0 || promotionModel.getDiscountPercent() > 100) {
      throw new IllegalArgumentException("Discount percent must be between 0 and 100!");
    }
    if (promotionModel.getDiscountAmount() < 0) {
      throw new IllegalArgumentException("Discount amount cannot be negative!");
    }

    int id = PromotionDAO.getInstance().insert(mapToEntity(promotionModel));
    promotionModel.setId(id);
    promotionList.add(promotionModel);
    return id;
  }

  @Override
  public int updateModel(PromotionModel promotionModel) throws SQLException, ClassNotFoundException {
    int updatedRows = PromotionDAO.getInstance().update(promotionModel);
    if (updatedRows > 0) {
      for (int i = 0; i < promotionList.size(); i++) {
        if (promotionList.get(i).getId() == promotionModel.getId()) {
          promotionList.set(i, promotionModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    PromotionModel promotionModel = getModelById(id);
    if (promotionModel == null) {
      throw new IllegalArgumentException("Promotion with ID " + id + " does not exist.");
    }
    int deletedRows = PromotionDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      promotionList.remove(promotionModel);
    }
    return deletedRows;
  }

  @Override
  public List<PromotionModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
    List<PromotionModel> results = new ArrayList<>();
    try {
      List<PromotionModel> entities = PromotionDAO.getInstance().search(value, columns);
      for (PromotionModel entity : entities) {
        PromotionModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for promotion: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for promotion: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No promotion found with the specified search criteria.");
    }

    return results;
  }
}
