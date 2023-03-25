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

  public PromotionBUS() throws SQLException, ClassNotFoundException {
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

  private boolean checkFilter(PromotionModel promotionModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> promotionModel.getId() == Integer.parseInt(value);
      case "description" -> promotionModel.getDescription().toLowerCase().contains(value.toLowerCase());
      case "quantity" -> promotionModel.getQuantity() == Integer.parseInt(value);
      case "start_date" -> promotionModel.getStartDate().toString().toLowerCase().contains(value.toLowerCase());
      case "end_date" -> promotionModel.getEndDate().toString().toLowerCase().contains(value.toLowerCase());
      case "condition_apply" -> promotionModel.getConditionApply().toLowerCase().contains(value.toLowerCase());
      case "discount_percent" -> promotionModel.getDiscountPercent() == Integer.parseInt(value);
      case "discount_amount" -> promotionModel.getDiscountAmount() == Integer.parseInt(value);
      default -> checkAllColumns(promotionModel, value);
    };
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
  public List<PromotionModel> searchModel(String value, String columns) throws SQLException, ClassNotFoundException {
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
