package com.bookstore.bus;

import com.bookstore.dao.CartItemsDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.CartItemsModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartItemsBUS implements IBUS<CartItemsModel> {

  private final List<CartItemsModel> cartItemsList = new ArrayList<>();
  private static CartItemsBUS instance;

  public static CartItemsBUS getInstance() {
    if (instance == null) {
      instance = new CartItemsBUS();
    }
    return instance;
  }

  private CartItemsBUS() {
    this.cartItemsList.addAll(CartItemsDAO.getInstance().readDatabase());
  }

  @Override
  public List<CartItemsModel> getAllModels() {
    return Collections.unmodifiableList(cartItemsList);
  }

  @Override
  public CartItemsModel getModelById(int id) {
    for (CartItemsModel cartItemsModel : cartItemsList) {
      if (cartItemsModel.getCartId() == id) {
        return cartItemsModel;
      }
    }
    return null;
  }

  private CartItemsModel mapToEntity(CartItemsModel from) {
    CartItemsModel to = new CartItemsModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(CartItemsModel from, CartItemsModel to) {
    to.setCartId(from.getCartId());
    to.setBookIsbn(from.getBookIsbn());
    to.setPrice(from.getPrice());
    to.setQuantity(from.getQuantity());
    to.setDiscount(from.getDiscount());
  }

  private boolean checkFilter(
    CartItemsModel CartItemsModel,
    String value,
    String[] columns
  ) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "cart_id" -> {
          if (CartItemsModel.getCartId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "book_isbn" -> {
          if (CartItemsModel.getBookIsbn().toString().equals(value)) {
            return true;
          }
        }
        case "price" -> {
          if (CartItemsModel.getPrice() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "quantity" -> {
          if (CartItemsModel.getQuantity() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "discount" -> {
          if (CartItemsModel.getDiscount() == Integer.parseInt(value)) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(CartItemsModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(CartItemsModel CartItemsModel, String value) {
    return (
      CartItemsModel.getCartId() == Integer.parseInt(value) ||
      CartItemsModel.getBookIsbn().equals(value) ||
      CartItemsModel.getPrice() == Integer.parseInt(value) ||
      CartItemsModel.getQuantity() == Integer.parseInt(value) ||
      CartItemsModel.getDiscount() == Integer.parseInt(value)
    );
  }

  @Override
  public int addModel(CartItemsModel model) {
    int id = CartItemsDAO.getInstance().insert(mapToEntity(model));
    model.setCartId(id);
    cartItemsList.add(model);
    return id;
  }

  @Override
  public int updateModel(CartItemsModel model) {
    int updatedRows = CartItemsDAO.getInstance().update(model);
    if (updatedRows > 0) {
      for (int i = 0; i < cartItemsList.size(); i++) {
        if (cartItemsList.get(i).getCartId() == model.getCartId()) {
          cartItemsList.set(i, model);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    CartItemsModel CartItemsModel = getModelById(id);
    if (CartItemsModel == null) {
      throw new IllegalArgumentException(
        "cart_items with cart_id " + id + " does not exist."
      );
    }
    int deletedRows = CartItemsDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      cartItemsList.remove(CartItemsModel);
    }
    return deletedRows;
  }

  @Override
  public List<CartItemsModel> searchModel(String value, String[] columns) {
    List<CartItemsModel> results = new ArrayList<>();
    List<CartItemsModel> entities = CartItemsDAO
      .getInstance()
      .search(value, columns);
    for (CartItemsModel entity : entities) {
      CartItemsModel model = mapToEntity(entity);
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
