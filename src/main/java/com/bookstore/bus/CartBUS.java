package com.bookstore.bus;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.CartDAO;
import com.bookstore.model.CartModel;
import com.bookstore.model.UserModel;

public class CartBUS extends BUSAbstract<CartModel> {

  private final List<CartModel> cartList = new ArrayList<>();
  private final CartDAO cartDAO = CartDAO.getInstance();

  public CartBUS() throws SQLException, ClassNotFoundException {
    this.cartList.addAll(cartDAO.readDatabase());
  }

  @Override
  protected ArrayList<CartModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return cartDAO.readDatabase();
  }

  @Override
  public int getId(CartModel cartModel) {
    return cartModel.getId();
  }

  public CartModel getCartModel(int id) {
    return getModel(id);
  }

  public List<CartModel> getCartList() {
    return Collections.unmodifiableList(cartList);
  }

  @Override
  protected CartModel mapToEntity(CartModel from) {
    CartModel to = new CartModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(CartModel from, CartModel to) {
    to.setUserId(from.getUserId());
    to.setCreatedAt(from.getCreatedAt());
    to.setStatus(from.getStatus());
    to.setExpires(from.getExpires());
    to.setPromotionId(from.getPromotionId());
  }

  @Override
  protected boolean checkFilter(CartModel cartModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> cartModel.getId() == Integer.parseInt(value);
      case "user_id" -> cartModel.getUserId() == Integer.parseInt(value);
      case "created_at" -> cartModel.getCreatedAt().equals(Timestamp.valueOf(value));
      case "status" -> cartModel.getStatus().toString().equalsIgnoreCase(value);
      case "expires" -> cartModel.getExpires() != null && cartModel.getExpires().equals(Timestamp.valueOf(value));
      case "promotion_id" -> cartModel.getPromotionId() == Integer.parseInt(value);
      default -> checkAllColumns(cartModel, value);
    };
  }

  private boolean checkAllColumns(CartModel cartModel, String value) {
    return cartModel.getId() == Integer.parseInt(value)
        || cartModel.getUserId() == Integer.parseInt(value)
        || cartModel.getStatus().toString().equalsIgnoreCase(value)
        || (cartModel.getExpires() != null && cartModel.getExpires().toString().contains(value))
        || cartModel.getPromotionId() == Integer.parseInt(value);
  }

  @Override
  public int insertModel(CartModel cartModel) throws SQLException, ClassNotFoundException {
    if (cartModel.getUserId() <= 0) {
      throw new IllegalArgumentException("User ID must be greater than 0!");
    }
    if (cartModel.getStatus() == null) {
      cartModel.setStatus(cartModel.getStatus() != null ? cartModel.getStatus() : CartModel.Status.PENDING);
    }
    return add(cartModel);
  }

  @Override
  public int updateModel(CartModel cartModel) throws SQLException, ClassNotFoundException {
    return update(cartModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<CartModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

}
