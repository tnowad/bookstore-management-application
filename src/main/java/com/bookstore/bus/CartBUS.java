package com.bookstore.bus;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.CartDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.CartModel;
import com.bookstore.model.CartModel.Status;

public class CartBUS implements IBUS<CartModel> {

  private final List<CartModel> cartList = new ArrayList<>();
  private static CartBUS instance;

  public static CartBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new CartBUS();
    }
    return instance;
  }

  private CartBUS() throws SQLException, ClassNotFoundException {
    this.cartList.addAll(CartDAO.getInstance().readDatabase());
  }

  @Override
  public List<CartModel> getAllModels() {
    return Collections.unmodifiableList(cartList);
  }

  @Override
  public CartModel getModelById(int id) throws SQLException, ClassNotFoundException {
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

  private boolean checkFilter(CartModel cartModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id":
          if (cartModel.getId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "user_id":
          if (cartModel.getUserId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        case "created_at":
          if (cartModel.getCreatedAt().toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "status":
          if (cartModel.getStatus().toString().equalsIgnoreCase(value)) {
            return true;
          }
          break;
        case "expires":
          if (cartModel.getExpires().toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
          }
          break;
        case "promotion_id":
          if (cartModel.getPromotionId() == Integer.parseInt(value)) {
            return true;
          }
          break;
        default:
          if (checkAllColumns(cartModel, value)) {
            return true;
          }
          break;
      }
    }
    return false;
  }

  private boolean checkAllColumns(CartModel cartModel, String value) {
    return cartModel.getId() == Integer.parseInt(value)
        || cartModel.getUserId() == Integer.parseInt(value)
        || cartModel.getCreatedAt().toString().toLowerCase().contains(value.toLowerCase())
        || cartModel.getStatus().toString().equalsIgnoreCase(value)
        || cartModel.getExpires().toString().toLowerCase().contains(value.toLowerCase())
        || cartModel.getPromotionId() == Integer.parseInt(value);
  }

  @Override
  public int addModel(CartModel cartModel) throws SQLException, ClassNotFoundException {
    if (cartModel.getUserId() <= 0) {
      throw new IllegalArgumentException("User ID must be greater than 0!");
    }
    if (cartModel.getStatus() == null || cartModel.getStatus().toString().isEmpty()) {
      cartModel.setStatus(Status.shopping);
    }
    if (cartModel.getExpires() == null) {
      cartModel.setExpires(new Timestamp(System.currentTimeMillis()));
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
  public int updateModel(CartModel cartModel) throws SQLException, ClassNotFoundException {
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

  public int updateStatus(int userId, Status status) throws ClassNotFoundException, SQLException {
    int success = CartDAO.getInstance().updateStatus(userId, status);
    if (success == 1) {
      for (CartModel cart : cartList) {
        if (cart.getUserId() == userId) {
          cart.setStatus(status);
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    CartModel cartModel = getModelById(id);
    if (cartModel == null) {
      throw new IllegalArgumentException("Cart with ID " + id + " does not exist.");
    }
    int deletedRows = CartDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      cartList.remove(cartModel);
    }
    return deletedRows;
  }

  @Override
  public List<CartModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
    List<CartModel> results = new ArrayList<>();
    try {
      List<CartModel> entities = CartDAO.getInstance().search(value, columns);
      for (CartModel entity : entities) {
        CartModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for cart: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for cart: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No cart found with the specified search criteria.");
    }

    return results;
  }

}
