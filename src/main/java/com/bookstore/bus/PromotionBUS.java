package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.PromotionDAO;
import com.bookstore.model.PromotionModel;

public class PromotionBUS extends BUSInterface<PromotionModel> {

  private final List<PromotionModel> promotionList = new ArrayList<>();
  private final PromotionDAO promotionDAO = PromotionDAO.getInstance();

  public PromotionBUS() throws SQLException, ClassNotFoundException {
    this.promotionList.addAll(promotionDAO.readDatabase());
  }

  @Override
  protected ArrayList<PromotionModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return promotionDAO.readDatabase();
  }

  @Override
  public int getId(PromotionModel promotionModel) {
    return promotionModel.getId();
  }

  public PromotionModel getPromotionModel(int id) {
    return getModel(id);
  }

  public List<PromotionModel> getPromotionList() {
    return Collections.unmodifiableList(promotionList);
  }

  @Override
  protected PromotionModel mapToEntity(PromotionModel from) {
    return new PromotionModel(from.getId(), from.getDescription(), from.getQuantity(),
        from.getStartDate(), from.getEndDate(), from.getConditionApply(), from.getDiscountPercent(),
        from.getDiscountAmount());
  }

  @Override
  protected void updateEntityFields(PromotionModel from, PromotionModel to) {
    to.setDescription(from.getDescription());
    to.setQuantity(from.getQuantity());
    to.setStartDate(from.getStartDate());
    to.setEndDate(from.getEndDate());
    to.setConditionApply(from.getConditionApply());
    to.setDiscountPercent(from.getDiscountPercent());
    to.setDiscountAmount(from.getDiscountAmount());
  }

  @Override
  protected boolean checkFilter(PromotionModel promotionModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id" -> {
        try {
          return Integer.parseInt(value) == promotionModel.getId();
        } catch (NumberFormatException e) {
          return false;
        }
      }
      case "description" -> {
        return promotionModel.getDescription().toLowerCase().contains(value.toLowerCase());
      }
      case "quantity" -> {
        try {
          return Integer.parseInt(value) > 0 && promotionModel.getQuantity() == Integer.parseInt(value);
        } catch (NumberFormatException e) {
          return false;
        }
      }
      case "start_date" -> {
        return promotionModel.getStartDate().equals(value);
      }
      case "end_date" -> {
        return promotionModel.getEndDate().equals(value);
      }
      case "condition_apply" -> {
        return promotionModel.getConditionApply() != null
            && promotionModel.getConditionApply().toLowerCase().contains(value.toLowerCase());
      }
      case "discount_percent" -> {
        try {
          return Integer.parseInt(value) >= 0 && promotionModel.getDiscountPercent() == Integer.parseInt(value);
        } catch (NumberFormatException e) {
          return false;
        }
      }
      case "discount_amount" -> {
        try {
          return Integer.parseInt(value) >= 0 && promotionModel.getDiscountAmount() == Integer.parseInt(value);
        } catch (NumberFormatException e) {
          return false;
        }
      }
      default -> {
        return checkAllColumns(promotionModel, value);
      }
    }
  }

  private boolean checkAllColumns(PromotionModel promotionModel, String value) {
    return Integer.toString(promotionModel.getId()).equals(value)
        || promotionModel.getDescription().toLowerCase().contains(value.toLowerCase())
        || (Integer.toString(promotionModel.getQuantity())).equals(value)
        || promotionModel.getStartDate().equals(value)
        || promotionModel.getEndDate().equals(value)
        || promotionModel.getConditionApply() != null
            && promotionModel.getConditionApply().toLowerCase().contains(value.toLowerCase())
        || (promotionModel.getDiscountPercent() >= 0 && promotionModel.getDiscountPercent() == Integer.parseInt(value))
        || (promotionModel.getDiscountAmount() >= 0 && promotionModel.getDiscountAmount() == Integer.parseInt(value));
  }

  @Override
  public int insertModel(PromotionModel promotionModel) throws SQLException, ClassNotFoundException {
    if (promotionModel.getDescription() == null || promotionModel.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty!");
    }
    if (promotionModel.getQuantity() <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero!");
    }
    if (promotionModel.getStartDate() == null) {
      throw new IllegalArgumentException("Start date cannot be null!");
    }
    if (promotionModel.getEndDate() == null) {
      throw new IllegalArgumentException("End date cannot be null!");
    }
    if (promotionModel.getDiscountPercent() == 0 && promotionModel.getDiscountAmount() == 0) {
      throw new IllegalArgumentException("At least one discount amount must be specified!");
    }
    return add(promotionModel);
  }

  @Override
  public int updateModel(PromotionModel promotionModel) throws SQLException, ClassNotFoundException {
    return update(promotionModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<PromotionModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

}
