package com.bookstore.bus;

import com.bookstore.dao.CartDAO;
import com.bookstore.enums.CartStatus;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.CartModel;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartBUS implements IBUS<CartModel> {

  private final List<CartModel> cartList = new ArrayList<>();
  private static CartBUS instance;

  public static CartBUS getInstance() {
    if (instance == null) {
      instance = new CartBUS();
    }
    return instance;
  }

  private CartBUS() {
    this.cartList.addAll(CartDAO.getInstance().readDatabase());
  }

  @Override
  public List<CartModel> getAllModels() {
    return Collections.unmodifiableList(cartList);
  }

  @Override
  public CartModel getModelById(int id) {
    for (CartModel cartModel : cartList) {
      if (cartModel.getId() == id) {
        return cartModel;
      }
    }
    return null;
  }

  private CartModel mapToEntity(CartModel from) {
    CartModel to = new CartModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(CartModel from, CartModel to) {
    to.setId(from.getId());
    to.setUserId(from.getUserId());
    to.setCreatedAt(from.getCreatedAt());
    to.setStatus(from.getStatus());
    to.setExpires(from.getExpires());
    to.setPromotionId(from.getPromotionId());
  }

  private boolean checkFilter(
    CartModel cartModel,
    String value,
    String[] columns
  ) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (cartModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "user_id" -> {
          if (cartModel.getUserId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "created_at" -> {
          if (
            cartModel
              .getCreatedAt()
              .toString()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        case "status" -> {
          if (cartModel.getStatus().toString().equalsIgnoreCase(value)) {
            return true;
          }
        }
        case "expires" -> {
          if (
            cartModel
              .getExpires()
              .toString()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        case "promotion_id" -> {
          if (cartModel.getPromotionId() == Integer.parseInt(value)) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(cartModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(CartModel cartModel, String value) {
    return (
      cartModel.getId() == Integer.parseInt(value) ||
      cartModel.getUserId() == Integer.parseInt(value) ||
      cartModel
        .getCreatedAt()
        .toString()
        .toLowerCase()
        .contains(value.toLowerCase()) ||
      cartModel.getStatus().toString().equalsIgnoreCase(value) ||
      cartModel
        .getExpires()
        .toString()
        .toLowerCase()
        .contains(value.toLowerCase()) ||
      cartModel.getPromotionId() == Integer.parseInt(value)
    );
  }

  @Override
  public int addModel(CartModel cartModel) {
    if (cartModel.getUserId() <= 0) {
      throw new IllegalArgumentException("User ID must be greater than 0!");
    }
    if (
      cartModel.getStatus() == null ||
      cartModel.getStatus().toString().isEmpty()
    ) {
      cartModel.setStatus(CartStatus.SHOPPING);
    }
    if (cartModel.getExpires() == null) {
      cartModel.setExpires(
        LocalDateTime.ofInstant(
          Instant.ofEpochMilli(System.currentTimeMillis()),
          ZoneId.systemDefault()
        )
      );
    }
    if (cartModel.getPromotionId() < 0) {
      throw new IllegalArgumentException("Promotion ID cannot be negative!");
    }

    int id = CartDAO.getInstance().insert(mapToEntity(cartModel));
    cartModel.setId(id);
    cartList.add(cartModel);
    return id;
  }

  @Override
  public int updateModel(CartModel cartModel) {
    int updatedRows = CartDAO.getInstance().update(cartModel);
    if (updatedRows > 0) {
      for (int i = 0; i < cartList.size(); i++) {
        if (cartList.get(i).getId() == cartModel.getId()) {
          cartList.set(i, cartModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  public int updateStatus(int userId, String status) {
    int success = CartDAO.getInstance().updateStatus(userId, status);
    if (success == 1) {
      for (CartModel cart : cartList) {
        if (cart.getUserId() == userId) {
          CartStatus roleEnum = CartStatus.valueOf(status.toUpperCase());
          cart.setStatus(roleEnum);
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) {
    CartModel cartModel = getModelById(id);
    if (cartModel == null) {
      throw new IllegalArgumentException(
        "Cart with ID " + id + " does not exist."
      );
    }
    int deletedRows = CartDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      cartList.remove(cartModel);
    }
    return deletedRows;
  }

  @Override
  public List<CartModel> searchModel(String value, String[] columns) {
    List<CartModel> results = new ArrayList<>();
    List<CartModel> entities = CartDAO.getInstance().search(value, columns);
    for (CartModel entity : entities) {
      CartModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  @Override
  public void refreshData() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'refreshData'");
  }
}
