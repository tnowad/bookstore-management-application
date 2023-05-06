package com.bookstore.bus;

import com.bookstore.dao.CartItemsDAO;
import com.bookstore.enums.BookStatus;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
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

  public CartItemsModel getModelByIdAndIsbn(int id, String isbn) {
    for (CartItemsModel cartItemsModel : cartItemsList) {
      if (
        cartItemsModel.getCartId() == id &&
        cartItemsModel.getBookIsbn().equals(isbn)
      ) {
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
        if (
          cartItemsList.get(i).getCartId() == model.getCartId() &&
          cartItemsList.get(i).getBookIsbn().equals(model.getBookIsbn())
        ) {
          cartItemsList.set(i, model);
          break;
        }
      }
    }
    return updatedRows;
  }

  public void update(CartItemsModel cartItemsModel) {
    CartItemsDAO.getInstance().update(cartItemsModel);
    for (int i = 0; i < cartItemsList.size(); i++) {
      if (
        cartItemsList.get(i).getCartId() == cartItemsModel.getCartId() &&
        cartItemsList.get(i).getBookIsbn().equals(cartItemsModel.getBookIsbn())
      ) {
        cartItemsList.set(i, cartItemsModel);
        break;
      }
    }
  }

  @Override
  public int deleteModel(int cartId) {
    CartItemsModel CartItemsModel = getModelById(cartId);
    if (CartItemsModel == null) {
      throw new IllegalArgumentException(
        "cart_items with cart_id " + cartId + " does not exist."
      );
    }
    int deletedRows = CartItemsDAO.getInstance().delete(cartId);
    if (deletedRows > 0) {
      cartItemsList.remove(CartItemsModel);
    }
    return deletedRows;
  }

  public int deleteModel(int id, String isbn) {
    CartItemsModel CartItemsModel = getModelByIdAndIsbn(id, isbn);
    if (CartItemsModel == null) {
      throw new IllegalArgumentException(
        "cart_items with cart_id " + id + " does not exist."
      );
    }
    int deletedRows = CartItemsDAO.getInstance().delete(id, isbn);
    if (deletedRows > 0) {
      cartItemsList.remove(CartItemsModel);
    }
    return deletedRows;
  }

  public void deleteModel(CartItemsModel cartItemsModel) {
    CartItemsDAO
      .getInstance()
      .delete(cartItemsModel.getCartId(), cartItemsModel.getBookIsbn());
    cartItemsList.remove(cartItemsModel);
  }

  public void deleteAll(int cartId) {
    CartItemsDAO.getInstance().delete(cartId);
    cartItemsList.removeIf(cartItemsModel ->
      cartItemsModel.getCartId() == cartId
    );
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
    cartItemsList.clear();
    cartItemsList.addAll(CartItemsDAO.getInstance().readDatabase());
  }

  @Override
  public CartItemsModel getModelById(int id) {
    throw new UnsupportedOperationException(
      "Unimplemented method 'getModelById'"
    );
  }

  public void addBookToCart(
    CartModel cartModel,
    BookModel bookModel,
    int quantity
  ) {
    cartModel = CartBUS.getInstance().getModelById(cartModel.getId());
    bookModel = BookBUS.getInstance().getBookByIsbn(bookModel.getIsbn());

    if (cartModel == null) {
      throw new IllegalArgumentException("Cart is null");
    }
    if (bookModel == null) {
      throw new IllegalArgumentException("Book is null");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be positive");
    }

    if (bookModel.getQuantity() < quantity) {
      throw new IllegalArgumentException("Not enough books in stock");
    }
    if (bookModel.getStatus() != BookStatus.AVAILABLE) {
      throw new IllegalArgumentException("Book is not available");
    }

    for (CartItemsModel cartItemsModel : cartItemsList) {
      if (
        cartItemsModel.getCartId() == cartModel.getId() &&
        cartItemsModel.getBookIsbn().equals(bookModel.getIsbn())
      ) {
        cartItemsModel.setQuantity(cartItemsModel.getQuantity() + quantity);
        CartItemsDAO.getInstance().update(cartItemsModel);
        return;
      }
    }

    CartItemsModel cartItemsModel = new CartItemsModel();
    cartItemsModel.setCartId(cartModel.getId());
    cartItemsModel.setBookIsbn(bookModel.getIsbn());
    cartItemsModel.setPrice(bookModel.getPrice());
    cartItemsModel.setQuantity(quantity);
    CartItemsDAO.getInstance().insert(cartItemsModel);
    refreshData();
  }

  public void addBookToCart(CartModel cartModel, BookModel bookModel) {
    System.out.println(cartModel.getId());
    addBookToCart(cartModel, bookModel, 1);
  }

  public List<CartItemsModel> getListCartItemsByCartId(int cartId) {
    List<CartItemsModel> cartItemsList = new ArrayList<CartItemsModel>();

    for (CartItemsModel cartItemsModel : CartItemsBUS
      .getInstance()
      .getAllModels()) {
      if (cartItemsModel.getCartId() == cartId) {
        cartItemsList.add(cartItemsModel);
      }
    }
    return cartItemsList;
  }
}
